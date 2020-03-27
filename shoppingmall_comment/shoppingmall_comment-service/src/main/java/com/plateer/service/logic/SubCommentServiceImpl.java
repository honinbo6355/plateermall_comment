
package com.plateer.service.logic;

import java.util.HashMap;
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

@Service
@Transactional
public class SubCommentServiceImpl implements SubCommentService {

	MyBatisSubCommentStore myBatisSubCommentStore;

	MyBatisSumEvaluationStore myBatisSumEvaluationStore;

	MyBatisCommentStore myBatisCommentStore;

	MyBatisCommentStatusStore myBatisCommentStatusStore;

	public SubCommentServiceImpl(MyBatisSubCommentStore myBatisSubCommentStore,
			MyBatisSumEvaluationStore myBatisSumEvaluationStore, MyBatisCommentStore myBatisCommentStore,
			MyBatisCommentStatusStore myBatisCommentStatusStore) {

		this.myBatisSubCommentStore = myBatisSubCommentStore;
		this.myBatisSumEvaluationStore = myBatisSumEvaluationStore;
		this.myBatisCommentStore = myBatisCommentStore;
		this.myBatisCommentStatusStore = myBatisCommentStatusStore;
	}

	@Override
	public void insertSubComment(SubComment comment) {

		// 상품평 추가
		myBatisSubCommentStore.insert(comment);

		// 구매여부 변경
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

		for (int i = 0; i < commentList.size(); i++) {

			if (commentList.get(i).getDeliveryValue() == 1) {
				deliveryCommon++;
			} else if (commentList.get(i).getDeliveryValue() == 2) {
				deliveryBest++;
			} else {
				deliveryWorst++;
			}

			if (commentList.get(i).getDesignValue() == 1) {
				designCommon++;
			} else if (commentList.get(i).getDesignValue() == 2) {
				designBest++;
			} else {
				designWorst++;
			}

			if (commentList.get(i).getSizeValue() == 1) {
				sizeCommon++;
			} else if (commentList.get(i).getSizeValue() == 2) {
				sizeBest++;
			} else {
				sizeWorst++;
			}

			newStarPoint += commentList.get(i).getStarPoint();
		}

		sumEvaluation.setDeliveryCommon((deliveryCommon * 100) / commentList.size());
		sumEvaluation.setDeliveryBest((deliveryBest * 100) / commentList.size());
		sumEvaluation.setDeliveryWorst((deliveryWorst * 100) / commentList.size());

		sumEvaluation.setDesignCommon((designCommon * 100) / commentList.size());
		sumEvaluation.setDesignBest((designBest * 100) / commentList.size());
		sumEvaluation.setDesignWorst((designWorst * 100) / commentList.size());

		sumEvaluation.setSizeCommon((sizeCommon * 100) / commentList.size());
		sumEvaluation.setSizeBest((sizeBest * 100) / commentList.size());
		sumEvaluation.setSizeWorst((sizeWorst * 100) / commentList.size());

		commentDto.setCustomerCount(commentDto.getCustomerCount() + 1);
		commentDto.setAverageStarPoint(newStarPoint / commentDto.getCustomerCount());

		// 전체평점 수정
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

		for (int i = 0; i < commentList.size(); i++) {

			if (commentList.get(i).getDeliveryValue() == 1) {
				deliveryCommon++;
			} else if (commentList.get(i).getDeliveryValue() == 2) {
				deliveryBest++;
			} else {
				deliveryWorst++;
			}

			if (commentList.get(i).getDesignValue() == 1) {
				designCommon++;
			} else if (commentList.get(i).getDesignValue() == 2) {
				designBest++;
			} else {
				designWorst++;
			}

			if (commentList.get(i).getSizeValue() == 1) {
				sizeCommon++;
			} else if (commentList.get(i).getSizeValue() == 2) {
				sizeBest++;
			} else {
				sizeWorst++;
			}

			newStarPoint += commentList.get(i).getStarPoint();
		}

		sumEvaluation.setDeliveryCommon((deliveryCommon * 100) / commentList.size());
		sumEvaluation.setDeliveryBest((deliveryBest * 100) / commentList.size());
		sumEvaluation.setDeliveryWorst((deliveryWorst * 100) / commentList.size());

		sumEvaluation.setDesignCommon((designCommon * 100) / commentList.size());
		sumEvaluation.setDesignBest((designBest * 100) / commentList.size());
		sumEvaluation.setDesignWorst((designWorst * 100) / commentList.size());

		sumEvaluation.setSizeCommon((sizeCommon * 100) / commentList.size());
		sumEvaluation.setSizeBest((sizeBest * 100) / commentList.size());
		sumEvaluation.setSizeWorst((sizeWorst * 100) / commentList.size());

		commentDto.setAverageStarPoint(newStarPoint / commentDto.getCustomerCount());

		myBatisCommentStore.modify(commentDto);
		myBatisSumEvaluationStore.modify(sumEvaluation);

	}

