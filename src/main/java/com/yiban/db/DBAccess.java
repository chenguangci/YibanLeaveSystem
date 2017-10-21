package com.yiban.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DBAccess {
    public SqlSession getSqlSession() throws IOException{
        Reader reader = Resources.getResourceAsReader("com/configuration.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        return factory.openSession();
    }
}
