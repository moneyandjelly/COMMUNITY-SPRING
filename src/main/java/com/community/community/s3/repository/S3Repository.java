package com.community.community.s3.repository;

public interface S3Repository {
    String presignPutObject(String fileName);
}