	@Override
	public void deleteSubComment(String orderId) {
		
		myBatisSubCommentStore.delete(orderId);
		
		//삭제하면 별점바꾸는거 꼭 하자! 내눈물들이 내게따지듯이 내겐 너를 사랑할 자격도 없다 해.. 우~~` 못난 내사랑아~~~고작 이것밖에 못하겠니?
//		SumEvaluation sumEvaluation = myBatisSumEvaluationStore.retrieve(comment.getGoodsCode());
//		CommentDto commentDto = myBatisCommentStore.retrieve(comment.getGoodsCode());
//
//		List<SubComment> commentList = myBatisSubCommentStore.retrieve(comment.getGoodsCode());
//
//		int deliveryCommon = 0;
//		int deliveryBest = 0;
//		int deliveryWorst = 0;
//
//		int designCommon = 0;
//		int designBest = 0;
//		int designWorst = 0;
//
//		int sizeCommon = 0;
//		int sizeBest = 0;
//		int sizeWorst = 0;
//
//		int newStarPoint = 0;
//
//		for (int i = 0; i < commentList.size(); i++) {
//
//			if (commentList.get(i).getDeliveryValue() == 1) {
//				deliveryCommon++;
//			} else if (commentList.get(i).getDeliveryValue() == 2) {
//				deliveryBest++;
//			} else {
//				deliveryWorst++;
//			}
//
//			if (commentList.get(i).getDesignValue() == 1) {
//				designCommon++;
//			} else if (commentList.get(i).getDesignValue() == 2) {
//				designBest++;
//			} else {
//				designWorst++;
//			}
//
//			if (commentList.get(i).getSizeValue() == 1) {
//				sizeCommon++;
//			} else if (commentList.get(i).getSizeValue() == 2) {
//				sizeBest++;
//			} else {
//				sizeWorst++;
//			}
//
//			newStarPoint += commentList.get(i).getStarPoint();
//		}
//
//		sumEvaluation.setDeliveryCommon((deliveryCommon * 100) / commentList.size());
//		sumEvaluation.setDeliveryBest((deliveryBest * 100) / commentList.size());
//		sumEvaluation.setDeliveryWorst((deliveryWorst * 100) / commentList.size());
//
//		sumEvaluation.setDesignCommon((designCommon * 100) / commentList.size());
//		sumEvaluation.setDesignBest((designBest * 100) / commentList.size());
//		sumEvaluation.setDesignWorst((designWorst * 100) / commentList.size());
//
//		sumEvaluation.setSizeCommon((sizeCommon * 100) / commentList.size());
//		sumEvaluation.setSizeBest((sizeBest * 100) / commentList.size());
//		sumEvaluation.setSizeWorst((sizeWorst * 100) / commentList.size());
//
//		commentDto.setAverageStarPoint(newStarPoint / commentDto.getCustomerCount());
//
//		myBatisCommentStore.modify(commentDto);
//		myBatisSumEvaluationStore.modify(sumEvaluation);

		
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

	@Override
	public List<SubComment> retrieveFilteredComments(String goodsCode, String goodsOption, String orderByOption) {

		HashMap<String, String> filter = new HashMap<>();
		filter.put("goodsCode", goodsCode);
		filter.put("goodsOption", goodsOption);
		filter.put("orderByOption", orderByOption);

		return myBatisSubCommentStore.retrieveFilter(filter);
	}
	
}
