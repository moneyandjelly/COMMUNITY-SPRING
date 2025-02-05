package com.community.community.s3.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.community.community.s3.repository.S3Repository;

import java.util.HashMap;
import java.util.Map;

@Service
public class S3ServiceImpl implements S3Service {

    private final S3Repository s3Repository;

    public S3ServiceImpl(S3Repository s3Repository) {
        this.s3Repository = s3Repository;
    }

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

    @Override
    public Map<String, String> generatePresignedUrl(String fileName) {
        String presignedUrl = s3Repository.presignPutObject(fileName);

        String s3url = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileName);

        Map<String, String> response = new HashMap<>();
        response.put("presignedUrl", presignedUrl);
        response.put("s3url", s3url);

        return response;
    }
}
