package com.itheima;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.domain.query.UserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class Mybatisplus02DqlApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void testGetAll() {
        //方式1：按条件查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.le("age", 50);
        List<User> users = userDao.selectList(wrapper);
        System.out.println(users);


        //方式2：lambda表达式
        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        wrapper2.lambda().le(User::getAge, 50);
        List<User> users2 = userDao.selectList(wrapper2);
        System.out.println(users2);

        //方式3：lambda格式按条件查询
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(User::getAge, 50);
        lambdaQueryWrapper.le(User::getAge, 40).or().ge(User::getAge, 45);
        List<User> userList = userDao.selectList(lambdaQueryWrapper);
        System.out.println(userList);

    }


    @Test
    void testQueryNull() {
        //模拟传递过来的查询数据
        UserQuery userQuery = new UserQuery();
        userQuery.setAge(50);
        userQuery.setAge2(99);

        //空的判定
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.le(null != userQuery.getAge2(), User::getAge, userQuery.getAge2());
        lambdaQueryWrapper.ge(null != userQuery.getAge(), User::getAge, userQuery.getAge());
        List<User> userList = userDao.selectList(lambdaQueryWrapper);
        System.out.println(userList);
    }


    //查询投影
    @Test
    void testLambdaQueryField() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        //设置只看这三个字段
        lqw.select(User::getId, User::getName, User::getAge);
        List<User> userList = userDao.selectList(lqw);
        System.out.println(userList);
    }

    @Test
    void testQueryField() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        //设置只看这三个字段
        qw.select("id", "name", "age");
        List<User> userList = userDao.selectList(qw);
        System.out.println(userList);
    }


    @Test
    void testCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("count(*) as count");
        List<Map<String, Object>> maps = userDao.selectMaps(wrapper);
        System.out.println(maps);
    }


    @Test
    void testGroup() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("count(*) as count, age");
        wrapper.groupBy("age");
        List<Map<String, Object>> maps = userDao.selectMaps(wrapper);
        System.out.println(maps);
    }


    @Test
    void testEquals() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPassword, "yuningshi54");
        User user = userDao.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    void testRange() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.between(User::getAge, 30, 50);
        List<User> users = userDao.selectList(wrapper);
        System.out.println(users);
    }


    @Test
    void testLike() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getPassword, "zhi");
        List<User> users = userDao.selectList(wrapper);
        System.out.println(users);
    }


}
