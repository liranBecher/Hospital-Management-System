package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import enums.*;

public class AddTreatmentPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField serialNumberField;
    private JTextField descriptionField;

    public AddTreatmentPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Serial Number
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Serial Number:"), gbc);
        gbc.gridx = 1;
        serialNumberField = new JTextField(10);
        add(serialNumberField, gbc);

        // Description
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        descriptionField = new JTextField(20);
        add(descriptionField, gbc);
    }

    // Methods to retrieve data from input fields
    public int getSerialNumber() {
        return Integer.parseInt(serialNumberField.getText().trim());
    }

    public String getDescription() {
        return descriptionField.getText().trim();
    }

}

