package com.tianmumu.unit.service;

import com.tianmumu.unit.vo.Staff;

public interface StaffService {
    Staff queryStaffById(long id);

    boolean deleteStaffById(long id);

    Long addStaff(Staff staff);

    boolean editStaff(Staff newStaff);
}
