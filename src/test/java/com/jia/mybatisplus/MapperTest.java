package com.jia.mybatisplus;

import com.jia.mybatisplus.entity.User;
import com.jia.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author:huangyongjia
 * @Date:2021/6/10 17:25
 * @Description:
 */
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testDel() {
        userMapper.deleteById(1L);
    }
}
