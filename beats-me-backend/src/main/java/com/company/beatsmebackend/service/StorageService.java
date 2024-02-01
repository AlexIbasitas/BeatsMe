package com.company.beatsmebackend.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {
    private final AmazonS3 bucket;

    public StorageService() {
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials("DO00WUD9DYD6N4GAZ38M", "5gQxx/n6jfIoZFH0QiT4FEKZmJLQRHv8Syx+/PE7fAU")
        );
        bucket = AmazonS3ClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("sfo2.digitaloceanspaces.com", "sfo2")
                ).build();
    }

    public void upload(MultipartFile file) throws IOException {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            bucket.putObject(new PutObjectRequest("beatsme", file.getOriginalFilename(), file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch(Exception e) {
            throw new IOException("Invalid File");
        }
    }

    public List<String> getFilenames() {
        ListObjectsV2Result songFilesNames = bucket.listObjectsV2("beatsme");
        List<S3ObjectSummary> objects = songFilesNames.getObjectSummaries();


        return objects.stream()
                .map(S3ObjectSummary::getKey).collect(Collectors.toList());
//                .forEach(s3ObjectSummary -> {
//                    System.out.println(s3ObjectSummary.toString());
//                });
    }
}
