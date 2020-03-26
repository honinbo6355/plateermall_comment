package com.plateer.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plateer.domain.SubComment;
import com.plateer.domain.SumEvaluation;
import com.plateer.domain.dto.CommentDto;
import com.plateer.service.SubCommentService;
import com.plateer.store.MyBatisCommentStatusStore;
import com.plateer.store.MyBatisCommentStore;
import com.plateer.store.MyBatisSubCommentStore;
import com.plateer.store.MyBatisSumEvaluationStore;
import com.plateer.store.SubCommentStore;
import com.plateer.store.mapper.SubCommentMapper;

@Service
@Transactional
public class SubCommentServiceImpl implements SubCommentService {

	@Autowired
	MyBatisSubCommentStore myBatisSubCommentStore;
	
	@Autowired
	MyBatisSumEvaluationStore myBatisSumEvaluationStore;
	
	@Autowired
	MyBatisCommentStore myBatisCommentStore;
	
	@Autowired
	MyBatisCommentStatusStore myBatisCommentStatusStore;
	
	@Override
	public void insertSubComment(SubComment comment) {

		//상품평 추가
		myBatisSubCommentStore.insert(comment);
		
		//구매여부 변경
		myBatisCommentStatusStore.modify(comment.getOrderId());
		
		SumEvaluation sumEvaluation = myBatisSumEvaluationStore.retrieve(comment.getGoodsCode());
		CommentDto commentDto = myBatisCommentStore.retrieve(comment.getGoodsCode());
		
		List<SubComment> commentList = myBatisSubCommentStore.retrieve(comment.getGoodsCode());
		
		int deliveryCommon = 0;
		int deliveryBest = 0;
		int deliveryWorst = 0;
		
		int designCommon = 0;
		int designBest = 0;
		int designWorst = 0;
		
		int sizeCommon = 0;
		int sizeBest = 0;
		int sizeWorst = 0;
		
		int newStarPoint = 0;
		
		for(int i=0; i<commentList.size(); i++) {
			
			if(commentList.get(i).getDeliveryValue() == 1) {
				deliveryCommon++;
			}else if(commentList.get(i).getDeliveryValue() == 2) {
				deliveryBest++;
			}else {
				deliveryWorst++;
			}
			
			if(commentList.get(i).getDesignValue() == 1) {
				designCommon++;
			}else if(commentList.get(i).getDesignValue() == 2) {
				designBest++;
			}else {
				designWorst++;
			}
			
			if(commentList.get(i).getSizeValue() == 1) {
				sizeCommon++;
			}else if(commentList.get(i).getSizeValue() == 2) {
				sizeBest++;
			}else {
				sizeWorst++;
			}
			
			newStarPoint += commentList.get(i).getStarPoint();
		}
		
		commentDto.setCustomerCount(commentDto.getCustomerCount()+1);
		
		sumEvaluation.setDeliveryCommon((deliveryCommon*100)/commentDto.getCustomerCount());
		sumEvaluation.setDeliveryBest((deliveryBest*100)/commentDto.getCustomerCount());
		sumEvaluation.setDeliveryWorst((deliveryWorst*100)/commentDto.getCustomerCount());
		
		sumEvaluation.setDesignCommon((designCommon*100)/commentDto.getCustomerCount());
		sumEvaluation.setDesignBest((designBest*100)/commentDto.getCustomerCount());
		sumEvaluation.setDesignWorst((designWorst*100)/commentDto.getCustomerCount());
		
		sumEvaluation.setSizeCommon((sizeCommon*100)/commentDto.getCustomerCount());
		sumEvaluation.setSizeBest((sizeBest*100)/commentDto.getCustomerCount());
		sumEvaluation.setSizeWorst((sizeWorst*100)/commentDto.getCustomerCount());
		
		commentDto.setAverageStarPoint(newStarPoint/commentDto.getCustomerCount());
		
		//전체평점 수정
		myBatisCommentStore.modify(commentDto);
		myBatisSumEvaluationStore.modify(sumEvaluation);
		
	}

	@Override
	public void modifySubComment(SubComment comment) {
		
		myBatisSubCommentStore.modify(comment);
		
		SumEvaluation sumEvaluation = myBatisSumEvaluationStore.retrieve(comment.getGoodsCode());
		CommentDto commentDto = myBatisCommentStore.retrieve(comment.getGoodsCode());
		
		List<SubComment> commentList = myBatisSubCommentStore.retrieve(comment.getGoodsCode());
		
		int deliveryCommon = 0;
		int deliveryBest = 0;
		int deliveryWorst = 0;
		
		int designCommon = 0;
		int designBest = 0;
		int designWorst = 0;
		
		int sizeCommon = 0;
		int sizeBest = 0;
		int sizeWorst = 0;
		
		int newStarPoint = 0;
		
		for(int i=0; i<commentList.size(); i++) {
			
			if(commentList.get(i).getDeliveryValue() == 1) {
				deliveryCommon++;
			}else if(commentList.get(i).getDeliveryValue() == 2) {
				deliveryBest++;
			}else {
				deliveryWorst++;
			}
			
			if(commentList.get(i).getDesignValue() == 1) {
				designCommon++;
			}else if(commentList.get(i).getDesignValue() == 2) {
				designBest++;
			}else {
				designWorst++;
			}
			
			if(commentList.get(i).getSizeValue() == 1) {
				sizeCommon++;
			}else if(commentList.get(i).getSizeValue() == 2) {
				sizeBest++;
			}else {
				sizeWorst++;
			}
			
			newStarPoint += commentList.get(i).getStarPoint();
		}
		
		sumEvaluation.setDeliveryCommon((deliveryCommon*100)/commentDto.getCustomerCount());
		sumEvaluation.setDeliveryBest((deliveryBest*100)/commentDto.getCustomerCount());
		sumEvaluation.setDeliveryWorst((deliveryWorst*100)/commentDto.getCustomerCount());
		
		sumEvaluation.setDesignCommon((designCommon*100)/commentDto.getCustomerCount());
		sumEvaluation.setDesignBest((designBest*100)/commentDto.getCustomerCount());
		sumEvaluation.setDesignWorst((designWorst*100)/commentDto.getCustomerCount());
		
		sumEvaluation.setSizeCommon((sizeCommon*100)/commentDto.getCustomerCount());
		sumEvaluation.setSizeBest((sizeBest*100)/commentDto.getCustomerCount());
		sumEvaluation.setSizeWorst((sizeWorst*100)/commentDto.getCustomerCount());
		
		commentDto.setAverageStarPoint(newStarPoint/commentDto.getCustomerCount());
		
		myBatisCommentStore.modify(commentDto);
		myBatisSumEvaluationStore.modify(sumEvaluation);
		
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
