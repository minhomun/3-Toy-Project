package com.team3.dto;

import java.util.Date;

import lombok.Data;

//                                      foreign key (`User_No`) references `User_Info` (`User_No`)
//@Data : lombok 연결
@Data
public class FreeBoardDto {
    private int Table_No;
    private int User_No;
    private String User_Id;
    private String TITLE;
    private String CONTENT;
    private Date DATE;

    public FreeBoardDto() {

    }

    public FreeBoardDto(int Table_No, int User_No, String User_Id, String TITLE, String CONTENT, Date DATE) {
        super();
        this.Table_No = Table_No;
        this.User_No =User_No;
        this.User_Id = User_Id;
        this.TITLE = TITLE;
        this.CONTENT =CONTENT;
        this.DATE = DATE;


    }
}