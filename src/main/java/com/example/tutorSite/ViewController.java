/* Tutor Site
 * Project Description: Example tutor site
 * Author: Kaelin Newhouse
 * Adapted from CodeJava at https://codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial
 * */

package com.example.tutorSite;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class ViewController {
	@Autowired
	private userRepository userRepo;

	@Autowired
	private lessonRepository lessonRepo;

	@Autowired
	private scheduleRepository scheduleRepo;
	
	@Autowired
	private classRepository classRepo;
	
	private User loggedIn;
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new User());
	     
	    return "register";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		//Encrypt password and save
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	     
	    userRepo.save(user);
	     
	    return "register_success";
	}
	
	//If user is a tutor, forward to tutor page. Otherwise, forward to student page.
	@GetMapping("/home")
	public String showHomePage(Model model) {
		//Check if user is tutor or student, save as global object, and return appropriate view
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		loggedIn = userRepo.findByUsername(((CustomUserDetails) auth.getPrincipal()).getUsername());
		
		if(loggedIn.getLabel().equals("Tutor")) {
			List<Lesson> listTutorLessons = lessonRepo.findAllByTutorId(loggedIn.getId());
			model.addAttribute("listTutorLessons" ,listTutorLessons);
			
			return "tutor";
		}

		List<Lesson> listStudentLessons = lessonRepo.findAllByStudentId(loggedIn.getId());
		model.addAttribute("listStudentLessons" ,listStudentLessons);
		
	    return "student";	
	
	}
	
	@GetMapping("/schedule")
	public String showSchedulePage(Model model) {
	    model.addAttribute("schedule", new Schedule());
	    
		return "tutorSetSchedule";
	}
	
	@PostMapping("/process_schedule")
	public String processSchedule(Model model, Schedule schedule) {
		//Save schedule
		schedule.setId(loggedIn.getId());
		scheduleRepo.save(schedule);
		
		//Check if user is tutor or student and return appropriate view
		if(loggedIn.getLabel().equals("Tutor")) {
			List<Lesson> listTutorLessons = lessonRepo.findAllByTutorId(loggedIn.getId());
			model.addAttribute("listTutorLessons" ,listTutorLessons);
			
			return "tutor";
		}

		List<Lesson> listStudentLessons = lessonRepo.findAllByStudentId(loggedIn.getId());
		model.addAttribute("listStudentLessons" ,listStudentLessons);
		
	    return "student";	
	}
	
	@GetMapping("/newLesson")
	public String newLessonTutor(Model model) {
		//Pass lists of students, tutors, classes, and a new lesson object to be passed back
		List<Class> listClasses = classRepo.findAllClasses();
		List<User> allTutors = userRepo.findAllTutors();
		List<User> allStudents = userRepo.findAllStudents();
		
		model.addAttribute("allClasses", listClasses);
		model.addAttribute("allTutors", allTutors);
		model.addAttribute("allStudents", allStudents);
		model.addAttribute("lesson", new Lesson());
		
	    return "newLessonTutor";	
	}
	
	@PostMapping("/process_newLesson")
	public String processNewLesson(Model model, Lesson lesson) {
		//Set names of values based on IDs
		String className = classRepo.findNameById(lesson.getClassId());
		lesson.setClassName(className);

		String studentName = userRepo.findNameById(lesson.getStudentId());
		lesson.setStudentName(studentName);

		String tutorName = userRepo.findNameById(lesson.getTutorId());
		lesson.setTutorName(tutorName);
		
		//Check if the selected date lines up with tutors set schedule
		Schedule tutorSchedule = scheduleRepo.findById(lesson.getTutorId());
		if( tutorSchedule.getDays().contains( lesson.getLessonDate() ) ) {
			
			//Save to repository
			lessonRepo.save(lesson);	
		}

		return "redirect:/home";
	}
}
