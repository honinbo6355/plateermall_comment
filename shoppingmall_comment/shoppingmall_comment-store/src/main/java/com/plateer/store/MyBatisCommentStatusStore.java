package com.plateer.store;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plateer.domain.CommentStatus;
import com.plateer.store.mapper.CommentStatusMapper;

@Repository
public class MyBatisCommentStatusStore implements CommentStatusStore {

	CommentStatusMapper commentStatusMapper;

	public MyBatisCommentStatusStore(CommentStatusMapper commentStatusMapper) {

		this.commentStatusMapper = commentStatusMapper;
	}
	
	@Override
	public List<String> retrieve(String userId) {

		return commentStatusMapper.retrieve(userId);
	}

	@Override
	public void insert(CommentStatus status) {

		commentStatusMapper.insert(status);
	}

	@Override
	public void modify(String orderId) {
		
		commentStatusMapper.modify(orderId);
	}
	
	
}
