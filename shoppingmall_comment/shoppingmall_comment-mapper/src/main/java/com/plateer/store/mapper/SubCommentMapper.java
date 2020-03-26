package com.plateer.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.plateer.domain.dto.SubComment;

@Mapper
public interface SubCommentMapper {

	List<SubComment> retrieveAll(String userId);
	
	void insert(SubComment comment);
	
	void modify(SubComment comment);
	
	void delete();
	
	void recommend(SubComment comment);
	
	List<SubComment> retrieveFilter(String goodsCode, String goodsOption, String orderByOption);
}
