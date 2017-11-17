package com.tianmumu.unit.service.impl;

import com.tianmumu.unit.mapper.StaffMapper;
import com.tianmumu.unit.service.StaffService;
import com.tianmumu.unit.vo.Staff;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StaffServiceImpl implements StaffService {
    @Resource
    private StaffMapper staffMapper;
    @Value("email.suffix")
    private String emailSuffix;

    public Staff queryStaffById(long id) {
        return staffMapper.selectById(id);
    }

    public boolean deleteStaffById(long id) {
        int count = staffMapper.deleteById(id);
        return count == 1;
    }

    public Long addStaff(Staff staff) {
        checkEmail(staff.getEmail());

        int count = staffMapper.insert(staff);
        if (count != 1) {
            throw new RuntimeException("插入新员工信息失败");
        }
        return staff.getId();
    }

    public boolean editStaff(Staff newStaff) {
        int count = staffMapper.update(newStaff);
        return count == 1;
    }

    private void checkEmail(String email) {
        if (email == null || email.equals("")) {
            throw new RuntimeException("员工信息的邮箱不可为空");
        }

        if (!email.endsWith(emailSuffix)) {
            throw new RuntimeException("员工邮箱必须使用工作邮箱");
        }
    }
}
