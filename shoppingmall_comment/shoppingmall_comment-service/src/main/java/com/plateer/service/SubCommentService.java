package com.plateer.service;

import java.util.List;

import com.plateer.domain.SubComment;

public interface SubCommentService {

	void insertSubComment(SubComment comment);
	
	void modifySubComment(SubComment comment);
	
	void deleteSubComment(String orderId);
	
	void recommendComment(SubComment comment);
	
	List<SubComment> retrieveAll(String userId);
	
	List<SubComment> retrieve(String goodsCode);
	
	List<SubComment> retrieveFilteredComments(String goodsCode, String goodsOption, String orderByOption);
}
