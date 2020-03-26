package com.plateer.domain;

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
public class SubComment {

	private String orderId;
	private String goodsCode;
	private String userId;
	private String selectedOptions;
	private String myPhoto;
	private int quantity;
	private int recommendCount;
	private int deliveryValue;
	private int designValue;
	private int sizeValue;
	private int starPoint;
	private String reviewContent;
	private String writtenDate;
}
