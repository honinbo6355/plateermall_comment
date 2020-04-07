package com.plateer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSConfig {
	
	@Autowired
    private Environment environment;
    
	public AWSCredentials getBasicAWSCredentials() {
		return new BasicAWSCredentials(
				"AKIAUP6M7IIAXTP5ZOO4" ,"o1ke+7BaSgEo2ivG30OioZ4gXcsyg+clEK+/4qgq"
			);
	}

	@Bean
	public AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(getBasicAWSCredentials()))
				.withRegion("ap-northeast-2")
				.withForceGlobalBucketAccessEnabled(true)
				.build();
	}

}