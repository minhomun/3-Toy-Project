package com.team3.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.team3.domain.Board;
import com.team3.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public List<Board> selectBoardList(Map<String, Object> paramMap) {
        return boardMapper.selectBoardList(paramMap);
    }

    /**
     * 게시물 조회 후 리턴
     * @param boardSeq
     * @return
     */
    public Board selectBoard(int boardSeq) {
        return boardMapper.selectBoard(boardSeq);
    }

    /**
     * 게시물 등록/업데이트 처리
     * @param board
     * @throws SQLException
     */
    public boolean save(Board board) {
        // 게시물 번호로 조회하여 데이가 있는지
        Board selectBoard = selectBoard(board.getBoardSeq());
        // 데이터가 없는경우
        if (selectBoard == null) {
            // 게시물 등록 쿼리 수행
            boardMapper.insertBoard(board);
            return true;
        }
        // 게시물 업데이트 쿼리 수행
        boardMapper.updateBoard(board);
        return false;
    }

    /**
     * 게시물 삭제 처리.
     * @param boardSeq
     */
    public void delete(int boardSeq) {
        // 게시물 번호로 조회하여 데이가 있는지
        Board selectBoard = selectBoard(boardSeq);
        Assert.notNull(selectBoard, "게시글이 존재하지 않습니다.");
        boardMapper.deleteBoard(boardSeq);
    }




}

