package com.plateer.store;

import java.util.HashMap;
import java.util.List;
import com.plateer.domain.SubComment;

public interface SubCommentStore {

	List<SubComment> retrieveAll(String userId);

	void insert(SubComment comment);

	void modify(SubComment comment);

	void delete();

	void recommend(SubComment comment);

	List<SubComment> retrieve(String goodsCode);

	List<SubComment> retrieveFilter(HashMap<String, String> filter);
}
