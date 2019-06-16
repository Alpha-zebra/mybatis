package com.alpha.mapper;

import com.alpha.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CategoryMapper_Page {
    @Select("select * from category_")
    @Results({
            @Result(property = "products",javaType = List.class,column = "id",
            many = @Many(
                    select = "com.alpha.mapper.ProductMapper.listByCategory"
            ))

    })
    public List<Category> list();
    @Select("select * from category_ limit #{start},#{count}")
    public List<Category> listByPage(@Param("start") int start, @Param("count") int count);

}
