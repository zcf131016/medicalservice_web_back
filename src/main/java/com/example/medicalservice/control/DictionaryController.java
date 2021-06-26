package com.example.medicalservice.control;

import com.alibaba.fastjson.JSONObject;
import com.example.medicalservice.domain.*;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.service.DictionaryService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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


    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "新增数据字典类型")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "typeCode", value = "类型编码"),
            @ApiImplicitParam(required = true, name = "typeName", value = "类型名")
    })
    @ResponseBody
    @PostMapping("/insertDictionaryType")
    public Result insertDictionaryType(@RequestBody DictionaryType dictionaryType) {
        try {
            dictionaryService.insertDictionaryType(dictionaryType);
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("该类型存在！");
        }
        //dictionaryService.insertDictionaryType(dictionaryType);
        return Result.success().setCode(ResultCodeEnum.Register.getCode()).setMsg("添加成功");

    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "查询全部数据字典类型")
    @ResponseBody
    @GetMapping("/getAllDictionaryType")
    public Result getAllDictionaryType() {
        //PageHelper.startPage(1,2);
        List<DictionaryType> dictionaryTypes = dictionaryService.getAllDictionaryType();
        int Count = dictionaryTypes.size();
        return Result.success().setData(dictionaryTypes).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询所有数据类型成功");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "查询字典类型根据typecode")
    @ApiImplicitParam(required = true, value = "typeCode", name = "类型编码")
    @ResponseBody
    @GetMapping("/getDictionaryTypeBytypeCode/{typeCode}")
    public Result getDictionaryTypeBytypeCode(@PathVariable("typeCode") String typeCode) {
        try {
            dictionaryService.getDictionaryTypeBytypeCode(typeCode);
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("查询不存在！");
        }
        DictionaryType dictionaryType = dictionaryService.getDictionaryTypeBytypeCode(typeCode);
        return Result.success().setData(dictionaryType).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");

    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "查询字典类型根据typename")
    @ApiImplicitParam(required = true, value = "typeName", name = "类型名")
    @ResponseBody
    @GetMapping("/getDictionaryTypeBytypeName/{typeName}")
    public Result getDictionaryTypeBytypeName(@PathVariable("typeName") String typeName) {
        try {
            dictionaryService.getDictionaryTypeBytypeName(typeName);
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("查询不存在！");
        }
        DictionaryType dictionaryType = dictionaryService.getDictionaryTypeBytypeName(typeName);
        return Result.success().setData(dictionaryType).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }


    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "分页查找所有字典类型")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "pageNum", value = "当前页数"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "每页显示条数")
    })
    @ResponseBody
    @GetMapping("/selectAllDictionaryType/{pageNum}/{pageSize}")
    public Result selectAllDictionaryType(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page page = new Page();
        page.setCurrentPage(pageNum);
        page.setPageSize(pageSize);
        JSONObject json = new JSONObject();
        try {
            //调用查询所有信息方法，并将从页面接受的页面和每页显示的信息数传过去
            PageInfo<DictionaryType> pageInfo = dictionaryService.selectAllDictionaryType(page);
            //将查出的信息封装为json
            json.put("pageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED);
        }
        return Result.success().setData(json).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有字典类型成功！");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "新增数据字典值")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "typeCode", value = "数据类型"),
            @ApiImplicitParam(required = true, name = "value", value = "数据值"),
            @ApiImplicitParam(required = true, name = "name", value = "数据名")
    })
    @ResponseBody
    @PostMapping("/insertDictionaryDetail")
    public Result insertDictionaryDetail(@RequestBody DictionaryDetail dictionaryDetail) {

        dictionaryService.insertDictionaryDetail(dictionaryDetail);

        return Result.success().setCode(ResultCodeEnum.Register.getCode()).setMsg("添加成功");
    }


    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "删除数据字典根据typecode")
    @ApiImplicitParam(required = true, value = "typeCode", name = "类型名")
    @ResponseBody
    @DeleteMapping("/deleteDictionaryByTypeCode/{typeCode}")
    public Result deleteDictionaryByTypeCode(@PathVariable("typeCode") String typeCode) {
        DictionaryType dictionaryType =  new  DictionaryType();
        DictionaryDetail dictionaryDetail = new DictionaryDetail();
        dictionaryType.setTypeCode(typeCode);
        dictionaryDetail.setTypeCode(typeCode);

        dictionaryService.deleteDictionaryType(dictionaryType);
        dictionaryService.deleteDictionaryDetailByTypeCode(dictionaryDetail);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除成功");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "删除数据值根据value和typeCode")
    @ApiImplicitParams({@ApiImplicitParam(required = true, value = "value", name = "值"),
            @ApiImplicitParam(required = true, value = "typeCode", name = "类型名")
    }
    )
    @ResponseBody
    @DeleteMapping("/deleteDictionaryDetailByValueAndTypeCode")
    public Result deleteDictionaryDetailByValue(@RequestBody DictionaryDetail dictionaryDetail) {

        dictionaryService.deleteDictionaryDetailByValueAndTypeCode(dictionaryDetail);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除成功");
    }


    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "更新数据值")
    @ResponseBody
    @PutMapping("/updateDictionaryDetail")
    public Result updateDictionaryDetail(@RequestBody DictionaryDetail dictionaryDetail) {
        dictionaryService.updateDictionaryDetail(dictionaryDetail);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("更新成功");

    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "查询数据值根据value")
    @ApiImplicitParam(required = true, value = "value", name = "值")
    @ResponseBody
    @GetMapping("/getDictionaryDetailByValue/{value}")
    public Result getDictionaryDetailByValue(@PathVariable("value") Integer value) {
        try {
            dictionaryService.getDictionaryDetailByValue(value);
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("查询不存在！");
        }
        DictionaryDetail dictionaryDetail = dictionaryService.getDictionaryDetailByValue(value);
        return Result.success().setData(dictionaryDetail).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");

    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "查询数据值根据name")
    @ApiImplicitParam(required = true, value = "name", name = "数据名")
    @ResponseBody
    @GetMapping("/getDictionaryDetailByName/{name}")
    public Result getDictionaryDetailByName(@PathVariable("name") String name) {
        try {
            dictionaryService.getDictionaryDetailByName(name);
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("查询不存在！");
        }
        DictionaryDetail dictionaryDetail = dictionaryService.getDictionaryDetailByName(name);
        return Result.success().setData(dictionaryDetail).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "查询数据值根据typeCode")
    @ApiImplicitParam(required = true, value = "typeCode", name = "类型编码")
    @ResponseBody
    @GetMapping("/getDictionaryDetailByTypeCode/{typeCode}")
    public Result getDictionaryDetailByTypeCode(@PathVariable("typeCode") String typeCode) {
        try {
            dictionaryService.getDictionaryDetailByTypeCode(typeCode);
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("数据类型查询不存在！");
        }
        List<DictionaryDetail> dictionaryDetails = dictionaryService.getDictionaryDetailByTypeCode(typeCode);
        return Result.success().setData(dictionaryDetails).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "查询全部数据字典类型和值")
    @ResponseBody
    @GetMapping("/getAllDictionary")
    public Result getAllDictionary() {
        List<DictionaryType> dictionaryTypes = dictionaryService.getAllDictionary();
        int Count = dictionaryTypes.size();
        return Result.success().setData(dictionaryTypes).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询所有数据类型成功");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "分页查询全部数据字典类型和值")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "pageNum", value = "当前页数"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "每页显示条数")
    })
    @ResponseBody
    @GetMapping("/selectAllDictionary/{pageNum}/{pageSize}")
    public Result selectAllDictionary(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page page = new Page();
        page.setCurrentPage(pageNum);
        page.setPageSize(pageSize);
        JSONObject json = new JSONObject();
        try {
            //调用查询所有信息方法，并将从页面接受的页面和每页显示的信息数传过去
            PageInfo<DictionaryType> pageInfo = dictionaryService.selectAllDictionary(page);
            //将查出的信息封装为json
            json.put("pageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED);
        }
        return Result.success().setData(json).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有字典类型和值成功！");

    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "新增数据字典类型")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "typeCode", value = "类型编码"),
            @ApiImplicitParam(required = true, name = "typeName", value = "类型名"),
            @ApiImplicitParam(required = true, name = "value", value = "数据值"),
            @ApiImplicitParam(required = true, name = "name", value = "数据名"),
            @ApiImplicitParam(required = true, name = "isDefault", value = "是否默认")

    })
    @ResponseBody
    @PostMapping("/insertDictionary")
    public Result insertDictionary(@RequestBody DictionaryType dictionaryType) {
        try {
            dictionaryService.insertDictionary(dictionaryType);
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.RegisterError).setMsg("该数据类型已经存在！");
        }

            //dictionaryService.insertDictionary(dictionaryType);
            //dictionaryService.insertDictionaryType(dictionaryType);
            return Result.success().setCode(ResultCodeEnum.CREATED.getCode()).setMsg("添加成功");

        }
}




















