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

public class TestMybatis_ManyToOne {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=ssf.openSession();

        List<Product> ps=session.selectList("listProduct");
        ps.forEach(p->{
            System.out.println(p+"Mapping category-------->"+p.getCategory());
        });
        session.commit();

        Product p=session.selectOne("getProduct",5);
        Category c=session.selectOne("getCategory",1);
        p.setCategory(c);
        session.update("updateProduct",p);

        //map
        Map<String,Integer> params=new HashMap<>();
        params.put("id",1);
        params.put("cid",2);
        session.update("updateProduct_map",params);



        session.commit();
        session.close();
        SqlSession session1=ssf.openSession();
        System.out.println("=================================");
         List<Product> ps1=session1.selectList("listProduct");
        ps1.forEach(p1->{
            System.out.println(p+"Mapping category-------->"+p.getCategory());
        });

        session1.commit();
        session1.close();





    }
}
