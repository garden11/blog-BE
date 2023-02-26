package com.name.blog.web;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import com.name.blog.provider.service.DBManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/*
* DB의 파일 데이터 관리 시 api 사용, 그 외 만료된 데이터는 DB 내 스케쥴러를 이용하여 관리
*/
@RestController
@RequiredArgsConstructor
@RequestMapping
public class DBManageController {
    private final DBManageService dbManageService;

    @GetMapping("/api/v1/post-image/delete-data/{startId}")
    @Auth(roles = {Role.ADMIN})
    public int deletePostImages(@PathVariable("startId") Long startId) {
        return dbManageService.runDeletingPostImageThreads(startId);
    }

    @GetMapping("/api/v1/profile-image/delete-data/{startId}")
    @Auth(roles = {Role.ADMIN})
    public int deleteProfileImage(@PathVariable("startId") Long startId) {
        return dbManageService.runDeletingProfileImagesThreads(startId);
    }
}
