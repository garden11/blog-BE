package com.name.blog.provider.service;

import com.name.blog.core.repository.PostImageRepository;
import com.name.blog.core.repository.ProfileImageRepository;
import com.name.blog.exception.ThreadRuntimeException;

// AWS 사용 시 주석 해제
import com.name.blog.util.S3FileUploader;

import com.name.blog.provider.useCase.DataManageUseCase;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class DataManageService implements DataManageUseCase {
    private final S3FileUploader s3FileUploader;

    private final PostImageRepository postImageRepository;
    private final ProfileImageRepository profileImageRepository;

    // S3 1회 처리양(최대 1000)
    private final int S3_PROCESS_AMOUNT = 1000;

    @Override
    @Transactional
    public int runDeletingPostImageThreads(Long startId)  {
        int processAmount = S3_PROCESS_AMOUNT;

        try {
            List<Tuple> tupleList = postImageRepository.findIdAndNameListByIdExpired(startId);
            List<Object[]> idAndNameList = new ArrayList<>();

            for (Tuple tuple : tupleList) {
                Object[] idAndName = new Object[tuple.size()];

                Object id = tuple.get(0, Object.class);
                Object name = tuple.get(1, Object.class);

                idAndName[0] = id;
                idAndName[1] = name;

                idAndNameList.add(idAndName);
            }

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

    @Override
    @Transactional
    public int runDeletingProfileImagesThreads(Long startId) {
        int processAmount = S3_PROCESS_AMOUNT;

        try {
            List<Tuple> tupleList = profileImageRepository.findIdAndNameListByIdExpired(startId);
            List<Object[]> idAndNameList = new ArrayList<>();

            for (Tuple tuple : tupleList) {
                Object[] idAndName = new Object[tuple.size()];

                Object id = tuple.get(0, Object.class);
                Object name = tuple.get(1, Object.class);

                idAndName[0] = id;
                idAndName[1] = name;

                idAndNameList.add(idAndName);
            }

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

        // AWS 사용 시 주석 해제
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

        // AWS 사용 시 주석 해제
        s3FileUploader.deleteFiles(nameList);

        profileImageRepository.deleteByIdIn(idList);
    }
}
