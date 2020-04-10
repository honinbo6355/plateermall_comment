package com.plateer.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.plateer.domain.CommentStatus;

@Mapper
public interface CommentStatusMapper {

	List<String> retrieve(String userId);
	
	void insert(CommentStatus status);
	
	void modify(String orderId);
	
	void delete(String orderId);
}
