package com.example.demo.controllers;


import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @GetMapping("/")
    public String index(Model model){
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudent(Model model, @PathVariable Long id){
        model.addAttribute("student", studentService.findById(id));
        return "edit_student";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(Model model, @PathVariable Long id){
        studentService.deleteById(id);
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "index";
    }

    @PostMapping("/edit")
    public String editStudent(Model model, @ModelAttribute Student student){
        studentService.save(student);
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "redirect:/";
    }

    @GetMapping("/student/add")
    public String addStudent(){
        return "add_student";
    }

    @PostMapping("/add")
    public String saveNewStudent(Model model, @ModelAttribute Student student){
        studentService.add(student);
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "redirect:/";
    }


}
