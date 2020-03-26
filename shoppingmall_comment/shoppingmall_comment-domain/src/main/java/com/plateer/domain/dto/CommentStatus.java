package com.plateer.domain.dto;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentStatus {

	private String orderId;
	private String userId;
	private String isWritten;
}
