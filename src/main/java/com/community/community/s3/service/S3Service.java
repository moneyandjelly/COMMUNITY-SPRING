package com.community.community.s3.service;

import java.util.Map;

public interface S3Service {
    Map<String, String> generatePresignedUrl(String fileName);
}
