package com.example.demo.services;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll(){
        return (List<Student>) studentRepository.findAll();
    }

    public Student findById(Long id){
        return studentRepository.findById(id).get();
    }

    public Student save(Student student){
        return studentRepository.save(student);
    }

    public Student add(Student student){
        if(!studentRepository.existsById(student.getId())){
            return studentRepository.save(student);
        }else {
            Student temp = new Student();
            temp.setFirst_name(student.getFirst_name());
            temp.setAge(student.getAge());
            return studentRepository.save(temp);
        }
    }

    public void deleteById(Long id){
        studentRepository.deleteById(id);
    }
}
