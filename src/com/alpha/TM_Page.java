package com.alpha;

import com.alpha.mapper.CategoryMapper;
import com.alpha.mapper.CategoryMapper_Dynamic;
import com.alpha.mapper.CategoryMapper_Page;
import com.alpha.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TM_Page {
    public static void main(String[] args) {
        SqlSession session=TestMybatis_deleteConnection.builder();
        
        xmlWay(session);
        System.out.println("annotation------------------>");
        annotationWay(session);
        System.out.println("dynamic------------------>");
        CategoryMapper_Dynamic cmd=session.getMapper(CategoryMapper_Dynamic.class);
        List<Category> categories=cmd.listByPage(5,5);
        categories.forEach(category -> {
            System.out.println(category);
        });

        CategoryMapper categoryMapper=session.getMapper(CategoryMapper.class);

        System.out.println(categoryMapper.count());

        session.commit();
        session.close();
    }

    private static void xmlWay(SqlSession session) {
        Map<String ,Object> params=new HashMap<>();
        params.put("start", 0);
        params.put("count",5);
        List<Category> cs=session.selectList("listCategory_page",params);
        cs.forEach(category -> {
            System.out.println(category);
        });
    }


    private static void annotationWay(SqlSession session) {
        CategoryMapper_Page mapper=session.getMapper(CategoryMapper_Page.class);
        List<Category> cs=mapper.listByPage(0,5);
        cs.forEach(category -> {
            System.out.println(category);
        });
    }
}
