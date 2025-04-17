package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import model.StaffMember;

public class AddUserPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> staffMemberBox;
    private HashMap<Integer, StaffMember> staffMembers;
    private Map<String, StaffMember> staffMemberMap;

    public AddUserPanel(HashMap<Integer, StaffMember> staffMembers) {
        this.staffMembers = staffMembers;

        // Initialize layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Initialize components
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        staffMemberMap = staffMembers.values().stream()
                .collect(Collectors.toMap(
                    staff -> staff.getFirstName() + " " + staff.getLastName(),
                    staff -> staff
                ));
        staffMemberBox = new JComboBox<>(staffMemberMap.keySet().toArray(new String[0]));

        // Add components to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Select Staff Member:"), gbc);
        gbc.gridx = 1;
        add(staffMemberBox, gbc);

        // Set preferred size of the panel
        setPreferredSize(new Dimension(400, 200));
    }

    // Getter methods
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        // Convert char array to string for simplicity, avoid in production code due to security reasons
        return new String(passwordField.getPassword());
    }

    public StaffMember getSelectedStaffMember() {
        return staffMemberMap.get(staffMemberBox.getSelectedItem());
    }
}
