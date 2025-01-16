package com.brennerresende.todolist.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.brennerresende.todolist.entities.enums.Priority;
import com.brennerresende.todolist.entities.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name  = "tb_task")
public class Task implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant createDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant conclusionDate;
	
	private Integer taskStatus;
	private Integer priority;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Task() {
	}
	
	public Task(Long id, String title,
			String description, Instant createDate,
			Instant conclusionDate,
			TaskStatus taskStatus,
			Priority priority,
			Category category,
			User user) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.createDate = createDate;
		this.conclusionDate = conclusionDate;
		setTaskStatus(taskStatus);
		setPriority(priority);
		this.setCategory(category);
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Instant createDate) {
		this.createDate = createDate;
	}

	public Instant getConclusionDate() {
		return conclusionDate;
	}

	public void setConclusionDate(Instant conclusionDate) {
		this.conclusionDate = conclusionDate;
	}
	
	public TaskStatus getTaskStatus() {
		return TaskStatus.valueOf(taskStatus) ;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		if (taskStatus != null) {
			this.taskStatus = taskStatus.getCode();
		}
	}
	public Priority getPriority() {
		return Priority.valueOf(priority);
	}

	public void setPriority(Priority priority) {
		if(priority != null) {
			this.priority = priority.getCode();
		}
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(id, other.id);
	}
}
