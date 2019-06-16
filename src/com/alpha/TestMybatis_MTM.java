package com.alpha;

import com.alpha.pojo.Order;
import com.alpha.pojo.OrderItem;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis_MTM {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=ssf.openSession();

        listOrder(session);
        session.commit();
        session.close();

    }

    public static void listOrder(SqlSession session) {
        List<Order> os=session.selectList("listOrder");
        os.forEach(o->{
            System.out.println(o);
            List<OrderItem> ois=o.getOrderItems();
            ois.forEach(oi->{
                System.out.println(oi);
            });
        });

    }
}
