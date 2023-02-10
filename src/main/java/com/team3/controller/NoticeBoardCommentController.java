package com.team3.controller;

import com.team3.domain.NoticeBoardComment;
import com.team3.domain.SecurityUser;
import com.team3.service.NoticeBoardCommentService;
import com.team3.service.UserInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Controller
@RequiredArgsConstructor
@RequestMapping("/noticeboardcomment")
@Log4j2
public class NoticeBoardCommentController {

    private final NoticeBoardCommentService noticeBoardCommentService;

    private final UserInfoService userInfoService;

    @GetMapping("/save")
    public String save(Model model,
                       @RequestParam String content,
                       @RequestParam BigInteger tableNo,
                       Authentication authentication) {


        if (content.length() == 0 || content.equals("") || content == null) {
            model.addAttribute("message", "댓글을 입력해 주세요.");
            model.addAttribute("url", "/noticeboard/" + tableNo);
            return "/noticeBoard/redirect";
        }
        if (content.length() < 2 || content.length() > 50) {
            model.addAttribute("message", "댓글은 2~50자 사이만 작성할 수 있습니다.");
            model.addAttribute("url", "/noticeboard/" + tableNo);
            return "/noticeBoard/redirect";
        }


        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String userId = securityUser.getId();
        BigInteger userNo = userInfoService.selectUserNo(userId);

        NoticeBoardComment comment = NoticeBoardComment.builder()
                .userNo(userNo)
                .userId(userId)
                .tableNo(tableNo)
                .content(content)
                .build();

        noticeBoardCommentService.save(comment);

        model.addAttribute("message", "댓글이 추가되었습니다.");
        model.addAttribute("url", "/noticeboard/" + tableNo);
        return "/noticeBoard/redirect";

    }

    @PostMapping("/delete")
    public String delete(Model model,
                         @RequestParam BigInteger tableNo,
                         @RequestParam BigInteger commentNo,
                         Authentication authentication) {

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String userId = securityUser.getId();
        log.info(userId);
        log.info(noticeBoardCommentService.selectComment(commentNo).getUserId());
        if (!userId.equals(noticeBoardCommentService.selectComment(commentNo).getUserId())) {
            model.addAttribute("message", "본인만 삭제 가능합니다.");
            model.addAttribute("url", "/noticeboard/" + tableNo);
            return "/noticeBoard/redirect";
        }


        noticeBoardCommentService.deleteComment(commentNo);

        model.addAttribute("message", "삭제 되었습니다.");
        model.addAttribute("url", "/noticeboard/" + tableNo);
        return "/noticeBoard/redirect";
    }

}
