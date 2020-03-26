package com.plateer.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plateer.domain.SubComment;
import com.plateer.store.mapper.SubCommentMapper;

@Repository
public class MyBatisSubCommentStore implements SubCommentStore {

	@Autowired
	SubCommentMapper subCommentMapper;
	
	@Override
	public List<SubComment> retrieveAll(String userId) {
		
		return subCommentMapper.retrieveAll(userId);
	}

	@Override
	public void insert(SubComment comment) {
		
		subCommentMapper.insert(comment);
	}

	@Override
	public void modify(SubComment comment) {

		subCommentMapper.modify(comment);
	}

	@Override
	public void delete() {

		subCommentMapper.delete();
	}

	@Override
	public void recommend(SubComment comment) {

		subCommentMapper.recommend(comment);
	}

	@Override
	public List<SubComment> retrieve(String goodsCode) {
		
		return subCommentMapper.retrieve(goodsCode);
	}

	@Override
	public List<SubComment> retrieveFilter(String goodsCode, String goodsOption, String orderByOption) {

		return null;
	}

}
