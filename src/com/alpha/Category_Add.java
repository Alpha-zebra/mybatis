package com.alpha;

import com.alpha.pojo.Category;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Category_Add {
    public static void main(String[] args) {
        SqlSession session=TestMybatis_deleteConnection.builder();
        for (int i=0;i<100;i++){
            Category c=new Category();
            c.setName("category name"+i);
            session.insert("addCategory",c);
        }
        List<Category> cs=session.selectList("listCategory");
        cs.forEach(category -> {
            System.out.println(category.getName());
        });

        session.commit();
        session.close();
    }
}
