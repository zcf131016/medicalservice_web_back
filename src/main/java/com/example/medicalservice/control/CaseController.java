package com.example.medicalservice.control;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.medicalservice.config.BaseConfig;
import com.example.medicalservice.domain.CaseFile;
import com.example.medicalservice.domain.CaseImage;
import com.example.medicalservice.domain.Cases;
import com.example.medicalservice.domain.StudentFile;
import com.example.medicalservice.service.CaseService;
import com.example.medicalservice.service.FileService;
import com.example.medicalservice.service.UserService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zx
 * @date 2021/6/23 9:06
 */

@Api(tags = "案例管理")
@RestController
@CrossOrigin
@RequestMapping("/case")
public class CaseController {
    @Autowired
    CaseService caseService;
    @Autowired
    BaseConfig baseConfig;
    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "查找所有案例")
    @GetMapping("/findAllCases")
    public Result findAllCases() {
        List<Cases> casesList = caseService.getAllCases();
        for (int i = 0; i < casesList.size(); i++) {
            casesList.get(i).setTeacherName(userService.getUserByUserId(casesList.get(i).getCreatTeacher()).getRealName());
        }
        int Count = casesList.size();
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询所有案例成功");
    }

    @ApiOperation(value = "分页查找所有案例")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "pageNum", value = "当前页数"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "每页显示条数")
    })
    @PostMapping("/findCasesByPage/{pageNum}/{pageSize}")
    public Result findCasesByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<Cases> casesList = caseService.getCasesbyPage(pageNum, pageSize);
        for (int i = 0; i < casesList.size(); i++) {
            casesList.get(i).setTeacherName(userService.getUserByUserId(casesList.get(i).getCreatTeacher()).getRealName());
        }
        int count = caseService.getAllCasesCount();
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("分页查找案例成功");
    }

    //    @ApiOperation(value = "根据courseId查找所有案例")
