package com.team3.service;

import com.team3.dto.UserInfoDto;
import com.team3.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoMapper userInfoMapper;
    public boolean findUser(UserInfoDto userInfo){
        int result = userInfoMapper.findUser(userInfo);
        System.out.println("result = " + result);
        return result == 1 ? true : false;
    }

    public int idCheck(String id) {
        return userInfoMapper.idCheck(id);
    }

    public int insertUserInfo(UserInfoDto userInfo) {
        return userInfoMapper.insertUserInfo(userInfo);
    }
}
