package com.plateer.service.config;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

@Service
public class S3Client {
	private static final String AWS_S3_BUCKET_NAME = "plateer-mall";
	private static final char FILE_SEPERATOR_CHAR = '/';
	private static final String PREFIX_FOLDER = "comments/";

	@Autowired
	private AmazonS3 amazonS3;

	public void createFolder(String folderName) {
		amazonS3.putObject(AWS_S3_BUCKET_NAME, folderName + "/", new ByteArrayInputStream(new byte[0]),
				new ObjectMetadata());
	}

	public PutObjectResult fileUpload(String fileName, byte[] fileData) throws FileNotFoundException {
		fileName = PREFIX_FOLDER + fileName;

		String filePath = fileName.replace(File.separatorChar, FILE_SEPERATOR_CHAR);
		ObjectMetadata metaData = new ObjectMetadata();

		metaData.setContentLength(fileData.length);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData);

		return amazonS3.putObject(new PutObjectRequest(AWS_S3_BUCKET_NAME, filePath, byteArrayInputStream, metaData).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	public PutObjectResult fileUpload(String fileName, File file) {
		fileName = PREFIX_FOLDER + fileName;

		String filePath = fileName.replace(File.separatorChar, FILE_SEPERATOR_CHAR);
		ObjectMetadata metaData = new ObjectMetadata();

		metaData.setContentLength(file.length());

		return amazonS3.putObject(new PutObjectRequest(AWS_S3_BUCKET_NAME, filePath, file));
	}

	public void fileDelete(String fileName) {
		String imgName = fileName.replace(File.separatorChar, FILE_SEPERATOR_CHAR);
		
		amazonS3.deleteObject(AWS_S3_BUCKET_NAME, imgName);
	}

	public String getFileURL(String fileName) {
		String imgName = fileName.replace(File.separatorChar, FILE_SEPERATOR_CHAR);
		return amazonS3.generatePresignedUrl(new GeneratePresignedUrlRequest(AWS_S3_BUCKET_NAME, imgName)).toString();
	}
}
