package com.example.Register;

public class RegisterBean {

	private String name;
	private String password;
	private String email;
    /**
     * Default constructor. 
     */
    public RegisterBean() {
    	name = "";
    	password = "";
    	email="";
    }
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
