package com.alpha;

import com.alpha.mapper.CategoryMapper;
import com.alpha.mapper.ProductMapper;
import com.alpha.pojo.Category;
import com.alpha.pojo.Product;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TestMybatis_annotation {
    public static void main(String[] args) {
        SqlSession session=TestMybatis_deleteConnection.builder();
        CategoryMapper mapper=session.getMapper(CategoryMapper.class);
        listAll(mapper);
        add(mapper);
        delete(mapper);
        get(mapper);
        update(mapper);
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

    private static void update(CategoryMapper mapper) {
        Category c=mapper.get(1);
        c.setName("annotation_update_1------------>");
        mapper.update(c);
        listAll(mapper);
    }

    public static void get(CategoryMapper mapper){
        Category c=mapper.get(1);
        System.out.println("annotation_get_1------------>");
        System.out.println(c.getName());
    }
    private static void delete(CategoryMapper mapper) {
        mapper.delete(2);
        System.out.println("annotation_delete------------>");
        listAll(mapper);
    }

    private static void add(CategoryMapper mapper) {
        Category c=new Category();
        c.setName("annotation_add");
        mapper.add(c);
        System.out.println("annotation_add------------>");
        listAll(mapper);
    }

    public static void listAll(CategoryMapper mapper) {
        List<Category> cs=mapper.list();
        System.out.println("annotation_list------------>");
        cs.forEach(c->{
            System.out.println(c.getName());
            List<Product> ps=c.getProducts();
            ps.forEach(p->{
                System.out.println("\t"+p.getName());
            });
        });
    }
}
