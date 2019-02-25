package com.example.demo.mapper;

import com.example.demo.entity.DataMap;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @date 2019-2-18 14:26
 */
@Mapper
public interface SystemMapper {

    @Select("select user_id id,username,name,dept_id  deptId from sys_user")
    List<DataMap> systemUser();

    @Select("select user_id id,username,name,dept_id  deptId from sys_user")
    List<Map<String, Object>> systemUserData();


    @Insert("insert into movie(mid,fid) values(#{mid},#{fid})")
    boolean insetMovie(DataMap map) throws Exception;
}
