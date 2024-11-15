package com.rudra.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rudra.todo.model.Todo;
import com.rudra.todo.repository.ITodoRepository;


@Service
public class TodoService {
	
	@Autowired
	ITodoRepository repo;
	
	public List<Todo> getAll(){
		ArrayList<Todo> todolist = new ArrayList<Todo>();
		this.repo.findAll().forEach(todo -> todolist.add(todo));
		return todolist;
	}
	public Todo getById(Long id){
		return repo.findById(id).get();
	}
	public Todo saveOrUpdate(Todo todo){
		return repo.save(todo);
	}
	
	public boolean delete(Long id){
		repo.deleteById(id);
		Todo todo = getById(id);
		if( todo == null) {
			return true;
		}
		return false;
	}
	
	public Todo updateStatus(Long id, String status){
		var todo = getById(id);
		todo.setStatus(status);
		return saveOrUpdate(todo);
	}
	
}
