package view;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import enums.Specialization;
import model.Doctor;
import model.Nurse;

import java.awt.*;
import java.util.Date;

public class EditNursePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idField, firstNameField, lastNameField, addressField, phoneNumberField, emailField, genderField, salaryField, licenseNumberField;
    private JDateChooser birthDateChooser, workStartDateChooser;
    private JCheckBox internshipCheckBox, intensiveCareCheckBox;
    private JComboBox<Specialization> specializationBox;

    public EditNursePanel(Nurse nurse) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        idField = new JTextField(5);
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        birthDateChooser = new JDateChooser();
        addressField = new JTextField(15);
        phoneNumberField = new JTextField(10);
        emailField = new JTextField(15);
        genderField = new JTextField(5);
        workStartDateChooser = new JDateChooser();
        salaryField = new JTextField(7);
        licenseNumberField = new JTextField(5);      
        intensiveCareCheckBox = new JCheckBox("Set for Intensive Care");

        // Add components to panel using GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        add(firstNameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        add(lastNameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Birth Date:"), gbc);
        gbc.gridx = 1;
        add(birthDateChooser, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        add(addressField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        add(phoneNumberField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        add(genderField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Work Start Date:"), gbc);
        gbc.gridx = 1;
        add(workStartDateChooser, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Salary:"), gbc);
        gbc.gridx = 1;
        add(salaryField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("License Number:"), gbc);
        gbc.gridx = 1;
        add(licenseNumberField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(intensiveCareCheckBox, gbc);

        // Initialize the fields with Doctor's data
        idField.setText(String.valueOf(nurse.getId()));
        firstNameField.setText(nurse.getFirstName());
        lastNameField.setText(nurse.getLastName());
        birthDateChooser.setDate(nurse.getBirthDate());
        addressField.setText(nurse.getAddress());
        phoneNumberField.setText(nurse.getPhoneNumber());
        emailField.setText(nurse.getEmail());
        genderField.setText(nurse.getGender());
        workStartDateChooser.setDate(nurse.getWorkStartDate());
        salaryField.setText(String.valueOf(nurse.getSalary()));
        licenseNumberField.setText(String.valueOf(nurse.getLicenseNumber()));
    }



    public int getIdField() {
        return Integer.parseInt(idField.getText().trim());
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JDateChooser getBirthDateChooser() {
        return birthDateChooser;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getGenderField() {
        return genderField;
    }

    public JDateChooser getWorkStartDateChooser() {
        return workStartDateChooser;
    }

    public JTextField getSalaryField() {
        return salaryField;
    }

    public JTextField getLicenseNumberField() {
        return licenseNumberField;
    }

    public JCheckBox getIntensiveCareCheckBox() {
        return intensiveCareCheckBox;
    }
}
