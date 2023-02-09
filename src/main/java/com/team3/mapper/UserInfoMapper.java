package com.team3.mapper;

import com.team3.domain.SecurityUser;
import com.team3.domain.UserInfoDto;

public interface UserInfoMapper {
    int checkUserInfo(String id);
    int idCheck(String id);
    int insertUserInfo(UserInfoDto userInfo);
    SecurityUser selectUser(String username);
}
