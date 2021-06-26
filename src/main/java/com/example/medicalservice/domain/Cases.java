package com.example.medicalservice.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@ApiModel
public class Cases {

    private Integer id;
    @ApiModelProperty(value = "案例id")
    private Integer caseId;
    @ApiModelProperty(value = "案例名称")
    private String caseName;
    @ApiModelProperty(value = "课程id")
    private Integer courseId;
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    private List<CaseImage> caseImages;
    private List<CaseFile> caseFiles;
    @ApiModelProperty(value = "创建时间")
    private Date creatTime;
    @ApiModelProperty(value = "案例描述")
    private String caseDesc;
    @ApiModelProperty(value = "创建老师")
    private Integer creatTeacher;
    @ApiModelProperty(value = "思考")
    private String thinking;
    @ApiModelProperty(value = "是否发布，1表示未发布，2表示已发布")
    private Integer isPublish;//1表示未发布，2表示已发布
    @ApiModelProperty(value = "教师名称")
    private String teacherName;

//  @Override
//  public String toString() {
//    return "Cases{" +
//            "id=" + id +
//            ", caseId=" + caseId +
//            ", caseName='" + caseName + '\'' +
//            ", courseId=" + courseId +
//            ", courseName='" + courseName + '\'' +
//            ", caseImages=" + caseImages +
//            ", caseFiles=" + caseFiles +
//            ", creatTime=" + creatTime +
//            ", caseDesc='" + caseDesc + '\'' +
//            ", creatTeacher='" + creatTeacher + '\'' +
//            ", medHistory='" + medHistory + '\'' +
//            ", thinking='" + thinking + '\'' +
//            '}';
//  }


//  @Override
//  public String toString() {
//    return "Cases{" +
//            "id=" + id +
//            ", caseId=" + caseId +
//            ", caseName='" + caseName + '\'' +
//            ", courseId=" + courseId +
//            ", courseName='" + courseName + '\'' +
//            ", caseImages=" + caseImages +
//            ", creatTime=" + creatTime +
//            ", caseDesc='" + caseDesc + '\'' +
//            ", creatTeacher='" + creatTeacher + '\'' +
//            ", medHistory='" + medHistory + '\'' +
//            ", thinking='" + thinking + '\'' +
//            '}';
//  }


    @Override
    public String toString() {
        return "Cases{" +
                "id=" + id +
                ", caseId=" + caseId +
                ", caseName='" + caseName + '\'' +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", caseImages=" + caseImages +
                ", caseFiles=" + caseFiles +
                ", creatTime=" + creatTime +
                ", caseDesc='" + caseDesc + '\'' +
                ", creatTeacher=" + creatTeacher +
                ", thinking='" + thinking + '\'' +
                ", isPublish=" + isPublish +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setCaseImages(List<CaseImage> caseImages) {
        this.caseImages = caseImages;
    }

    public void setCaseFiles(List<CaseFile> caseFiles) {
        this.caseFiles = caseFiles;
    }

    public List<CaseImage> getCaseImages() {
        return caseImages;
    }

    public List<CaseFile> getCaseFiles() {
        return caseFiles;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

//  public void setCaseImages(List<CaseImage> caseImages) {
//    this.caseImages = caseImages;
//  }

//  public void setCaseFiles(List<CaseFile> caseFiles) {
//    this.caseFiles = caseFiles;
//  }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public void setCreatTeacher(Integer creatTeacher) {
        this.creatTeacher = creatTeacher;
    }


    public void setThinking(String thinking) {
        this.thinking = thinking;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public String getCaseName() {
        return caseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

//  public List<CaseImage> getCaseImages() {
//    return caseImages;
//  }

//  public List<CaseFile> getCaseFiles() {
//    return caseFiles;
//  }

    public Date getCreatTime() {
        return creatTime;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public Integer getCreatTeacher() {
        return creatTeacher;
    }

    public String getThinking() {
        return thinking;
    }
}
