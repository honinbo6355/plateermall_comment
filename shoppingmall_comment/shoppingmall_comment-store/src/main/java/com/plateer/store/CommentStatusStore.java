package com.plateer.store;

import java.util.List;

import com.plateer.domain.CommentStatus;

public interface CommentStatusStore {

	List<String> retrieve(String userId);

	void insert(CommentStatus status);

	void modify(String orderId);
}
