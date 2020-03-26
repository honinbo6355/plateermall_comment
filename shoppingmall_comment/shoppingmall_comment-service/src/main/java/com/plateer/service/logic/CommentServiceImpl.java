package com.plateer.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plateer.domain.dto.CommentDto;
import com.plateer.service.CommentService;
import com.plateer.store.MyBatisCommentStore;
import com.plateer.store.MyBatisSubCommentStore;
import com.plateer.store.MyBatisSumEvaluationStore;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	MyBatisCommentStore myBatisCommentStore;
	
	@Autowired
	MyBatisSumEvaluationStore myBatisSumEvaluationStore;
	
	@Autowired
	MyBatisSubCommentStore myBatisSubCommentStore;
	
	@Override
	public CommentDto retrieveComment(String goodsCode) {
		CommentDto commentDto = myBatisCommentStore.retrieve(goodsCode);
		
		commentDto.setSumEvaluation(myBatisSumEvaluationStore.retrieve(goodsCode));
		commentDto.setCommentList(myBatisSubCommentStore.retrieve(goodsCode));
		
		return commentDto;
	}

}
