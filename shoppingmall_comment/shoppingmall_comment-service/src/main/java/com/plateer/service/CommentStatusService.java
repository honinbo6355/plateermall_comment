package com.plateer.service;

import java.util.List;

import com.plateer.domain.CommentStatus;

public interface CommentStatusService {

	List<String> retrieveOrderIdList(String userId);
	
	void insertCommentStatus(CommentStatus status);
	
	void updateCommentStatus(String orderId);
	
}
