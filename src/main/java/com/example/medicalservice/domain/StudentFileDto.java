package com.example.medicalservice.domain;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/7/1 9:20
 */
public class StudentFileDto {
    List<Integer> fileIds;

    public void setFileIds(List<Integer> fileIds) {
        this.fileIds = fileIds;
    }

    public List<Integer> getFileIds() {
        return fileIds;
    }
}
