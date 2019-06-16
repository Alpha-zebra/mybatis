package com.alpha;

import com.alpha.pojo.Category;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TM_PageHelper {
    public static void main(String[] args) throws IOException {
        SqlSession session=TestMybatis_deleteConnection.builder();
        PageHelper.offsetPage(1,5);
        List<Category> cs=session.selectList("listCategory_PageHelper");
        cs.forEach(category -> {
            System.out.println(category.getName());
        });
        PageInfo pageInfo=new PageInfo(cs);
        System.out.println("Total:"+pageInfo.getTotal());
        System.out.println(pageInfo);
        session.commit();
        session.close();


        String resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session1=sqlSessionFactory.openSession();

        System.out.println("Level Two cache----------------------");
        Category c1=session1.selectOne("getCategory",1);
        System.out.println(c1);
        Category c2=session1.selectOne("getCategory",1);
        System.out.println(c2);
        session1.commit();
        session1.close();

        SqlSession session2=sqlSessionFactory.openSession();
        Category c3=session2.selectOne("getCategory",1);
        System.out.println(c3);


        session2.commit();

        session2.close();

    }

}
