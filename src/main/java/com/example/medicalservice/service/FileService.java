package com.example.medicalservice.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author Lin YuHang
 * @date 2021/6/23 16:20
 */
public interface FileService {
    boolean uploadFile(MultipartFile file,String prefix, String sourcePath);
    boolean downloadFile(String file, HttpServletResponse response);
    boolean deleteFile(String file);
}
