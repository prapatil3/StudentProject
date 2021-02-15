package net.student.info.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.student.info.entity.Student;
import net.student.info.repository.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentRepository repo;

	@GetMapping("/")
	public ModelAndView showWelcomePage() {
		HashMap<String, Object> mav = new HashMap<String, Object>();
		mav.put("msg", "Welcome to Student Management System");
		List<Student> students = repo.findAll();
		mav.put("students", students);
		return new ModelAndView("welcome", mav);
	}

	@GetMapping("signup")
	public ModelAndView showSignUpForm(Student student) {
		return new ModelAndView("add-student");
	}

	@PostMapping("add")
	public ModelAndView addStudent(@Valid Student student, BindingResult result) {
		HashMap<String, Object> mav = new HashMap<String, Object>();
		mav.put("msg", "Welcome to Student Management System");
		if (result.hasErrors()) {
			return new ModelAndView("add-student");
		} else {
			repo.save(student);
			List<Student> students = repo.findAll();
			mav.put("students", students);
		}
		return new ModelAndView("welcome", mav);
	}

	@GetMapping("edit/{id}")
	public ModelAndView showUpdateForm(@PathVariable("id") Long id) {
		System.out.println("$$$$$ "+id);
		HashMap<String, Object> mav = new HashMap<String, Object>();
		mav.put("msg", "Welcome to Student Management System");
		
		Student student = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));

		mav.put("student", student);
		return new ModelAndView("update-student", mav);
	
	}
	
	@PostMapping("update-edit/{id}")
	public ModelAndView updateStudent(@PathVariable("id") Long id, @Valid Student student, BindingResult result) {
		System.out.println("##################");
		HashMap<String, Object> mav = new HashMap<String, Object>();
		mav.put("msg", "Welcome to Student Management System");
		
		if (result.hasErrors()) {
			student.setId(id);
			return new ModelAndView("update-student");

		}else {
			repo.save(student);
			List<Student> students = repo.findAll();
			mav.put("students", students);
		}

		
		return new ModelAndView("welcome", mav);
	}

//	@GetMapping("delete/{id}")
//	public String deleteStudent(@PathVariable("id") long id, Model model) {
//		
//		Student student = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
//		repo.delete(student);
//		model.addAttribute("students", repo.findAll());
//		return "welcome";
//	}
	@GetMapping("/delete/{id}")
	public ModelAndView deleteStudent(@PathVariable("id")Long id) {
		System.out.println("$$$$$ "+id);
		HashMap<String, Object> mav = new HashMap<String, Object>();
		Student student = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		mav.put("msg", "Welcome to Student Management System");
		repo.delete(student);
		List<Student> students = repo.findAll();
		mav.put("students", students);
		return new ModelAndView("welcome",mav);		
		
	}		
}
