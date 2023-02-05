package com.team3.controller;

import com.team3.dto.UserInfoDto;
import com.team3.service.UserInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Pattern;


@Controller
@RequiredArgsConstructor
public class UserInfoController {
    final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserInfoService userInfoService;

    @RequestMapping({"/", "/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping("/findUser")
    public String findUser(Model model, HttpSession session, UserInfoDto userInfo){
        System.out.println(userInfo.getId());
        System.out.println(userInfo.getPassword());
        boolean isAdministrated = userInfoService.findUser(userInfo);
        if (isAdministrated == true){
            session.setAttribute("id", userInfo.getId());
            System.out.println("성공!");
            model.addAttribute("msg", "로그인을 성공하였습니다.");

            return "notice";
        }
        else{
            System.out.println("실패!");
            model.addAttribute("msg", "아이디와 비밀번호를 확인해주세요.");
            return "login";
        }
    }

    @RequestMapping("/registerForm")
    public String registerForm(){
        return "registration";
    }

    @RequestMapping("idCheck")
    @ResponseBody
    public int idCheck(Model model, String id){

        return userInfoService.idCheck(id);
    }
    @PostMapping("/registration")
    @ResponseBody
    public String registration(UserInfoDto userInfo, String passwordCheck){
        String regPassword = "^.{8,15}$";
        String regName = "^.{0,10}$";
        String regPhoneNumber = "^[0-9]{10,11}$";
        if (!userInfo.getPassword().equals(passwordCheck) || !Pattern.matches(regPassword, userInfo.getPassword())){
            return "passwordError";
        }
        if (!Pattern.matches(regName, userInfo.getName())){
            return "nameError";
        }
        if (!Pattern.matches(regPhoneNumber, userInfo.getPhoneNumber())){
            return "phoneNumberError";
        }
        int insertUserInfo = userInfoService.insertUserInfo(userInfo);
        return "success";
    }

    //아래 나중에 지우면됨
    @RequestMapping("notice")
    public String notice(){
        return "notice";
    }


}
