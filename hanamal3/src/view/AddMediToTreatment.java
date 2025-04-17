package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.JPanel;

import model.*;
import control.*;

public class AddMediToTreatment extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<Medication> medicationComboBox;
    private JComboBox<Treatment> treatmentComboBox;
    
	public AddMediToTreatment(Hospital hospital) {
		 setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();
		 gbc.insets = new Insets(5, 5, 5, 5); 
		 gbc.fill = GridBagConstraints.HORIZONTAL;
		 
		 	// Combo box for selecting Medication
	        gbc.gridx = 0; gbc.gridy = 0;
	        add(new JLabel("Select Medication:"), gbc);
	        gbc.gridx = 1; gbc.gridwidth = 2;
	        medicationComboBox = new JComboBox<>(hospital.getMedications().values().toArray(new Medication[0]));
	        add(medicationComboBox, gbc);
	        medicationComboBox.setPreferredSize(new Dimension(300, medicationComboBox.getPreferredSize().height));
	        gbc.gridwidth = 1;

	        // Combo box for selecting treatment
	        gbc.gridx = 0; gbc.gridy++;
	        add(new JLabel("Select treatment:"), gbc);
	        gbc.gridx = 1; gbc.gridwidth = 2;
	        treatmentComboBox = new JComboBox<>(hospital.getTreatments().values().toArray(new Treatment[0]));
	        add(treatmentComboBox, gbc);
	        treatmentComboBox.setPreferredSize(new Dimension(300, treatmentComboBox.getPreferredSize().height));
	        gbc.gridwidth = 1;

	        setPreferredSize(new Dimension(378, 150));
	}

	    // Additional getters if needed
	    public Medication getSelectedMedication() {
	        return (Medication) medicationComboBox.getSelectedItem();
	    }

	    public Treatment getSelectedTreatment() {
	        return (Treatment) treatmentComboBox.getSelectedItem();
	    }
	}
	
	
