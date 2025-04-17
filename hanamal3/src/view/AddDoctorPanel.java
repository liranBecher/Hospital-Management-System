package view;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import enums.Specialization;
import java.awt.*;
import java.util.Date;
import java.util.HashSet;

public class AddDoctorPanel extends JPanel {
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
    private JTextField genderField;
    private transient JDateChooser workStartDateChooser;
    private JTextField salaryField;
    private JTextField licenseNumberField;
    private JCheckBox internshipCheckBox;
    private JComboBox<Specialization> specializationBox;

    public AddDoctorPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
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
        internshipCheckBox = new JCheckBox("Finished Internship?");
        specializationBox = new JComboBox<>(Specialization.values());

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
        add(internshipCheckBox, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Specialization:"), gbc);
        gbc.gridx = 1;
        add(specializationBox, gbc);

        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setPreferredSize(new Dimension(400, 500));
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

    public JCheckBox getInternshipCheckBox() {
        return internshipCheckBox;
    }

    public JComboBox<Specialization> getSpecializationBox() {
        return specializationBox;
    }
}
