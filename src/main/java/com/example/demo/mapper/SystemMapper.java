package com.example.demo.mapper;

import com.example.demo.entity.DataMap;
import com.example.demo.provider.DaoProvider;
import org.apache.ibatis.annotations.*;

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


    /**
     * 批量插入数据
     *
     * @param list
     * @return
     */

    @Insert({
            "<script>",
            "insert into movie(mid,fid)",
            "values",
            "<foreach  item='item' index='index' collection='list' separator=','>",
            "(#{item.mid},#{item.fid})",
            "</foreach>",
            "</script>"
    })
    int insertCollectionList(List<DataMap> list);


    @InsertProvider(type = DaoProvider.class, method = "insertImportExcel")
    int insertCollectionListProvider(List<DataMap> list);

    @Update({
            "<script>",
            "<foreach item='value' index='key' collection='pushResult' separator=';'>",
            "update movie set mid=#{mid} ,fid=#{fid} where mid=#{mid}",
            "</foreach>",
            "</script>"
    })
    int updataByMsgId(Map<String, Map<String, String>> pushResult);


}
