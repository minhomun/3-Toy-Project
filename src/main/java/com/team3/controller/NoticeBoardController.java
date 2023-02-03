package com.team3.controller;

import com.team3.domain.NoticeBoard;
import com.team3.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/noticeboard")
@Log4j2
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    @GetMapping
    public String displayBoard(Model model, @RequestParam(required = false) String query) {
        log.info("실행됨?");
        List<NoticeBoard> boardList = noticeBoardService.selectNoticeBoardList(query);

        model.addAttribute("boardList", boardList);

        log.info("실행됨?");
        return "noticeBoard/list";
    }
}
