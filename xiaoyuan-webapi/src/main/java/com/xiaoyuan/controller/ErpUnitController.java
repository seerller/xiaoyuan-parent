package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpUnit;
import com.xiaoyuan.service.IErpUnitService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 20190404 Thur. 14:41 没用到？
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpUnit")
public class ErpUnitController {
    @Autowired
    private IErpUnitService unitService;

    @RequestMapping("/detail")
    public MessageBean detail(@RequestParam Integer id) {
        if (id != null) {
            ErpUnit erpUnit = unitService.get(id);
            if (erpUnit != null) {
                return new MessageBean(10001, "", erpUnit);
            }
        }
        return new MessageBean(10000, "没有找到此信息", null);
    }

    @RequestMapping("/delete")
    public MessageBean delete(@RequestParam Integer id) {
        if (id != null) {
            int result = unitService.delete(id);
            if (result > 0) {
                return new MessageBean(10001, "单位设置删除成功", null);
            }
        }
        return new MessageBean(10000, "单位设置删除失败", null);
    }

    @RequestMapping("/stop")
    public MessageBean stop(ErpUnit unit, @RequestParam Integer id) {
        if (id != null) {
            unit.setId(id);
            unit.setStatus_(0);
            int result = unitService.update(unit);
            if (result > 0) {
                return new MessageBean(10001, "单位设置已禁止", null);
            }
        }
        return new MessageBean(10000, "单位设置禁止异常", null);
    }

    @RequestMapping("/lists")
    public MessageBean list() {
        List<ErpUnit> lists = unitService.lists();
        if (lists != null) {
            return new MessageBean(10001, "", lists);
        }
        return new MessageBean(10000, "列表数据为空", lists);
    }

    @RequestMapping("/add")
    public MessageBean add(@RequestParam(value = "user_name", required = false) String user_name, ErpUnit erpUnit, @RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
			/*ErpUser user = new ErpUser();
			user.setId(id);
			if(user_name != null && user_name !=""){
				user.setUser_name(user_name);
			}
			userService.update(user);*/
            ErpUnit unit = new ErpUnit();
            unit.setId(id);
            if (erpUnit.getUnit_dept() != null && erpUnit.getUnit_dept() != "") {
                unit.setUnit_dept(erpUnit.getUnit_dept());
            }
            if (erpUnit.getUnit_position() != null && erpUnit.getUnit_position() != "") {
                unit.setUnit_position(erpUnit.getUnit_position());
            }
            if (erpUnit.getUnit_audit() != null && erpUnit.getUnit_audit() != "") {
                unit.setUnit_audit(erpUnit.getUnit_audit());
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            unit.setUpdatetime(sdf.format(new Date()));
            int result = unitService.update(unit);
            if (result > 0) {
                return new MessageBean(10001, "单位设置修改成功", null);
            }
        }
        return new MessageBean(10000, "单位设置修改失败", null);
    }
}
