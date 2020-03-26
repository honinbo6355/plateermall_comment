package com.plateer.store.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.plateer.domain.dto.CommentDto;

@Mapper
public interface CommentMapper {

	CommentDto retrieve(String goodsCode);

}
