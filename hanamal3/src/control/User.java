package control;

import java.io.Serializable;

import enums.UserRole;
import model.Doctor;
import model.Nurse;
import model.StaffMember;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getPassword() {
		return password;
	}

	private String username;
    private String password;
    private UserRole role;
    private StaffMember worker;

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.worker = null;
    }

    public User(String username, String password, StaffMember staffMember) {
        this.username = username;
        this.password = password;
        this.worker = staffMember;
        if (worker instanceof Doctor)
        	this.role = UserRole.DOCTOR;
        else if (worker instanceof Nurse)
        	this.role = UserRole.NURSE;        
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public UserRole getRole() {
        return role;
    }

	public StaffMember getWorker() {
		return worker;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

    
}
