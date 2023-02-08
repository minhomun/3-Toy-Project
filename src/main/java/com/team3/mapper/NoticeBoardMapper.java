package com.team3.mapper;

import com.team3.domain.NoticeBoard;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface NoticeBoardMapper {

    public List<NoticeBoard> selectNoticeBoardList(String query);

    public NoticeBoard selectNoticeBoard(BigInteger tableNo);
}
