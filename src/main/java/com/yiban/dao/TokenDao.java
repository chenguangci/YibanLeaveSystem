package com.yiban.dao;

import com.yiban.bean.Token;
import com.yiban.dao.db.DBAccess;
import com.yiban.dao.mapper.TokenMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * 获取access_token
 */
public class TokenDao {
    private DBAccess dbAccess = DBAccess.getDbAccess();
    public String selectToken(String type){
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
            return tokenMapper.selectToken(type);
        } catch (IOException e) {
            return "error";
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    public void addToken(Token token){
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
            tokenMapper.addToken(token);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
    }
}
