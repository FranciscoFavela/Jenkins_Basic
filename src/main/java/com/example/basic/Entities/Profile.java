package com.example.basic.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "tbl_profiles")
@NoArgsConstructor
@AllArgsConstructor
public class Profile extends Base {
	/*
	@Column(name = "idprofile")
	private int idprofile;
	*/
	@Column(name = "profile")
	private String profile;
}
