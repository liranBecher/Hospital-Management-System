package view;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;

import enums.*;

import java.util.Date;

public class AddPatientPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private transient JDateChooser birthDateChooser;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JComboBox<String> genderComboBox;
    private JComboBox<HealthFund> healthFundComboBox;
    private JComboBox<BiologicalSex> biologicalSexComboBox;

    public AddPatientPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(10);
        add(idField, gbc);

        // First Name
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        firstNameField = new JTextField(10);
        add(firstNameField, gbc);

        // Last Name
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        lastNameField = new JTextField(10);
        add(lastNameField, gbc);

        // Birth Date using JDateChooser
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Birth Date:"), gbc);
        gbc.gridx = 1;
        birthDateChooser = new JDateChooser();
        birthDateChooser.setDateFormatString("dd/MM/yyyy"); // Set the date format
        add(birthDateChooser, gbc);

        // Address
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        addressField = new JTextField(10);
        add(addressField, gbc);

        // Phone Number
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        phoneNumberField = new JTextField(10);
        add(phoneNumberField, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(10);
        add(emailField, gbc);

        // Gender
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        add(genderComboBox, gbc);

        // Health Fund
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Health Fund:"), gbc);
        gbc.gridx = 1;
        healthFundComboBox = new JComboBox<>(HealthFund.values());
        add(healthFundComboBox, gbc);

        // Biological Sex
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Biological Sex:"), gbc);
        gbc.gridx = 1;
        biologicalSexComboBox = new JComboBox<>(BiologicalSex.values());
        add(biologicalSexComboBox, gbc);
    }

    // Methods to retrieve data from input fields
    public int getId() {
        return Integer.parseInt(idField.getText().trim());
    }

    public String getFirstName() {
        return firstNameField.getText().trim();
    }

    public String getLastName() {
        return lastNameField.getText().trim();
    }

    public JDateChooser getBirthDate() {
        return birthDateChooser;
    }

    public String getAddress() {
        return addressField.getText().trim();
    }

    public String getPhoneNumber() {
        return phoneNumberField.getText().trim();
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public String getGender() {
        return (String) genderComboBox.getSelectedItem();
    }

    public HealthFund getHealthFund() {
        return (HealthFund) healthFundComboBox.getSelectedItem();
    }

    public BiologicalSex getBiologicalSex() {
        return (BiologicalSex) biologicalSexComboBox.getSelectedItem();
    }
}
