package com.team3.service;

import com.team3.domain.NoticeBoard;
import com.team3.mapper.NoticeBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeBoardService {

    private final NoticeBoardMapper noticeBoardMapper;

    public List<NoticeBoard> selectNoticeBoardList(String query) {
        return noticeBoardMapper.selectNoticeBoardList(query);
    }


}
