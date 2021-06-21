package com.example.medicalservice.control;

import com.alibaba.fastjson.JSONObject;
import com.example.medicalservice.domain.DictionaryDetail;
import com.example.medicalservice.domain.DictionaryType;
import com.example.medicalservice.domain.Page;
import com.example.medicalservice.domain.User;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.service.DictionaryService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @DESCRIPTION:
 * @USER: 11364
 * @DATE: 2021/6/20 16:07
 */
@Api(tags = "字典管理")
@RestController
@RequestMapping("/dict")
public class DictionaryController {

    @Autowired
    DictionaryService dictionaryService;

    @ApiOperation(value = "新增数据字典类型")
    @ResponseBody
    @PostMapping("/insertDictionaryType")
    public Result insertDictionaryType(@RequestBody DictionaryType dictionaryType) {
        try {
            dictionaryService.insertDictionaryType(dictionaryType);
        }catch (UserFriendException e){
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("该类型存在！");
        }
            //dictionaryService.insertDictionaryType(dictionaryType);
            return Result.success().setCode(ResultCodeEnum. Register.getCode()).setMsg("添加成功");

    }

    @ApiOperation(value = "查询全部数据字典类型")
    @ResponseBody
    @GetMapping("/getAllDictionaryType")
    public Result getAllDictionaryType() {
        //PageHelper.startPage(1,2);
        List<DictionaryType> dictionaryTypes = dictionaryService.getAllDictionaryType();
        int Count = dictionaryTypes.size();
        return Result.success().setData(dictionaryTypes).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询所有数据类型成功");
    }

    //@RequiresRoles(value={"teacher","admin"},logical= Logical.OR)
    @ApiOperation(value = "查询字典类型根据typecode")
    @ResponseBody
    @GetMapping("/getDictionaryTypeBytypeCode/{typeCode}")
    public Result getDictionaryTypeBytypeCode(@PathVariable("typeCode")String typeCode) {
        try {
            dictionaryService.getDictionaryTypeBytypeCode(typeCode);
        }catch (UserFriendException e){
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("查询不存在！");
        }
        DictionaryType dictionaryType  = dictionaryService.getDictionaryTypeBytypeCode(typeCode);
        return Result.success().setData(dictionaryType).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");

    }

    //@RequiresRoles(value={"teacher","admin"},logical= Logical.OR)
    @ApiOperation(value = "查询字典类型根据typename")
    @ResponseBody
    @GetMapping("/getDictionaryTypeBytypeName/{typeName}")
    public Result getDictionaryTypeBytypeName(@PathVariable("typeName")String typeName) {
        try {
            dictionaryService.getDictionaryTypeBytypeName(typeName);
        }catch (UserFriendException e){
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("查询不存在！");
        }
        DictionaryType dictionaryType  = dictionaryService.getDictionaryTypeBytypeName(typeName);
        return Result.success().setData(dictionaryType).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }


