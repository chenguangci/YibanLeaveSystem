package com.yiban.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DBAccess {
    private Reader reader;
    private SqlSessionFactory factory;
    public DBAccess(){
        try {
            reader = Resources.getResourceAsReader("com/configuration.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory = new SqlSessionFactoryBuilder().build(reader);
    }
    public SqlSession getSqlSession() throws IOException{
        return factory.openSession();
    }
}