//    @ApiParam(name="courseId",type = "Integer")
//    @GetMapping("/findCasesbycourseId/{courseId}")
//    public Result findCasesbycourseId(@PathVariable Integer courseId){
//        List<Cases> casesList= caseService.getcasesByCourseId(courseId);
//        int count=casesList.size();
//        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("查询案例成功");
//    }
    @ApiOperation(value = "根据courseId查找所有案例并分页")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "courseId", value = "课程id"),
            @ApiImplicitParam(required = true, name = "pageNum", value = "当前页数"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "每页显示条数")
    })
    @GetMapping("/findCasesbycourseId/{courseId}/{pageNum}/{pageSize}")
    public Result findCasesbycourseId(@PathVariable Integer courseId, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<Cases> casesList = caseService.getcasesByCourseId(courseId, pageNum, pageSize);
        for (int i = 0; i < casesList.size(); i++) {
            casesList.get(i).setTeacherName(userService.getUserByUserId(casesList.get(i).getCreatTeacher()).getRealName());
        }
        int count = caseService.getcasesCountByCourseId(courseId);
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("查询案例成功");
    }

    @ApiOperation(value = "根据案例名称查找案例并分页")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "caseName", value = "课程名称"),
            @ApiImplicitParam(required = true, name = "pageNum", value = "当前页数"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "每页显示条数")
    })
    @GetMapping("/findCasesbycaseName/{caseName}/{pageNum}/{pageSize}")
    public Result findCasesbycaseName(@PathVariable String caseName, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<Cases> casesList = caseService.getcasesBycaseName(caseName, pageNum, pageSize);
        for (int i = 0; i < casesList.size(); i++) {
            casesList.get(i).setTeacherName(userService.getUserByUserId(casesList.get(i).getCreatTeacher()).getRealName());
        }
        int count = caseService.getcasesCountBycaseName(caseName);
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("查询案例成功");
    }

    @ApiOperation(value = "根据teacherId查找所有案例")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "creatTeacher", value = "创建老师id"),
            @ApiImplicitParam(required = true, name = "pageNum", value = "当前页数"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "每页显示条数")
    })
    @GetMapping("/findCasesbyteacherId/{creatTeacher}/{pageNum}/{pageSize}")
    public Result findCasesbyteacherId(@PathVariable Integer creatTeacher, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<Cases> casesList = caseService.getcasesByteacherId(creatTeacher, pageNum, pageSize);
        String teaName = userService.getUserByUserId(creatTeacher).getRealName();
        for (int i = 0; i < casesList.size(); i++) {
            casesList.get(i).setTeacherName(teaName);
        }
        int count = caseService.getcasesCountByteacherId(creatTeacher);
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("查询案例成功");
    }

    @ApiOperation(value = "根据caseId查找案例")
    @ApiImplicitParam(required = true, name = "caseId", value = "案例id")
    @GetMapping("/findCasesbycaseId/{caseId}")
    public Result findCasesbycaseId(@PathVariable Integer caseId) {
        Cases cases = caseService.getcasebyId(caseId);
        cases.setTeacherName(userService.getUserByUserId(cases.getCreatTeacher()).getRealName());
        List<CaseImage> caseImageList = caseService.getcaseimagebyId(caseId);
        List<CaseFile> caseFileList = caseService.getcasefilebyId(caseId);
        cases.setCaseImages(caseImageList);
        cases.setCaseFiles(caseFileList);
        return Result.success().setData(cases).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取案例成功!");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "进入新增案例，返回随机生成的caseId")
    @PostMapping("/intoinsertcase")
    public Result intoinsertcase() {
        Integer caseId = caseService.intoCreateCase();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("caseId", caseId);
        return Result.success().setData(jsonObject).setCode(ResultCodeEnum.OK.getCode()).setMsg("返回案例id成功!");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "新增案例")
    @PostMapping("/insertcase")
    public Result insertcase(@RequestBody Cases cases) {
        if (cases.getIsPublish() == 0) {
            cases.setIsPublish(1);//若为设置是否发布，默认设置为未发布
        }
        Integer caseId = caseService.insertCases(cases);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("新增案例成功！");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "根据caseId删除案例，若有文件和图片（外键级联，还未设置）也一并删除")
    @ApiImplicitParam(required = true, name = "caseId", value = "案例id")
    @DeleteMapping("/deletecase/{caseId}")
    public Result deletecase(@PathVariable Integer caseId) {
        if (caseService.getcasefileCountbyId(caseId) != 0)
            this.deleteCaseFileByCaseId(caseId);
        caseService.deleteCasesByid(caseId);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除案例成功！");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "批量删除案例", notes = "前端提供名为caseIdList的Integer数组表示需要删除的案例id号，若有文件和图片则一并删除")
    @ApiImplicitParam(required = true, value = "需要删除的案例id数组",name = "caseIdList",paramType = "query",dataType = "Integer")
    @DeleteMapping("/batchdeletecase")
    public Result batchdeletecase(@RequestParam List<Integer> caseIdList) {
        for (int i = 0; i < caseIdList.size(); i++) {
            if (caseService.getcasefileCountbyId(caseIdList.get(i)) != 0)
                this.deleteCaseFileByCaseId(caseIdList.get(i));
            caseService.deleteCasesByid(caseIdList.get(i));
        }
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除案例成功！");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "修改案例、发布案例")
    @PutMapping("/updatecase")
    public Result updatecase(@RequestBody Cases cases) {
        caseService.updateCases(cases);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除案例成功！");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "上传病例图片")
    @RequestMapping(value = "/uploadimgetocase", produces = {"application/json;charset=UTF-8", "image/png;charset=UTF-8", "image/jpeg;charset=UTF-8"}, method = RequestMethod.POST)
    public Result uploadimgetocase(@RequestParam("file") MultipartFile file,
                                   @RequestParam("caseId") Integer caseId) {
//        try {
//            CaseImage caseImage=new CaseImage();
//            InputStream inputStream = file.getInputStream();
//            byte[] pictureData = new byte[(int) file.getSize()];
//            inputStream.read(pictureData);
        try {
            String originalfileName=file.getOriginalFilename();
            CaseImage caseImage = new CaseImage();
            Blob blob = new SerialBlob(file.getBytes());
            caseImage.setImageName(originalfileName);
            caseImage.setCaseId(caseId);
            caseImage.setImage(blob);
            int i = caseService.insertCasesImage(caseImage);
            CaseImage caseImage1 = caseService.getcaseimagebymainId(i);
            return Result.success().setData(caseImage1).setCode(ResultCodeEnum.OK.getCode()).setMsg("上传图片成功!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Result.failure(ResultCodeEnum.UPLOAD_FAILED).setMsg("上传失败");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failure(ResultCodeEnum.UPLOAD_FAILED).setMsg("上传失败");
        }

//        byte imag[]=(byte[])bean.getBlobObject();
//            caseImage.setImage(pictureData);
//            caseImage.setCaseId(caseId);
//            caseImage.setDescription(description);
//            int i = caseService.insertCasesImage(caseImage);
//            CaseImage caseImage1=caseService.getcaseimagebymainId(i);
//            return Result.success().setData(caseImage1).setCode(ResultCodeEnum.OK.getCode()).setMsg("上传图片成功!");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return Result.failure(ResultCodeEnum.UPLOAD_FAILED).setMsg("上传失败");
//        }
    }

    @ApiOperation(value = "根据案例id读取病例图片")
    @RequestMapping(value = "/readimage/{caseId}", method = RequestMethod.GET)
    public Result getPhotoById(@PathVariable("caseId") Integer caseId, final HttpServletResponse response) throws Exception {
        List<CaseImage> caseImageList = caseService.getcaseimagebyId(caseId);
        JSONArray jsonArray = new JSONArray();
        //List<String> strings=new ArrayList<>();
        for (int i = 0; i < caseImageList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            BASE64Encoder encoder = new BASE64Encoder();
            byte[] data = (byte[]) caseImageList.get(i).getImage();
            String imagebase = encoder.encode(data);
            imagebase.replaceAll("\r|\n", "");
            //System.out.println(imagebase);
            jsonObject.put("id", caseImageList.get(i).getId());
            jsonObject.put("caseId", caseImageList.get(i).getCaseId());
            jsonObject.put("imagebase", imagebase);
            jsonObject.put("creatTime",caseImageList.get(i).getCreatTime());
            jsonObject.put("imageName",caseImageList.get(i).getImageName());
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取病例图片成功!");
//        InputStream in = new ByteArrayInputStream(data);
//        int len = 0;
//        byte[] buf = new byte[1024];
//        while ((len = in.read(buf, 0, 1024)) != -1) {
//            outputSream.write(buf, 0, len);
//        }
//        outputSream.close();
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "根据图片id删除图片")
    @ApiImplicitParam(required = true, name = "id", value = "图片id")
    @DeleteMapping("/deletecaseimage/{id}")
    public Result deletecaseimage(@PathVariable Integer id) {
        caseService.deletecasesImageByid(id);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("根据id删除图片成功");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "根据案例id删除该案例中的所有图片")
    @ApiImplicitParam(required = true, name = "caseId", value = "案例id")
    @DeleteMapping("/deletecaseimageByCaseId/{caseId}")
    public Result deletecaseimageByCaseId(@PathVariable Integer caseId) {
        caseService.deletecasesImageByCaseid(caseId);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("根据案例id删除所有图片成功");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "上传文件（治疗或诊断方案）到指定案例")
    @RequestMapping(value = "/uploadFiletoCases", produces = {"application/json;charset=UTF-8", "application/pdf;charset=UTF-8", "application/zip;charset=UTF-8", "application/msword;charset=UTF-8", "application/vnd.ms-powerpoint;charset=UTF-8", "image/png;charset=UTF-8", "image/jpeg;charset=UTF-8", "application/rar;charset=UTF-8"}, method = RequestMethod.POST)
    public Result uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("caseId") Integer caseId,
                             HttpServletRequest request) {
        String prefix = String.valueOf(caseId) + "_";
        boolean result = fileService.uploadFile(file, prefix, baseConfig.getCaseFilePath());
        if (result) {
            CaseFile caseFile = new CaseFile();
            caseFile.setCaseId(caseId);
            caseFile.setFileUrl(baseConfig.getCaseFilePath() + prefix + file.getOriginalFilename());
            caseService.insertCasesFile(caseFile);
//            StudentFile studentFile = new StudentFile();
//            studentFile.setCaseId(caseId);
            //studentFile.setFileType(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
//            studentFile.setFilePath(baseConfig.getStudentFilePath() + prefix + file.getOriginalFilename());
//            studentFileService.uploadFile(studentFile);
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("上传成功");
        }
        return Result.failure(ResultCodeEnum.UPLOAD_FAILED).setMsg("上传失败");
    }

    @ApiOperation(value = "在案例中下载文件")
    @RequestMapping(value = "/downloadFileFromCasesbyid/{id}", produces = {"application/json;charset=UTF-8", "application/pdf;charset=UTF-8", "application/zip;charset=UTF-8", "application/msword;charset=UTF-8", "application/vnd.ms-powerpoint;charset=UTF-8", "image/png;charset=UTF-8", "image/jpeg;charset=UTF-8", "application/rar;charset=UTF-8"}, method = RequestMethod.POST)
    public Result downloadFileFromCasesbyid(@PathVariable("id") Integer id,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        CaseFile caseFile = caseService.downloadcasefilebyId(id);
        if (caseFile == null)
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("文件不存在");
        String filePath = caseFile.getFileUrl();
        File newFile = new File(filePath);
        if (!newFile.exists())
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("对应路径的文件不存在");
//        StudentFile studentFile = studentFileService.getFileById(fileId);
//        if(studentFile == null ) return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("文件不存在");
//        String filePath = studentFile.getFilePath();
//        File newFile = new File(filePath);
//        if(!newFile.exists()) return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("对应路径的文件不存在");
        response.setHeader("Content-Disposition", "attachment;filename=" + filePath.substring(filePath.lastIndexOf("/")));
        response.setContentType("application/octet-stream");
        // 获取文件
        boolean result = fileService.downloadFile(filePath, response);
        if (result)
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("下载成功");
        return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED).setMsg("下载失败");
    }

    @ApiOperation(value = "根据案例Id获取文件列表")
    @GetMapping("/getFileByCaseId/{caseId}")
    public Result getFileByCaseId(@PathVariable("caseId") Integer caseId) {
        List<CaseFile> caseFileList = caseService.getcasefilebyId(caseId);
        if (caseFileList.size() == 0) {
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("该案例没有文件");
        } else {
            return Result.success().setData(caseFileList).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取文件列表成功");
        }
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "根据文件id删除案例文件")
    @DeleteMapping("/deleteCaseFileById/{id}")
    public Result deleteCaseFileById(@PathVariable("id") Integer id) {
        CaseFile caseFile = caseService.downloadcasefilebyId(id);
        if (caseFile == null) return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("文件不存在");
        boolean result = fileService.deleteFile(caseFile.getFileUrl());
        if (result) {
            caseService.deletecasesFileByid(id);
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除成功");
        }
        return Result.failure(ResultCodeEnum.DELETE_FAILED).setMsg("删除失败");
    }

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "根据案例id删除该案例的所有文件")
    @DeleteMapping("/deleteCaseFileByCaseId/{caseId}")
    public Result deleteCaseFileByCaseId(@PathVariable("caseId") Integer caseId) {
        List<CaseFile> caseFileList = caseService.getcasefilebyId(caseId);
        if (caseFileList.size() == 0)
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("该案例中没有文件");
        boolean totalResult = true;
        for (int i = 0; i < caseFileList.size(); i++) {
            boolean result = fileService.deleteFile(caseFileList.get(i).getFileUrl());
            if (result) {
                caseService.deletecasesFileByid(caseFileList.get(i).getId());
            } else {
                totalResult = false;
                continue;
            }
        }
        if (totalResult == true) {
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除成功");
        } else {
            return Result.failure(ResultCodeEnum.DELETE_FAILED).setMsg("有文件删除失败");
        }
    }
}
