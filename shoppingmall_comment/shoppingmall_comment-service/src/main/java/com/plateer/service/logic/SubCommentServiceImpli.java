package com.plateer.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plateer.domain.SubComment;
import com.plateer.service.SubCommentService;
import com.plateer.store.MyBatisSubCommentStore;
import com.plateer.store.SubCommentStore;
import com.plateer.store.mapper.SubCommentMapper;

@Service
public class SubCommentServiceImpli implements SubCommentService {

	@Autowired
	MyBatisSubCommentStore myBatisSubCommentStore;
	
	@Override
	public void insertSubComment(SubComment comment) {

		myBatisSubCommentStore.insert(comment);
	}

	@Override
	public void modifySubComment(SubComment comment) {
		
		myBatisSubCommentStore.modify(comment);
	}

	@Override
	public void deleteSubComment() {

	}

	@Override
	public void recommendComment(SubComment comment) {
	
		myBatisSubCommentStore.recommend(comment);
	}

	@Override
	public List<SubComment> retrieveAll(String userId) {
		
		return myBatisSubCommentStore.retrieveAll(userId);
	}

	@Override
	public List<SubComment> retrieve(String goodsCode) {
		
		return myBatisSubCommentStore.retrieve(goodsCode);
	}

}
