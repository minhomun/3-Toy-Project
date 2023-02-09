package com.team3;

import com.team3.dao.FreeBoardDao;
import com.team3.dto.FreeBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class KangController {

    @Autowired
    FreeBoardDao boardDao;

    @RequestMapping("/")
//    @ResponseBody
    public String root() {
//        return "root()함수 호출됨.";
        System.out.println("d");
        return "redirect:freeboard"; //freeboard로 리다이렘트 됨.
    }


    @RequestMapping("/freeboard")
    public String freeboard( Model model) {
        List<FreeBoardDto> list = boardDao.list();
        model.addAttribute("list", list);

        return "freeboard"; //"freeboard.jsp" 디스패치해줌.

    }
    @RequestMapping("/writefreeboard")
    public String writefreeboard( ) {

        return "writefreeboard"; //"writefreeboard.jsp" 디스패치해줌.
    }
    
    @RequestMapping("/writeAction")
    @ResponseBody
    public String writeAction(@RequestParam("User_Id") String User_Id,
                              @RequestParam("TITLE") String TITLE,
                              @RequestParam("CONTENT") String CONTENT)
        {
        int result = FreeBoardDao.write(User_Id,TITLE,CONTENT);
        if( result == 1) {
            System.out.println("글쓰기 성공");
            //request.getSession().stAttribute("alert_message","글쓰기 성공");
         //   return "redirect:freeBoard"; //freeBoard으로 리다이렉트 됨.
            return "<script>alert('글쓰기 성공!'); location.href='freeForm';</script>";
        }else {
            System.out.println("글쓰기 실패");
            //request.getSession().stAttribute("alert_message","글쓰기 실패");
           // return "redirect:writeFreeBoard"; //writeFreeBoard으로 리다이렉트 됨.
            return "<script>alert('글쓰기 실패!'); location.href='writefreeForm'</script>";
        }
        return "redirect:freeboard"; //"freeboard.jsp" 리다이렉트 됨.
    }

    @RequestMapping("/contentForm")
    public String contentForm(@RequestParam("Table_No") String Table_No,
                              Model model)
    {
        FreeBoardDto dto = FreeBoardDao.viewDto(Table_No);
        model.addAttribute("dto",dto);

        return "contentForm"; //contentForm.jsp으로 리다이렉트 됨.
    }

    @RequestMapping("/updateAction")
    @ResponseBody
    public String updateAction(@RequestParam("Table_No") String Table_No,
                               @RequestParam("TITLE") String TITLE,
                               @RequestParam("CONTENT") String CONTENT) {

        int result FreeBoardDao.updateDto(Table_No, TITLE, CONTENT);
        if( result == 1) {
            System.out.println("글수정 성공");
            //request.getSession().stAttribute("alert_message","글수정 성공");
            //   return "redirect:freeBoard"; //freeBoard으로 리다이렉트 됨.
            return "<script>alert('글수정 성공!'); location.href='/freeForm';</script>";
        }else {
            System.out.println("글수정 실패");
            //request.getSession().stAttribute("alert_message","글수정 실패");
            // return "redirect:writeFreeBoard"; //writeFreeBoard으로 리다이렉트 됨.
            return "<script>alert('글수정 실패!'); location.href='/contentForm?Table_No=" + Table_No+"';</script>";

    }

    @RequestMapping("/deleteAction")
    @ResponseBody
    public String deleteAction(@RequestParam("Table_No") String Table_No) {


    }
        int result = FreeBoardDao.deleteDto(Table_No);
        if (result == 1) {
            System.out.println("글삭제 성공");
//            request.getSession().stAttribute("alert_message", "글삭제 성공");
//            return "redirect:freeBoard"; //freeBoard으로 리다이렉트 됨.
              return "<script>alert('글삭제 성공!'); location.href='/freeForm';</script>";
        } else {
            System.out.println("글삭제 실패");
//            request.getSession().stAttribute("alert_message", "글삭제 실패");
//            return "redirect:writeFreeBoard"; //writeFreeBoard으로 리다이렉트 됨.
              return "<script>alert('글삭제 실패!'); location.href='/contentForm?Table_No=" + Table_No+"';</script>";

        }
//    ?Table_No=${ dto.Table_No }
    }
