package com.team3.controller;

import com.team3.domain.NoticeBoardComment;
import com.team3.service.NoticeBoardCommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@Controller
@RequiredArgsConstructor
@RequestMapping("/noticeboardComment")
@Log4j2
public class NoticeBoardCommentController {

    private final NoticeBoardCommentService noticeBoardCommentService;

    @GetMapping("/save")
    public String save(Model model,
                       HttpSession httpSession,
                       @RequestParam String content,
                       @RequestParam BigInteger tableNo) {

        // session 부분 일단 주석 처리

//        String userId = (String) httpSession.getAttribute("userId");
//        BigInteger userNo = (BigInteger) httpSession.getAttribute("userNO");

        if (content.length() == 0 || content.equals("")) {
            model.addAttribute("message", "댓글을 입력해 주세요.");
            model.addAttribute("url", "/noticeboard/" + tableNo);
            return "/noticeBoard/redirect";
        }
        if (content.length() < 10 || content.length() > 500) {
            model.addAttribute("message", "댓글은 2~50자 사이만 작성할 수 있습니다.");
            model.addAttribute("url", "/noticeboard/" + tableNo);
            return "/noticeBoard/redirect";
        }

        String userId = "normal";
        BigInteger userNo = BigInteger.valueOf(2);

        NoticeBoardComment comment = NoticeBoardComment.builder()
                .userNo(userNo)
                .userId(userId)
                .tableNo(tableNo)
                .content(content)
                .build();

        noticeBoardCommentService.save(comment);

        return "redirect:/noticeboard/" + tableNo;
    }
}
