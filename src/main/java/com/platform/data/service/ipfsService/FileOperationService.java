package com.platform.data.service.ipfsService;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileOperationService {
    String uploadFile(byte[] fileBytes) throws IOException;
    byte[] getFile(String fileName) throws IOException;
    byte[] downloadFile(String hash) throws IOException;
    void saveFile(MultipartFile multipartFile) throws IOException;
    void deleteFile(String fileName);
}
