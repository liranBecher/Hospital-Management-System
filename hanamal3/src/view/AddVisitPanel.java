package view;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.toedter.calendar.JDateChooser;
import model.*;

public class AddVisitPanel extends JPanel {
    private JTextField visitNumberField;
    private JComboBox<Patient> patientComboBox;
    private transient JDateChooser startDateChooser;
    private transient JDateChooser endDateChooser;

    public AddVisitPanel(Collection<Patient> patients) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Visit Number
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Visit Number:"), gbc);
        gbc.gridx = 1;
        visitNumberField = new JTextField(10);
        add(visitNumberField, gbc);

        // Patient Selection
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Patient:"), gbc);
        gbc.gridx = 1;
        patientComboBox = new JComboBox<>(patients.toArray(new Patient[0]));
        add(patientComboBox, gbc);

        // Start Date
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Start Date:"), gbc);
        gbc.gridx = 1;
        startDateChooser = new JDateChooser();
        add(startDateChooser, gbc);

        // End Date
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("End Date:"), gbc);
        gbc.gridx = 1;
        endDateChooser = new JDateChooser();
        add(endDateChooser, gbc);

        patientComboBox.setPreferredSize(new Dimension(150, 25));

    }

    // Methods to retrieve data from input fields
    public int getVisitNumber() {
        return Integer.parseInt(visitNumberField.getText().trim());
    }

    public Patient getSelectedPatient() {
        return (Patient) patientComboBox.getSelectedItem();
    }

    public JDateChooser getStartDate() {
        return startDateChooser;
    }

    public JDateChooser getEndDate() {
        return endDateChooser;
    }

}
