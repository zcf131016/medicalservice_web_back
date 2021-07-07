package com.example.medicalservice.service.impl;

import com.example.medicalservice.config.BaseConfig;
import com.example.medicalservice.domain.StudentFile;
import com.example.medicalservice.service.FileService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Lin YuHang
 * @date 2021/6/23 16:22
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    BaseConfig baseConfig;

    @Override
    public boolean uploadFile(MultipartFile file,String prefix, String sourcePath) {
        if (!file.isEmpty()) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            fileName = prefix + fileName;
            // 获取后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 文件存放路径
            String filePath = sourcePath;
            File dest = new File(new File(filePath) + "/" + fileName);
            // 检查文件路径是否存在
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest); // 存入服务器
                return true;
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean downloadFile(String file, HttpServletResponse response) {
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] content = new byte[fis.available()]; // 文件字节数组
            fis.read(content); // 读入字节数组
            fis.close();

            ServletOutputStream sos = response.getOutputStream();
            sos.write(content);

            sos.flush();
            sos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if(!file.exists()) return false;
        try {
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
