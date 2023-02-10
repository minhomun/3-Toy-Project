package com.team3.controller;

import com.team3.domain.SecurityUser;
import com.team3.domain.UserInfoDto;
import com.team3.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;


@Controller
@RequiredArgsConstructor
public class UserInfoController {
    final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserInfoService userInfoService;

    @GetMapping({"/", "/login"})
    public String loginPage(Model model) {
        System.out.println("/login");
        model.addAttribute("user", new LoginRequest());
        return "login_register/login";
    }
    @PostMapping("/loginProcess")
    public String checkUserInfo(Model model, UserInfoDto userInfo){
        System.out.println(userInfo.getId());
        System.out.println(userInfo.getPassword());
        boolean isAdministrated = userInfoService.checkUserInfo(userInfo);
        if (isAdministrated == true){
            System.out.println("성공!");
            model.addAttribute("msg", "로그인을 성공하였습니다.");

            return "redirect:noticeBoard";
        }
        else{
            System.out.println("실패!");
            model.addAttribute("msg", "아이디와 비밀번호를 확인해주세요.");
            return "login_register/login";
        }
    }

    @RequestMapping("/registerForm")
    public String registerForm(){
        return "login_register/registration";
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
    public String notice(Model model, Authentication authentication){
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        model.addAttribute("user", securityUser);
        return "/notice";
    }
}
