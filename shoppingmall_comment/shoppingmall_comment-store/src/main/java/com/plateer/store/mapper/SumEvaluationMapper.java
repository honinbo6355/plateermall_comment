package com.plateer.store.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.plateer.domain.SumEvaluation;

@Mapper
public interface SumEvaluationMapper {

	void modify(SumEvaluation sumEvaluation);
	
	SumEvaluation retrieve(String goodsCode);
}
