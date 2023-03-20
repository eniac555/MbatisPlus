package com.itheima;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class Mybatisplus02DqlApplicationTests {

    @Autowired
    private UserDao userDao;


    @Test
    void testSave(){
        User user = new User();
        user.setName("itheima111");
        user.setPassword("123");
        user.setAge(23);
        user.setTel("12345678910");
        //user.setId(886L);
        userDao.insert(user);
    }


    @Test
    void testDelete(){
        List<Long> list = new ArrayList<>();
        list.add(666L);
        userDao.deleteBatchIds(list);
    }

    @Test
    void testUpdate(){
        User user = new User();
        user.setName("itheimahhh");
        user.setPassword("123");
        user.setAge(53);
        user.setTel("1234567555");
        user.setId(886L);
        user.setVersion(1);//使用乐观锁需要先获得当前的version值
        userDao.updateById(user);
    }

    @Test
    void testUpdate2(){
        User user = userDao.selectById(888L);
        user.setName("robert");
        user.setPassword("999999");
        //当前这个对象已经有version值了，不需要再去查了
        userDao.updateById(user);
    }

    @Test
    void optimisticLocker(){//模拟乐观锁
        User user1 = userDao.selectById(888L);//version=2
        User user2 = userDao.selectById(888L);//version=2

        user2.setName("aaa");
        userDao.updateById(user2);//version=3

        user1.setName("bbb");//version=2条件失效，下面的语句不会执行
        userDao.updateById(user1);
    }


}
