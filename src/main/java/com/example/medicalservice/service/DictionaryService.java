package com.example.medicalservice.service;

import com.example.medicalservice.domain.DictionaryDetail;
import com.example.medicalservice.domain.DictionaryType;
import com.example.medicalservice.domain.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @DESCRIPTION:
 * @USER: 11364
 * @DATE: 2021/6/20 16:17
 */

public interface DictionaryService {


    int insertDictionaryType(DictionaryType dictionaryType);

    List<DictionaryType> getAllDictionaryType();

    //分页查询用户
    PageInfo selectAllDictionaryType(Page page);

    //查看数据字典类型根据typecode
    DictionaryType getDictionaryTypeBytypeCode(String typeCode);

    //查看数据字典类型根据typename
    DictionaryType getDictionaryTypeBytypeName(String typeName);

    //更新数据字典类型
    int updateDictionaryType(DictionaryType dictionaryType);

    //删除数据字典类型
    int deleteDictionaryType(DictionaryType dictionaryType);

    //新增数据字典值
    int insertDictionaryDetail(DictionaryDetail dictionaryDetail);

    //删除数据字典值根据typecode
    int deleteDictionaryDetailByTypeCode(DictionaryDetail dictionaryDetail);

    //删除数据字典值根据value
    int deleteDictionaryDetailByValue(DictionaryDetail dictionaryDetail);

    //修改数据字典值
    int updateDictionaryDetail(DictionaryDetail dictionaryDetail);


    //查看字典数据值根据value
    DictionaryDetail getDictionaryDetailByValue(Integer value);

    //查看字典数据值根据name
    DictionaryDetail getDictionaryDetailByName(String name);

    //查询字典数据值根据typecode
    List<DictionaryDetail> getDictionaryDetailByTypeCode(String typeCode);

    //查询所有数据类型包括值
    List<DictionaryType> getAllDictionary();

    //分页查询查询所有数据类型包括值
    PageInfo selectAllDictionary(Page page);







}
