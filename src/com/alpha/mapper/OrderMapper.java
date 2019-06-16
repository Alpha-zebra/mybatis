package com.alpha.mapper;

import com.alpha.pojo.Order;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.awt.*;
import java.util.List;

public interface OrderMapper {
    @Select("select * from order_")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderItems" ,javaType =List.class,column = "id",
            many = @Many(
                    select = "com.alpha.mapper.OrderItemMapper.listByOrder"
            ))
    })
    public List<Order> list();
}
