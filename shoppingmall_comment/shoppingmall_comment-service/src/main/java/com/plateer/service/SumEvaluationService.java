package com.plateer.service;

import org.springframework.stereotype.Service;

import com.plateer.domain.SumEvaluation;

@Service
public interface SumEvaluationService {

	SumEvaluation retrieveSumEvaluation(String goodsCode);
}
