package com.name.blog.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Slf4j
@Component
public class LocalFileUploader {
	public static final String ORIGINAL_FILE_NAME_KEY = "originalName";
	public static final String CHANGED_FILE_NAME_KEY = "name";
	public static final String URI_KEY = "uri";

	public Map<String, Object> uploadFile(MultipartFile file, String uploadPath, String handlerPath) throws IOException {
		Map<String, Object> result = new HashMap<>();
		File fileUploadDirectory = new File(uploadPath);
		UUID uuid = UUID.randomUUID();
		
		String originalFileName = file.getOriginalFilename();

		//원본 파일 이름에서 확장자 추출
		String fileNameExtension = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
		//uuid와 확장자를 결합하여 새로운 파일 이름 생성
		String changedFileName = uuid.toString() + fileNameExtension;

		String realURI =fileUploadDirectory + "/" + changedFileName;
		String handlerURI = handlerPath + "/" + changedFileName;
		
		if(!fileUploadDirectory.exists()) {
			fileUploadDirectory.mkdirs();
		}
	
		try {
			File uploadFile = new File(realURI);
			file.transferTo(uploadFile);
		} catch (IllegalStateException | java.io.IOException error) {
			error.printStackTrace();

			throw error;
		}

		result.put(CHANGED_FILE_NAME_KEY, changedFileName);
		result.put(ORIGINAL_FILE_NAME_KEY, originalFileName);
		result.put(URI_KEY, handlerURI);

		return result;
	}
}
