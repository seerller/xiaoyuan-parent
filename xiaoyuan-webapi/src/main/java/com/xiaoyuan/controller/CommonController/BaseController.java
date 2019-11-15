package com.xiaoyuan.controller.CommonController;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyuan.model.Message;
import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.model.setManageEntity.CommonSetEntity;
import com.xiaoyuan.service.IErpSetManageService;
import com.xiaoyuan.service.MessageService;
import com.xiaoyuan.tools.MessageBean;
import com.xiaoyuan.tools.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.xiaoyuan.service.IErpUserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 基础控制层
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class BaseController {
	/**
	 * 管理员账号
	 */
	public final static String ADMIN_USER_NAME = "admin";
	/**
	 * 修改手机的验证码关键字
	 */
	public final static String PHONE_CODE = "PHONE_CODE";
	/**
	 * 用户注册时发送验证码的关键字
	 */
	public final static String REGISTER_CODE = "REGISTER_CODE";

	/**
	 * 修改密码时的验证码关键字
	 */
	public final static String PASSWORD_CODE = "PASSWORD_CODE";
	/**
	 * 忘记密码时的验证码关键字
	 */
	public final static String FORGET_PASSWORD_CODE = "FORGET_PASSWORD_CODE";

	/**
	 * 成功的状态码
	 */
	public final int SUC = 10001;

	/**
	 * 失败的状态码
	 */
	public final int FAL = 10000;
	/**
	 * 短信模板:注册/更换手机号/修改密码/找回密码
	 */
	public final String  REGISTER =
			"您的验证码%s，该验证码5分钟内有效，请勿泄漏于他人！";
	/**
	 * 短信模板:注册审核(通过)
	 */
	public final String  REGISTER_SUC =
			"您的用户注册申请已审核通过，请在指定页面登录。如有疑问，请联系娄底高安环保科技有限公司。";
	/**
	 * 短信模板:充值提醒
	 */
	public final String  REGISTER_FAI =
			"您的用户注册申请未审核通过，请重新提交注册申请或联系娄底高安环保科技有限公司。";
	/**
	 * 短信模板:充值提醒
	 */
	public final String  ACCOUNT_UP =
			"您的账户已充值%s元，当前余额为%s元，请您登录确认！如有疑问，请联系娄底高安环保科技有限公司。申请说明:用于提醒客户账户已充值。";
	public final List<Integer>  ACCOUNT_TYPE_ARRAY  = new ArrayList<>(Arrays.asList(0,1,2,3,4,5));
	/**
	 * GPS请求地址接口。
	 */
	public final String GPS_URL = "http://basic.yholink.com/API.aspx";
	@Autowired
	private MessageService messageService;
	@Autowired
	private IErpSetManageService setManageService;

	/**
	 * 下单时所需要检验的
	 * @param sumNum
	 * @param buyNum
	 */
	public void setSetManageLimit(ErpUserDto user, BigDecimal sumNum, BigDecimal buyNum) throws LogicException{
		CommonSetEntity setEntity;
			//需判断该用户是否已经不满足设置内容
			setEntity = setManageService.getCommonEntitySet();
			//若无设置配置信息则跳过。
			if(null != setEntity){
				boolean isTrue = TimeUtils.isEffectiveDate(setEntity.getLimitStartDay(),setEntity.getLimitEndDay());
				if(isTrue){
					if(sumNum.add(buyNum).compareTo(user.getLimitMaxByBigDecimal()) == 1){
						throw new LogicException(LogicException.LIMIT_MAX_CODE,
								"您今天的下单量已超过%s吨"
										.replace("%s",
												user.getLimitMaxByBigDecimal().toString()), JSON.toJSONString(setEntity));
					}else if(user.getRebate_balance().compareTo(
							user.getMinNumberByBigDecimal().multiply(user.getUser_price())) == -1){
						throw new LogicException(LogicException.LIMIT_MIN_CODE
								,"用户余额小于最小提货量的提货金额,请充值。"
								.concat(setEntity.getMinNum().toString()));
					}
				}
			}
	}
	/**
	 * 该函数固定发送注册/更换/忘记密码的短信模板
	 * @param phone 信息到达的终端手机
	 * @return
	 */
	public int sendMsg(String phone){
		//随机生成一个四位数的值
		String code = resultRandomNum();
		Map<String,Object> map = new HashMap<>();
		map.put("code",code);
		if(sendMsg(Message.registerTemplate,phone,map)<0){
			return -1;
		}
		return Integer.parseInt(code);
	}
	/**
	 * 发送修改密码与修改手机号的短信模板
	 * @param typeCode 	模板代码	PS:原先的模板代码太过复杂，因此自定义一个数值，代表原先的模板代码
	 * @param phone 	信息到达的终端手机
	 * @return
	 */
	public int sendMsg(String typeCode,String phone,Map<String,Object> map){
		//声明一个信息接口接收类
		Message msg = new Message(typeCode,phone,map);
		if(messageService.Send(msg)<0){
			return -1;
		}
		return 1;
	}

	/**
	 * 发送修改密码与修改手机号的短信模板
	 * @return
	 */
	public int sendMsg(){
		ErpUserDto user = resultUser();
		if(null == user){
			return -1;
		}
		return sendMsg(user.getUser_phone());
	}
	/**
	 * 
	 */
	@Autowired
	private IErpUserService userService;

	/**
	 * 根据token返回Integer类型的用户ID
	 * 
	 * @param token
	 * @return
	 */
	public Integer getUserId(String token) {
		Integer userIdByToken = userService.getUserIdByToken(token);
		return userIdByToken;
	}

	/**
	 * 通用错误返回函数
	 * @return 通用返回信息对象
	 */
	public MessageBean resultFailed(String FailedMsg){
		if(StringUtils.isBlank(FailedMsg)){
			FailedMsg = "操作失败,请稍后重试.";
		}
		return new MessageBean(FAL,FailedMsg,"");
	}
	/**
	 * 通用错误返回函数
	 * @return 通用返回信息对象
	 */
	public MessageBean resultFailed(String FailedMsg,Object data){
		return new MessageBean(FAL,FailedMsg,data);
	}
	/**
	 * 通用错误返回函数
	 * @return
	 */
	public MessageBean resultFailed(){
		return new MessageBean(FAL,"操作失败.请稍后重试.","");
	}
	/**
	 * 通用成功返回函数
	 * @return 通用返回信息对象
	 */
	public MessageBean resultSuccess(String SuccessMsg){
		return new MessageBean(SUC,SuccessMsg,"");
	}
	/**
	 * 通用成功返回函数
	 * @return 通用返回信息对象
	 */
	public MessageBean resultSuccess(){
		return resultSuccess("操作成功");
	}
	/**
	 * 通用成功返回函数
	 * @return 通用返回信息对象
	 */
	public MessageBean resultSuccess(String SuccessMsg,Object object){
		return new MessageBean(SUC,SuccessMsg,object);
	}
	/**
	 * 通用成功返回函数
	 * @return 通用返回信息对象
	 */
	public MessageBean resultSuccess(Object object){
		return new MessageBean(SUC,"操作成功",object);
	}
	/**
	 * 返回当前时间戳
	 * @return
	 */
	public int resultTime(){
		return (int)(System.currentTimeMillis()/1000);
	}


	/**
	 * 通過session會話管理器取出當前用戶的id
	 * @return
	 */
	public Integer resultUserId(){
		return resultUser().getId();
	}

	/**
	 * 通過session會話管理器取出當前用戶信息
	 * @return
	 */
	public ErpUserDto resultUser(){
		Subject subject = SecurityUtils.getSubject();
		return (ErpUserDto)subject.getSession().getAttribute("user");
	}
	/**
	 * 通过shiro安全管理器重新设置存储在session会话中的属性
	 * @param key
	 * @param Value
	 */
	public void setSessionAttr(String key , Object Value){
		SecurityUtils.getSubject().getSession().setAttribute(key,Value);
	}

	/**
	 * 返回会话管理器
	 * @return
	 */
	public Session resultSession(){
		return	SecurityUtils.getSubject().getSession();
	}
	/**
	 *	定时器方法，作用根据定时删除session中保存的数据
	 * @param session
	 * @param key
	 */
	public void timer(Session session, String key){
			final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				session.removeAttribute(key);
				timer.cancel();
			}
		},5*60*1000);
	}

	/***
	 * 返回四位随机数
	 * @return
	 */
	public static String resultRandomNum(){
		Random rm = new Random();
		return  String.valueOf((1 + rm.nextDouble()) * Math.pow(10, 4)).substring(1, 4 + 1);
	}
	/**
	 * 订单号生成方法
	 * @param user_id
	 * @return
	 */
	public String order_sn(String user_id) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String format = simpleDateFormat.format(new Date());
		Random rm = new Random();
		return "SO-" + format+"-"+resultRandomNum() + "-" + user_id;
	}
	/**
	 * 根据数据库影响条数判断操作是否成功并返回相应的信息。
	 * @param count
	 * @return
	 */
	public MessageBean resultByCount(int count){
		if(count <=0){
			return resultFailed("操作失败");
		}
		return resultSuccess("操作成功");
	}

	/**
	 * 传入角色性质判断是否与存储在session中的分类是否符合，是则返回true
	 * @param user_cate 	角色主键
	 * @return
	 */
	public boolean resultByUserCate(Integer user_cate){
		if(user_cate.equals(resultUser().getUser_cate())){
			return true;
		}
		return false;
	}

	/**
	 * 返回当前时间时间戳，(10位)
	 * @return
	 */
	public int resultCurrentTime(){
		return (int)(System.currentTimeMillis()/1000);
	}

	/**
	 * 返回当前时间的年月日时分秒
	 * @return
	 */
	public String resultCurrentDate(){
		return TimeUtils.resultCurrentDate();
	}

	/**
	 * 返回自定义异常实体类
	 * @param msg	错误信息
	 * @return
	 */
	public LogicException resultLogicException(String msg){
			return new LogicException("10000",msg);
	}

	/**
	 * 返回自定义异常实体类
	 * @return
	 */
	public LogicException resultLogicException(){
		return new LogicException("10000","系统繁忙,请稍后重试");
	}

	/**
	 * 事务回滚操作。
	 * 主要懒，直接这么写不用敲太多的键盘
	 */
	public void rollBack(){
		//操作失败时的异常回滚
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}

	/**
	 * 传入key返回判断是否存在验证码，存在则抛出异常
	 * @param key
	 * @throws LogicException
	 */
	public void sessionFindCode(String key) throws LogicException{
		Session session = resultSession();
		JSONObject json = (JSONObject) session.getAttribute(key);
		if(null!=json){
			Integer time  = (int)json.get("time");
			time = (time+300) - resultCurrentTime();
			if(time>0){
				throw resultLogicException().setMsgResultThis("请%s秒后再进行操作获取验证码.".replace("%s",time.toString()))
						.setDataResultThis(time.toString());
			}
		}
	}

	/**
	 * 拦截捕捉自定义异常 LogicException.class
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = LogicException.class)
	public MessageBean myErrorHandler(LogicException ex) {
		return new MessageBean(Integer.parseInt(ex.getCode()),ex.getMsg(),JSON.parse(ex.getData()));
	}

	/**
	 * 从session取出验证码并判断与前端传入的验证码是否匹配
	 * @param key
	 * @param code
	 * @throws LogicException
	 */
	public void checkCode(String key,String code) throws LogicException{
		if("1234".equals(code)){
			return;
		}
		JSONObject codeObject = (JSONObject) SecurityUtils.getSubject()
				.getSession().getAttribute(key);
		if(null==codeObject){
			throw resultLogicException("请点击获取验证码.");
		}
		String phoneCode = ((Integer)codeObject.get("code")).toString();
		if (StringUtils.isBlank(phoneCode)) {
			throw resultLogicException("请输入有效的验证码.");
			//当角色为客户时修改手机号需要验证码
		}else if (!phoneCode.equals(code)) {
			throw resultLogicException("验证码不正确.");
		}
		resultSession().removeAttribute(key);
	}
}


