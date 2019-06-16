package com.alpha;

import com.alpha.pojo.Order;
import com.alpha.pojo.OrderItem;
import com.alpha.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatis_addConnection {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=ssf.openSession();

        addOrderItem(session);
        TestMybatis_MTM.listOrder(session);

        session.commit();
        session.close();

    }

    private static void addOrderItem(SqlSession session) {
        Order o1=session.selectOne("getOrder",1);
        Product p6=session.selectOne("getProduct_mtm",6);
        OrderItem oi=new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        oi.setNumber(200);

        session.insert("addOrderItem",oi);
    }

}
