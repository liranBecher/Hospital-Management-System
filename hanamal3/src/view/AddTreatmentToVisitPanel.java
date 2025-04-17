package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.JPanel;

import model.*;
import control.*;

public class AddTreatmentToVisitPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<Treatment> treatComboBox;
    private JComboBox<Visit> visitComboBox;
    
	public AddTreatmentToVisitPanel(Hospital hospital) {
		 setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();
		 gbc.insets = new Insets(5, 5, 5, 5); 
		 gbc.fill = GridBagConstraints.HORIZONTAL;
		 
		 	// Combo box for selecting problem
	        gbc.gridx = 0; gbc.gridy = 0;
	        add(new JLabel("Select Medical problem:"), gbc);
	        gbc.gridx = 1; gbc.gridwidth = 2;
	        treatComboBox = new JComboBox<>(hospital.getTreatments().values().toArray(new Treatment[0]));
	        add(treatComboBox, gbc);
	        treatComboBox.setPreferredSize(new Dimension(150, 25));
	        gbc.gridwidth = 1;

	        // Combo box for selecting treatment
	        gbc.gridx = 0; gbc.gridy++;
	        add(new JLabel("Select visit:"), gbc);
	        gbc.gridx = 1; gbc.gridwidth = 2;
	        visitComboBox = new JComboBox<>(hospital.getVisits().values().toArray(new Visit[0]));
	        add(visitComboBox, gbc);
	        visitComboBox.setPreferredSize(new Dimension(150, 25));
	        gbc.gridwidth = 1;

	        setPreferredSize(new Dimension(378, 150));
	}

	    // Additional getters if needed
	    public Treatment getSelectedTreatment() {
	        return (Treatment) treatComboBox.getSelectedItem();
	    }

	    public Visit getSelectedVisit() {
	        return (Visit) visitComboBox.getSelectedItem();
	    }
	}
	
	
