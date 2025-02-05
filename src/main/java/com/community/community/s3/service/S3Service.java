package com.community.community.s3.service;

public interface S3Service {
    String generatePresignedUrl(String fileName);
}
