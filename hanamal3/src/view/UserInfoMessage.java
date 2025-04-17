package view;

import control.*;
import enums.UserRole;
import model.Department;
import model.Doctor;
import model.Nurse;
import model.StaffMember;

public class UserInfoMessage {
	private String adminInfo;
	private String staffMemberInfo;
	private String doctorInfo;
	private String nurseInfo;
	
	public UserInfoMessage(User user) {
		Hospital hospital = Hospital.getInstance();
		StaffMember worker = user.getWorker();
		// Admin info
        this.adminInfo = String.format(
            "<html><body>"
            + "<h2>Hello Admin,</h2>"
            + "<p>There are <b>%d</b> departments.</p>"
            + "<p>There are <b>%d</b> staff members.</p>"
            + "<p>There are <b>%d</b> patients.</p>"
            + "<p>There are <b>%d</b> medications.</p>"
            + "<p>There are <b>%d</b> visits.</p>"
            + "<p>There are <b>%d</b> treatments.</p>"
            + "<p>There are <b>%d</b> medical problems.</p>"
            + "</body></html>",
            hospital.getDepartments().size(),
            hospital.getStaffMembers().size(),
            hospital.getPatients().size(),
            hospital.getMedications().size(),
            hospital.getVisits().size(),
            hospital.getTreatments().size(),
            hospital.getMedicalProblems().size()
        );

     // Staff member info
        if (worker != null) {
            String departments = "";
            for (Department d : worker.getDepartments()) {
                departments += d.getName() + " ";
            }

            this.staffMemberInfo = String.format(
                "<html><body>"
                + "<h2>Hello %s %s,</h2>"
                + "<p>Your ID is: <b>%d</b></p>"
                + "<p>Your birthday is: <b>%s</b></p>"
                + "<p>Your address is: <b>%s</b></p>"
                + "<p>Your phone number is: <b>%s</b></p>"
                + "<p>Your email is: <b>%s</b></p>"
                + "<p>Your gender is: <b>%s</b></p>"
                + "<p>You're working with us since: <b>%s</b></p>"
                + "<p>Your departments are: <b>%s</b></p>"
                + "<p>Your salary is: <b>%.2f</b></p>"
                + "</body></html>",
                worker.getFirstName(),
                worker.getLastName(),
                worker.getId(),
                worker.getBirthDate(),
                worker.getAddress(),
                worker.getPhoneNumber(),
                worker.getEmail(),
                worker.getGender(),
                worker.getWorkStartDate(),
                departments.trim(),
                worker.getSalary()
            );


         // Doctor info
            if (worker instanceof Doctor) {
                Doctor doc = (Doctor) worker;
                String intern = doc.isFinishInternship() ? "have" : "haven't";
                this.doctorInfo = String.format(
                    "%s<html><body>"
                    + "<p>Your License Number is: <b>%d</b></p>"
                    + "<p>You %s finished internship</p>"
                    + "<p>Your specialization is: <b>%s</b></p>"
                    + "</body></html>",
                    staffMemberInfo,
                    doc.getLicenseNumber(),
                    intern,
                    doc.getSpecialization()
                );
            }

            // Nurse info
            if (worker instanceof Nurse) {
                Nurse nurse = (Nurse) worker;
                this.nurseInfo = String.format(
                    "%s<html><body>"
                    + "<p>Your License Number is: <b>%d</b></p>"
                    + "</body></html>",
                    staffMemberInfo,
                    nurse.getLicenseNumber()
                );
            }
		}

	}
	
	public String getAdminInfo() {
		return adminInfo;
	}
	public void setAdminInfo(String adminInfo) {
		this.adminInfo = adminInfo;
	}
	public String getStaffMemberInfo() {
		return staffMemberInfo;
	}
	public void setStaffMemberInfo(String staffMemberInfo) {
		this.staffMemberInfo = staffMemberInfo;
	}
	public String getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(String doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public String getNurseInfo() {
		return nurseInfo;
	}
	public void setNurseInfo(String nurseInfo) {
		this.nurseInfo = nurseInfo;
	}
	
	public String getMessage(User user) {
		if(user.getRole().equals(UserRole.ADMIN))
			return adminInfo;
		if(user.getRole().equals(UserRole.DOCTOR))
			return doctorInfo;
		if(user.getRole().equals(UserRole.NURSE))
			return nurseInfo;
		return "Hello " + user.getUsername();
	} 
	

}
