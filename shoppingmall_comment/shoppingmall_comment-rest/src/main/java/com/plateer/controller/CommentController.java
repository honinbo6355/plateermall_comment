package com.plateer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plateer.domain.SubComment;
import com.plateer.domain.dto.CommentDto;
import com.plateer.service.logic.CommentServiceImpl;
import com.plateer.service.logic.CommentStatusServiceImpl;
import com.plateer.service.logic.SubCommentServiceImpl;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(allowCredentials = "true", origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT},
allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
        "Access-Control-Request-Headers", "Access-Control-Allow-Origin", "Set-Cookie"},
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
	
	@GetMapping("getwrittencomment/{purchaseCode}")
	public SubComment getWrittenComment(@PathVariable("purchaseCode") String purchaseCode) {
		
		return new SubComment("1231", "1203973748", "testId", "사이즈선택:235", "", 1, 30, 2, 2, 2, 5, "발 볼이 생각보다 좁아서 아프네요. 사이즈는 5mm정도 작게 나온 것 같아요.", "2020-03-24");
	}
	
	@GetMapping("getunwrittenorderid/{userId}")
	public List<String> getUnWrittenComment(@PathVariable("userId") String userId){
		
		return commentStatusServiceImpl.retrieveOrderIdList(userId);
	}
	
	@PostMapping
	public void addComment(@RequestBody SubComment comment) {
		
		//subCommentServiceImpl.insertSubComment(new SubComment("1233", "1203973748", "testId", "사이즈선택:2", "", 1, 30, 2, 2, 2, 5, "발 볼이 생각보다 좁아서 아프네요. 사이즈는 5mm정도 작게 나온 것 같아요.", "2020-03-24"));
		//subCommentServiceImpli.insertSubComment(new SubComment("1232", "1203973748", "testId", "사이즈선택:245", "", 1, 30, 2, 2, 2, 5, "테스트입니다", "2020-03-24"));
		System.out.println(comment);
	}
	
	@PutMapping
	public void modifyComment(@RequestBody SubComment comment) {
		
		subCommentServiceImpl.modifySubComment(comment);
	}
	
	@PutMapping("recommendation")
	public void recommendComment(@RequestBody SubComment comment) {
		
		subCommentServiceImpl.recommendComment(comment);
	}
	
	@GetMapping("getfiltergoodsoption/{goodsCode}/{goodsOption}/{orderByOption}")
	public List<SubComment> getFilterGoodsOption(@PathVariable("goodsCode") String goodsCode, @PathVariable("goodsOption") String goodsOption,
			@PathVariable("orderByOption") String orderByOption){
		
		return subCommentServiceImpl.retrieveFilteredComments(goodsCode, goodsOption, orderByOption);
		
//		List<SubComment> myCommentList = new ArrayList<>();
//		
//		myCommentList.add(new SubComment("1231", "1203973748", "상품옵션테스트입니다", "사이즈선택:235", "", 1, 30, 2, 2, 2, 5, "테스트입니다", "2020-03-24"));
//		myCommentList.add(new SubComment("2555", "1203973748", "상품옵션테스트입니다2", "사이즈선택:245", "", 1, 20, 1, 1, 1, 3, "테스트에용", "2020-03-24"));
//		
	}

}
