package com.team3.controller;

import com.team3.domain.Criteria;
import com.team3.domain.NoticeBoard;
import com.team3.domain.NoticeBoardComment;
import com.team3.domain.SecurityUser;
import com.team3.dto.NoticeBoardDTO;
import com.team3.dto.NoticeBoardPageDTO;
import com.team3.service.NoticeBoardCommentService;
import com.team3.service.NoticeBoardService;
import com.team3.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
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

    private final UserInfoService userInfoService;


    /**
     * 목록 화면
     * @param model
     * @param query
     * @return
     */
    @GetMapping
    public String displayBoard(Model model, @RequestParam(required = false) String query) {
        List<NoticeBoard> boardList = noticeBoardService.selectNoticeBoardList(query);

        model.addAttribute("boardList", boardList);

        return "noticeBoard/list";
    }

    @GetMapping("/page")
    public String boardList(Model model, @ModelAttribute Criteria criteria,
                            @RequestParam(required = false) String query) {
        if (query != null) {
            criteria.setKeyword(query);
        }
        List<NoticeBoard> boardList = noticeBoardService.selectNoticeBoardListWithPaging(criteria);
        model.addAttribute("boardList", boardList);
        model.addAttribute("pageMaker", new NoticeBoardPageDTO(noticeBoardService.getTotal(), 5, criteria));

        return "noticeBoard/page";
    }


    /**
     * 게시물 화면
     * @param model
     * @param tableNo
     * @return
     */

    @GetMapping("/{tableNo}")
    public String displayBoard(Model model, @PathVariable("tableNo") BigInteger tableNo) {

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
     * @param title
     * @param content
     * @return
     */
    @PostMapping("/save")
    public String save(Model model,
                       @RequestParam String title,
                       @RequestParam String content,
                       Authentication authentication) {


        if (title.length() == 0 || title.equals("") || title == null) {
            model.addAttribute("message", "제목을 입력해 주세요.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }
        if (title.length() < 2 || title.length() > 50 || title == null) {
            model.addAttribute("message", "제목은 2~50자 사이만 작성할 수 있습니다.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }
        if (content.length() == 0 || content.equals("") || content == null) {
            model.addAttribute("message", "내용을 입력해 주세요.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }
        if (content.length() < 10 || content.length() > 500 || content == null) {
            model.addAttribute("message", "내용은 10~500자 사이만 작성할 수 있습니다.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String userId = securityUser.getId();
        BigInteger userNo = userInfoService.selectUserNo(userId);

        NoticeBoardDTO noticeBoardDTO = NoticeBoardDTO.builder()
                .userNo(userNo)
                .userId(userId)
                .title(title)
                .content(content)
                .build();


        noticeBoardService.insertNoticeBoard(noticeBoardDTO);

        model.addAttribute("message", "저장되었습니다.");
        model.addAttribute("url", "/noticeboard");

        return "/noticeBoard/redirect";
    }

    /**
     * 수정 화면
     * @param model
     * @param tableNo
     * @return
     */
    @GetMapping("/edit/{tableNo}")
    public String edit(Model model,
                       @PathVariable BigInteger tableNo,
                       Authentication authentication) {
        NoticeBoard noticeBoard = noticeBoardService.selectNoticeBoard(tableNo);

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String userId = securityUser.getId();
        if (userId != noticeBoardService.selectNoticeBoard(tableNo).getUserId()) {
            model.addAttribute("message", "본인만 수정 가능합니다.");
            model.addAttribute("url", String.valueOf(userId));
            return "/noticeBoard/redirect";
        }

        model.addAttribute("board", noticeBoard);
        return "noticeBoard/write";
    }

    /**
     * 게시글 수정
     * @param model
     * @param title
     * @param content
     * @param tableNo
     * @return
     */
    @PostMapping("/update")
    public String update(Model model,
                         @RequestParam String title,
                         @RequestParam String content,
                         @RequestParam(required = false) BigInteger tableNo,
                         Authentication authentication) {


        if (title.length() == 0 || title.equals("") || title == null) {
            model.addAttribute("message", "제목을 입력해 주세요.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }
        if (title.length() < 2 || title.length() > 50 || title == null) {
            model.addAttribute("message", "제목은 2~50자 사이만 작성할 수 있습니다.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }
        if (content.length() == 0 || content.equals("") || content == null) {
            model.addAttribute("message", "내용을 입력해 주세요.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }
        if (content.length() < 10 || content.length() > 500 || content == null) {
            model.addAttribute("message", "내용은 10~500자 사이만 작성할 수 있습니다.");
            model.addAttribute("url", "write");
            return "/noticeBoard/redirect";
        }


        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String userId = securityUser.getId();
        BigInteger userNo = userInfoService.selectUserNo(userId);

        NoticeBoard noticeBoard = NoticeBoard.builder()
                .tableNo(tableNo)
                .userNo(userNo)
                .userId(userId)
                .title(title)
                .content(content)
                .build();


        noticeBoardService.updateNoticeBoard(noticeBoard);

        model.addAttribute("message", "수정되었습니다.");
        model.addAttribute("url", "/noticeboard/" + tableNo);


        return "/noticeBoard/redirect";
    }

    /**
     * 게시글 삭제
     * @param model
     * @param tableNo
     * @return
     */
    @PostMapping("/delete")
    public String delete(Model model,
                         @RequestParam BigInteger tableNo,
                         Authentication authentication) {

//         본인만 삭제 가능
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String userId = securityUser.getId();
        if (!userId.equals(noticeBoardService.selectNoticeBoard(tableNo).getUserId())) {
            model.addAttribute("message", "본인만 삭제 가능합니다.");
            model.addAttribute("url", "/noticeboard/" + tableNo);
            return "/noticeBoard/redirect";
        }

        noticeBoardService.deleteNoticeBoard(tableNo);

        noticeBoardCommentService.deleteAllComment(tableNo);

        model.addAttribute("message", "삭제 되었습니다.");
        model.addAttribute("url", "/noticeboard");
        return "/noticeBoard/redirect";
    }
}
