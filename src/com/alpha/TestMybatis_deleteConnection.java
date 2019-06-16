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

public class TestMybatis_deleteConnection {
        public static void main(String[] args) throws IOException {
            String resource="mybatis-config.xml";
            InputStream inputStream= Resources.getResourceAsStream(resource);
            SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session=ssf.openSession();

            deleteOrderItem(session);
            TestMybatis_MTM.listOrder(session);

            session.commit();
            session.close();
    }

    private static void deleteOrderItem(SqlSession session) {
            Order o1=session.selectOne("getOrder",1);
            Product p6=session.selectOne("getProduct_mtm",6);
        OrderItem oi=new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        session.delete("deleteOrderItem",oi);

    }
    public static SqlSession builder(){
        String resource="mybatis-config.xml";
        InputStream inputStream= null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=ssf.openSession();
        return session;
    }

}
