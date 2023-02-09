package com.team3.service;

import com.team3.domain.NoticeBoardComment;
import com.team3.mapper.NoticeBoardCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeBoardCommentService {

    private final NoticeBoardCommentMapper noticeBoardCommentMapper;

    public List<NoticeBoardComment> selectNoticeBoardList(BigInteger tableNo) {
        return noticeBoardCommentMapper.selectNoticeBoardCommentList(tableNo);
    }

    public void save(NoticeBoardComment comment) {
        noticeBoardCommentMapper.save(comment);
    }

    public void deleteComment(BigInteger commentNo) {
        noticeBoardCommentMapper.deleteComment(commentNo);
    }
}
