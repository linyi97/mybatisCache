package com.shaq.mybatisCache.service;

import com.shaq.mybatisCache.mbg.model.Student;
/**
 * 会员管理Service
 * Created by macro on 2018/8/3.
 */
public interface StudentService {

    /**
     * 查询信息
     */
    Student selectById(Long id);

    /**
     * 新增
     */
    int insert(Student student);

    /**
     * 更新
     */
    int updateStudent(Long id, Student student);

    /**
     * 删除
     */
    int deleteStudent(Long id);

}
