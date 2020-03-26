package com.plateer.store;

import com.plateer.domain.SumEvaluation;

public interface SumEvaluationStore {

	SumEvaluation retrieve(String goodsCode);
	
	void modify(SumEvaluation sumEvaluation);

}
