package com.tianmumu.unit.controller;

import com.tianmumu.unit.service.StaffService;
import com.tianmumu.unit.vo.Staff;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Resource
    private StaffService staffService;

    @RequestMapping("/query")
    public Staff queryStaffById(Long id) {
        if (id == null) {
            throw new RuntimeException("id不能为空");
        }

        return staffService.queryStaffById(id);
    }

    @RequestMapping("/delete")
    public void deleteStaffById(Long id) {
        if (id == null) {
            throw new RuntimeException("id不能为空");
        }

        boolean isSuc = staffService.deleteStaffById(id);
        if (!isSuc) {
            throw new RuntimeException("删除员工信息失败");
        }
    }

    @RequestMapping("/add")
    public Long addStaff(Staff staff) {
        if (staff == null) {
            throw new RuntimeException("添加新员工信息不能为空");
        }

        return staffService.addStaff(staff);
    }

    @RequestMapping("/edit")
    public void editStaff(Staff newStaff) {
        if (newStaff.getId() == null) {
            throw new RuntimeException("编辑员工信息，员工id不能为空");
        }

        boolean isSuc = staffService.editStaff(newStaff);
        if (!isSuc) {
            throw new RuntimeException("编辑员工信息失败");
        }
    }
}
