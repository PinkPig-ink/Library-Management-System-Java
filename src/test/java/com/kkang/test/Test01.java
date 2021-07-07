package com.kkang.test;

import com.kkang.mapper.UserMassage;
import com.kkang.pojo.UserAllMassage;
import com.kkang.utils.MybatisUtil;
import com.kkang.view.MyWindows;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Test01 {
    @Test
    public void test01(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMassage mapper = sqlSession.getMapper(UserMassage.class);
        List<UserAllMassage> userAllMassages = mapper.selectAllUserMassage();
        System.out.println(userAllMassages);
    }
    @Test
    public void test02(){
        UserAllMassage kk = new UserAllMassage("1233123123331", "123", "kk", "123");
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMassage mapper = sqlSession.getMapper(UserMassage.class);
        mapper.insertDataToLibrary(kk);
    }

    @Test
    public void test03(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMassage mapper = sqlSession.getMapper(UserMassage.class);
        System.out.println(mapper.selectUnReturn());
    }

    @Test
    public void test04(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String nowtime = sdf.format(now);
        System.out.println(nowtime);
    }

}
