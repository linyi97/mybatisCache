package com.shaq.mybatisCache.service.Impl;

import com.shaq.mybatisCache.mbg.mapper.StudentMapper;
import com.shaq.mybatisCache.mbg.model.Student;
import com.shaq.mybatisCache.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lin
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student selectById(Long id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Student student) {
        return studentMapper.insertSelective(student);
    }

    @Override
    public int updateStudent(Long id, Student student) {
        student.setId(id);
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public int deleteStudent(Long id) {
        return studentMapper.deleteByPrimaryKey(id);
    }
}
