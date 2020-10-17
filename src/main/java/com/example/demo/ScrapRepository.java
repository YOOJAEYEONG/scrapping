package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapRepository extends JpaRepository<Scrapping, Integer>{

	
	public List<Scrapping> findByBizNum(int bizNum);
	//like검색도 가능
	public List<Scrapping> findByResultLike(String result);
	
}
