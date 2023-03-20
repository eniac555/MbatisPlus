package com.itheima;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mybatisplus01QuickstartApplicationTests {

    @Autowired
    private UserDao userDao;


    @Test
    void testSave(){
        User user = new User();
        user.setName("itheima");
        user.setPassword("123");
        user.setAge(23);
        user.setTel("12345678910");
        userDao.insert(user);
    }

    @Test
    void testDelete(){
        userDao.deleteById(0L);
    }

    @Test
    void testUpdate(){
        User user = new User();
        user.setName("itheima");
        user.setPassword("123");
        user.setAge(23);
        user.setTel("12345678910");
        user.setId(10L);
        userDao.updateById(user);
    }

    @Test
    void testGetById(){
        User user = userDao.selectById(5L);
        System.out.println(user);
    }

    @Test
    void testGetAll() {
        List<User> users = userDao.selectList(null);
        System.out.println(users);
    }

    @Test
    void testGetByPage(){
        Page<User> page = new Page<>(1,5);

        userDao.selectPage(page,null);
        System.out.println("一共多少条："+page.getTotal());
        System.out.println("当前页码值："+page.getCurrent());
        System.out.println("每页显示数："+page.getSize());
        System.out.println("一共多少页："+page.getPages());
        System.out.println("数据："+page.getRecords());
    }

}
