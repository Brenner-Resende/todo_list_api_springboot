package com.brennerresende.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.brennerresende.todolist.entities.Task;
import com.brennerresende.todolist.entities.enums.TaskStatus;
import com.brennerresende.todolist.repositories.TaskRepository;
import com.brennerresende.todolist.services.exceptions.DatabaseException;
import com.brennerresende.todolist.services.exceptions.InvalidDataInput;
import com.brennerresende.todolist.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;
	
	public List<Task> findAll() {
		return repository.findAll();
	}
	
	public Task findById(Long id) {
		Optional<Task> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Task insert(Task obj) {
		validateTaskStatusAndDates(obj);
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Task update(Long id, Task obj) {
		try {
			Task entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Task entity, Task obj) {
		validateTaskStatusAndDates(obj);
		entity.setTitle(obj.getTitle());
		entity.setDescription(obj.getDescription());
		entity.setCreateDate(obj.getCreateDate());
		entity.setConclusionDate(obj.getConclusionDate());
		entity.setTaskStatus(obj.getTaskStatus());
		entity.setPriority(obj.getPriority());
		entity.setCategory(obj.getCategory());
		entity.setUser(obj.getUser());
	}
	
    private void validateTaskDates(Task obj) {
        if (obj.getConclusionDate() != null && obj.getCreateDate() != null) {
            if (obj.getConclusionDate().isBefore(obj.getCreateDate())) {
                throw new InvalidDataInput("The conclusion date must be after the creation date.");
            }
        }
    }
    
    private void validateTaskStatusAndDates(Task obj) {
        validateTaskDates(obj);

        if (obj.getTaskStatus() == TaskStatus.DONE) {
            if (obj.getConclusionDate() == null) {
                throw new InvalidDataInput("A task cannot be marked as DONE without a valid conclusion date.");
            }
        }

        if (obj.getConclusionDate() != null && obj.getTaskStatus() != TaskStatus.DONE) {
            obj.setTaskStatus(TaskStatus.DONE);
        }
    }
}
