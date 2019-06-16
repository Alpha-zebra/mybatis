package com.alpha;

import com.alpha.pojo.Category;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TM_CP30 {
    public static void main(String[] args) {

        SqlSession session=TestMybatis_deleteConnection.builder();
        List<Category> cs=session.selectList("listCategory");
        cs.forEach(category -> {
            System.out.println(category.getName());
        });

        session.close();
    }
}
