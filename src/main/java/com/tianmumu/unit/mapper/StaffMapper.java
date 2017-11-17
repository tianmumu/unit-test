package com.tianmumu.unit.mapper;

import com.tianmumu.unit.vo.Staff;

public interface StaffMapper {
    int insert(Staff staff);

    Staff selectById(long id);

    int deleteById(long id);

    int update(Staff staff);
}
