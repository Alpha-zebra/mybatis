package com.alpha;

import com.alpha.mapper.OrderMapper;
import com.alpha.pojo.Order;
import com.alpha.pojo.OrderItem;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TM_annotation_MTM {
    public static void main(String[] args) {
        SqlSession session=TestMybatis_deleteConnection.builder();

        listOrder(session);

        session.commit();
        session.close();
    }
    private static void listOrder(SqlSession session){
        OrderMapper mapper=session.getMapper(OrderMapper.class);
        List<Order> os=mapper.list();
        os.forEach(o->{
            System.out.println(o.getCode());
            List<OrderItem> ois=o.getOrderItems();
            if (ois!=null){
                ois.forEach(oi->{
                    System.out.printf("\t%s\t%f\t%d%n",oi.getProduct().getName(),oi.getProduct().getPrice(),oi.getNumber());
                });
            }
        });
    }
}
