package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import enums.Specialization;
import model.Doctor;
import model.StaffMember;

public class AddDepartmentPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField numberField;
    private JTextField nameField;
    private JTextField locationField;
    private JComboBox<Specialization> specializationBox;
    private JComboBox<String> managerBox; 
    private Map<String, Doctor> doctorMap;

    public AddDepartmentPanel(HashMap<Integer, StaffMember> doctors) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        numberField = new JTextField(5);
        nameField = new JTextField(10);
        locationField = new JTextField(15);
        specializationBox = new JComboBox<>(Specialization.values());
        doctorMap = doctors.values().stream()
        		.filter(doctor -> doctor instanceof Doctor)
                .collect(Collectors.toMap(
                    doctor -> doctor.getFirstName() + " " + doctor.getLastName(),
                    doctor -> (Doctor)doctor
                ));
        managerBox = new JComboBox<>(doctorMap.keySet().toArray(new String[0]));

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Number:"), gbc);
        gbc.gridx = 1;
        add(numberField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        add(locationField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Specialization:"), gbc);
        gbc.gridx = 1;
        add(specializationBox, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Manager:"), gbc);
        gbc.gridx = 1;
        add(managerBox, gbc);

        // Wrap in a scroll pane if the panel gets too large
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setPreferredSize(new Dimension(400, 500));
    }

    public int getNumber() {
    	try {
            return Integer.parseInt(numberField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return -1;  // Indicate invalid input
        }

    }

    public String getName() {
        return nameField.getText();
    }

    public String getTheLocation() {
        return locationField.getText();
    }

    public Specialization getSpecialization() {
        return (Specialization) specializationBox.getSelectedItem();
    }

    public Doctor getManager() {
        return  doctorMap.get(managerBox.getSelectedItem());
    }
}
