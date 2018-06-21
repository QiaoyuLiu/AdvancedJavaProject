package fr.epita.quiz.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Login {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private String login;
private String password;

public Login(String login, String password) {
	super();
	this.login = login;
	this.password = password;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
}
