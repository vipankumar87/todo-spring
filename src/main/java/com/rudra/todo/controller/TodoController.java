package com.rudra.todo.controller;

//import java.util.Arrays;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import com.rudra.todo.model.Todo;
//import com.rudra.todo.model.Todo;
import com.rudra.todo.service.TodoService;

//@RestMapping
@Controller
public class TodoController{

	@Autowired
	TodoService svc;
	
//	@RequestMapping("/")
	@GetMapping({"/", "viewTodoList"})
	public String index(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list", svc.getAll());
		model.addAttribute("message", message);
		return "viewTodoList";
	}
	
	@PostMapping("/updateStatus/{id}")
	public String updateTodoStatus(@PathVariable Long id) {
		if(svc.updateStatus(id, "updated") != null) {
			return "redirect:/viewTodoList";
		}
		return "redirect:/viewTodoList";
	}
	@GetMapping({"/addToDoItem", "addToDoItem"})
	public String addToDoItem(Model model) {
		model.addAttribute("todo", new Todo());
		
		return "addToDoItem";
	}

}
