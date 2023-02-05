package com.team3.mapper;

import com.team3.dto.UserInfoDto;

public interface UserInfoMapper {
    int findUser(UserInfoDto id);
    int idCheck(String id);

    int insertUserInfo(UserInfoDto userInfo);
}
