package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.DictionaryDetail;
import com.example.medicalservice.domain.DictionaryType;
import com.example.medicalservice.domain.Page;
import com.example.medicalservice.domain.User;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.mapper.DictionaryMapper;
import com.example.medicalservice.service.DictionaryService;
import com.example.medicalservice.util.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @DESCRIPTION:
 * @USER: 11364
 * @DATE: 2021/6/20 16:18
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public int insertDictionaryType(DictionaryType dictionaryType) {
        if (dictionaryMapper.getDictionaryTypeBytypeCode(dictionaryType.getTypeCode())!=null||dictionaryMapper.getDictionaryTypeBytypeName(dictionaryType.getTypeName())!=null) {
            throw new UserFriendException("该数据类型存在", "400");
        }
            return dictionaryMapper.insertDictionaryType(dictionaryType);
    }

    @Override
    public List<DictionaryType> getAllDictionaryType() {
        System.out.println(dictionaryMapper.getAllDictionaryType());
        return dictionaryMapper.getAllDictionaryType();

    }

    @Override
    public PageInfo selectAllDictionaryType(Page page) {
        //设置页码和每页个数
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        //查询符合条件的记录
        List<DictionaryType> dictionaryTypes = dictionaryMapper.selectAllDictionaryType(page);
        System.out.println(dictionaryTypes);
        //将查询到的信息封装到PageInfo
        PageInfo pageInfo = new PageInfo(dictionaryTypes);
        //返回PageInfo类型数据
        return pageInfo;
    }

    @Override
    public DictionaryType getDictionaryTypeBytypeCode(String typeCode) {
            if (dictionaryMapper.getDictionaryTypeBytypeCode(typeCode)==null) {
                throw new UserFriendException("该数据类型不存在", "400");
            }
        return dictionaryMapper.getDictionaryTypeBytypeCode(typeCode);
    }

    @Override
    public DictionaryType getDictionaryTypeBytypeName(String typeName) {
        if (dictionaryMapper.getDictionaryTypeBytypeName(typeName)==null) {
            throw new UserFriendException("该数据类型不存在", "400");
        }
        return dictionaryMapper.getDictionaryTypeBytypeName(typeName);
    }

    @Override
    public int updateDictionaryType(DictionaryType dictionaryType) {
        if(dictionaryType.getTypeCode()==null||dictionaryType.getTypeName()==null){
            throw new UserFriendException("参数不可以为空", ResultCodeEnum.BAD_REQUEST.getCode());
        }
        return dictionaryMapper.updateDictionaryType(dictionaryType);

    }

    @Override
    public int deleteDictionaryType(DictionaryType dictionaryType) {
        return dictionaryMapper.deleteDictionaryType(dictionaryType);
    }



    @Override
    public int insertDictionaryDetail(DictionaryDetail dictionaryDetail) {
        if (dictionaryDetail.getIsDefault()==null){
            dictionaryDetail.setIsDefault(0);//未设置则isdefault为0
        }
        return dictionaryMapper.insertDictionaryDetail(dictionaryDetail);
    }

    @Override
    public int deleteDictionaryDetailByTypeCode(DictionaryDetail dictionaryDetail) {
        return dictionaryMapper.deleteDictionaryDetailByTypeCode(dictionaryDetail);
    }

    @Override
    public int deleteDictionaryDetailByValue(DictionaryDetail dictionaryDetail) {
        return dictionaryMapper.deleteDictionaryDetailByValue(dictionaryDetail);
    }

    @Override
    public int updateDictionaryDetail(DictionaryDetail dictionaryDetail) {

        return dictionaryMapper.updateDictionaryDetail(dictionaryDetail);
    }

    @Override
    public DictionaryDetail getDictionaryDetailByValue(Integer value) {
        if (dictionaryMapper.getDictionaryDetailByValue(value)==null) {
            throw new UserFriendException("该数据类型不存在", "400");
        }
        return dictionaryMapper.getDictionaryDetailByValue(value);
    }

    @Override
    public DictionaryDetail getDictionaryDetailByName(String name) {
        if (dictionaryMapper.getDictionaryDetailByName(name)==null) {
            throw new UserFriendException("该数据类型不存在", "400");
        }
        return dictionaryMapper.getDictionaryDetailByName(name);

    }

    @Override
    public List<DictionaryDetail> getDictionaryDetailByTypeCode(String typeCode) {
        if (dictionaryMapper.getDictionaryDetailByTypeCode(typeCode).size()==0) {
            throw new UserFriendException("该数据类型不存在", "400");
        }
        return dictionaryMapper.getDictionaryDetailByTypeCode(typeCode);
    }

    @Override
    public List<DictionaryType> getAllDictionary() {
        return dictionaryMapper.getAllDictionary();
    }

    @Override
    public PageInfo selectAllDictionary(Page page) {
        //设置页码和每页个数
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        //查询符合条件的记录
        List<DictionaryType> dictionaryTypes = dictionaryMapper.selectAllDictionary(page);
        System.out.println(dictionaryTypes);
        //将查询到的信息封装到PageInfo
        PageInfo pageInfo = new PageInfo(dictionaryTypes);
        //返回PageInfo类型数据
        return pageInfo;
    }


}
