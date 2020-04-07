package com.plateer.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plateer.domain.CommentRecommend;
import com.plateer.domain.CommentStatus;
import com.plateer.domain.SubComment;
import com.plateer.domain.dto.CommentDto;
import com.plateer.service.logic.CommentServiceImpl;
import com.plateer.service.logic.CommentStatusServiceImpl;
import com.plateer.service.logic.SubCommentServiceImpl;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(allowCredentials = "true", origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT},
allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
        "Access-Control-Request-Headers", "Access-Control-Allow-Origin", "Set-Cookie", "Authorization"},
exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"}, maxAge = 3000)
public class CommentController {
	
	CommentServiceImpl commentServiceImpl;
	
	SubCommentServiceImpl subCommentServiceImpl;
	
	CommentStatusServiceImpl commentStatusServiceImpl;
	
	public CommentController(CommentServiceImpl commentServiceImpl, SubCommentServiceImpl subCommentServiceImpl, CommentStatusServiceImpl commentStatusServiceImpl) {
	
		this.commentServiceImpl = commentServiceImpl;
		this.subCommentServiceImpl = subCommentServiceImpl;
		this.commentStatusServiceImpl = commentStatusServiceImpl;
	}
	
	@GetMapping("getcommentlist/{goodsCode}")
	public CommentDto getComment(@PathVariable("goodsCode") String goodsCode){
		
		return commentServiceImpl.retrieveComment(goodsCode);
	}
	
	@GetMapping("getmycomment/{userId}")
	public List<SubComment> getMyComment(@PathVariable("userId") String userId){
		
		return subCommentServiceImpl.retrieveAll(userId);
	}
	
	@GetMapping("getunwrittenorderid/{userId}")
	public List<String> getUnWrittenComment(@PathVariable("userId") String userId){
		
		return commentStatusServiceImpl.retrieveOrderIdList(userId);
	}
	
	@PostMapping
	public void addComment(@RequestBody SubComment comment) {
		
		subCommentServiceImpl.insertSubComment(comment);
	}
	
	@PutMapping
	public void modifyComment(@RequestBody SubComment comment) {
		
		System.out.println(comment);
		subCommentServiceImpl.modifySubComment(comment);
	}
	
	@PutMapping("recommendation")
	public void recommendComment(@RequestBody SubComment comment) {
		
		subCommentServiceImpl.recommendComment(comment);
	}
	
	@GetMapping("getfiltergoodsoption/{goodsCode}/{goodsOption}/{orderByOption}")
	public List<SubComment> getFilterGoodsOption(@PathVariable("goodsCode") String goodsCode, @PathVariable("goodsOption") String goodsOption,
			@PathVariable("orderByOption") String orderByOption) throws UnsupportedEncodingException{
		
		return subCommentServiceImpl.retrieveFilteredComments(goodsCode, goodsOption, orderByOption);
	}
	
	@DeleteMapping("/{orderId}")
	public void deleteComment(@PathVariable("orderId") String orderId) {
		
		subCommentServiceImpl.deleteSubComment(orderId);
	}

	@PostMapping("/addcommentstatus")
	public void addCommentStatus(@RequestBody CommentStatus status) {
		
		commentStatusServiceImpl.insertCommentStatus(status);
	}
	
	@GetMapping("/isrecommend/{orderId}/{email}")
	public Boolean isRecommend(@PathVariable("orderId") String orderId, @PathVariable("email") String email) {
		
		CommentRecommend recommend = new CommentRecommend(orderId, email);
		return subCommentServiceImpl.retrieveRecommend(recommend);
	}
	
	@PostMapping("/addrecommend")
	public void addRecommend(@RequestBody CommentRecommend commentRecommend) {
		
		subCommentServiceImpl.insertRecommend(commentRecommend);
	}
	
	@GetMapping("/getphotolist/{goodsCode}")
	public List<SubComment> getPhotoList(@PathVariable("goodsCode") String goodsCode){
		
		return subCommentServiceImpl.retrievePhoto(goodsCode);
	}
}
