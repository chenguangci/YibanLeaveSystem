package com.yiban.dao;

import com.yiban.dao.db.DBAccess;
import com.yiban.dao.mapper.ClassMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class SearchDao {
    private DBAccess dbAccess = DBAccess.getDbAccess();
    public String searchTeacherByStudentId(String id){
//        SqlSession sqlSession = null;
//        String teacher = null;
//        try {
//            sqlSession = dbAccess.getSqlSession();
//            ClassMapper classMapper = sqlSession.getMapper(ClassMapper.class);
//            teacher = classMapper.searchTeacherByStudentId(id);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (sqlSession!=null)
//                sqlSession.close();
//        }
//        return teacher;
        return "10849451";
    }

    /**
     * 通过易班id查找老师对应的专业班级
     * @param id 易班ID
     * @return 对应的专业班级ID
     */
    public List<String> searchTeacher(@Param("id") String id){
        SqlSession sqlSession = null;
        List<String> teacher = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            ClassMapper classMapper = sqlSession.getMapper(ClassMapper.class);
            teacher = classMapper.searchTeacher(id);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)
                sqlSession.close();
        }
        return teacher;
    }
}
