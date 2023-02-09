package com.team3.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeBoardComment {

    private BigInteger commentNo;
    private BigInteger tableNo;
    private BigInteger userNo;
    private String userId;
    private String content;
    private LocalDateTime date;
}
