package com.plateer.service.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plateer.domain.SubComment;
import com.plateer.domain.dto.CommentDto;
import com.plateer.service.CommentService;
import com.plateer.store.MyBatisCommentStore;
import com.plateer.store.MyBatisSubCommentStore;
import com.plateer.store.MyBatisSumEvaluationStore;

@Service
public class CommentServiceImpl implements CommentService {

	MyBatisCommentStore myBatisCommentStore;
	
	MyBatisSumEvaluationStore myBatisSumEvaluationStore;
	
	MyBatisSubCommentStore myBatisSubCommentStore;
	
	public CommentServiceImpl(MyBatisCommentStore myBatisCommentStore, MyBatisSumEvaluationStore myBatisSumEvaluationStore, MyBatisSubCommentStore myBatisSubCommentStore) {

		this.myBatisCommentStore = myBatisCommentStore;
		this.myBatisSumEvaluationStore = myBatisSumEvaluationStore;
		this.myBatisSubCommentStore = myBatisSubCommentStore;
	}
	
	@Override
	public CommentDto retrieveComment(String goodsCode) {
		CommentDto commentDto = myBatisCommentStore.retrieve(goodsCode);
		commentDto.setSumEvaluation(myBatisSumEvaluationStore.retrieve(goodsCode));
		List<SubComment> commentList = myBatisSubCommentStore.retrieve(goodsCode);
		
		for(SubComment comment : commentList) {
			String userId = comment.getUserId();
			String encodedId = "";
			
			for(int i=0; i< 3; i++) {
				encodedId += userId.charAt(i);
			}
			
			for(int i=4; i<userId.length(); i++) {
				encodedId += '*';
			}
			
			comment.setUserId(encodedId);
		}
		
		commentDto.setCommentList(commentList);
		
		return commentDto;
	}

}
