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

import com.plateer.domain.dto.CommentDto;
import com.plateer.domain.dto.SubCommentDto;
import com.plateer.domain.dto.SumEvaluationDto;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(allowCredentials = "true", origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT},
allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
        "Access-Control-Request-Headers", "Access-Control-Allow-Origin", "Set-Cookie"},
exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"}, maxAge = 3000)
public class CommentController {

//	@GetMapping
//	public CommentDto getCommentDto() {
//		
//		return new CommentDto("testId", "name");
//	}
	
	@GetMapping("getcommentlist/{goodsCode}")
	public CommentDto getComment(@PathVariable("goodsCode") String goodsCode){
		SumEvaluationDto sumEvaluation = new SumEvaluationDto(60, 0, 0, 20, 50, 0, 20, 30, 40);
		List<SubCommentDto> commentList = new ArrayList<>();
		
		commentList.add(new SubCommentDto("1231", goodsCode, "testId", "사이즈선택:235", "", 1, 30, 2, 2, 2, 5, "발 볼이 생각보다 좁아서 아프네요. 사이즈는 5mm정도 작게 나온 것 같아요.", "2020-03-24"));
		commentList.add(new SubCommentDto("25552", goodsCode, "testuser", "사이즈선택:245", "", 1, 20, 1, 1, 1, 3, "흠 전 잘 모르겠어요.", "2020-03-24"));
		
		CommentDto comment = new CommentDto("1203973748", "uuid", 4, 2, sumEvaluation, commentList);
		
		return comment;
	}
	
	@GetMapping("getmycomment/{userId}")
	public List<SubCommentDto> getMyComment(@PathVariable("userId") String userId){
		
		List<SubCommentDto> myCommentList = new ArrayList<>();
		
		myCommentList.add(new SubCommentDto("1231", "1203973748", "testId", "사이즈선택:235", "", 1, 30, 2, 2, 2, 5, "발 볼이 생각보다 좁아서 아프네요. 사이즈는 5mm정도 작게 나온 것 같아요.", "2020-03-24"));
		myCommentList.add(new SubCommentDto("2555", "1203973748", "testId", "사이즈선택:245", "", 1, 20, 1, 1, 1, 3, "흠 전 잘 모르겠어요.ㅠㅠ", "2020-03-24"));
		
		return myCommentList;
	}
	
	@GetMapping("getwrittencomment/{purchaseCode}")
	public SubCommentDto getWrittenComment(@PathVariable("purchaseCode") String purchaseCode) {
		
		return new SubCommentDto("1231", "1203973748", "testId", "사이즈선택:235", "", 1, 30, 2, 2, 2, 5, "발 볼이 생각보다 좁아서 아프네요. 사이즈는 5mm정도 작게 나온 것 같아요.", "2020-03-24");
	}
	
	@GetMapping("getunwrittenorderid/{userId}")
	public List<String> getUnWrittenComment(@PathVariable("userId") String userId){
		
		List<String> orderIdList = new ArrayList<>();
		orderIdList.add("1234");
		orderIdList.add("1235");
		
		return orderIdList;
	}
	
	@PostMapping
	public void addComment(@RequestBody SubCommentDto comment) {
		System.out.println(comment);
	}
	
	@PutMapping
	public void modifyComment(@RequestBody SubCommentDto comment) {
		System.out.println(comment);
	}
	
	@PutMapping("recommendation")
	public void recommendComment(@RequestBody SubCommentDto comment) {
		System.out.println(comment);
	}
	
	@GetMapping("getfiltergoodsoption/{goodsCode}/{goodsOption}/{orderByOption}")
	public List<SubCommentDto> getFilterGoodsOption(@PathVariable("goodsCode") String goodsCode, @PathVariable("goodsOption") String goodsOption,
			@PathVariable("orderByOption") String orderByOption){
		
		List<SubCommentDto> myCommentList = new ArrayList<>();
		
		myCommentList.add(new SubCommentDto("1231", "1203973748", "상품옵션테스트입니다", "사이즈선택:235", "", 1, 30, 2, 2, 2, 5, "테스트입니다", "2020-03-24"));
		myCommentList.add(new SubCommentDto("2555", "1203973748", "상품옵션테스트입니다2", "사이즈선택:245", "", 1, 20, 1, 1, 1, 3, "테스트에용", "2020-03-24"));
		
		return myCommentList;
	}

}
