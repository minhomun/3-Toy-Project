package com.team3.controller;

import com.team3.domain.NoticeBoard;
import com.team3.domain.NoticeBoardComment;
import com.team3.service.NoticeBoardCommentService;
import com.team3.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/noticeboard")
@Log4j2
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    private final NoticeBoardCommentService noticeBoardCommentService;

    @GetMapping
    public String displayBoard(Model model, @RequestParam(required = false) String query) {
        log.info("실행됨?");
        List<NoticeBoard> boardList = noticeBoardService.selectNoticeBoardList(query);

        model.addAttribute("boardList", boardList);

        log.info("실행됨?");
        return "noticeBoard/list";
    }

    @GetMapping(value = "/{tableNo}")
    public String displayBoard(Model model, @PathVariable("tableNo") BigInteger tableNo) {
        log.info("dispalyBoard 실행");

        NoticeBoard board = noticeBoardService.selectNoticeBoard(tableNo);
        List<NoticeBoardComment> commentList = noticeBoardCommentService.selectNoticeBoardList(tableNo);

        model.addAttribute("board", board);
        model.addAttribute("commentList", commentList);

        return "noticeBoard/detail";
    }


    @GetMapping(value = "/write")
    public String writeBoard(Model model) {

        return "noticeBoard/write";
    }
}
