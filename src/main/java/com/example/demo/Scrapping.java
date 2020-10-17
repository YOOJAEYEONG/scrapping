package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
public class Scrapping {

	
   	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @Column(nullable = false,unique = true)
    private int bizNum;

    @Column(length = 200, nullable = false)
    private String result;
    
    @Builder
    public Scrapping(int bizNum, String result) {
        this.bizNum = bizNum;
        this.result = result;
    }
	    
}
