package com.name.blog.provider.useCase;

public interface DataManageUseCase {
    int runDeletingPostImageThreads(Long startId);

    int runDeletingProfileImagesThreads(Long startId);
}
