package org.global.api.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface PracticeDao {
    
    @Select("SELECT id, name, email, phone FROM practice_user")
    List<Map<String, Object>> selectAllUsers();
}
