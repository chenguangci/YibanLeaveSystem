package com.yiban.mapper;

import com.yiban.entity.Token;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenMapper {
    Token selectToken(int type);
    int addToken(Token token);
    int updateToken(Token token);
}
