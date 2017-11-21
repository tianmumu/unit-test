package com.tianmumu.unit.mapper;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tianmumu.unit.vo.Staff;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-ctx.xml"})
@Transactional
//@Rollback(false)
public class StaffMapperTest {
    @Resource
    private StaffMapper staffMapper;

    @Test
    public void testInsert() {
        Staff staff = new Staff();
        staff.setEmail("haha@163.com");
        staff.setMobile("123456");
        staff.setName("王小二");
        staff.setPassword("123456");
        int count = staffMapper.insert(staff);
        assertThat(count, equalTo(1));
    }

    @Test
    @Sql(statements = "insert into `staff` (`id`, `name`, `email`, `mobile`, `password`) values (100001, '张三', 'san.zhang@163.com', '123', '0123');")
    public void testSelectById() {
        Staff staff = staffMapper.selectById(100001);
        assertThat(staff.getEmail(), equalTo("san.zhang@163.com"));
    }

    @Test
    @Sql("classpath:testDbSetup.sql")
    public void testDeleteById() {
        int count = staffMapper.deleteById(100007);
        assertThat(count, equalTo(1));
    }

    @Test
    @Sql(value = "classpath:testDbSetup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testUpdateById() {
        Staff staff = new Staff();
        staff.setId(100001L);
        staff.setName("张三");
        staff.setMobile("2137287397");
        staff.setPassword("1234");
        staff.setEmail("san.zhang@163.com");
        int count = staffMapper.update(staff);
        assertThat(count, equalTo(1));
    }
}
