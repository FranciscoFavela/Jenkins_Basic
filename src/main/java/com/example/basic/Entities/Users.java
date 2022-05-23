package com.example.basic.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "tbl_users")
@NoArgsConstructor
@AllArgsConstructor
public class Users extends Base {
	/*
	@Column(name = "iduser ")
	private int iduser;
	 */
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private int password;

	@Column(name = "idprofile")
	private String idprofile;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_profile")
	private Profile profile;

	@Column(name = "idemployee")

	private int idemployee;
	@Column(name = "status")
	private String status;
	@Column(name = "createddate")
	private String createddate;
	@Column(name = "updatedate")
	private String updatedate;
	@Column(name = "login")
	private String login;
}