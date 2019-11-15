
package com.xiaoyuan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.API;
import com.xiaoyuan.model.APICate;
import com.xiaoyuan.model.UserRole;
import com.xiaoyuan.service.IAPIService;
import com.xiaoyuan.service.IUserRoleService;
import com.xiaoyuan.tools.MessageBean;


/**
 * 菜单
 *
 * @author YZH
 * @version 2019年2月26日 下午5:43:09
 */
@RestController
@RequestMapping("/Menu")
@CrossOrigin
public class APIController {
    @Autowired
    private IAPIService apiService;
    @Autowired
    private IUserRoleService userRoleService;







//    @Autowired IRoleser



    //一级菜单
    //前端传参role_id,然后得到role_content,然后split，
    // list里面的每一个id和content中的每一个字符串比较，相同则加到list2返回list2
    //二级菜单和三级菜单也做同样的操作，大概
    //某人说过SQL不适合做逻辑处理
    @RequestMapping("/first_api")
    public MessageBean first_api(Integer role_id) {
        //准备袋子和数据
        ArrayList<APICate> list = this.apiService.first_api();
        if (role_id == 0) {//权限编辑页面返回全部，不筛选
            return new MessageBean(10001, "", list);
        }
        UserRole role = this.userRoleService.role(role_id);
        if (role == null) {
            return new MessageBean(10000, "根据role_id找不到角色对象", role);
        }
        String ids = role.getRole_auth_ids();
        String[] split = ids.split(",");
        //根据角色权限内容筛选
        List<APICate> list2 = new ArrayList<APICate>();
        for (int i = 0; i < split.length; i++) {//每个i 都有 j 次比较次数的机会
            for (int j = 0; j < list.size(); j++) {
                if (split[i].equals(list.get(j).getId().toString())) {//id相同
                    list2.add(list.get(j));
                }
            }
        }
        if (list2.size() > 0) {
            return new MessageBean(10001, "", list2);
        }
        return new MessageBean(10000, "一级菜单为空", list2);
    }

    //二级
    @RequestMapping("/second_api")
    public MessageBean second_api(Integer role_id) {
        ArrayList<API> list = this.apiService.second_api();
        if (role_id == 0) {//权限编辑页面返回全部，不筛选
            return new MessageBean(10001, "", list);
        }
        UserRole role = this.userRoleService.role(role_id);
        if (role == null) {
            return new MessageBean(10000, "根据role_id找不到角色对象", role);
        }
        String ids = role.getRole_auth_ids();
        String[] split = ids.split(",");
        //根据角色权限内容筛选
        List<API> list2 = new ArrayList<API>();
        for (int i = 0; i < split.length; i++) {//每个i 都有 j 次比较次数的机会
            for (int j = 0; j < list.size(); j++) {
                if (split[i].equals(list.get(j).getId().toString())) {//id相同
                    list2.add(list.get(j));
                }
            }
        }
        if (list2.size() > 0) {
            return new MessageBean(10001, "", list2);
        }
        return new MessageBean(10000, "二级菜单为空", list2);
    }

    //三级菜单
    @RequestMapping("/third_api")
    public MessageBean third_api(Integer role_id) {
        ArrayList<API> list = this.apiService.third_api();
        if (role_id == 0) {//权限编辑页面返回全部，不筛选
            return new MessageBean(10001, "", list);
        }
        UserRole role = this.userRoleService.role(role_id);
        if (role == null) {
            return new MessageBean(10000, "根据role_id找不到角色对象", role);
        }
        String ids = role.getRole_auth_ids();
        String[] split = ids.split(",");
        //根据角色权限内容筛选
        List<API> list2 = new ArrayList<API>();
        for (int i = 0; i < split.length; i++) {//每个i 都有 j 次比较次数的机会
            for (int j = 0; j < list.size(); j++) {
                if (split[i].equals(list.get(j).getId().toString())) {//id相同
                    list2.add(list.get(j));
                }
            }
        }
        if (list2.size() > 0) {
            return new MessageBean(10001, "", list2);
        }
        return new MessageBean(10000, "三级菜单为空", list2);
    }

    // @Autowired
    // private IUserRoleService userRoleService;

    /**
     * 获取一级菜单
     *
     * @return
     */
    @RequestMapping("/lists")
    public MessageBean lists(
            @RequestParam(value = "condition", required = false) String condition,
            @RequestParam(value = "login_name", required = false) String login_name) {

        List<APICate> lists = apiService.lists(null);

        if (lists != null) {
            return new MessageBean(10001, null, lists);
        }
        return new MessageBean(10000, "未获取到菜单列表", null);
    }

    /**
     * 获取二级菜单
     * <p>
     * 单词拼错了。。
     *
     * @param c
     * @param listsorder
     * @return 2019年2月25日上午8:56:19
     * @author : YZH
     */
    @RequestMapping("/categroylists")
    public MessageBean categroylists(
            @RequestParam(value = "c", required = false) String c,
            @RequestParam(value = "listsorder", required = false) Integer listsorder) {
        /*
         * List<APICate> list = apiService.lists(); for (APICate apiCate : list)
         * {
         */
        List<API> lists = apiService.listsbycategroy(c, null, null);
        if (listsorder != null) {
            if (listsorder == 1) {
                lists = apiService.listsbycategroy(c, 1, null);
            } else if (listsorder == 0) {
                lists = apiService.listsbycategroy(c, 0, null);
            }
        } else {
            lists = apiService.listsbycategroy(c, null, null);
        }
        if (lists != null) {
            return new MessageBean(10001, null, lists);
        }
        /* } */
        return new MessageBean(10000, "未获取到二级菜单列表", null);
    }


}
