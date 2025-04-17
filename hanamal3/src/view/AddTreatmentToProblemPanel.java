package view;
 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
 
import model.*;
import control.*;
 
public class AddTreatmentToProblemPanel extends JPanel {
 
    private static final long serialVersionUID = 1L;
 
    private JComboBox<MedicalProblem> medicalProblemsComboBox;
    private JComboBox<Treatment> treatComboBox;
    private MedicalProblem selected;
 
    private List<MedicalProblem> medicalProblems;
 
    public AddTreatmentToProblemPanel(Hospital hospital) {
        medicalProblems = new ArrayList<>(Hospital.getInstance().getMedicalProblems().values());
 
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
 
        // Setup combo boxes for each type
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 3; // To align combo boxes under all radio buttons
        medicalProblemsComboBox = new JComboBox<>(medicalProblems.toArray(new MedicalProblem[0]));
 
        // Add combo boxes to panel, initially set visibility
        medicalProblemsComboBox.setVisible(true);
        add(medicalProblemsComboBox, gbc);
 
        // Add action listeners for combo box to update the selected item
        medicalProblemsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = (MedicalProblem) medicalProblemsComboBox.getSelectedItem();
                revalidate();  // Revalidate panel to update layout
                repaint();     // Repaint panel to reflect changes
            }
        });
 
        // Combo box for selecting treatment
        gbc.gridx = 0; gbc.gridy = 2; 
        gbc.gridwidth = 1; // Reset gridwidth for the rest of the components
        add(new JLabel("Select treatment:"), gbc);
 
        gbc.gridx = 1; gbc.gridwidth = 2;
        treatComboBox = new JComboBox<>(hospital.getTreatments().values().toArray(new Treatment[0]));
        treatComboBox.setPreferredSize(new Dimension(300, treatComboBox.getPreferredSize().height));
 
        // Set the first item as the default selected item
        if (treatComboBox.getItemCount() > 0) {
            treatComboBox.setSelectedIndex(0);
        }
 
        add(treatComboBox, gbc);
 
        setPreferredSize(new Dimension(400, 200));
    }
 
    public MedicalProblem getSelectedMedicalProblem() {
        return selected;
    }
 
    public Treatment getSelectedTreatment() {
        Treatment selectedTreatment = (Treatment) treatComboBox.getSelectedItem();
        if (selectedTreatment == null) {
            // Handle null case, e.g., log a message or throw an exception
            System.out.println("No treatment selected");
        }
        return selectedTreatment;
    }
}