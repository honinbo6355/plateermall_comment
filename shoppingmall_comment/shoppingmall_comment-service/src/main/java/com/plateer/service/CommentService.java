package com.plateer.service;

import org.springframework.stereotype.Service;

import com.plateer.domain.dto.CommentDto;

@Service
public interface CommentService {

	CommentDto retrieveComment(String goodsCode);
}
