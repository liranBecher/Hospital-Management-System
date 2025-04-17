//EX3_212002000_327690947

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import control.*;
import enums.*;
import model.Department;
import model.Doctor;
import model.*;

public class QueriesPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Hospital hospital;
    private UserRole userRole;

    public QueriesPanel(Hospital hospital, UserRole userRole) {
        this.hospital = hospital;
        this.userRole = userRole;
        setLayout(new GridLayout(0, 1, 10, 10)); // Adjust grid size based on your layout needs

        // Query 1: Method 36 - Number of patients whose visits ended before a given date
        JButton btnHowManyVisitBefore = new JButton("How Many Visits Before Date");
        btnHowManyVisitBefore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleHowManyVisitBefore();
            }
        });
        add(btnHowManyVisitBefore);

        // Query 2: Method 33 - Number of medications within a dosage range
        JButton btnCountMedications = new JButton("Count Medications by Dosage Range");
        btnCountMedications.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCountMedications();
            }
        });
        add(btnCountMedications);

        // Query 3: Method 40 - Number of doctors by specialization (Admin only)
        JButton btnDoctorsBySpecialization = new JButton("Doctors by Specialization");
        btnDoctorsBySpecialization.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDoctorsBySpecialization();
            }
        });
        btnDoctorsBySpecialization.setEnabled(userRole == UserRole.ADMIN);
        add(btnDoctorsBySpecialization);

        // Queries 4.1 to 4.4 displayed side by side
        JPanel panelStats = new JPanel(new GridLayout(1, 4, 10, 10));

        // Query 4.1: Method 41 - Intensive care qualified staff count
        JButton btnIntensiveCareStaff = new JButton("Intensive Care Staff Count");
        btnIntensiveCareStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleIntensiveCareStaffCount();
            }
        });
        panelStats.add(btnIntensiveCareStaff);

        // Query 4.2: Method 42 - Average salary of staff members
        JButton btnAverageSalary = new JButton("Average Salary");
        btnAverageSalary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAverageSalary();
            }
        });
        panelStats.add(btnAverageSalary);

        // Query 4.3: Method 43 - Compliance with the Ministry of Health Standard
        JButton btnComplianceCheck = new JButton("Health Ministry Compliance Check");
        btnComplianceCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleComplianceCheck();
            }
        });
        panelStats.add(btnComplianceCheck);

        // Query 4.4: Method 44 - Appoint a new manager (Admin only)
        JButton btnAppointManager = new JButton("Appoint New Manager");
        btnAppointManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAppointNewManager();
            }
        });
        btnAppointManager.setEnabled(userRole == UserRole.ADMIN);
        panelStats.add(btnAppointManager);

        add(panelStats);
    }

    private void handleHowManyVisitBefore() {
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDate(new Date());
        int result = JOptionPane.showConfirmDialog(this, dateChooser, "Select Date", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Date selectedDate = dateChooser.getDate();  // Get the selected date from the date chooser
            
            if (selectedDate != null) {
                // Call the hospital method with the selected date
                int count = hospital.howManyVisitBefore(selectedDate);
                JOptionPane.showMessageDialog(this, "Number of visits before " + selectedDate + ": " + count);
            } else {
                JOptionPane.showMessageDialog(this, "No date selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleCountMedications() {
        // Show dialog to input min and max dosage
        double minDosage = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter minimum dosage:"));
        double maxDosage = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter maximum dosage:"));
        int count = hospital.countMedications(minDosage, maxDosage);
        JOptionPane.showMessageDialog(this, "Number of medications with dosage between " + minDosage + " and " + maxDosage + ": " + count);
    }

    private void handleDoctorsBySpecialization() {
        HashMap<Specialization, Integer> doctorCountBySpec = hospital.getNumberOfDoctorsBySpecialization();
        StringBuilder result = new StringBuilder("Doctors by Specialization:\n");
        for (Specialization spec : doctorCountBySpec.keySet()) {
            result.append(spec).append(": ").append(doctorCountBySpec.get(spec)).append("\n");
        }
        JOptionPane.showMessageDialog(this, result.toString());
    }

    private void handleIntensiveCareStaffCount() {
        int count = hospital.howManyIntensiveCareStaffMembers();
        JOptionPane.showMessageDialog(this, "Intensive Care Qualified Staff Members: " + count);
    }

    private void handleAverageSalary() {
        double avgSalary = hospital.avgSalary();
        JOptionPane.showMessageDialog(this, "Average Salary of Staff: " + avgSalary);
    }

    private void handleComplianceCheck() {
        boolean complies = hospital.isCompliesWithTheMinistryOfHealthStandard();
        JOptionPane.showMessageDialog(this, "Complies with Ministry of Health Standard: " + (complies ? "Yes" : "No"));
    }

    private void handleAppointNewManager() {
        // Show dialog to select department
        Department selectedDepartment = (Department) JOptionPane.showInputDialog(this, "Select Department:",
                "Appoint New Manager", JOptionPane.QUESTION_MESSAGE, null, hospital.getDepartments().values().toArray(), null);
        if (selectedDepartment != null) {
            Doctor newManager = hospital.AppointANewManager(selectedDepartment);
            JOptionPane.showMessageDialog(this, "New Manager Appointed: " + newManager.shortToString());
        }
    }
}
