package com.yiban.dao;

import com.yiban.bean.LeaveContent;
import com.yiban.dao.db.DBAccess;
import com.yiban.dao.mapper.ContentMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContentDao {
    private static DBAccess dbAccess = new DBAccess();
    public boolean addContent(LeaveContent content){
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            ContentMapper mapper = sqlSession.getMapper(ContentMapper.class);
            mapper.addContent(content);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    /**
     * 查询请假信息
     * @param id 辅导员或班主任的易班ID
     * @return 辅导员对应班级的请假信息
     */
    public List<LeaveContent> selectLeaves(String id){
        SqlSession sqlSession = null;
        List<LeaveContent> contents = new ArrayList<LeaveContent>();
        try {
            sqlSession = dbAccess.getSqlSession();
            ContentMapper contentMapper = sqlSession.getMapper(ContentMapper.class);
            contents = contentMapper.selectLeaves(id);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return contents;
    }

    /**
     * 查询所有请假信息
     * @param id id 辅导员或班主任的易班ID
     * @return 辅导员对应班级的请假信息
     */
    public List<LeaveContent> selectAll(String id){
        SqlSession sqlSession = null;
        List<LeaveContent> contents = new ArrayList<LeaveContent>();
        try {
            sqlSession = dbAccess.getSqlSession();
            ContentMapper contentMapper = sqlSession.getMapper(ContentMapper.class);
            contents = contentMapper.selectAll(id);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return contents;
    }

    /**
     * 查看个人的请假信息
     * @param myId 学号
     * @return 请假信息
     */
    public List<LeaveContent> myLeave(String myId){
        SqlSession sqlSession = null;
        List<LeaveContent> contents = new ArrayList<LeaveContent>();
        try {
            sqlSession = dbAccess.getSqlSession();
            ContentMapper contentMapper = sqlSession.getMapper(ContentMapper.class);
            contents = contentMapper.myLeaves(myId);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return contents;
    }

    public List<LeaveContent> todayLeaves(@Param("id") String id, @Param("today") String today){
        SqlSession sqlSession = null;
        List<LeaveContent> contents = new ArrayList<LeaveContent>();
        try {
            sqlSession = dbAccess.getSqlSession();
            ContentMapper contentMapper = sqlSession.getMapper(ContentMapper.class);
            contents = contentMapper.todayLeaves(id,today);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return contents;
    }

    /**
     * 根据请假类型查询（事假或病假）
     * @param id 辅导员或班主任的易班id
     * @param type 类型
     * @return 相应的请假信息
     */
    public List<LeaveContent> selectByType(@Param("id") String id, @Param("type") String type){
        SqlSession sqlSession = null;
        List<LeaveContent> contents = new ArrayList<LeaveContent>();
        try {
            sqlSession = dbAccess.getSqlSession();
            ContentMapper contentMapper = sqlSession.getMapper(ContentMapper.class);
            contents = contentMapper.selectByType(id,type);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return contents;
    }
}
