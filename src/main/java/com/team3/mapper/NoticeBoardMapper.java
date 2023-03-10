package com.team3.mapper;

import com.team3.domain.Criteria;
import com.team3.domain.NoticeBoard;
import com.team3.dto.NoticeBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface NoticeBoardMapper {

    public List<NoticeBoard> selectNoticeBoardList(String query);

    public NoticeBoard selectNoticeBoard(BigInteger tableNo);

    public void insertNoticeBoard(NoticeBoardDTO noticeBoardDTO);

    public void updateNoticeBoard(NoticeBoard noticeBoard);

    public void deleteNoticeBoard(BigInteger tableNo);

    public List<NoticeBoard> selectNoticeBoardListWithPaging(Criteria criteria);

    public int getTotal();
}
