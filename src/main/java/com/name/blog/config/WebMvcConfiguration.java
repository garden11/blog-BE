package com.name.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final com.name.blog.provider.security.AuthInterceptor authInterceptor;

	@Value("${local.post.image.file.upload.path}")
	private String localPostImageFileUploadPath;
	
	@Value("${local.post.image.file.upload.handler.path}")
	private String localPostImageFileUploadHandlerPath;
	
	@Value("${local.profile.image.file.upload.path}")
	private String localProfileImageFileUploadPath;
	
	@Value("${local.profile.image.file.upload.handler.path}")
	private String localProfileImageFileUploadHandlerPath;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(localPostImageFileUploadHandlerPath + "/**")
                .addResourceLocations("file:///" + localPostImageFileUploadPath + "/");
        registry.addResourceHandler(localProfileImageFileUploadHandlerPath + "/**")
        		.addResourceLocations("file:///" + localProfileImageFileUploadPath + "/");
    }
}
