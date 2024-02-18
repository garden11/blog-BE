package com.name.blog.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.SdkClientException;
//import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

// AWS 사용 시 주석 해제
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/* 참조: https://devlog-wjdrbs96.tistory.com/m/323 */
// AWS 사용 시 주석 해제
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class S3FileUploader {
//    public static final String ORIGINAL_FILE_NAME_KEY = "originalName";
//    public static final String FILE_NAME_KEY = "name";
//    public static final String URI_KEY = "uri";
//
//    private final AmazonS3 amazonS3;
//
//    @Value("${cloud.aws.s3.bucket}")
//    public String bucket;  // S3 버킷 이름
//
//    // Multipart를 통해 전송된 파일을 업로드 하는 메소드
//	public Map<String, Object> uploadFile(MultipartFile file) {
//		Map<String, Object> result = new HashMap<>();
//
//		UUID uuid = UUID.randomUUID();
//
//		String originalFileName = file.getOriginalFilename();
//        //원본 파일 이름에서 확장자 추출
//		String fileNameExtension = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
//        //uuid와 확장자를 결합하여 새로운 파일 이름 생성
//		String changedFileName = uuid.toString() + fileNameExtension;
//
//		ObjectMetadata objectMetadata = new ObjectMetadata();
//		objectMetadata.setContentLength(file.getSize());
//		objectMetadata.setContentType(file.getContentType());
//
//		try (InputStream inputStream = file.getInputStream()) {
//			amazonS3.putObject(new PutObjectRequest(bucket, changedFileName, inputStream, objectMetadata));
//		} catch (IOException e) {
//			throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생하였습니다 (%s)", file.getOriginalFilename()));
//		}
//
//        result.put(FILE_NAME_KEY, changedFileName);
//		result.put(ORIGINAL_FILE_NAME_KEY, originalFileName);
//		result.put(URI_KEY, amazonS3.getUrl(bucket, changedFileName).toString());
//
//		return result;
//	}
//
//	public void deleteFiles(List<String> fileNameList) {
//		ArrayList<KeyVersion> keyList = new ArrayList<KeyVersion>();
//
//		for(String fileName : fileNameList) {
//			keyList.add(new KeyVersion(fileName));
//		}
//
//		try{
//			DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucket)
//					.withKeys(keyList)
//					.withQuiet(false);
//
//			amazonS3.deleteObjects(deleteObjectsRequest);
//
//		} catch(AmazonServiceException error) {
//			error.printStackTrace();
//
//			throw  error;
//		} catch (SdkClientException error) {
//			error.printStackTrace();
//
//			throw error;
//		}
//	}
//}
