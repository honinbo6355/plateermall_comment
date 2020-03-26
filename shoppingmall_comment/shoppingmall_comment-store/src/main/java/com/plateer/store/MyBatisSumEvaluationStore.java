package com.plateer.store;

import org.springframework.stereotype.Repository;

import com.plateer.domain.SumEvaluation;
import com.plateer.store.mapper.SumEvaluationMapper;

@Repository
public class MyBatisSumEvaluationStore implements SumEvaluationStore {

	SumEvaluationMapper sumEvaluationMapper;
	
	public MyBatisSumEvaluationStore(SumEvaluationMapper sumEvaluationMapper) {
		
		this.sumEvaluationMapper = sumEvaluationMapper;
	}
	
	@Override
	public SumEvaluation retrieve(String goodsCode) {
		System.out.println("mapper");
		
		return sumEvaluationMapper.retrieve(goodsCode);
	}

	@Override
	public void modify(SumEvaluation sumEvaluation) {
		
		sumEvaluationMapper.modify(sumEvaluation);
	}

}
