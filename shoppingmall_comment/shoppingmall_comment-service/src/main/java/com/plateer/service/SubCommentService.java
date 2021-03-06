package com.plateer.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.plateer.domain.CommentRecommend;
import com.plateer.domain.SubComment;

public interface SubCommentService {

	void insertSubComment(SubComment comment);
	
	void modifySubComment(SubComment comment);
	
	void deleteSubComment(String orderId);
	
	void recommendComment(SubComment comment);
	
	List<SubComment> retrieveAll(String userId);
	
	List<SubComment> retrieve(String goodsCode);
	
	List<SubComment> retrievePhoto(String goodsCode);
	
	List<SubComment> retrieveFilteredComments(String goodsCode, String goodsOption, String orderByOption);
	
	Boolean retrieveRecommend(CommentRecommend commentRecommend);
	
	void insertRecommend(CommentRecommend commentRecommend);
	
	List<String> uploadFile(List<MultipartFile> files);
}
