package view;

import javax.swing.*;

import exceptions.*;

import java.awt.*;

public class AddMedicationPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private JTextField codeField;
    private JTextField nameField;
    private JTextField dosageField;
    private JTextField numberOfDoseField;

    public AddMedicationPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Field for Code
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Code:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        codeField = new JTextField(20);
        add(codeField, gbc);
        gbc.gridwidth = 1;

        // Field for Name
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        nameField = new JTextField(20);
        add(nameField, gbc);
        gbc.gridwidth = 1;

        // Field for Dosage
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Dosage:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        dosageField = new JTextField(20);
        add(dosageField, gbc);
        gbc.gridwidth = 1;

        // Field for Number of Doses
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Number of Doses:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        numberOfDoseField = new JTextField(20);
        add(numberOfDoseField, gbc);
        gbc.gridwidth = 1;
    }

    // Getters to retrieve user input
    public int getCode() {
        try {
            return Integer.parseInt(codeField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the code.");
            throw e; // Re-throw or handle appropriately
        }
    }

    public String getName() {
        return nameField.getText();
    }

    public double getDosage() {
        try {
            double dosage = Double.parseDouble(dosageField.getText());
            if (dosage < 0) {
                throw new NegativeDosageException(dosage);
            }
            return dosage;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the dosage.");
            throw e; // Re-throw or handle appropriately
        }
    }

    public int getNumberOfDose() {
        try {
            int numberOfDose = Integer.parseInt(numberOfDoseField.getText());
            if (numberOfDose < 0) {
                throw new NegativeNumberOfDosesException(numberOfDose);
            }
            return numberOfDose;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the number of doses.");
            throw e; // Re-throw or handle appropriately
        }
    }
}
