package com.online.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permission {
	
	@Id
	@GeneratedValue
	Long id;
	
	
	public String name;
	
	public String description;

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	

}
