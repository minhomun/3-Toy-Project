package com.team3.mapper;

import com.team3.domain.NoticeBoardComment;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface NoticeBoardCommentMapper {

    public List<NoticeBoardComment> selectNoticeBoardCommentList(BigInteger tableNo);
}
