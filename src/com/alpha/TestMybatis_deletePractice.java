package com.alpha;

import com.alpha.pojo.Order;
import org.apache.ibatis.session.SqlSession;

public class TestMybatis_deletePractice {
    public static void main(String[] args) {
        SqlSession session=TestMybatis_deleteConnection.builder();

        Order o=session.selectOne("getOrder",1);
        session.delete("deleteOrder",o);

        TestMybatis_MTM.listOrder(session);
        session.commit();
        session.close();

    }
}
