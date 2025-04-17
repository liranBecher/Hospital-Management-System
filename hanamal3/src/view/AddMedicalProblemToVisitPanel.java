package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;

import model.*;
import control.*;

public class AddMedicalProblemToVisitPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<Fracture> fraComboBox;
    private JComboBox<Injury> injComboBox;
    private JComboBox<Disease> disComboBox;
    private JComboBox<Visit> visitComboBox;
    private JRadioButton fractureRadioButton;
    private JRadioButton injuryRadioButton;
    private JRadioButton diseaseRadioButton;
    private MedicalProblem selected;

    
    private List<Fracture> fractureList;

    private List<Injury> injuryList;

    private List<Disease> diseaseList;
    
	public AddMedicalProblemToVisitPanel(Hospital hospital) {
		fractureList = Hospital.getInstance().getMedicalProblems().values().stream()
    	        .filter(medicalProblem -> medicalProblem instanceof Fracture)
    	        .map(medicalProblem -> (Fracture) medicalProblem)
    	        .collect(Collectors.toList());
    	
    	injuryList = Hospital.getInstance().getMedicalProblems().values().stream()
    	        .filter(medicalProblem -> medicalProblem instanceof Injury)
    	        .map(medicalProblem -> (Injury) medicalProblem)
    	        .collect(Collectors.toList());
    	
    	diseaseList = Hospital.getInstance().getMedicalProblems().values().stream()
    	        .filter(medicalProblem -> medicalProblem instanceof Disease)
    	        .map(medicalProblem -> (Disease) medicalProblem)
    	        .collect(Collectors.toList());
    	
    	
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Setup radio buttons and add to panel
        fractureRadioButton = new JRadioButton("Fracture");
        injuryRadioButton = new JRadioButton("Injury");
        diseaseRadioButton = new JRadioButton("Disease");

        ButtonGroup group = new ButtonGroup();
        group.add(fractureRadioButton);
        group.add(injuryRadioButton);
        group.add(diseaseRadioButton);

        gbc.gridx = 0; gbc.gridy = 0;
        add(fractureRadioButton, gbc);
        gbc.gridx = 1;
        add(injuryRadioButton, gbc);
        gbc.gridx = 2;
        add(diseaseRadioButton, gbc);

        // Setup combo boxes for each type
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 3; // To align combo boxes under all radio buttons
        fraComboBox = new JComboBox<>(fractureList.toArray(new Fracture[0]));
        injComboBox = new JComboBox<>(injuryList.toArray(new Injury[0]));
        disComboBox = new JComboBox<>(diseaseList.toArray(new Disease[0]));

        // Add combo boxes to panel, initially set visibility
        fraComboBox.setVisible(false);
        injComboBox.setVisible(false);
        disComboBox.setVisible(false);
        
        fraComboBox.setPreferredSize(new Dimension(150, 25));
        injComboBox.setPreferredSize(new Dimension(150, 25));
        disComboBox.setPreferredSize(new Dimension(150, 25));

        
        add(fraComboBox, gbc);
        add(injComboBox, gbc);
        add(disComboBox, gbc);
        

        // Add action listeners for radio buttons to toggle combo box visibility
        fractureRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fractureRadioButton.isSelected()) {
                    fraComboBox.setVisible(true);
                    injComboBox.setVisible(false);
                    disComboBox.setVisible(false);
                    selected = (MedicalProblem) fraComboBox.getSelectedItem();
                }
                revalidate();  // Revalidate panel to update layout
                repaint();     // Repaint panel to reflect changes
            }
        });

        injuryRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (injuryRadioButton.isSelected()) {
                    fraComboBox.setVisible(false);
                    injComboBox.setVisible(true);
                    disComboBox.setVisible(false);
                    selected = (MedicalProblem) injComboBox.getSelectedItem();
                }
                revalidate();  // Revalidate panel to update layout
                repaint();     // Repaint panel to reflect changes
            }
        });

        diseaseRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (diseaseRadioButton.isSelected()) {
                    fraComboBox.setVisible(false);
                    injComboBox.setVisible(false);
                    disComboBox.setVisible(true);
                    selected = (MedicalProblem) disComboBox.getSelectedItem();
                }
                revalidate();  // Revalidate panel to update layout
                repaint();     // Repaint panel to reflect changes
            }
        });

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
	    public MedicalProblem getSelectedMedicalProblem() {
	        return (MedicalProblem) selected;
	    }

	    public Visit getSelectedVisit() {
	        return (Visit) visitComboBox.getSelectedItem();
	    }
	}
	
	
