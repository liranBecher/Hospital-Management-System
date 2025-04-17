package view;

import java.awt.*;
import javax.swing.*;
import control.*;
import model.*;

public class AddVisitToPatientPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JComboBox<Patient> patientComboBox;
    private JComboBox<Visit> visitComboBox;

    public AddVisitToPatientPanel(Hospital hospital) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Combo box for selecting patient
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Select Patient:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        patientComboBox = new JComboBox<>(hospital.getPatients().values().toArray(new Patient[0]));
        add(patientComboBox, gbc);
        patientComboBox.setPreferredSize(new Dimension(300, 25));
        gbc.gridwidth = 1;

        // Combo box for selecting visit
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Select Visit:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        visitComboBox = new JComboBox<>(hospital.getVisits().values().toArray(new Visit[0]));
        add(visitComboBox, gbc);
        visitComboBox.setPreferredSize(new Dimension(300, 25));
        gbc.gridwidth = 1;

        setPreferredSize(new Dimension(378, 150));
    }

    public Patient getSelectedPatient() {
        return (Patient) patientComboBox.getSelectedItem();
    }

    public Visit getSelectedVisit() {
        return (Visit) visitComboBox.getSelectedItem();
    }
}
