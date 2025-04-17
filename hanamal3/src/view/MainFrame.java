//EX3_212002000_327690947

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.regex.*;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

import com.toedter.calendar.JDateChooser;

import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import control.Hospital;
import control.User;
import enums.HealthFund;
import enums.Specialization;
import enums.*;
import exceptions.*;
import model.*;

import javax.swing.*;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Point;
import java.awt.Toolkit;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 20L;
	private JPanel contentPane;
	private Hospital hospital;
    private User currentUser;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        

	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MainFrame(User user) throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/view/resources/hanamal-favicon-color (1).png")));
		getContentPane().setEnabled(false);
        currentUser = user;
        hospital = Hospital.getInstance();
        initializeUI(user);
    }

	private void initializeUI(User user) {
        setTitle("Hanamal Management System - " + currentUser.getRole());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
     // Main panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(249, 246, 237));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(null);
        
        // Icon
        ImageIcon iconOrg = new ImageIcon(getClass().getResource("/view/resources/logo-no-background.png"));
        Image icon = iconOrg.getImage().getScaledInstance(167, 40, Image.SCALE_SMOOTH);
        ImageIcon finalIcon = new ImageIcon(icon);
        JLabel iconLabel = new JLabel(finalIcon);
        iconLabel.setBounds(609, 0, 167, 40);
        panel.add(iconLabel);

        // Greeting message
        JLabel greetingLabel = new JLabel("Hello, " + user.getUsername());
        greetingLabel.setBounds(462, 10, 300, 30);
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        greetingLabel.setForeground(new Color(0, 0, 0));
        panel.add(greetingLabel);
        
        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        setJMenuBar(menuBar);
        menuBar.setBackground(Color.decode("#0f3054"));
        menuBar.setForeground(Color.decode("111111"));

        // Menu item color and font
        Color itemColor = Color.decode("#111111");
        Font menuFont = new Font("Arial", Font.BOLD, 16);
        
        // File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(menuFont);
        fileMenu.setForeground(new Color(249, 246, 237));
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> {
            try {
                Main.save();  // Save data before exiting
                System.exit(0);  // Exit the application
            } catch (IOException ioException) {
                ioException.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving data: " + ioException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					Main.save();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                LoginScreen gi = new LoginScreen();
                gi.setVisible(true);
                dispose();            
            }
        });
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> {
            try {
                Main.save();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        fileMenu.add(exitItem);
        fileMenu.add(logoutItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);

        // Admin Menu (for admin users)
        if (currentUser.getRole() == UserRole.ADMIN) {
        	
        	JMenuItem addUserItem = new JMenuItem("Add User");
        	addUserItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					addUser();					
				}
			});
        	fileMenu.add(addUserItem);
        	
            JMenu adminMenu = new JMenu("Admin");
            adminMenu.setFont(menuFont);
            adminMenu.setForeground(new Color(249, 246, 237));
            JMenuItem addDataItem = new JMenuItem("Add Data");
            addDataItem.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                	addAdminData();
                }
            });
            JMenuItem viewDataItem = new JMenuItem("View Data");
            viewDataItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					viewAdminData();
				}
			});
            JMenuItem removeDateItem = new JMenuItem("Remove Data");
            removeDateItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					removeAdminData();					
				}
			});
            adminMenu.add(addDataItem);
            adminMenu.add(viewDataItem);
            adminMenu.add(removeDateItem);
            menuBar.add(adminMenu);
        }
        
        // Doctor Menu (for doctor users)
        if (currentUser.getRole() == UserRole.DOCTOR) {
            JMenu doctorMenu = new JMenu("Doctor");
            doctorMenu.setFont(menuFont);
            doctorMenu.setForeground(new Color(249, 246, 237));
            JMenuItem addDataItem = new JMenuItem("Add Data");
            addDataItem.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                	addDoctorData();
                }
            });
            JMenuItem viewDataItem = new JMenuItem("View Data");
            viewDataItem.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					viewDataForStaff();
				}
			});
            doctorMenu.add(addDataItem);
            doctorMenu.add(viewDataItem);
            menuBar.add(doctorMenu);
        }
        // Nurse Menu (for nurse users)
        if (currentUser.getRole() == UserRole.NURSE) {
            JMenu nurseMenu = new JMenu("Nurse");
            nurseMenu.setFont(menuFont);
            nurseMenu.setForeground(new Color(249, 246, 237));
            JMenuItem addDataItem = new JMenuItem("Add Data");
            addDataItem.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                	addNurseData();
                }
            });
            JMenuItem viewDataItem = new JMenuItem("View Data");
            viewDataItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					viewDataForStaff();
				}
			});
            nurseMenu.add(addDataItem);
            nurseMenu.add(viewDataItem);
            menuBar.add(nurseMenu);
        }
        
        JMenu quriesMenu = new JMenu("Quaries");
        quriesMenu.setFont(menuFont);
        quriesMenu.setForeground(new Color(249, 246, 237));
        JMenuItem quariesItem = new JMenuItem("show");
        quariesItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				showQueriesPanel();
			}
			});
        quriesMenu.add(quariesItem);
        menuBar.add(quriesMenu);
        
        JMenu exportJMenu = new JMenu("Export");
        exportJMenu.setFont(menuFont);
        exportJMenu.setForeground(new Color(249, 246, 237));
        JMenuItem exportItem = new JMenuItem("export");
        exportItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				export();
			}
			});
        exportJMenu.add(exportItem);
        menuBar.add(exportJMenu);
    
        
        ImageIcon editIcon = new ImageIcon(getClass().getResource("/view/resources/edit-icon.png"));
        Image editImage = editIcon.getImage().getScaledInstance(52, 46, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(editImage);
        JButton editButton = new JButton(scaledIcon);
        editButton.setOpaque(false);
        editButton.setBorderPainted(false);
        editButton.setBounds(649, 100, 52, 46);
        editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
                editData(user);
                MainFrame newOne;                
                try {
					Main.save();
					newOne = new MainFrame(user);
					newOne.setVisible(true);
					dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});  
    
        panel.add(editButton);
        
        ImageIcon refIcon = new ImageIcon(getClass().getResource("/view/resources/refresh-icon.png"));
        Image refImage = refIcon.getImage().getScaledInstance(52, 46, Image.SCALE_SMOOTH);
        ImageIcon scaledRefIcon = new ImageIcon(refImage);
        JButton refButton = new JButton(scaledRefIcon);
        refButton.setOpaque(false);
        refButton.setBorderPainted(false);
        refButton.setBounds(647, 100, 52, 46);
        refButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
                MainFrame newOne;
				try {
					Main.save();
					newOne = new MainFrame(user);
					newOne.setVisible(true);
					dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}            	
			}
		});  
        panel.add(refButton);
        
        if(user.getRole().equals(UserRole.ADMIN))
        	editButton.setVisible(false);
        else 
        	refButton.setVisible(false);
        
        //info message for user
        JTextPane centralMessagePane = new JTextPane();
        centralMessagePane.setContentType("text/html");
        centralMessagePane.setEditable(false);
        UserInfoMessage infoMessage = new UserInfoMessage(user);
        String centralMessage = infoMessage.getMessage(user);
        centralMessagePane.setText(centralMessage);
        centralMessagePane.setOpaque(true);
        centralMessagePane.setBackground(new Color(249, 246, 237, 123));
        StyledDocument doc = centralMessagePane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        JScrollPane scrollPane = new JScrollPane(centralMessagePane);
        scrollPane.setBounds(100, 100, 600, 370);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane);

        /*
         * 
         //gif
        ImageIcon gifIcon = new ImageIcon("/view/resources/070ee1846d384f721543ad1859f06f89.gif");
        JLabel gifLabel = new JLabel(new ImageIcon(MainFrame.class.getResource("/view/resources/070ee1846d384f721543ad1859f06f89.gif")));
        gifLabel.setBounds(0, 278, 325, 260);
        
        panel.add(gifLabel);
        */
        
        //background
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/view/resources/background.png"));
       
        JLabel backLabel = new JLabel();
        backLabel.setBounds(0, 0, getWidth(), getHeight());
        backLabel.setIcon(new ImageIcon(backgroundIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        panel.add(backLabel);
               
        setContentPane(panel);
        setVisible(true);
    }
	
	private void export() {
		ExportToWordPanel panel = new ExportToWordPanel(hospital);
		JScrollPane scrollPane = new JScrollPane(panel);
		JOptionPane.showMessageDialog(this, scrollPane, "Export Panel", JOptionPane.PLAIN_MESSAGE);
    	
	}
	
	//editData
	private void editData(User user) {
		
		
		if (user.getRole().equals(UserRole.DOCTOR)) {
            if (user.getWorker() instanceof Doctor) {
            	Doctor doctor = (Doctor) user.getWorker();
            	EditDoctorPanel detailsPanel = new EditDoctorPanel(doctor);

            	// Wrap the panel in a JScrollPane
            	JScrollPane scrollPane = new JScrollPane(detailsPanel);
            	scrollPane.setPreferredSize(new Dimension(400, 500));

            	// Show in JOptionPane
            	int result = JOptionPane.showConfirmDialog(null, scrollPane, "Edit Doctor Details", JOptionPane.OK_CANCEL_OPTION);
            	if (result == JOptionPane.OK_OPTION) {
    		        try {
    		            	user.getWorker().setFirstName(detailsPanel.getFirstNameField().getText());
    		            	user.getWorker().setLastName(detailsPanel.getLastNameField().getText());
    		            	user.getWorker().setAddress(detailsPanel.getAddressField().getText());
    		            	user.getWorker().setPhoneNumber(detailsPanel.getPhoneNumberField().getText());
    		            	user.getWorker().setEmail(detailsPanel.getEmailField().getText());
    		            	user.getWorker().setGender(detailsPanel.getGenderField().getText());
    		            	
    		            	try {
    		            	    double salary = Double.parseDouble(detailsPanel.getSalaryField().getText().trim());
    		            	    user.getWorker().setSalary(salary);
    		            	    if (salary < 0) {
    		                        throw new NegativeDosageException(salary);
    		                    }
    		            	} catch (NumberFormatException e) {
    		            	    // Handle the exception (e.g., show an error message)
    		            	    JOptionPane.showMessageDialog(null, "Invalid salary format", "Error", JOptionPane.ERROR_MESSAGE);
    		            	}

    		            	((Doctor)user.getWorker()).setFinishInternship(detailsPanel.getInternshipCheckBox().isSelected());

    		            	Specialization specialization = (Specialization) detailsPanel.getSpecializationBox().getSelectedItem();
    		            	((Doctor)user.getWorker()).setSpecialization(specialization);
    		            	
    		            	if(detailsPanel.getIntensiveCareCheckBox().isSelected())
    		            	{
    		            		IntensiveCareDoctor ICDoc = new IntensiveCareDoctor(doctor.getId(), doctor.getFirstName(), doctor.getLastName(),
    		            					doctor.getBirthDate(), doctor.getAddress(), doctor.getPhoneNumber(),
    		            					doctor.getEmail(), doctor.getGender(), doctor.getWorkStartDate(), doctor.getSalary(), doctor.getLicenseNumber(), 
    		            					doctor.isFinishInternship());
    		            		hospital.addIntensiveCareDoctor(ICDoc);
    		            		hospital.addUser(user.getUsername(), user.getPassword(), ICDoc);
    		            		this.currentUser = hospital.getUserByUsername(user.getUsername());
    		            		hospital.removeDoctor(doctor);    		           		
    		            	}
    		            	
    		                JOptionPane.showMessageDialog(this, "Visit added edited!", "Success", JOptionPane.INFORMATION_MESSAGE);
    		        } catch (Exception e) {
    		            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    		        }
    		    }

            } else {
                // Handle the case where the worker is not a doctor
                JOptionPane.showMessageDialog(null, "The user is not a doctor.", "Error", JOptionPane.ERROR_MESSAGE);
            }					
		}
		
		if (user.getRole().equals(UserRole.NURSE)) {
            if (user.getWorker() instanceof Nurse) {
            	Nurse nurse = (Nurse) user.getWorker();
            	EditNursePanel detailsPanel = new EditNursePanel(nurse);

            	// Wrap the panel in a JScrollPane
            	JScrollPane scrollPane = new JScrollPane(detailsPanel);
            	scrollPane.setPreferredSize(new Dimension(400, 500));

            	// Show in JOptionPane
            	int result = JOptionPane.showConfirmDialog(null, scrollPane, "Edit Nurse Details", JOptionPane.OK_CANCEL_OPTION);
            	if (result == JOptionPane.OK_OPTION) {
    		        try {
    		            	user.getWorker().setFirstName(detailsPanel.getFirstNameField().getText());
    		            	user.getWorker().setLastName(detailsPanel.getLastNameField().getText());
    		            	user.getWorker().setAddress(detailsPanel.getAddressField().getText());
    		            	user.getWorker().setPhoneNumber(detailsPanel.getPhoneNumberField().getText());
    		            	user.getWorker().setEmail(detailsPanel.getEmailField().getText());
    		            	user.getWorker().setGender(detailsPanel.getGenderField().getText());
    		            	
    		            	try {
    		            	    double salary = Double.parseDouble(detailsPanel.getSalaryField().getText().trim());
    		            	    user.getWorker().setSalary(salary);
    		            	} catch (NumberFormatException e) {
    		            	    // Handle the exception (e.g., show an error message)
    		            	    JOptionPane.showMessageDialog(null, "Invalid salary format", "Error", JOptionPane.ERROR_MESSAGE);
    		            	}

    		            	if(detailsPanel.getIntensiveCareCheckBox().isSelected())
    		            	{
    		            		IntensiveCareNurse ICNurse = new IntensiveCareNurse(nurse.getId(), nurse.getFirstName(), nurse.getLastName(),
    		            					nurse.getBirthDate(), nurse.getAddress(), nurse.getPhoneNumber(),
    		            					nurse.getEmail(), nurse.getGender(), nurse.getWorkStartDate(), nurse.getSalary(), nurse.getLicenseNumber());
    		            		hospital.addIntensiveCareNurse(ICNurse);
    		            		hospital.addUser(user.getUsername(), user.getPassword(), ICNurse);
    		            		this.currentUser = hospital.getUserByUsername(user.getUsername());
    		            		hospital.removeNurse(nurse);    		           		
    		            	}
    		            	
    		                JOptionPane.showMessageDialog(this, "Visit added edited!", "Success", JOptionPane.INFORMATION_MESSAGE);
    		        } catch (Exception e) {
    		            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    		        }
    		    }

            } else {
                // Handle the case where the worker is not a doctor
                JOptionPane.showMessageDialog(null, "The user is not a doctor.", "Error", JOptionPane.ERROR_MESSAGE);
            }					
		}
			
	}
	
	
	//Queries panel
	private void showQueriesPanel() {
	    QueriesPanel queriesPanel = new QueriesPanel(hospital, currentUser.getRole());
	    JScrollPane scrollPane = new JScrollPane(queriesPanel);
	    JOptionPane.showMessageDialog(this, scrollPane, "Queries Panel", JOptionPane.PLAIN_MESSAGE);
	}
	
	//nurse menu 
		private void addNurseData() {
			Object[] possibilities = {"Patient", "Visit", "Visit to Patient"};
	
			String s = (String)JOptionPane.showInputDialog(
					this,
					"Choose type to add:",
					"Add Data",
					JOptionPane.PLAIN_MESSAGE,
					null,
					possibilities,
					"Patient");
			if (s == null) {
				// User canceled the dialog
				return;
			}
			if ((s != null) && (s.length() > 0)) {
	
				if(s.equals("Patient"))
					addPatient();
	
				if(s.equals("Visit"))
					addVisit();
	
				if(s.equals("Visit to Patient"))
					addVisitToPatient();	
			}
		}
		
		private void addVisitToPatient() {
            JOptionPane.showMessageDialog(this, "Vi!", "Success", JOptionPane.INFORMATION_MESSAGE);

			AddVisitToPatientPanel detailsPanel = new AddVisitToPatientPanel(hospital);
		    JScrollPane scrollPane = new JScrollPane(detailsPanel);
		    int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter The Details", JOptionPane.OK_CANCEL_OPTION);
	
		    if (result == JOptionPane.OK_OPTION) {
		        try {
		            Patient selectedPatient = detailsPanel.getSelectedPatient();
		            Visit selectedVisit = detailsPanel.getSelectedVisit();
	
		            if (selectedPatient != null && selectedVisit != null) {
		                selectedPatient.addVisit(selectedVisit);
		                JOptionPane.showMessageDialog(this, "Visit added successfully to the patient!", "Success", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(this, "Please select a valid patient and visit.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		}

	
	//doctor menu methods:
	private void addDoctorData() {
		Object[] possibilities = {"Medical Problem", "Visit", "Treatment", "Treatment to Medical Problem", 
										"Medication to Treatment", "Medical Problem to visit", 
											"Treatment to Visit"};

		String s = (String)JOptionPane.showInputDialog(
				this,
				"Choose type to add:",
				"Add Data",
				JOptionPane.PLAIN_MESSAGE,
				null,
				possibilities,
				"Medical Problem");
		if (s == null) {
			// User canceled the dialog
			return;
		}
		if ((s != null) && (s.length() > 0)) {
			
			if(s.equals("Medical Problem"))
            	addMedicalProblem();
			
			if(s.equals("Medication"))
            	addMedication();
			
			if(s.equals("Treatment"))
            	addTreatment();
			
			if(s.equals("Treatment to Medical Problem"))
            	addTreatmentToMedProblem();
			
			if(s.equals("Medication to Treatment"))
            	addMediToTreatment();
			
			if(s.equals("Medical Problem to visit"))
            	addMedicalProblemToVisit();
			
			if(s.equals("Treatment to Visit"))
            	addTreatmentToVisit();
		}
		
	}
	
	private void addTreatmentToVisit() {
		AddTreatmentToVisitPanel detailsPanel = new AddTreatmentToVisitPanel(hospital);
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter The Details", JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION) {
        	try {
	            Treatment selectedTreatment = (Treatment) detailsPanel.getSelectedTreatment();
	            Visit selectedVisit = (Visit) detailsPanel.getSelectedVisit();

	            if (selectedTreatment != null && selectedVisit != null) {
	                selectedVisit.addTreatment(selectedTreatment);
	                JOptionPane.showMessageDialog(this, "Treatment added successfully to the visit!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(this, "Please select a valid treatment and visit.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
        }
	}
	
	private void addMedicalProblemToVisit() {
		AddMedicalProblemToVisitPanel detailsPanel = new AddMedicalProblemToVisitPanel(hospital);
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter The Details", JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION) {
        	try {
	            MedicalProblem selectedMedical = (MedicalProblem) detailsPanel.getSelectedMedicalProblem();
	            Visit selectedVisit = (Visit) detailsPanel.getSelectedVisit();

	            if (selectedMedical != null && selectedVisit != null) {
	                selectedVisit.addMedicalProblem(selectedMedical);
	                JOptionPane.showMessageDialog(this, "Medical problem added successfully to the visit!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(this, "Please select a valid medical problem and visit.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
        }
	}
	
	private void addMediToTreatment() {
		AddMediToTreatment detailsPanel = new AddMediToTreatment(hospital);
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter Details", JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION) {
        	try {
	            Medication selectedMedication = (Medication) detailsPanel.getSelectedMedication();
	            Treatment selectedTreatment = (Treatment) detailsPanel.getSelectedTreatment();

	            if (selectedMedication != null && selectedTreatment != null) {
	                selectedTreatment.addMedication(selectedMedication);
	                JOptionPane.showMessageDialog(this, "Medication added successfully to the treatment!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(this, "Please select a valid medication and treatment.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
        }
	}
	
	private void addTreatmentToMedProblem() {
		AddTreatmentToProblemPanel detailsPanel = new AddTreatmentToProblemPanel(hospital);

        JScrollPane scrollPane = new JScrollPane(detailsPanel);

        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter the Details", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
        	try {
	            MedicalProblem selectedProblem = (MedicalProblem) detailsPanel.getSelectedMedicalProblem();
	            Treatment selectedTreatment = (Treatment) detailsPanel.getSelectedTreatment();

	            if (selectedProblem != null && selectedTreatment != null) {
	                selectedProblem.addTreatment(selectedTreatment);
	                JOptionPane.showMessageDialog(this, "Treatment added successfully to the medical problem!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(this, "Please select a valid medical problem and treatment.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
        }
	}

	private void viewDataForStaff() {
    	Object[] possibilities = {"Department", "Medical Problem", "Medication","Patient", "Treatment", "Visit"};
    	
    	String s = (String)JOptionPane.showInputDialog(
                this,
                "Choose type to view:",
                "view Data",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "Department");
    	
    	 if (s == null) {
    	        // User canceled the dialog
    	        return;
    	 }
    	 
    	 String primaryKey = JOptionPane.showInputDialog(
    	            this,
    	            "Enter the primary key (ID or code):",
    	            "Enter Key",
    	            JOptionPane.PLAIN_MESSAGE);

    	 if (primaryKey == null || primaryKey.trim().isEmpty()) {
    		 // User canceled the dialog or entered an empty key
    		 return;
    	 }
    	 
    	 String foundDet = null;

    	 switch (s) {
    	     case "Department":
    	         try {
    	             int departmentId = Integer.parseInt(primaryKey);
    	             Department department = hospital.getRealDepartment(departmentId);
    	             if (department != null) {
    	                 foundDet = department.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Department not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (NumberFormatException e) {
    	             JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the department: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     case "Medical Problem":
    	         try {
    	             MedicalProblem medicalProblem = hospital.getMedicalProblem(primaryKey); // Assuming primaryKey is a String
    	             if (medicalProblem != null) {
    	                 foundDet = medicalProblem.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Medical Problem not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the medical problem: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     case "Medication":
    	         try {
    	             int medicationId = Integer.parseInt(primaryKey);
    	             Medication medication = hospital.getRealMedication(medicationId);
    	             if (medication != null) {
    	                 foundDet = medication.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Medication not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (NumberFormatException e) {
    	             JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the medication: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     case "Patient":
    	         try {
    	             int patientId = Integer.parseInt(primaryKey);
    	             Patient patient = hospital.getRealPatient(patientId);
    	             if (patient != null) {
    	                 foundDet = patient.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Patient not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (NumberFormatException e) {
    	             JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the patient: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     case "Treatment":
    	         try {
    	             int treatmentId = Integer.parseInt(primaryKey);
    	             Treatment treatment = hospital.getRealTreatment(treatmentId);
    	             if (treatment != null) {
    	                 foundDet = treatment.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Treatment not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (NumberFormatException e) {
    	             JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the treatment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     case "Visit":
    	         try {
    	             int visitId = Integer.parseInt(primaryKey);
    	             Visit visit = hospital.getRealVisit(visitId);
    	             if (visit != null) {
    	                 foundDet = visit.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Visit not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (NumberFormatException e) {
    	             JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the visit: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     default:
    	         JOptionPane.showMessageDialog(this, "Invalid selection!", "Error", JOptionPane.ERROR_MESSAGE);
    	         break;
    	 }

    	 // If foundDet is not null, display the result
    	 if (foundDet != null) {
    	     JOptionPane.showMessageDialog(this, foundDet, "Object Information", JOptionPane.INFORMATION_MESSAGE);
    	 }


    }
	
	//admin data methods:
	private void removeAdminData() {
		Object[] possibilities = {"Doctor","Department", "Disease", "Injury", "Fracture", "Medication", "Nurse", "Patient",
				"Treatment", "Visit"};
	    String s = (String)JOptionPane.showInputDialog(
	                    this,
	                    "Choose type to remove:",
	                    "Remove Data",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    possibilities,
	                    "Doctor");

	    if (s == null) {
	        // User canceled the dialog
	        return;
	    }
	    if ((s != null) && (s.length() > 0)) {
	        try {
	            switch (s) {
	                case "Doctor":
	                    String doctorId = JOptionPane.showInputDialog("Enter Doctor ID to remove:");
	                    hospital.removeDoctor(hospital.getRealDoctor(Integer.parseInt(doctorId)));
	                    JOptionPane.showMessageDialog(null, "Doctor removed successfully!");
	                    break;
	                
	                case "Department":
	                    String departmentId = JOptionPane.showInputDialog("Enter Department ID to remove:");
	                    hospital.removeDepartment(hospital.getRealDepartment(Integer.parseInt(departmentId)));
	                    JOptionPane.showMessageDialog(null, "Department removed successfully!");
	                    break;
	                
	                case "Injury":
	                    String injuryID = JOptionPane.showInputDialog("Enter Medical Problem ID to remove:");
	                    hospital.removeMedicalProblem(hospital.getRealInjury(injuryID));
	                    JOptionPane.showMessageDialog(null, "Medical Problem removed successfully!");
	                    break;
	                    
	                case "Disease":
	                    String DiseaseID = JOptionPane.showInputDialog("Enter Medical Problem ID to remove:");
	                    hospital.removeMedicalProblem(hospital.getRealDisease(DiseaseID));
	                    JOptionPane.showMessageDialog(null, "Medical Problem removed successfully!");
	                    break;
	                    
	                case "Fracture":
	                    String fractureID = JOptionPane.showInputDialog("Enter Medical Problem ID to remove:");
	                    hospital.removeMedicalProblem(hospital.getRealFracture(fractureID));
	                    JOptionPane.showMessageDialog(null, "Medical Problem removed successfully!");
	                    break;
	                
	                case "Medication":
	                    String medicationCode = JOptionPane.showInputDialog("Enter Medication Code to remove:");
	                    hospital.removeMedication(hospital.getRealMedication(Integer.parseInt(medicationCode)));
	                    JOptionPane.showMessageDialog(null, "Medication removed successfully!");
	                    break;
	                
	                case "Nurse":
	                    String nurseId = JOptionPane.showInputDialog("Enter Nurse ID to remove:");
	                    hospital.removeNurse(hospital.getRealNurse(Integer.parseInt(nurseId)));
	                    JOptionPane.showMessageDialog(null, "Nurse removed successfully!");
	                    break;
	                
	                case "Patient":
	                    String patientId = JOptionPane.showInputDialog("Enter Patient ID to remove:");
	                    hospital.removePatient(hospital.getRealPatient(Integer.parseInt(patientId)));
	                    JOptionPane.showMessageDialog(null, "Patient removed successfully!");
	                    break;
	                
	                case "Treatment":
	                    String treatmentId = JOptionPane.showInputDialog("Enter Treatment ID to remove:");
	                    hospital.removeTreatment(hospital.getRealTreatment(Integer.parseInt(treatmentId)));
	                    JOptionPane.showMessageDialog(null, "Treatment removed successfully!");
	                    break;
	                
	                case "Visit":
	                    String visitId = JOptionPane.showInputDialog("Enter Visit ID to remove:");
	                    hospital.removeVisit(hospital.getRealVisit(Integer.parseInt(visitId)));
	                    JOptionPane.showMessageDialog(null, "Visit removed successfully!");
	                    break;
	                
	                default:
	                    JOptionPane.showMessageDialog(null, "Invalid selection.", "Error", JOptionPane.ERROR_MESSAGE);
	                    break;
	            }
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a numeric value.", "Input Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Error removing data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
    
    private void viewAdminData() {
    	Object[] possibilities = {"Doctor","Department", "Medical Problem", "Medication", "Nurse", "Patient",
				"Treatment", "Visit"};
    	
    	String s = (String)JOptionPane.showInputDialog(
                this,
                "Choose type to view:",
                "view Data",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "Doctor");
    	
    	 if (s == null) {
    	        // User canceled the dialog
    	        return;
    	 }
    	 
    	 String primaryKey = JOptionPane.showInputDialog(
    	            this,
    	            "Enter the primary key (ID or code):",
    	            "Enter Key",
    	            JOptionPane.PLAIN_MESSAGE);

    	 if (primaryKey == null || primaryKey.trim().isEmpty()) {
    		 // User canceled the dialog or entered an empty key
    		 return;
    	 }
    	 
    	 String foundDet = null;

    	 switch (s) {
    	     case "Doctor":
    	         try {
    	             int doctorId = Integer.parseInt(primaryKey);
    	             Doctor doctor = hospital.getRealDoctor(doctorId);
    	             if (doctor != null) {
    	                 foundDet = doctor.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Doctor not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (NumberFormatException e) {
    	             JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the doctor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     case "Department":
    	         try {
    	             int departmentId = Integer.parseInt(primaryKey);
    	             Department department = hospital.getRealDepartment(departmentId);
    	             if (department != null) {
    	                 foundDet = department.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Department not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (NumberFormatException e) {
    	             JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the department: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     case "Medical Problem":
    	         try {
    	             MedicalProblem medicalProblem = hospital.getMedicalProblem(primaryKey); // Assuming primaryKey is a String
    	             if (medicalProblem != null) {
    	                 foundDet = medicalProblem.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Medical Problem not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the medical problem: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     case "Medication":
    	         try {
    	             int medicationId = Integer.parseInt(primaryKey);
    	             Medication medication = hospital.getRealMedication(medicationId);
    	             if (medication != null) {
    	                 foundDet = medication.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Medication not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (NumberFormatException e) {
    	             JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the medication: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     case "Nurse":
    	         try {
    	             int nurseId = Integer.parseInt(primaryKey);
    	             Nurse nurse = hospital.getRealNurse(nurseId);
    	             if (nurse != null) {
    	                 foundDet = nurse.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Nurse not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (NumberFormatException e) {
    	             JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the nurse: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     case "Patient":
    	         try {
    	             int patientId = Integer.parseInt(primaryKey);
    	             Patient patient = hospital.getRealPatient(patientId);
    	             if (patient != null) {
    	                 foundDet = patient.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Patient not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (NumberFormatException e) {
    	             JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the patient: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     case "Treatment":
    	         try {
    	             int treatmentId = Integer.parseInt(primaryKey);
    	             Treatment treatment = hospital.getRealTreatment(treatmentId);
    	             if (treatment != null) {
    	                 foundDet = treatment.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Treatment not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (NumberFormatException e) {
    	             JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the treatment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     case "Visit":
    	         try {
    	             int visitId = Integer.parseInt(primaryKey);
    	             Visit visit = hospital.getRealVisit(visitId);
    	             if (visit != null) {
    	                 foundDet = visit.toString();
    	             } else {
    	                 JOptionPane.showMessageDialog(this, "Visit not found with ID: " + primaryKey, "Not Found", JOptionPane.ERROR_MESSAGE);
    	             }
    	         } catch (NumberFormatException e) {
    	             JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	         } catch (Exception e) {
    	             JOptionPane.showMessageDialog(this, "An error occurred while retrieving the visit: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	         }
    	         break;

    	     default:
    	         JOptionPane.showMessageDialog(this, "Invalid selection!", "Error", JOptionPane.ERROR_MESSAGE);
    	         break;
    	 }

    	 // If foundDet is not null, display the result
    	 if (foundDet != null) {
    	     JOptionPane.showMessageDialog(this, foundDet, "Object Information", JOptionPane.INFORMATION_MESSAGE);
    	 }
    }
	
    private void addAdminData() {
        Object[] possibilities = {"Doctor","Department", "Medical Problem", "Medication", "Nurse", "Patient",
        								"Treatment", "Visit", "Treatment to Medical Problem", 
										"Medication to Treatment", "Medical Problem to visit", 
										"Treatment to Visit", "Visit to Patient"};
        
        String s = (String)JOptionPane.showInputDialog(
                        this,
                        "Choose type to add:",
                        "Add Data",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        "Doctor");
        if (s == null) {
	        // User canceled the dialog
	        return;
        }
        if ((s != null) && (s.length() > 0)) {
            if (s.equals("Doctor"))
            	addDoctor();
            
            if (s.equals("Department"))
            	addDepartment();
            
            if(s.equals("Medical Problem"))
            	addMedicalProblem();
            	
            if(s.equals("Medication"))
            	addMedication();
            
            if(s.equals("Nurse"))
            	addNurse();
            
            if(s.equals("Patient"))
            	addPatient();
            
            if(s.equals("Treatment"))
            	addTreatment();
            
            if(s.equals("Visit"))
            	addVisit();
            
            if (s.equals("Treatment to Medical Problem")) 
            	addTreatmentToMedProblem();
            
            if (s.equals("Medication to Treatment")) 
            	addMediToTreatment();
            
            if (s.equals("Medical Problem to visit")) 
            	addMedicalProblemToVisit();
            
            if (s.equals("Treatment to Visit")) 
            	addTreatmentToVisit();
            
			if(s.equals("Visit to Patient"))
				addVisitToPatient();	
        
        }
    }
    
    private void addUser() {
        AddUserPanel detailsPanel = new AddUserPanel(hospital.getStaffMembers());
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter User Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Get the input data from the panel
                String username = detailsPanel.getUsername();
                String password = detailsPanel.getPassword();
                StaffMember selectedStaff = detailsPanel.getSelectedStaffMember();

                hospital.addUser(username, password, selectedStaff);

                JOptionPane.showMessageDialog(null, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error adding user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void addVisit() {
        AddVisitPanel detailsPanel = new AddVisitPanel(hospital.getPatients().values());
        JScrollPane scrollPane = new JScrollPane(detailsPanel);

        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter Visit Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int visitNumber = detailsPanel.getVisitNumber();
                Patient selectedPatient = detailsPanel.getSelectedPatient();
                Date startDate = new Date(detailsPanel.getStartDate().getDate().getTime());
                Date endDate = new Date(detailsPanel.getEndDate().getDate().getTime());
                validateDate(startDate);
                validateDate(endDate);
                validateNumber(visitNumber);
                Visit visit = new Visit(visitNumber, selectedPatient, startDate, endDate);
                hospital.addVisit(visit);
                JOptionPane.showMessageDialog(null, "Visit added successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error adding visit: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void addTreatment() {

        AddTreatmentPanel detailsPanel = new AddTreatmentPanel();
        JScrollPane scrollPane = new JScrollPane(detailsPanel);

        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter Treatment Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int serialNumber = detailsPanel.getSerialNumber();
                String description = detailsPanel.getDescription();
                validateNumber(serialNumber);
                Treatment treatment = new Treatment(serialNumber, description);
                hospital.addTreatment(treatment);
                JOptionPane.showMessageDialog(null, "Treatment added successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error adding treatment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
   
    private void addPatient() {
        AddPatientPanel detailsPanel = new AddPatientPanel();
        JScrollPane scrollPane = new JScrollPane(detailsPanel);

        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter Patient Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = detailsPanel.getId();
                String firstName = detailsPanel.getFirstName();
                String lastName = detailsPanel.getLastName();
                Date birthDate = new Date(detailsPanel.getBirthDate().getDate().getTime());
                String address = detailsPanel.getAddress();
                String phoneNumber = detailsPanel.getPhoneNumber();
                String email = detailsPanel.getEmail();
                String gender = detailsPanel.getGender();
                HealthFund healthFund = detailsPanel.getHealthFund();
                BiologicalSex biologicalSex = detailsPanel.getBiologicalSex();
                validateDate(birthDate);
                validateNumber(id);
                Patient patient = new Patient(id, firstName, lastName, birthDate, address, phoneNumber, email, gender, healthFund, biologicalSex);
                hospital.addPatient(patient);
                JOptionPane.showMessageDialog(null, "Patient added successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error adding patient: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void addNurse() {
    	AddNursePanel detailsPanel = new AddNursePanel();
        JScrollPane scrollPane = new JScrollPane(detailsPanel);

        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter Doctor Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(detailsPanel.getIdField().getText());
                String firstName = detailsPanel.getFirstNameField().getText();
                String lastName = detailsPanel.getLastNameField().getText();
                Date birthDate = new Date(detailsPanel.getBirthDateChooser().getDate().getTime());
                String address = detailsPanel.getAddressField().getText();
                String phoneNumber = detailsPanel.getPhoneNumberField().getText();
                String email = detailsPanel.getEmailField().getText();
                String gender = detailsPanel.getGenderField().getText();
                Date workStartDate = new Date(detailsPanel.getWorkStartDateChooser().getDate().getTime());
                double salary = Double.parseDouble(detailsPanel.getSalaryField().getText());
                int licenseNumber = Integer.parseInt(detailsPanel.getLicenseNumberField().getText());
                validateDate(workStartDate);
                validateDate(birthDate);
                validateNumber(id);
                if (salary < 0) {
                    throw new NegativeDosageException(salary);
                }
                Nurse nurse = new Nurse(id, firstName, lastName, birthDate, address, phoneNumber, email, gender, 
                                workStartDate, salary, licenseNumber);
                
                hospital.addNurse(nurse);
                JOptionPane.showMessageDialog(null, "Nurse added successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error adding NUrse: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void addMedication() {
        AddMedicationPanel detailsPanel = new AddMedicationPanel();
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        
        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter Medication Details", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                int code = detailsPanel.getCode();
                String name = detailsPanel.getName();
                double dosage = detailsPanel.getDosage();
                int numberOfDose = detailsPanel.getNumberOfDose();

                if (dosage < 0) {
                    throw new NegativeDosageException(dosage);
                }
                if (numberOfDose < 0) {
                    throw new NegativeNumberOfDosesException(numberOfDose);
                }
                
                Medication medication = new Medication(code, name, dosage, numberOfDose);
                hospital.addMedication(medication);
                
                JOptionPane.showMessageDialog(null, "Medication added successfully!");

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input format. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (NegativeDosageException | NegativeNumberOfDosesException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error adding medication: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
 
    private void addMedicalProblem() {
    	AddMedicalProblemPanel detailsPanel = new AddMedicalProblemPanel(hospital.getDepartments());
        JScrollPane scrollPane = new JScrollPane(detailsPanel);

        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter Medical Problem Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String type = detailsPanel.getSelectedType();
                String name = detailsPanel.getName();
                Department department = detailsPanel.getDepartment();
                
                MedicalProblem medicalProblem;
                
                if ("Fracture".equals(type)) {
                    String location = detailsPanel.getTheLocation();
                    boolean requiresCast = detailsPanel.isRequiresCast();
                    medicalProblem = new Fracture(name, department, location, requiresCast);
                } else if ("Injury".equals(type)) {
                    double commonRecoveryTime = detailsPanel.getCommonRecoveryTime();
                    String location = detailsPanel.getTheLocation();
                    medicalProblem = new Injury(name, department, commonRecoveryTime, location);
                } else if ("Disease".equals(type)) {
                    String description = detailsPanel.getDescription();
                    medicalProblem = new Disease(name, department, description);
                } else {
                    throw new IllegalArgumentException("Invalid type selected.");
                }
                
                hospital.addMedicalProblem(medicalProblem);
                JOptionPane.showMessageDialog(null, "Medical Problem "+medicalProblem.getCode() +" added successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error adding medical problem: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void addDepartment() {
        AddDepartmentPanel detailsPanel = new AddDepartmentPanel(hospital.getStaffMembers());
        JScrollPane scrollPane = new JScrollPane(detailsPanel);

        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter Department Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int number = detailsPanel.getNumber();
                String name = detailsPanel.getName();
                String location = detailsPanel.getTheLocation();
                Specialization specialization = detailsPanel.getSpecialization();
                Doctor manager = detailsPanel.getManager();
                validateNumber(number);
                Department department = new Department(number, name, manager, location, specialization);
                hospital.addDepartment(department);
                JOptionPane.showMessageDialog(null, "Department added successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error adding department: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void addDoctor() {
    	AddDoctorPanel detailsPanel = new AddDoctorPanel();
        JScrollPane scrollPane = new JScrollPane(detailsPanel);

        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Please Enter Doctor Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = detailsPanel.getIdField();
                String firstName = detailsPanel.getFirstNameField().getText();
                String lastName = detailsPanel.getLastNameField().getText();
                Date birthDate = new Date(detailsPanel.getBirthDateChooser().getDate().getTime());
                String address = detailsPanel.getAddressField().getText();
                String phoneNumber = detailsPanel.getPhoneNumberField().getText();
                String email = detailsPanel.getEmailField().getText();
                String gender = detailsPanel.getGenderField().getText();
                Date workStartDate = new Date(detailsPanel.getWorkStartDateChooser().getDate().getTime());
                double salary = Double.parseDouble(detailsPanel.getSalaryField().getText());
                int licenseNumber = Integer.parseInt(detailsPanel.getLicenseNumberField().getText());
                boolean isFinishInternship = detailsPanel.getInternshipCheckBox().isSelected();
                Specialization specialization = (Specialization) detailsPanel.getSpecializationBox().getSelectedItem();
                validateDate(workStartDate);
                validateDate(birthDate);
                validateNumber(id);
                if (salary < 0) {
                    throw new NegativeDosageException(salary);
                }
                Doctor doctor = new Doctor(id, firstName, lastName, birthDate, address, phoneNumber, email, gender, 
                                workStartDate, salary, licenseNumber, isFinishInternship, specialization);
                
                hospital.addDoctor(doctor);
                JOptionPane.showMessageDialog(null, "Doctor added successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error adding doctor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void validateDate(Date Date) {
        if (Date.after(Hospital.TODAY)) {
            throw new FutureDateException(Date);
        }
    }
    
    private void validateNumber(int number) {
    	if (number<=0)
    		throw new NUmberLengthException();
    }
}

        


