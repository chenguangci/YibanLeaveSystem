package com.yiban.dao;

import com.yiban.db.DBAccess;
import com.yiban.mapper.ClassMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class SearchDao {
    private DBAccess dbAccess = new DBAccess();
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
    public List<String> searchTeacher(String id){
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
