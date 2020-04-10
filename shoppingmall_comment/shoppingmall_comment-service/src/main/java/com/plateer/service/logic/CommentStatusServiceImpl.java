package com.plateer.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plateer.domain.CommentStatus;
import com.plateer.service.CommentStatusService;
import com.plateer.store.MyBatisCommentStatusStore;

@Service
public class CommentStatusServiceImpl implements CommentStatusService {

	@Autowired
	MyBatisCommentStatusStore myBatisCommentStatusStore;
	
	@Override
	public List<String> retrieveOrderIdList(String userId) {

		return myBatisCommentStatusStore.retrieve(userId);
	}

	@Override
	public void insertCommentStatus(CommentStatus status) {

		myBatisCommentStatusStore.insert(status);
	}

	@Override
	public void updateCommentStatus(String orderId) {

		myBatisCommentStatusStore.modify(orderId);
	}

	@Override
	public void deleteCommentStatus(String orderId) {
		
		myBatisCommentStatusStore.delete(orderId);
	}
	
	

}
