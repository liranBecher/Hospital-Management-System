package view;

import javax.swing.*;

import org.apache.poi.xwpf.usermodel.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import control.Hospital;
import model.Patient;
import model.Visit;

public class ExportToWordPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Hospital hospital; // Declare hospital field

    /**
     * Create the panel.
     */
    public ExportToWordPanel(Hospital hospital) {
        this.hospital = hospital;
        setLayout(new BorderLayout()); // Use BorderLayout for automatic positioning

        // Initialize components
        JButton exportButton = new JButton("Export to Word");
        JComboBox<Patient> patientComboBox = new JComboBox<>(hospital.getPatients().values().toArray(new Patient[0]));
        patientComboBox.setPreferredSize(new Dimension(300, patientComboBox.getPreferredSize().height));

        
        // Create a panel for components
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Patient:"));
        topPanel.add(patientComboBox);
        topPanel.add(exportButton);

        // Add components to the panel
        add(topPanel, BorderLayout.CENTER); // Center area of the BorderLayout

        // Add action listener to the export button
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = patientComboBox.getSelectedItem();
                if (selectedItem instanceof Patient) {
                    Patient selectedPatient = (Patient) selectedItem;
                    StringBuilder message = new StringBuilder();
                    message.append(selectedPatient.toString()).append("\n\n");

                    // Add visit details
                    for (Visit visit : selectedPatient.getVisitsList()) {
                        message.append(visit.toString()).append("\n");
                    }

                    exportToWord(message.toString());
                } else {
                    JOptionPane.showMessageDialog(ExportToWordPanel.this, "Selected item is not a patient.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void exportToWord(String message) {
        if (message == null || message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No content to export.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // Ensure the file ends with .docx
            if (!selectedFile.getName().toLowerCase().endsWith(".docx")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".docx");
            }

            // Confirm overwrite if the file already exists
            if (selectedFile.exists()) {
                int overwriteOption = JOptionPane.showConfirmDialog(
                    this, 
                    "The file already exists. Do you want to overwrite it?", 
                    "Confirm Overwrite", 
                    JOptionPane.YES_NO_OPTION
                );
                if (overwriteOption == JOptionPane.NO_OPTION) {
                    return;
                }
            }

            try (XWPFDocument document = new XWPFDocument()) {
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(message);

                try (FileOutputStream out = new FileOutputStream(selectedFile)) {
                    document.write(out);
                    JOptionPane.showMessageDialog(this, "Export successful!");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error exporting to Word: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
