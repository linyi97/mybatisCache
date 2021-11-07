package com.shaq.mybatisCache.controller;

import com.shaq.mybatisCache.commom.api.CommonResult;
import com.shaq.mybatisCache.mbg.model.Student;
import com.shaq.mybatisCache.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Api(tags = "StudentController", description = "学生信息管理")
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    /**
     * 开启事务，测试一级缓存效果
     **/
    @ApiOperation("获取指定id的学生信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional(rollbackFor = Throwable.class)
    public CommonResult<Student> selectById(@PathVariable("id") Long id) {
        Student student = new Student();
        //第一次查询，缓存到一级缓存
        student = studentService.selectById(id);
        System.out.println(student.toString());
        //第二次查询，直接读取一级缓存
        student = studentService.selectById(id);
        System.out.println(student.toString());
        return CommonResult.success(student);
    }

    /**
     * 测试二级缓存效果
     **/
    @ApiOperation("查询并新增学生")
    @RequestMapping(value = "/selectByIdAndAddStudent/{id}", method = RequestMethod.GET)
    @ResponseBody
//    @Transactional(rollbackFor = Throwable.class)
    public CommonResult<Student> selectByIdAndAddStudent(@PathVariable("id") Long id) {
        System.out.println(studentService.selectById(id));
        System.out.println("增加了" + studentService.insert(buildStudent()) + "个学生");
        System.out.println(studentService.selectById(id));
        return CommonResult.success(null);
    }

    @ApiOperation("添加学生")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insertStudent(@RequestBody Student student) {
        CommonResult commonResult;
        int count = studentService.insert(student);
        if (count == 1) {
            commonResult = CommonResult.success(student);
            LOGGER.debug("createBrand success:{}", student);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", student);
        }
        return commonResult;
    }


    private Student buildStudent(){
        Student student = new Student();
        student.setName("明明");
        student.setAge(20);
        student.setGender("male");
        return student;
    }
}
