package com.plateer.domain.dto;

import java.util.List;

import com.plateer.domain.SubComment;
import com.plateer.domain.SumEvaluation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDto {

	private String goodsCode;
	private String uuid;
	private double averageStarPoint;
	private int customerCount;
	private SumEvaluation sumEvaluation;
	private List<SubComment> commentList;
}
