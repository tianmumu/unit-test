package com.tianmumu.unit.service;

import com.tianmumu.unit.mapper.StaffMapper;
import com.tianmumu.unit.service.impl.StaffServiceImpl;
import com.tianmumu.unit.vo.Staff;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-ctx.xml"})
public class StaffServiceTest {
    @InjectMocks
    private StaffServiceImpl staffService;//注意此处不能使用接口
    @Mock
    private StaffMapper staffMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testQueryStaffById() {
        int id = 12;
        Staff dbStaff = new Staff(12L, "王小二", "haha@163.com", "1231", "1232");
        when(staffMapper.selectById(id)).thenReturn(dbStaff);
        Staff queryResult = staffService.queryStaffById(id);
        assertThat(queryResult, equalTo(dbStaff));
    }

    @Test
    public void testDeleteStaffByIdSuc() {
        int id = 15;
        when(staffMapper.deleteById(id)).thenReturn(1);
        boolean isSuc = staffService.deleteStaffById(id);
        assertThat(isSuc, equalTo(true));
    }

    @Test
    public void testDeleteStaffByIdFail() {
        int id = 15;
        when(staffMapper.deleteById(id)).thenReturn(0);
        boolean isSuc = staffService.deleteStaffById(id);
        assertThat(isSuc, equalTo(false));
    }

    @Test
    public void testAddStaffSuc() {
        ReflectionTestUtils.setField(staffService, "emailSuffix", "@163.com");
        Staff staff = new Staff(100L, "王小二", "haha.163.com", "1231", "1232");
        when(staffMapper.insert(staff)).thenReturn(1);
        Long id = staffService.addStaff(staff);
        assertThat(id, equalTo(100L));
    }

    @Test(expected = RuntimeException.class)
    public void testAddStaffFailByWrongEmail() {
        ReflectionTestUtils.setField(staffService, "emailSuffix", "@163.com");
        Staff staff = new Staff(100L, "王小二", "ou.duan@163.com", "1231", "1232");
        staffService.addStaff(staff);
    }

    @Test(expected = RuntimeException.class)
    public void testAddStaffFailByNoEmail() {
        ReflectionTestUtils.setField(staffService, "emailSuffix", "@163.com");
        Staff staff = new Staff(100L, "王小二", null, "1231", "1232");
        staffService.addStaff(staff);
    }


    @Test
    public void testEditStaffSuc() {
        Staff staff = new Staff(100L, "王小二", "haha.163.com", "1231", "1232");
        when(staffMapper.update(staff)).thenReturn(1);
        boolean isSuc = staffService.editStaff(staff);
        assertThat(isSuc, equalTo(true));
    }

    @Test(expected = RuntimeException.class)
    public void testCheckEmail() {//借助ReflectionTestUtils测试私有方法
        ReflectionTestUtils.setField(staffService, "emailSuffix", "@163.com");
        ReflectionTestUtils.invokeMethod(staffService, "checkEmail", "123");
    }
}
