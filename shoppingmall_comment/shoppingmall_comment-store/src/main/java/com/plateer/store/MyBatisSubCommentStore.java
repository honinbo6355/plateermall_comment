package com.plateer.store;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.plateer.domain.CommentRecommend;
import com.plateer.domain.SubComment;
import com.plateer.store.mapper.SubCommentMapper;

@Repository
public class MyBatisSubCommentStore implements SubCommentStore {

	SubCommentMapper subCommentMapper;
	
	public MyBatisSubCommentStore(SubCommentMapper subCommentMapper) {
		
		this.subCommentMapper = subCommentMapper;
	}
	
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
	public void recommend(SubComment comment) {

		subCommentMapper.recommend(comment);
	}

	@Override
	public List<SubComment> retrieve(String goodsCode) {
		
		return subCommentMapper.retrieve(goodsCode);
	}

	@Override
	public List<SubComment> retrieveFilter(HashMap<String, String> filter) {

		return subCommentMapper.retrieveFilter(filter);
	}

	@Override
	public void delete(String orderId) {

		subCommentMapper.delete(orderId);
	}

	@Override
	public String retrieveGoodsCode(String orderId) {

		return subCommentMapper.retrieveGoodsCode(orderId);
	}

	@Override
	public CommentRecommend retrieveRecommend(CommentRecommend commentRecommend) {

		return subCommentMapper.retrieveRecommend(commentRecommend);
	}

	@Override
	public void insertRecommend(CommentRecommend commentRecommend) {
		
		subCommentMapper.insertRecommend(commentRecommend);
	}

	@Override
	public List<SubComment> retrievePhoto(String goodsCode) {
		
		return subCommentMapper.retrievePhoto(goodsCode);
	}

	@Override
	public SubComment retreiveSubComment(String orderId) {

		return subCommentMapper.retreiveSubComment(orderId);
	}

}
