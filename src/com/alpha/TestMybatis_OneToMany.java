package com.alpha;

import com.alpha.pojo.Category;
import com.alpha.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis_OneToMany {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        List<Category> cs=session.selectList("listCategory");
        cs.forEach(c->{
            System.out.println(c);
            List<Product> ps=c.getProducts();
            ps.forEach(p->{
                System.out.println(p);
            });
        });
        session.commit();
        session.close();

    }
}
