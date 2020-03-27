package com.plateer.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plateer.domain.dto.CommentDto;
import com.plateer.store.mapper.CommentMapper;

@Repository
public class MyBatisCommentStore implements CommentStore {

	@Autowired
	CommentMapper commentMapper;
	
	public MyBatisCommentStore(CommentMapper commentMapper) {

		this.commentMapper = commentMapper;
	}
	
	@Override
	public CommentDto retrieve(String goodsCode) {

		return commentMapper.retrieve(goodsCode);
	}

	@Override
	public void modify(CommentDto comment) {

		commentMapper.modify(comment);
	}

}
