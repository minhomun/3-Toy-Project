package com.team3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class NoticeBoardDTO {
    private BigInteger userNo;
    private String userId;
    private String title;
    private String content;
}
