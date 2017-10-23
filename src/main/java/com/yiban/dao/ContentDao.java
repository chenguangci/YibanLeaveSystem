package com.yiban.dao;

import com.yiban.bean.LeaveContent;
import com.yiban.db.DBAccess;
import com.yiban.mapper.ContentMapper;
import org.apache.ibatis.session.SqlSession;
import java.io.IOException;

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
        } catch (IOException e) {
            return false;
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
    }
}
