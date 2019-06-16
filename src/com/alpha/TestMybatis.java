package com.alpha;


import com.alpha.pojo.Category;
import com.alpha.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        List<Category> cs=session.selectList("listCategory");
        cs.forEach(c->{
            System.out.println(c.getName());
        });
        System.out.println("====================");
        List<Product> lp=session.selectList("listProduct");
        lp.forEach(p->{
            System.out.println(p);
        });
        System.out.println("Add====================");

        Category c=new Category();
        c.setName("Category_add");
        session.insert("addCategory",c);
        listAll(session);
        System.out.println("Delete====================");

        c.setId(3);
        session.delete("deleteCategory",c);
        listAll(session);
        System.out.println("Get====================");
        Category c1=session.selectOne("getCategory",1);
        System.out.println(c1.getName());
        System.out.println("Update====================");
        c1.setName("Update_category");
        session.update("updateCategory",c1);
        listAll(session);
        System.out.println("listCategoryByName====================");

        List<Category> cs1=session.selectList("listCategoryByName","add");
        cs1.forEach(cc->{
            System.out.println(cc.getName());
        });
        System.out.println("listCategoryByIdAndName====================");
        Map<String,Object> params=new HashMap<>();
        params.put("id",8);
        params.put("name","add");
             List<Category> cs2=session.selectList("listCategoryByIdAndName",params);
        cs2.forEach(ccc->{
            System.out.println(ccc.getName());
        });



        session.commit();
        session.close();


    }

    public static void listAll(SqlSession session){
        List<Category> cs=session.selectList("listCategory");
        cs.forEach(c->{
            System.out.println(c.getName());
        });
    }
}
