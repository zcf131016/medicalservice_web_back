package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.CaseFile;
import com.example.medicalservice.domain.CaseImage;
import com.example.medicalservice.domain.Cases;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/22 18:59
 */
@Mapper
public interface CasesMapper {
    //查找所有案例（无图片及文件）
    List<Cases> getAllCases();

    //获取案例总数
    int getAllCasesCount();

    //根据courseId查找所有案例
    List<Cases> getcasesByCourseId(Integer courseId);

    //根据courseId获取案例总数
    int getcasesCountByCourseId(Integer courseId);

    //根据案例名称查找所有案例
    List<Cases> getcasesBycaseName(String caseName);

    //根据案例名称查找到的案例总数
    int getcasesCountBycaseName(String caseName);

    //根据老师id查找所有案例
    List<Cases> getcasesByteacherId(Integer creatTeacher);

    //根据老师id查找到的案例总数
    int getcasesCountByteacherId(Integer creatTeacher);

    //根据caseId查找案例（无图片及文件）
    Cases getcasebyId(Integer caseId);

    //新增案例
    int insertCases(Cases cases);

    //根据案例id删除案例（图片和文件需要在control层删除）
    int deleteCasesByid(Integer caseId);

    //修改案例
    int updateCases(Cases cases);

    //根据案例id获取所有图片
    List<CaseImage> getcaseimagebyId(Integer caseId);

    //添加图片
    int insertCasesImage(CaseImage caseImage);

    //根据图片id删除图片
    int deletecasesImageByid(Integer id);

    //根据案例id删除该案例的所有图片
    int deletecasesImageByCaseid(Integer caseId);

    //根据案例id获取图片数目
    int getcaseimageCountbyId(Integer caseId);

    //根据图片id获取图片
    CaseImage getcaseimagebymainId(Integer id);

    //根据案例id获取所有文件
    List<CaseFile> getcasefilebyId(Integer caseId);

    //根据案例id获取文件数目
    int getcasefileCountbyId(Integer caseId);

    //根据id获取指定文件
    CaseFile downloadcasefilebyId(Integer id);

    //添加文件
    int insertCasesFile(CaseFile caseFile);

    //根据文件id删除文件
    int deletecasesFileByid(Integer id);

    //根据案例id删除案例中的所有文件
    int deletecasesFileByCaseid(Integer caseId);
}
