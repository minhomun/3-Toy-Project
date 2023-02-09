package com.team3.dao;

import com.team3.dto.FreeBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper : MyBatis와 인터페이스 함수를 연결함.
@Mapper
public interface FreeBoardDao {
    public List<FreeBoardDto> list();
    public int write(String user_id, String title, String content) ;

    public FreeBoardDto viewDto(String Table_no);

    };



}
