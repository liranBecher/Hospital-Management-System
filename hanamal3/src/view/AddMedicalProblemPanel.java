package view;

import javax.swing.*;
import java.awt.*;
import model.Department;
import model.Fracture;
import model.Injury;
import model.Disease;
import enums.Specialization;

import java.util.HashMap;
import java.util.List;

public class AddMedicalProblemPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JRadioButton fractureButton;
    private JRadioButton injuryButton;
    private JRadioButton diseaseButton;

    private JTextField nameField; //medical problem
    private JComboBox<Department> departmentComboBox; //medical problem
    private JTextField locationField; //fracture+injury
    private JLabel locationJLabel;
    private JTextField commonRecoveryTimeField; //injury
    private JLabel recoveryJLabel;
    private JTextField descriptionField; //disease
    private JLabel descriptionJLabel;
    private JCheckBox requiresCastCheckBox; //fracture
    private JLabel castJLabel;
    

    public AddMedicalProblemPanel(HashMap<Integer, Department> departments) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Radio buttons for selecting type of MedicalProblem
        fractureButton = new JRadioButton("Fracture");
        diseaseButton = new JRadioButton("Disease");
        injuryButton = new JRadioButton("Injury");

        ButtonGroup group = new ButtonGroup();
        group.add(fractureButton);
        group.add(injuryButton);
        group.add(diseaseButton);

        gbc.gridx = 0; gbc.gridy = 0;
        add(fractureButton, gbc);
        gbc.gridx = 1;
        add(diseaseButton, gbc);
        gbc.gridx = 2;
        add(injuryButton, gbc);

        // Fields common to all types
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        nameField = new JTextField(20);
        add(nameField, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Department:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        departmentComboBox = new JComboBox<>(departments.values().toArray(new Department[0]));
        add(departmentComboBox, gbc);
        gbc.gridwidth = 1;
        departmentComboBox.setPreferredSize(new Dimension(300, departmentComboBox.getPreferredSize().height));


        // Fields specific to each type
        gbc.gridx = 0; gbc.gridy++;
        locationJLabel = new JLabel("Location:");
        add(locationJLabel, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        locationField = new JTextField(20);
        add(locationField, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy++;
        castJLabel = new JLabel("Requires Cast:");
        add(castJLabel, gbc);
        gbc.gridx = 1;
        requiresCastCheckBox = new JCheckBox();
        add(requiresCastCheckBox, gbc);

        gbc.gridx = 0; gbc.gridy++;
        recoveryJLabel = new JLabel("Common Recovery Time:");
        add(recoveryJLabel, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        commonRecoveryTimeField = new JTextField(20);
        add(commonRecoveryTimeField, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy++;
        descriptionJLabel = new JLabel("Description:");
        add(descriptionJLabel, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        descriptionField = new JTextField(20);
        add(descriptionField, gbc);
        gbc.gridwidth = 1;

        departmentComboBox.setPreferredSize(new Dimension(150, 25));

        //show/hide fields based on selection
        fractureButton.addActionListener(e -> updateFields("Fracture"));
        injuryButton.addActionListener(e -> updateFields("Injury"));
        diseaseButton.addActionListener(e -> updateFields("Disease"));

        // default state
        updateFields("Fracture");
        
        setPreferredSize(new Dimension(378, 227));
    }

    private void updateFields(String type) {
        boolean isFracture = "Fracture".equals(type);
        boolean isInjury = "Injury".equals(type);
        boolean isDisease = "Disease".equals(type);

        locationField.setVisible(isFracture || isInjury);
        locationJLabel.setVisible(isFracture || isInjury);
        requiresCastCheckBox.setVisible(isFracture);
        castJLabel.setVisible(isFracture);
        commonRecoveryTimeField.setVisible(isInjury);
        recoveryJLabel.setVisible(isInjury);
        descriptionField.setVisible(isDisease);
        descriptionJLabel.setVisible(isDisease);
        
    }

    public String getSelectedType() {
        if (fractureButton.isSelected()) return "Fracture";
        if (injuryButton.isSelected()) return "Injury";
        if (diseaseButton.isSelected()) return "Disease";
        return null;
    }

    public String getName() {
        return nameField.getText();
    }

    public String getTheLocation() {
        return locationField.getText();
    }

    public double getCommonRecoveryTime() {
        return Double.parseDouble(commonRecoveryTimeField.getText());
    }

    public String getDescription() {
        return descriptionField.getText();
    }

    public boolean isRequiresCast() {
        return requiresCastCheckBox.isSelected();
    }

    public Department getDepartment() {
        return (Department) departmentComboBox.getSelectedItem();
    }
}
