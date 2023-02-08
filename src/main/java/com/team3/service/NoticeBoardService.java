package com.team3.service;

import com.team3.domain.NoticeBoard;
import com.team3.mapper.NoticeBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class NoticeBoardService {

    private final NoticeBoardMapper noticeBoardMapper;

    public List<NoticeBoard> selectNoticeBoardList(String query) {
        return noticeBoardMapper.selectNoticeBoardList(query);
    }

    public NoticeBoard selectNoticeBoard(BigInteger tableNo) {
        return noticeBoardMapper.selectNoticeBoard(tableNo);
    }


}
