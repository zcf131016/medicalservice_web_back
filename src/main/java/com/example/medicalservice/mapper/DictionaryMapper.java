package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.DictionaryDetail;
import com.example.medicalservice.domain.DictionaryType;
import com.example.medicalservice.domain.Page;
import com.example.medicalservice.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @DESCRIPTION:
 * @USER: 11364
 * @DATE: 2021/6/20 16:07
 */

@Mapper
public interface DictionaryMapper {

    //新增字典类型
    int insertDictionaryType(DictionaryType dictionaryType);

    //查看所有数据字典类型
    List<DictionaryType> getAllDictionaryType();

    //分页查询所有数据字典类型
    List<DictionaryType> selectAllDictionaryType();


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

    //更新数据字典值
    int updateDictionaryDetail(DictionaryDetail dictionaryDetail);

    //查看字典数据值根据value
    DictionaryDetail getDictionaryDetailByValue(Integer value);

    //查看字典数据值根据name
    DictionaryDetail getDictionaryDetailByName(String name);

    //查询字典数据值根据typecode
    List<DictionaryDetail> getDictionaryDetailByTypeCode(String typeCode);

    //查询所有数据类型包括值
    List<DictionaryType> getAllDictionary();

    //分页查询所有数据字典类型
    List<DictionaryType> selectAllDictionary();

    //查询所有数据类型包括值根据类型
    List<DictionaryType> getAllDictionaryByTypeCode(String typeCode);

    //








}
