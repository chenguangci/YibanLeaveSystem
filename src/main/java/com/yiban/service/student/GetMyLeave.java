package com.yiban.service.student;

import com.yiban.entity.Information;
import com.yiban.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GetMyLeave {
    private ContentMapper contentMapper;
    @Autowired
    public GetMyLeave(ContentMapper contentMapper){
        this.contentMapper = contentMapper;
    }
    /**
     * 查看个人的请假信息
     * @param myId 登陆者的学号
     * @return 个人的请假信息
     */
    @Transactional
    public List<Information> getMyLeave(String myId){
        return contentMapper.myLeaves(myId);
    }
}
