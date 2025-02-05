package com.community.community.s3.service;

import org.springframework.stereotype.Service;

import com.community.community.s3.repository.S3Repository;

@Service
public class S3ServiceImpl implements S3Service {

    private final S3Repository s3Repository;

    public S3ServiceImpl(S3Repository s3Repository) {
        this.s3Repository = s3Repository;
    }

    @Override
    public String generatePresignedUrl(String fileName) {
        return s3Repository.presignPutObject(fileName);
    }
}
