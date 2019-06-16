package com.alpha;

import com.alpha.mapper.ProductMapper;
import com.alpha.pojo.Category;
import com.alpha.pojo.Product;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class Recover {
    public static void main(String[] args) {
        SqlSession session=TestMybatis_deleteConnection.builder();
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(6);

        Category c=session.selectOne("getCategory",2);
        System.out.println(c);
        List<Product> products=session.selectList("listProduct_foreach",list);
        System.out.println(products.size());
        products.forEach(p->{
            p.setCategory(c);
        });


        System.out.println("annotation_OTO-------------->");
        ProductMapper mapper_product=session.getMapper(ProductMapper.class);
        List<Product> ps=mapper_product.list();
        System.out.println(ps.size());
        ps.forEach(p->{
            System.out.println(p);
        });
        ps.forEach(product -> {
            System.out.println(product.getName()+"\t mapping category:\t"+product.getCategory().getName());
        });
        session.commit();
        session.close();

    }
}
