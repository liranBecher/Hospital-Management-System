package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import control.Hospital;
import enums.Specialization;

public class IntensiveCareNurse extends Nurse implements IntensiveCareStaffMember, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;

	public IntensiveCareNurse(int id, String firstName, String lastName, Date birthDate, String address,
			String phoneNumber, String email, String gender, Date workStartDate, double salary, int licenseNumber) {
		super(id, firstName, lastName, birthDate, address, phoneNumber, email,
				gender, workStartDate, salary, licenseNumber);
		Department department= Hospital.getInstance().searchDepartmentBySpecialization(Specialization.IntensiveCare);
		department.addNurse(this);
	}
	
	public IntensiveCareNurse(int id, String firstName, String lastName, Date birthDate, String address, String phoneNumber,
			String email, String gender, Date workStartDate,
			HashSet<Department> departments, double salary, int licenseNumber) {
		super(id, firstName, lastName, birthDate, address, phoneNumber, email, gender, workStartDate,
				departments, salary,licenseNumber);
		Department department= Hospital.getInstance().searchDepartmentBySpecialization(Specialization.IntensiveCare);
		department.addNurse(this);
	}
	
	@Override
	public boolean removeDepartment(Department department) {
		//The method prevents deletion of the department if it is an intensive care department
		if(department==null||department.getSpecialization().equals(Specialization.IntensiveCare)) {
			return false;
		}
		return super.removeDepartment(department);
	}
	
	@Override
	public String toString() {
		return "IntensiveCare" + super.toString();
	}
	
	@Override
	public Department getIntensiveCareDepartment() {
		// TODO Auto-generated method stub
		return super.getDepartmentBySpecialization(Specialization.IntensiveCare);
	}
	
	
}
