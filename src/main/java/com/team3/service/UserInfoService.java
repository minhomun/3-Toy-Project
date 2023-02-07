package com.team3.service;

import com.team3.domain.SecurityUser;
import com.team3.domain.UserInfoDto;
import com.team3.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoService implements UserDetailsService {
    private final UserInfoMapper userInfoMapper;
    private final PasswordEncoder passwordEncoder;

    public boolean checkUserInfo(UserInfoDto userInfo) {
        int result = userInfoMapper.checkUserInfo(userInfo.getId());
        System.out.println("result = " + result);
        return result == 1 ? true : false;
    }

    public int idCheck(String id) {
        return userInfoMapper.idCheck(id);
    }

    public int insertUserInfo(UserInfoDto userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        System.out.println("ROLE : " + userInfo.getRole());
        int test = userInfoMapper.insertUserInfo(userInfo);
        System.out.println("test = " + test);
        return test;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        SecurityUser user = userInfoMapper.selectUser(id);
        System.out.println(id);
        //logger.info("loadUserByUserName : {}", username);
        if (user == null) {
            System.out.println("실패");
            throw new UsernameNotFoundException("회원이 존재하지 않습니다.");
        }
        return user;
    }
}

