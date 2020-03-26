package com.plateer.store;

import com.plateer.domain.dto.CommentDto;

public interface CommentStore {

	CommentDto retrieve(String goodsCode);
	
	void modify(CommentDto comment);
}
