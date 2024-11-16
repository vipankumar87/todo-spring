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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/updateStatus/{id}")
	public String updateTodoStatus(@PathVariable Long id) {
		if(svc.updateStatus(id, "updated") != null) {
			return "redirect:/todo/viewTodoList";
		}
		return "redirect:/todo/viewTodoList";
	}
	@GetMapping({"/addToDoItem", "addToDoItem"})
	public String addToDoItem(Model model) {
		model.addAttribute("todo", new Todo());
		
		return "addToDoItem";
	}
	
	@PostMapping("/saveToDoItem")
	public String saveToDoItem(Todo todo, RedirectAttributes redirectAttributes) {
		if(svc.saveOrUpdate(todo) != null) {
			redirectAttributes.addFlashAttribute("message", "Saved successfully");
			return "redirect:/viewTodoList";
		}
		redirectAttributes.addFlashAttribute("message", "Saved failed");
		return "redirect:/addToDoItem";
	}
	@GetMapping("/editToDoItem/{id}")
	public String editToDoItem(@PathVariable Long id, Model model) {
		model.addAttribute("todo", svc.getById(id));
		
		return "EditToDoItem";
	}

	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem(Todo todo, RedirectAttributes redirectAttributes) {
		if(svc.saveOrUpdate(todo) != null) {
			redirectAttributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/editToDoItem/" + todo.getId();
	}
	
	@GetMapping("/deleteToDoItem/{id}")
	public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			if (svc.delete(id)) {
				redirectAttributes.addFlashAttribute("message", "Delete Success");
				return "redirect:/viewTodoList";
			}
			redirectAttributes.addFlashAttribute("message", "Delete Failure");
						
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("message", "Delete Failure "+ e.getMessage());
		}
		return "redirect:/viewTodoList";
	}
}
