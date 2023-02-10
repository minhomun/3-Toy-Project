package com.team3.domain;

import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

@Data
public class Criteria {

    private int pageNum;
    private int amount;
    private String type;
    private String keyword;

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public Criteria(){
        this(1, 10);
    }


}