    @ApiOperation(value = "分页查找所有字典类型")
    @ResponseBody
    @GetMapping("/selectAllDictionaryType")
    public Result selectAllDictionaryType(@RequestBody Page page) {
        //数据绑定：包括分页信息，条件，
        JSONObject json = new JSONObject();
        try {
            //调用查询所有信息方法，并将从页面接受的页面和每页显示的信息数传过去
            PageInfo<User> pageInfo= dictionaryService.selectAllDictionaryType(page);
            //将查出的信息封装为json
            json.put("pageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //想页面返回信息
        //return Result.success().setData(json.toJSONString()).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有字典类型成功");
        return Result.success().setData(json).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有字典类型成功");
    }

    @ApiOperation(value = "新增数据字典值")
    @ResponseBody
    @PostMapping("/insertDictionaryDetail")
    public Result insertDictionaryDetail(@RequestBody DictionaryDetail dictionaryDetail) {

        dictionaryService.insertDictionaryDetail(dictionaryDetail);

        return Result.success().setCode(ResultCodeEnum. Register.getCode()).setMsg("添加成功");
    }




    @ApiOperation(value = "删除数据值根据typecode")
    @ResponseBody
    @DeleteMapping("/deleteDictionaryDetailByTypeCode")
    public Result deleteDictionaryDetailByTypeCode(@RequestBody DictionaryDetail dictionaryDetail){

        dictionaryService.deleteDictionaryDetailByTypeCode(dictionaryDetail);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除成功");
    }

    @ApiOperation(value = "删除数据值根据value")
    @ResponseBody
    @DeleteMapping("/deleteDictionaryDetailByValue")
    public Result deleteDictionaryDetailByValue(@RequestBody DictionaryDetail dictionaryDetail){

        dictionaryService.deleteDictionaryDetailByValue(dictionaryDetail);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除成功");
    }


    @ApiOperation(value = "更新数据值")
    @ResponseBody
    @PutMapping("/updateDictionaryDetail")
    public Result updateDictionaryDetail(@RequestBody DictionaryDetail dictionaryDetail){
        dictionaryService.updateDictionaryDetail(dictionaryDetail);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("更新成功");

    }

    @ApiOperation(value = "查询数据值根据value")
    @ResponseBody
    @GetMapping("/getDictionaryDetailByValue/{value}")
    public Result getDictionaryDetailByValue(@PathVariable("value")Integer value) {
        try {
            dictionaryService.getDictionaryDetailByValue(value);
        }catch (UserFriendException e){
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("查询不存在！");
        }
        DictionaryDetail dictionaryDetail = dictionaryService.getDictionaryDetailByValue(value);
        return Result.success().setData(dictionaryDetail).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");

    }

    //@RequiresRoles(value={"teacher","admin"},logical= Logical.OR)
    @ApiOperation(value = "查询数据值根据name")
    @ResponseBody
    @GetMapping("/getDictionaryDetailByName/{name}")
    public Result getDictionaryDetailByName(@PathVariable("name")String name) {
        try {
            dictionaryService.getDictionaryDetailByName(name);
        }catch (UserFriendException e){
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("查询不存在！");
        }
        DictionaryDetail dictionaryDetail = dictionaryService.getDictionaryDetailByName(name);
        return Result.success().setData(dictionaryDetail).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }

    @ApiOperation(value = "查询数据值根据typeCode")
    @ResponseBody
    @GetMapping("/getDictionaryDetailByTypeCode/{typeCode}")
    public Result getDictionaryDetailByTypeCode(@PathVariable("typeCode")String typeCode) {
        try {
            dictionaryService.getDictionaryDetailByTypeCode(typeCode);
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("数据类型查询不存在！");
        }
        List<DictionaryDetail> dictionaryDetails = dictionaryService.getDictionaryDetailByTypeCode(typeCode);
        return Result.success().setData(dictionaryDetails).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }

    @ApiOperation(value = "查询全部数据字典类型和值")
    @ResponseBody
    @GetMapping("/getAllDictionary")
    public Result getAllDictionary() {
        List<DictionaryType> dictionaryTypes = dictionaryService.getAllDictionary();
        int Count = dictionaryTypes.size();
        return Result.success().setData(dictionaryTypes).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询所有数据类型成功");
    }

    @ApiOperation(value = "分页查询全部数据字典类型和值")
    @ResponseBody
    @GetMapping("/selectAllDictionary")
    public Result selectAllDictionary(@RequestBody Page page) {
        //数据绑定：包括分页信息，条件，
        JSONObject json = new JSONObject();
        try {
            //调用查询所有信息方法，并将从页面接受的页面和每页显示的信息数传过去
            PageInfo<User> pageInfo= dictionaryService.selectAllDictionary(page);
            //将查出的信息封装为json
            json.put("pageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //想页面返回信息
        //return Result.success().setData(json.toJSONString()).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有字典类型成功");
        return Result.success().setData(json).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有字典类型成功");
    }

    }














