package com.alpha;

import com.alpha.mapper.CategoryMapper_Dynamic;
import com.alpha.pojo.Category;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TM_Annotation_Dynamic {
    public static void main(String[] args) {
        SqlSession session=TestMybatis_deleteConnection.builder();
        CategoryMapper_Dynamic cmd=session.getMapper(CategoryMapper_Dynamic.class);

        add(cmd);
//        delete(cmd);
        session.commit();
        Category cL=new Category();
        cL.setName("123456789012345678901234567890_long_transaction");
        cmd.add(cL);
        listAll(cmd);
        session.commit();
        session.close();
    }

    private static void delete(CategoryMapper_Dynamic cmd) {

    }

    private static void add(CategoryMapper_Dynamic cmd) {
        Category c=new Category();
        c.setName("Category_Dynamic_transaction");
        cmd.add(c);




    }

    private static void listAll(CategoryMapper_Dynamic cmd) {
        List<Category> cs=cmd.list();
        cs.forEach(c->{
            System.out.println(c);
        });
    }
}
