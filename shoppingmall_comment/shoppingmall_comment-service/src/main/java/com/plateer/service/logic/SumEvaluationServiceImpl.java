package com.plateer.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plateer.domain.SumEvaluation;
import com.plateer.service.SumEvaluationService;
import com.plateer.store.SumEvaluationStore;

@Service
public class SumEvaluationServiceImpl implements SumEvaluationService {

	@Autowired
	SumEvaluationStore sumEvaluationStore;
	
	@Override
	public SumEvaluation retrieveSumEvaluation(String goodsCode) {

		return sumEvaluationStore.retrieve(goodsCode);
	}

}
