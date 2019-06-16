package com.alpha;

import com.alpha.pojo.Product;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TM_if {
    public static void main(String[] args) {
        SqlSession session=TestMybatis_deleteConnection.builder();
        System.out.println("select all_if");
        List<Product> ps=session.selectList("listProduct_if");
        ps.forEach(p->{
            System.out.println(p);
        });
//        listProduct(session);
        System.out.println("fuzzy select_if");
        Map<String,Object> params=new HashMap<>();
//        params.put("name","a");
//        List<Product> ps=session.selectList("listProductByName",params);
        List<Product> ps2=session.selectList("listProduct_if",params);
        ps2.forEach(p->{
            System.out.println(p);
        });
        System.out.println("multiple select--------------->");
        params.put("price","10");

        List<Product> ps3=session.selectList("listProduct_if",params);
        ps3.forEach(p->{
            System.out.println(p);
        });

        System.out.println("update set------------->");
        Product p=new Product();
        p.setId(6);
        p.setName("product set");
        p.setPrice(99.99f);
        session.update("updateProduct_set",p);

        listProduct(session);
        System.out.println("update trim------------->");
        p=new Product();
        p.setId(5);
        p.setName("product trim");
        p.setPrice(99.99f);
        session.update("updateProduct_trim",p);
        listProduct(session);
        System.out.println("listProduct_when------------->");
        ps3=session.selectList("listProduct_when");
        ps3.forEach(p3->{
            System.out.println(p3);
        });
        System.out.println("listProduct_foreach------------->");
        List<Integer> ids=new ArrayList<>();
        ids.add(1);
        ids.add(3);
        ids.add(5);

        ps=session.selectList("listProduct_foreach",ids);
        ps.forEach(p4->{
            System.out.println(p4);
        });
        System.out.println("listProduct_bind------------->");
        params.clear();
        params.put("name","product");
        ps=session.selectList("listProduct_bind",params);
        ps.forEach(p5->{
            System.out.println(p5);
        });



        session.commit();
        session.close();
    }

    private static void listProduct(SqlSession session) {
        List<Product> ps=session.selectList("listProduct");
        ps.forEach(p->{
            System.out.println(p);
        });
    }
}
