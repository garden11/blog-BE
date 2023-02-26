package com.name.blog.provider.service;

import com.name.blog.core.repository.PostImageRepository;
import com.name.blog.core.repository.ProfileImageRepository;
import com.name.blog.exception.ThreadRuntimeException;
import com.name.blog.util.S3FileUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class DBManageService {
    private final S3FileUploader s3FileUploader;

    private final PostImageRepository postImageRepository;
    private final ProfileImageRepository profileImageRepository;

    // S3 1회 처리양(최대 1000)
    private final int S3_PROCESS_AMOUNT = 1000;

    @Transactional
    public int runDeletingPostImageThreads(Long startId)  {
        int processAmount = S3_PROCESS_AMOUNT;

        try {
            List<Object[]> idAndNameList = postImageRepository.selectIdsAndNamesExpired(startId);
            int totalCount = idAndNameList.size();
            int runCount = Long.valueOf(totalCount /processAmount).intValue() + 1;

            ExecutorService executorService = Executors.newFixedThreadPool(4);

            if(totalCount <= processAmount) {
                deletePostImages(idAndNameList);
            } else {
                boolean isDone = false;
                int i = 0;
                while(!isDone) {
                    Thread.sleep(1000);

                    for(int j = 0; !isDone && j < 4; j++) {
                        int startIndex = i * processAmount;
                        int endIndex = startIndex + processAmount - 1;

                        executorService.submit(() -> {
                            deletePostImages(idAndNameList.subList(startIndex, endIndex));
                        });

                        i = i + 1;
                        if (i == runCount) {isDone = true; }
                    }
                }
            }

            return totalCount;
        } catch (InterruptedException error) {
            error.printStackTrace();

            throw new ThreadRuntimeException();
        }
    }

    @Transactional
    public int runDeletingProfileImagesThreads(Long startId) {
        int processAmount = S3_PROCESS_AMOUNT;

        try {
            List<Object[]> idAndNameList = profileImageRepository.selectIdsAndNamesExpired(startId);
            int totalCount = idAndNameList.size();
            int runCount = Long.valueOf(totalCount /processAmount).intValue() + 1;

            ExecutorService executorService = Executors.newFixedThreadPool(4);

            if(totalCount <= processAmount) {
                deleteProfileImages(idAndNameList);
            } else {
                boolean isDone = false;
                int i = 0;
                while(!isDone) {
                    Thread.sleep(1000);

                    for(int j = 0; !isDone && j < 4; j++) {
                        int startIndex = i * processAmount;
                        int endIndex = startIndex + processAmount - 1;

                        executorService.submit(() -> {
                            deleteProfileImages(idAndNameList.subList(startIndex, endIndex));
                        });

                        i = i + 1;
                        if(i == runCount) { isDone = true; }
                    }
                }
            }

            return totalCount;
        } catch (InterruptedException error) {
            error.printStackTrace();

            throw new ThreadRuntimeException();
        }
    }

    private void deletePostImages (List<Object[]>idAndNameList) {
        if(idAndNameList.size() == 0) {
            return;
        }

        List<Long> idList = new ArrayList();
        List<String> nameList = new ArrayList();

        for(Object[] idAndName : idAndNameList) {
            idList.add(Long.valueOf(String.valueOf(idAndName[0])));
            nameList.add(String.valueOf(idAndName[1]));
        }

        s3FileUploader.deleteFiles(nameList);
        postImageRepository.deleteByIdIn(idList);
    }

    private void deleteProfileImages (List<Object[]> idAndNameList) {
        if(idAndNameList.size() == 0) {
            return;
        }

        List<Long> idList = new ArrayList();
        List<String> nameList = new ArrayList();

        for(Object[] idAndName : idAndNameList) {
            idList.add(Long.valueOf(String.valueOf(idAndName[0])));
            nameList.add(String.valueOf(idAndName[1]));
        }

        s3FileUploader.deleteFiles(nameList);
        profileImageRepository.deleteByIdIn(idList);
    }
}
