package com.team3.controller;

import com.team3.domain.NoticeBoard;
import com.team3.domain.NoticeBoardComment;
import com.team3.dto.NoticeBoardDTO;
import com.team3.service.NoticeBoardCommentService;
import com.team3.service.NoticeBoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/noticeboard")
@Log4j2
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    private final NoticeBoardCommentService noticeBoardCommentService;


    /**
     * 목록 화면
     * @param model
     * @param query
     * @return
     */
    @GetMapping
    public String displayBoard(Model model, @RequestParam(required = false) String query) {
        log.info("실행됨?");
        List<NoticeBoard> boardList = noticeBoardService.selectNoticeBoardList(query);

        model.addAttribute("boardList", boardList);

        log.info("실행됨?");
        return "noticeBoard/list";
    }

    /**
     * 게시물 화면
     * @param model
     * @param tableNo
     * @return
     */
    
    @GetMapping("/{tableNo}")
    public String displayBoard(Model model, @PathVariable("tableNo") BigInteger tableNo) {
        log.info("dispalyBoard 실행");

        NoticeBoard board = noticeBoardService.selectNoticeBoard(tableNo);
        List<NoticeBoardComment> commentList = noticeBoardCommentService.selectNoticeBoardList(tableNo);

        model.addAttribute("board", board);
        model.addAttribute("commentList", commentList);

        return "noticeBoard/detail";
    }


    /**
     * 등록 화면
     * @param model
     * @return
     */
    @GetMapping("/write")
    public String write(Model model) {

        return "noticeBoard/write";
    }

    /**
     * 게시글 등록
     * @param model
     * @param httpSession
     * @param title
     * @param content
     * @return
     */
    @PostMapping("/save")
    public String save(Model model, HttpSession httpSession, @RequestParam String title, @RequestParam String content) {

        log.info("작동하나요?");

        if (title.length() == 0 || title.equals("")) {
            model.addAttribute("message", "제목을 입력해 주세요.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }
        if (title.length() < 2 || title.length() > 50) {
            model.addAttribute("message", "제목은 2~50자 사이만 작성할 수 있습니다.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }
        if (content.length() == 0 || content.equals("")) {
            model.addAttribute("message", "내용을 입력해 주세요.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }
        if (content.length() < 10 || content.length() > 500) {
            model.addAttribute("message", "내용은 10~500자 사이만 작성할 수 있습니다.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }


        log.info("작동하나요?2");

        // session 부분 일단 주석 처리

//        String userId = (String) httpSession.getAttribute("userId");
//        BigInteger userNo = (BigInteger) httpSession.getAttribute("userNO");


        String userId = "normal";
        BigInteger userNo = BigInteger.valueOf(2);

        NoticeBoardDTO noticeBoardDTO = NoticeBoardDTO.builder()
                .userNo(userNo)
                .userId(userId)
                .title(title)
                .content(content)
                .build();

        log.info("작동하나요?3");

        noticeBoardService.insertNoticeBoard(noticeBoardDTO);

        log.info("작동하나요?4");

        return "redirect:/noticeboard";
    }

    /**
     * 수정 화면
     * @param model
     * @param tableNo
     * @param httpSession
     * @return
     */
    @GetMapping("/edit/{tableNo}")
    public String edit(Model model,
                       @PathVariable BigInteger tableNo,
                       HttpSession httpSession) {
        NoticeBoard noticeBoard = noticeBoardService.selectNoticeBoard(tableNo);

        // 본인만 수정 가능
//        String userId = (String) httpSession.getAttribute("userId");
//        if (userId != noticeBoard.getUserId()) {
//            model.addAttribute("message", "내용은 10~500자 사이만 작성할 수 있습니다.");
//            model.addAttribute("url", String.valueOf(userId));
//            return "/noticeBoard/redirect";
//        }

        model.addAttribute("board", noticeBoard);
        return "noticeBoard/write";
    }

    /**
     * 게시글 수정
     * @param model
     * @param httpSession
     * @param title
     * @param content
     * @param tableNo
     * @return
     */
    @PostMapping("/update")
    public String update(Model model, HttpSession httpSession,
                         @RequestParam String title,
                         @RequestParam String content,
                         @RequestParam(required = false) BigInteger tableNo) {

        log.info("작동하나요?");

        if (title.length() == 0 || title.equals("")) {
            model.addAttribute("message", "제목을 입력해 주세요.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }
        if (title.length() < 2 || title.length() > 50) {
            model.addAttribute("message", "제목은 2~50자 사이만 작성할 수 있습니다.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }
        if (content.length() == 0 || content.equals("")) {
            model.addAttribute("message", "내용을 입력해 주세요.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }
        if (content.length() < 10 || content.length() > 500) {
            model.addAttribute("message", "내용은 10~500자 사이만 작성할 수 있습니다.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }


        log.info("작동하나요?2");

        // session 부분 일단 주석 처리

//        String userId = (String) httpSession.getAttribute("userId");
//        BigInteger userNo = (BigInteger) httpSession.getAttribute("userNO");


        String userId = "normal";
        BigInteger userNo = BigInteger.valueOf(2);

        NoticeBoard noticeBoard = NoticeBoard.builder()
                .tableNo(tableNo)
                .userNo(userNo)
                .userId(userId)
                .title(title)
                .content(content)
                .build();

        log.info("작동하나요?3");

        noticeBoardService.updateNoticeBoard(noticeBoard);

        log.info("작동하나요?4");

        return "redirect:/noticeboard/" + tableNo;
    }

    /**
     * 게시글 삭제
     * @param model
     * @param tableNo
     * @param httpSession
     * @return
     */
    @GetMapping("/delete/{tableNo}")
    public String delete(Model model,
                         @PathVariable BigInteger tableNo,
                         HttpSession httpSession) {

        // 본인만 삭제 가능
//        String userId = (String) httpSession.getAttribute("userId");
//        if (userId != noticeBoard.getUserId()) {
//            model.addAttribute("message", "내용은 10~500자 사이만 작성할 수 있습니다.");
//            model.addAttribute("url", String.valueOf(userId));
//            return "/noticeBoard/redirect";
//        }

        log.info("되나?1");
        noticeBoardService.deleteNoticeBoard(tableNo);

        log.info("되나?2");
        model.addAttribute("message", "삭제 되었습니다.");
        model.addAttribute("url", "/noticeboard");
        return "/noticeBoard/redirect";
    }
}
