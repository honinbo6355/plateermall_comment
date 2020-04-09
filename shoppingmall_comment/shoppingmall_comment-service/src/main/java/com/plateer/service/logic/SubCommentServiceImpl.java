
package com.plateer.service.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.plateer.domain.CommentRecommend;
import com.plateer.domain.SubComment;
import com.plateer.domain.SumEvaluation;
import com.plateer.domain.dto.CommentDto;
import com.plateer.service.SubCommentService;
import com.plateer.service.config.S3Client;
import com.plateer.store.MyBatisCommentStatusStore;
import com.plateer.store.MyBatisCommentStore;
import com.plateer.store.MyBatisSubCommentStore;
import com.plateer.store.MyBatisSumEvaluationStore;

@Service
@Transactional
public class SubCommentServiceImpl implements SubCommentService {

	S3Client s3Client;
	
	MyBatisSubCommentStore myBatisSubCommentStore;

	MyBatisSumEvaluationStore myBatisSumEvaluationStore;

	MyBatisCommentStore myBatisCommentStore;

	MyBatisCommentStatusStore myBatisCommentStatusStore;

	public SubCommentServiceImpl(S3Client s3Client, MyBatisSubCommentStore myBatisSubCommentStore,
			MyBatisSumEvaluationStore myBatisSumEvaluationStore, MyBatisCommentStore myBatisCommentStore,
			MyBatisCommentStatusStore myBatisCommentStatusStore) {

		this.s3Client = s3Client;
		this.myBatisSubCommentStore = myBatisSubCommentStore;
		this.myBatisSumEvaluationStore = myBatisSumEvaluationStore;
		this.myBatisCommentStore = myBatisCommentStore;
		this.myBatisCommentStatusStore = myBatisCommentStatusStore;
	}

	@Override
	public void insertSubComment(SubComment comment) {

		if (comment.getMyPhoto2().equals(null)) {
			comment.setMyPhoto2("");
		}

		if (comment.getMyPhoto3().equals(null)) {
			comment.setMyPhoto("");
		}
		// 상품평 추가
		myBatisSubCommentStore.insert(comment);

		// 구매여부 변경
		myBatisCommentStatusStore.modify(comment.getOrderId());

		calculateComment(comment.getGoodsCode());

	}

	@Override
	public void modifySubComment(SubComment comment) {

		myBatisSubCommentStore.modify(comment);

		calculateComment(comment.getGoodsCode());
	}
	
	@Override
	public void deleteSubComment(String orderId) {

		String goodsCode = myBatisSubCommentStore.retrieveGoodsCode(orderId);
		SubComment comment = myBatisSubCommentStore.retreiveSubComment(orderId);

		if(!comment.getMyPhoto().equals("")) s3Client.fileDelete("comments/" + comment.getMyPhoto().substring(comment.getMyPhoto().lastIndexOf("/")+1));
		if(!comment.getMyPhoto2().equals("")) s3Client.fileDelete("comments/" + comment.getMyPhoto2().substring(comment.getMyPhoto2().lastIndexOf("/")+1));
		if(!comment.getMyPhoto3().equals("")) s3Client.fileDelete("comments/" + comment.getMyPhoto3().substring(comment.getMyPhoto3().lastIndexOf("/")+1));
		
		myBatisSubCommentStore.delete(orderId);
		calculateComment(goodsCode);
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

		List<SubComment> commentList = myBatisSubCommentStore.retrieveFilter(filter);

		for (SubComment comment : commentList) {
			String userId = comment.getUserId();
			String encodedId = "";

			for (int i = 0; i < 3; i++) {
				encodedId += userId.charAt(i);
			}

			for (int i = 4; i < userId.length(); i++) {
				encodedId += '*';
			}

			comment.setUserId(encodedId);
		}

		return commentList;
	}

	public void calculateComment(String goodsCode) {

		SumEvaluation sumEvaluation = myBatisSumEvaluationStore.retrieve(goodsCode);
		CommentDto commentDto = myBatisCommentStore.retrieve(goodsCode);

		List<SubComment> commentList = myBatisSubCommentStore.retrieve(goodsCode);

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

		if (commentList.size() != 0) {
			for (int i = 0; i < commentList.size(); i++) {

				if (commentList.get(i).getDeliveryValue().equals("1")) {
					deliveryCommon++;
				} else if (commentList.get(i).getDeliveryValue().equals("2")) {
					deliveryBest++;
				} else {
					deliveryWorst++;
				}

				if (commentList.get(i).getDesignValue().equals("1")) {
					designCommon++;
				} else if (commentList.get(i).getDesignValue().equals("2")) {
					designBest++;
				} else {
					designWorst++;
				}

				if (commentList.get(i).getSizeValue().equals("1")) {
					sizeCommon++;
				} else if (commentList.get(i).getSizeValue().equals("2")) {
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

			commentDto.setCustomerCount(commentList.size());
			commentDto.setAverageStarPoint(Math.round((newStarPoint / commentDto.getCustomerCount()) * 100) / 100);

		} else {
			sumEvaluation.setDeliveryBest(0);
			sumEvaluation.setDeliveryCommon(0);
			sumEvaluation.setDeliveryWorst(0);
			sumEvaluation.setDesignBest(0);
			sumEvaluation.setDeliveryCommon(0);
			sumEvaluation.setDesignWorst(0);
			sumEvaluation.setSizeBest(0);
			sumEvaluation.setSizeCommon(0);
			sumEvaluation.setSizeWorst(0);

			commentDto.setAverageStarPoint(0);
			commentDto.setCustomerCount(0);
		}

		myBatisCommentStore.modify(commentDto);
		myBatisSumEvaluationStore.modify(sumEvaluation);
	}

	@Override
	public Boolean retrieveRecommend(CommentRecommend commentRecommend) {

		CommentRecommend recommend = myBatisSubCommentStore.retrieveRecommend(commentRecommend);

		if (recommend == null)
			return true;
		else
			return false;
	}

	@Override
	public void insertRecommend(CommentRecommend commentRecommend) {

		myBatisSubCommentStore.insertRecommend(commentRecommend);
	}

	@Override
	public List<SubComment> retrievePhoto(String goodsCode) {

		return myBatisSubCommentStore.retrievePhoto(goodsCode);
	}

	@Override
	public List<String> uploadFile(List<MultipartFile> files) {
		List<String> list = new ArrayList<String>();

		for (MultipartFile file : files) {
			UUID uuid = UUID.randomUUID();
			String newName = uuid + "_" + file.getOriginalFilename();

			try {
				s3Client.fileUpload(newName, file.getBytes());
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			list.add("https://plateer-mall.s3.ap-northeast-2.amazonaws.com/comments/" + newName);
		}

		return list;
	}
}
