package com.yiban.dao;

import com.yiban.bean.LeaveContent;
import com.yiban.db.DBAccess;
import com.yiban.mapper.ContentMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContentDao {
    private DBAccess dbAccess = new DBAccess();
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
}
