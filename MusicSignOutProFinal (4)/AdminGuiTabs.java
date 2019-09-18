
/*
 * AdminGuiTabs
 * Tabs for the administrator to use, student list, instrument list, sign in and out tab
 * @Authors: Andrew,Naymul,Daniel,Connor
 * Date: November 28, 2017
 */

//awt
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//io
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//util
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
//swing
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.SystemColor;

public class AdminGuiTabs {
 // JTabbedPane
 private JTabbedPane tabbedPane;

 // panels
 private JPanel studentListPanel;
 private JPanel instrumentListPanel;
 private JPanel signOutPanel;

 // layout
 private GroupLayout gl_studentListPanel;
 private GroupLayout instrumentGroupLayout;

 // text fields
 private JTextField studentSearchField;
 private JTextField studentNameField;
 private JTextField studentIDField;
 private JTextField studentInstrumentField;
 private JTextField studentGradeField;
 private JTextField signOutField;
 private JTextField signInField;
 private JTextField barcodeTextField;
 private JTextField txtSearch;
 private JTextField instrumentDescriptionTextField;
 private JTextField assignedOutTextField;
 private JTextField latestActivityTextField;
 private JTextField instrumentIDTextField;

 // buttons
 private JButton studentAddButton;
 private JButton studentDeleteButton;
 private JButton studentSearchButton;
 private JButton studentRefreshButton;
 private JButton studentEditButton;
 private JButton studentConfirmButton;
 private JButton searchSignOutButton;
 private JButton searchSignInButton;
 private JButton btnEnter;
 private JButton btnFind;
 private JButton btnAddInstrument;
 private JButton btnDeleteInstrument;
 private JButton btnRefresh;
 private JButton btnEdit;
 private JButton btnConfirm;
 private JButton availableInstrumentsButton;
 private JButton unavailableInstrumentsButton;


 // labels
 private JLabel lblPleaseScanStudent;
 private JLabel barcodeLabel;
 private JLabel studentNameLabel;
 private JLabel studentIDLabel;
 private JLabel studentInstrumentLabel;
 private JLabel studentGradeLabel;
 private JLabel studentSignedOutLabel;
 private JLabel studentErrorLabel;
 private JLabel studentSignedInLabel;
 private JLabel instrumentErrorLabel;
 private JLabel latestActivityLabel;
 private JLabel conditionLabel_1;
 private JLabel availabilityLabel_1;
 private JLabel instrumentIdLabel;
 private JLabel signedOutLabel;
 private JLabel instrumentDescriptionLabel;

 // sign out variables
 private JTextField usernameTextField;
 private JTextField instrumentTextField;

 // student list JList
 private JList<String> instrumentList;
 private DefaultListModel<String> studentListModel;
 private JList<String> studentList;
 private DefaultListModel<String> signOutListModel;
 private JList<String> signOutList;
 private JScrollPane signOutScrollPane;
 private JScrollPane studentScrollPane;
 private JScrollPane signInScrollPane;
 private DefaultListModel<String> signInListModel;
 private JList<String> signInList;
 private JScrollPane instrumentScrollPane;


 // instrument jlist
 private DefaultListModel<String> instrumentListModel;
 private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy/HH:mm");

 // array list
 private ArrayList<Student> studentArrList = new ArrayList<Student>();
 private static ArrayList<Instrument> instrumentArrList;
 private static ArrayList<Instrument> instrumentDisplayList;
 private static ArrayList<String> instrumentLog;

 // stores selected student
 private int currentStudent;

 // frame
 private JFrame frame;

 // combo box
 private JComboBox<?> conditionComboBox;
 private JComboBox<?> availabilityComboBox;

 // AdminGUI
 private static AdminGuiTabs instance;

 /**
  * Launch the application.
  */
 public static AdminGuiTabs getInstance() {
  if (instance == null) {
   instance = new AdminGuiTabs();
  }
  return instance;
 }

 public void openInfoPage() {
  run();
 }

 private static void run() {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     // create new AdminGUI
     AdminGuiTabs window = new AdminGuiTabs();
     window.frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }

 /**
  * Create the application.
  */
 public AdminGuiTabs() {
  // initialize gui setup
  initialize();
  // read Student.csv file
  readStudentFile();
 }

 /**
  * Initialize the contents of the frame.
  */
 @SuppressWarnings("serial")
private void initialize() {
  // initialize frame
  frame = new JFrame("Admin Window");
  frame.setBounds(100, 100, 1280, 850);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.getContentPane().setLayout(new BorderLayout(0, 0));

  // setup tabbedPane
  tabbedPane = new JTabbedPane(JTabbedPane.TOP);
  tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
  frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

  // sign in JList
  signInListModel = new DefaultListModel<String>();

  // sign out JList
  signOutListModel = new DefaultListModel<String>();

  // student list JList
  studentListModel = new DefaultListModel<String>();

  // -------END OF SIGN OUT PANEL-------

  // ---------INSTRUMENT LIST PANEL-----------------

  instrumentListPanel = new JPanel();
  instrumentListPanel.setBackground(new Color(102, 153, 153));
  tabbedPane.addTab("Instrument List", null, instrumentListPanel, null);

  readInstrumentFile(); //Read instrument file to update info for program

  String[] availabilityChoices = { "Available", "Unavailable", "In Repair" };
  String[] conditionChoices = { "Good", "Mediocre", "Bad", "Broken" };

  // JList
  instrumentListModel = new DefaultListModel<String>();  
  instrumentList = new JList<String>(instrumentListModel);
  instrumentList.setFont(new Font("Tahoma", Font.PLAIN, 14));
  instrumentList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
  instrumentList.setVisibleRowCount(-1);

  instrumentList.addListSelectionListener(new instrumentListSelectionListener());

  for (int i = 0; i < instrumentArrList.size(); i++) {
   instrumentListModel.addElement(instrumentArrList.get(i).getID());
  }

  instrumentList.setCellRenderer(new DefaultListCellRenderer() {
 /*
  * getListCellRenderer
  * To change the colours of the instrument based on availability
  * @param J List, Object value, intof the index , and boolean to see if its selected
  * @return Component to change colour
  */
   public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index, boolean isSelected,
     boolean cellHasFocus) {
    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    Instrument selected = new Instrument("aaa");
    for (int i = 0; i < instrumentArrList.size(); i++) {
     if (instrumentArrList.get(i).getID().equals(value)) {
      selected = instrumentArrList.get(i);
     }
    }

    if (selected.getAvailability().equals("Unavailable")) {
     setBackground(Color.RED);
    }
    if (selected.getAvailability().equals("Available")) {
     setBackground(Color.GREEN);
    }
    if (selected.getAvailability().equals("In Repair")) {
     setBackground(Color.YELLOW);
    }
    return this;
   }
  });

  //Initialize scrollpanes
  instrumentScrollPane = new JScrollPane(instrumentList);
  instrumentScrollPane.setPreferredSize(new Dimension(500, 80));
  instrumentScrollPane.setSize(new Dimension(500, 80));
  instrumentScrollPane.setMinimumSize(new Dimension(100, 10));
  studentScrollPane = new JScrollPane(studentList);
  studentScrollPane.setPreferredSize(new Dimension(500, 80));
  signOutScrollPane = new JScrollPane(signOutList);
  signInScrollPane = new JScrollPane(signInList);

  // Initialize Labels
  instrumentIdLabel = new JLabel("Instrument ID: ");
  instrumentIdLabel.setForeground(Color.BLACK);
  instrumentIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
  new JLabel("Availability: ");
  new JLabel("Condition: ");
  signedOutLabel = new JLabel("Assigned to: ");
  signedOutLabel.setForeground(Color.BLACK);
  signedOutLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
  instrumentDescriptionLabel = new JLabel("Description: ");
  instrumentDescriptionLabel.setForeground(Color.BLACK);
  instrumentDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
  conditionLabel_1 = new JLabel("Condition: ");
  conditionLabel_1.setForeground(Color.BLACK);
  conditionLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
  availabilityLabel_1 = new JLabel("Availability: ");
  availabilityLabel_1.setForeground(Color.BLACK);
  availabilityLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

  instrumentErrorLabel = new JLabel("Error : Remove commas\r\n from fields");
  instrumentErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
  instrumentErrorLabel.setForeground(Color.RED);
  instrumentErrorLabel.setVisible(false);
  
  
  // Initialize Textfields
  txtSearch = new JTextField();
  txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
  txtSearch.setText("Search");
  txtSearch.selectAll();
  txtSearch.setColumns(10);

  instrumentIDTextField = new JTextField();
  instrumentIDTextField.setHorizontalAlignment(JTextField.LEFT);
  instrumentIDTextField.setColumns(10);

  assignedOutTextField = new JTextField();
  assignedOutTextField.setHorizontalAlignment(JTextField.LEFT);
  assignedOutTextField.setColumns(10);

  instrumentDescriptionTextField = new JTextField();
  instrumentDescriptionTextField.setHorizontalAlignment(JTextField.LEFT);
  instrumentDescriptionTextField.setColumns(10);

  // Initialize Combo boxes
  availabilityComboBox = new JComboBox<Object>(availabilityChoices);
  availabilityComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
  availabilityComboBox.setEnabled(false);

  conditionComboBox = new JComboBox<Object>(conditionChoices);
  conditionComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
  conditionComboBox.setEnabled(false);

  // Initialize buttons and listeners respectively
  btnFind = new JButton("Find");
  btnFind.setFont(new Font("Tahoma", Font.PLAIN, 18));
  btnFind.addActionListener(new ActionListener() {

   /*
    * actionPerformed This method finds the instrument searched
    * 
    * @param ActionEvent to detect the press of the button
    * 
    * @return Nothing
    */
   public void actionPerformed(ActionEvent arg0) {
    instrumentListModel.removeAllElements(); // Remove all displayed elements of list
    instrumentDisplayList = new ArrayList<Instrument>();

    for (int p = 0; p < instrumentArrList.size(); p++) {
     if (instrumentArrList.get(p).getID().indexOf(txtSearch.getText()) > -1) { // Check which instruments match the searched text

      instrumentListModel.addElement(instrumentArrList.get(p).getID());
      instrumentDisplayList.add(instrumentArrList.get(p)); // Add back list of instruments matched with text
     }
    }
   }
  });

  btnAddInstrument = new JButton("Add Instrument");
  btnAddInstrument.setFont(new Font("Tahoma", Font.PLAIN, 18));
  btnAddInstrument.addActionListener(new ActionListener() {

   /*
    * actionPerformed This method opens a window to add another instrument
    * 
    * @param ActionEvent to detect the press of the button
    * 
    * @return Nothing
    */
   public void actionPerformed(ActionEvent arg0) {
 @SuppressWarnings("unused")
 AddInstrumentGui addInstrumentWindow = new AddInstrumentGui();
   }
  });

  btnDeleteInstrument = new JButton("Delete Instrument");
  btnDeleteInstrument.setFont(new Font("Tahoma", Font.PLAIN, 18));
  btnDeleteInstrument.addActionListener(new ActionListener() {

   /*
    * actionPerformed This method deletes an instrument from the arraylist
    * 
    * @param ActionEvent to detect the press of the button
    * 
    * @return Nothing
    */
   public void actionPerformed(ActionEvent e) {
    if (JOptionPane.showConfirmDialog(frame,
      "Are you sure you want to delete the instrument: " + instrumentList.getSelectedValue(),
      "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
     int selectedIndex = instrumentList.getSelectedIndex();
     readStudentFile();
     if (selectedIndex != -1) {

      for (int h = 0; h < instrumentArrList.size(); h++) { // Find selected instrument in list


       if (instrumentArrList.get(h).getID()
         .equals(instrumentDisplayList.get(selectedIndex).getID())) {

        for (int k = 0; k < studentArrList.size(); k++) {
         if (instrumentArrList.get(h).getID()
           .equals(studentArrList.get(k).getInstrument().getID())) {
          studentArrList.get(k).setInstrument(new Instrument(""));
         }
        }

        instrumentArrList.remove(h); // Remove from actual arraylist of instruments
       }
      }
      instrumentDisplayList.remove(selectedIndex); //  Remove from display arraylist of instruments

      instrumentListModel.removeAllElements();
      for (int i = 0; i < instrumentDisplayList.size(); i++) {
       instrumentListModel.addElement(instrumentDisplayList.get(i).getID()); // Reset so it shows only valid instruments
 
      }
      writeInstrumentFile(); // Save changes to file
      writeFile();
      resetTab(); // Refresh displayed info
     }
    }
   }
  });

  btnEdit = new JButton("Edit");
  btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
  btnEdit.addActionListener(new ActionListener() {

   /*
    * actionPerformed This method makes the instrument editable
    * 
    * @param ActionEvent to detect the press of the button
    * 
    * @return Nothing
    */
   public void actionPerformed(ActionEvent arg0) {
    int selectedIndex = instrumentList.getSelectedIndex(); // Get the selected instrument 


    if (selectedIndex != -1) {
     // make textfields editable
     instrumentIDTextField.setEditable(true);
     instrumentDescriptionTextField.setEditable(true);
     barcodeTextField.setEditable(true);
     availabilityComboBox.setEnabled(true);
     conditionComboBox.setEnabled(true);
    }
   }
  });

  btnConfirm = new JButton("Confirm");
  btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
  btnConfirm.addActionListener(new ActionListener() {

   /*
    * actionPerformed This method saves the instrument to the arraylist
    * 
    * @param ActionEvent to detect the press of the button
    * 
    * @return Nothing
    */
   public void actionPerformed(ActionEvent e) {
    // only if there is an item to edit

    if (instrumentDisplayList.size() > 0 && instrumentList.getSelectedIndex() != -1) {
     // get selected jlist index
     int selectedIndex = instrumentList.getSelectedIndex();

     if ((instrumentDescriptionTextField.getText().indexOf(",") > -1)  //Check for commas
       || (instrumentIDTextField.getText().indexOf(",") > -1)
       || (barcodeTextField.getText().indexOf(",") > -1)) {
      instrumentErrorLabel.setVisible(true);
     } else {
      // set textfields to new information
      instrumentDisplayList.get(selectedIndex).setAvailability(availabilityComboBox.getSelectedItem().toString());
      instrumentDisplayList.get(selectedIndex).setCondition(conditionComboBox.getSelectedItem().toString());
      instrumentDisplayList.get(selectedIndex).setDescription(instrumentDescriptionTextField.getText());
      instrumentDisplayList.get(selectedIndex).setBarcode(barcodeTextField.getText());

      for (int m = 0; m < instrumentArrList.size(); m++) { // Save changes in both arraylists


       if (instrumentArrList.get(m).getID()
         .equals(instrumentDisplayList.get(selectedIndex).getID())) {
        instrumentDisplayList.get(selectedIndex).setID(instrumentIDTextField.getText());
        instrumentArrList.set(m, instrumentDisplayList.get(selectedIndex));
       }
      }

      // Make all fields uneditable
      instrumentIDTextField.setEditable(false);
      availabilityComboBox.setEnabled(false);
      conditionComboBox.setEnabled(false);
      instrumentDescriptionTextField.setEditable(false);
      barcodeTextField.setEditable(false);
      assignedOutTextField.setEditable(false);

      instrumentListModel.removeAllElements(); // Clear list

      writeInstrumentFile(); // Save changes
      
      for (int i = 0; i < instrumentDisplayList.size(); i++) {
       instrumentListModel.addElement(instrumentDisplayList.get(i).getID());
      }
      // reset pane;
      instrumentErrorLabel.setVisible(false);
      resetTab();
     }
    }
   }
  });

  btnRefresh = new JButton("Refresh");
  btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 18));
  btnRefresh.addActionListener(new ActionListener() {
   /*
    * actionPerformed This method refreshes the JList of instruments
    * 
    * @param ActionEvent to detect the press of the button
    * 
    * @return Nothing
    */
   public void actionPerformed(ActionEvent arg0) {
    readInstrumentFile();
    instrumentListModel.removeAllElements(); // Clear list of instruments to add valid ones

    for (int i = 0; i < instrumentArrList.size(); i++) {
     instrumentListModel.addElement(instrumentArrList.get(i).getID());
    }
    // reset pane;
    resetTab();

   }
  });

  availableInstrumentsButton = new JButton("Available Instruments");
  availableInstrumentsButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
  availableInstrumentsButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {

    instrumentListModel.removeAllElements(); // Remove all displayed instruments
    instrumentDisplayList = new ArrayList<Instrument>();

    for (int p = 0; p < instrumentArrList.size(); p++) {  //Show only available instruments
     if (instrumentArrList.get(p).getAvailability().equals("Available")) {

      instrumentListModel.addElement(instrumentArrList.get(p).getID());
      instrumentDisplayList.add(instrumentArrList.get(p));

     }
    }

   }
  });

  unavailableInstrumentsButton = new JButton("Unavailable/Broken Instruments");
  unavailableInstrumentsButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
  unavailableInstrumentsButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {

    instrumentListModel.removeAllElements(); // Remove all displayed instruments
    instrumentDisplayList = new ArrayList<Instrument>();

    for (int p = 0; p < instrumentArrList.size(); p++) {
     if ((instrumentArrList.get(p).getAvailability().equals("Unavailable")) //Show only unavailable/broken instruments
       || (instrumentArrList.get(p).getAvailability().equals("Broken"))) {

      instrumentListModel.addElement(instrumentArrList.get(p).getID());
      instrumentDisplayList.add(instrumentArrList.get(p));

     }
    }

   }
  });

  barcodeLabel = new JLabel("Barcode:");
  barcodeLabel.setForeground(Color.BLACK);
  barcodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

  barcodeTextField = new JTextField();
  barcodeTextField.setColumns(10);

  latestActivityLabel = new JLabel("Latest Activity: ");
  latestActivityLabel.setForeground(Color.BLACK);
  latestActivityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

  latestActivityTextField = new JTextField();
  latestActivityTextField.setEditable(false);
  latestActivityTextField.setColumns(10);

  
  //Group Layout
  instrumentGroupLayout = new GroupLayout(instrumentListPanel);
  instrumentGroupLayout.setHorizontalGroup(
   instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
    .addGroup(instrumentGroupLayout.createSequentialGroup()
     .addContainerGap()
     .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
      .addGroup(instrumentGroupLayout.createSequentialGroup()
       .addComponent(txtSearch, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
       .addPreferredGap(ComponentPlacement.UNRELATED)
       .addComponent(btnFind, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
       .addGap(773))
      .addGroup(instrumentGroupLayout.createSequentialGroup()
       .addComponent(btnAddInstrument, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
       .addPreferredGap(ComponentPlacement.RELATED)
       .addComponent(btnDeleteInstrument, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
       .addPreferredGap(ComponentPlacement.RELATED)
       .addComponent(btnRefresh, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
       .addGap(568))
      .addGroup(instrumentGroupLayout.createSequentialGroup()
       .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(instrumentGroupLayout.createSequentialGroup()
         .addGap(10)
         .addComponent(instrumentScrollPane, GroupLayout.PREFERRED_SIZE, 458, GroupLayout.PREFERRED_SIZE)
         .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
          .addGroup(instrumentGroupLayout.createSequentialGroup()
           .addGap(34)
           .addComponent(instrumentErrorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
           .addGap(103))
          .addGroup(instrumentGroupLayout.createSequentialGroup()
           .addGap(134)
           .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.TRAILING, false)
            .addComponent(signedOutLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(instrumentDescriptionLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 88, Short.MAX_VALUE)
            .addComponent(instrumentIdLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(barcodeLabel, Alignment.LEADING)
            .addComponent(availabilityLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(conditionLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(latestActivityLabel, Alignment.LEADING))
           .addGap(84))))
        .addGroup(instrumentGroupLayout.createSequentialGroup()
         .addComponent(availableInstrumentsButton, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
         .addGap(18)
         .addComponent(unavailableInstrumentsButton, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)))
       .addPreferredGap(ComponentPlacement.RELATED)
       .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
        .addComponent(latestActivityTextField, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        .addGroup(instrumentGroupLayout.createSequentialGroup()
         .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
          .addComponent(barcodeTextField, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
          .addComponent(instrumentIDTextField, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
         .addPreferredGap(ComponentPlacement.RELATED))
        .addGroup(instrumentGroupLayout.createSequentialGroup()
         .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
          .addComponent(conditionComboBox, 0, 315, Short.MAX_VALUE)
          .addComponent(availabilityComboBox, 0, 315, Short.MAX_VALUE)
          .addComponent(assignedOutTextField, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
          .addComponent(instrumentDescriptionTextField, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
         .addPreferredGap(ComponentPlacement.RELATED))
        .addGroup(instrumentGroupLayout.createSequentialGroup()
         .addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
         .addGap(71)
         .addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
         .addGap(62)))))
     .addGap(128))
  );
  instrumentGroupLayout.setVerticalGroup(
   instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
    .addGroup(instrumentGroupLayout.createSequentialGroup()
     .addGap(4)
     .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.BASELINE)
      .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
      .addComponent(btnFind, GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE))
     .addPreferredGap(ComponentPlacement.RELATED)
     .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.BASELINE)
      .addComponent(btnAddInstrument, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(btnDeleteInstrument, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(btnRefresh))
     .addPreferredGap(ComponentPlacement.RELATED)
     .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
      .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.BASELINE)
       .addComponent(unavailableInstrumentsButton)
       .addComponent(availableInstrumentsButton))
      .addGroup(instrumentGroupLayout.createSequentialGroup()
       .addGap(26)
       .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.BASELINE)
        .addComponent(instrumentIdLabel)
        .addComponent(instrumentIDTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
     .addPreferredGap(ComponentPlacement.RELATED)
     .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
      .addComponent(instrumentScrollPane, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE)
      .addGroup(instrumentGroupLayout.createSequentialGroup()
       .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(instrumentGroupLayout.createSequentialGroup()
         .addGap(16)
         .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.BASELINE)
          .addComponent(barcodeLabel)
          .addComponent(barcodeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        .addGroup(instrumentGroupLayout.createSequentialGroup()
         .addGap(62)
         .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.BASELINE)
          .addComponent(availabilityComboBox, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
          .addComponent(availabilityLabel_1))))
       .addGap(12)
       .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(instrumentGroupLayout.createSequentialGroup()
         .addGap(6)
         .addComponent(conditionLabel_1)
         .addGap(26)
         .addComponent(signedOutLabel)
         .addGap(81))
        .addGroup(instrumentGroupLayout.createSequentialGroup()
         .addComponent(conditionComboBox, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
         .addGap(18)
         .addComponent(assignedOutTextField, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
         .addGap(20)))
       .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(instrumentGroupLayout.createSequentialGroup()
         .addComponent(instrumentDescriptionLabel)
         .addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
         .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.BASELINE)
          .addComponent(latestActivityLabel)
          .addComponent(latestActivityTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
         .addGap(23))
        .addGroup(instrumentGroupLayout.createSequentialGroup()
         .addComponent(instrumentDescriptionTextField, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
         .addGap(80)))
       .addGap(48)
       .addGroup(instrumentGroupLayout.createParallelGroup(Alignment.BASELINE)
        .addComponent(instrumentErrorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        .addComponent(btnConfirm, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
       .addGap(20)))
     .addGap(158))
  );
  instrumentListPanel.setLayout(instrumentGroupLayout);
    
      // --------------- STUDENT LIST PANEL -----------------
      // initialize text fields
      studentSearchField = new JTextField();
      studentSearchField.setFont(new Font("Tahoma", Font.PLAIN, 14));
      studentSearchField.setText("Search name or barcode");
      studentSearchField.setColumns(10);
      studentNameField = new JTextField();
      studentNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
      studentNameField.setColumns(10);
      studentNameField.setEditable(false);
      studentIDField = new JTextField();
      studentIDField.setFont(new Font("Tahoma", Font.PLAIN, 14));
      studentIDField.setColumns(10);
      studentIDField.setEditable(false);
      studentInstrumentField = new JTextField();
      studentInstrumentField.setFont(new Font("Tahoma", Font.PLAIN, 14));
      studentInstrumentField.setColumns(10);
      studentInstrumentField.setEditable(false);
      studentGradeField = new JTextField();
      studentGradeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
      studentGradeField.setColumns(10);
      studentGradeField.setEditable(false);
      signOutField = new JTextField();
      signOutField.setFont(new Font("Tahoma", Font.PLAIN, 14));
      signOutField.setColumns(10);
      signInField = new JTextField();
      signInField.setFont(new Font("Tahoma", Font.PLAIN, 14));
      signInField.setColumns(10);
      
        // initialize labels
        studentNameLabel = new JLabel("Student Name: ");
        studentNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        studentIDLabel = new JLabel("ID Number:");
        studentIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        studentInstrumentLabel = new JLabel("Instrument:");
        studentInstrumentLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        studentGradeLabel = new JLabel("Grade:");
        studentGradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        studentSignedOutLabel = new JLabel("Sign Out Dates:");
        studentSignedOutLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        studentErrorLabel = new JLabel("");
        studentErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        studentSignedInLabel = new JLabel("Sign in Dates:");
        studentSignedInLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
          // initialize search button
          studentSearchButton = new JButton("Find");
          studentSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
          
            // initialize student list panel
            studentListPanel = new JPanel();
            studentListPanel.setBackground(new Color(51, 153, 102));
            
              // action listener for search button
              studentSearchButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                String target = studentSearchField.getText();
                // call for findStudent method
                ArrayList<Student> temp = findStudent(target);
                // set scroll panel to show objects from temp ArrayList
                studentListModel.removeAllElements();
                for (int i = 0; i < temp.size(); i++) {
                 studentListModel.addElement(temp.get(i).getName());
                }
                // make studentArrList equal temp ArrayList so user can view
                // info when they
                // select something from j list
                studentArrList = temp;
                resetTab();
               }
              });
              
                // initialize add button
                studentAddButton = new JButton("Add Student");
                studentAddButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
                
                  // action listener for add button
                  studentAddButton.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                    // opens add student window
                    @SuppressWarnings("unused")
                 AddStudentGui addStudentWindow = new AddStudentGui();
                   }
                  });
                  
                    // initialize delete button
                    studentDeleteButton = new JButton("Delete Student");
                    studentDeleteButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
                    
                      // action listener for delete button
                      studentDeleteButton.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) {
                        if (JOptionPane.showConfirmDialog(frame,
                          "Are you sure you want to delete the student: " + studentList.getSelectedValue(), "WARNING",
                          JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                         // get which item is selected in the jlist
                         int selectedIndex = studentList.getSelectedIndex();
                         String oldInstrument = studentArrList.get(selectedIndex).getInstrument().getID();
                    
                         // only execute if the jlist contains an item
                         if (selectedIndex != -1) {
                          // read Instrument.csv file
                          readInstrumentFile();
                    
                          // remove assigned Student object from instruments
                          for (int k = 0; k < instrumentArrList.size(); k++) {
                           if (oldInstrument.equals(instrumentArrList.get(k).getID())) {
                            instrumentArrList.get(k).removeStudent(studentArrList.get(selectedIndex).getName());
                    
                           }
                    
                          }
                    
                          // remove from student jlist
                          studentListModel.remove(selectedIndex);
                          // remove student from arraylist
                          studentArrList.remove(selectedIndex);
                          // update .csv file
                          writeFile();
                          writeInstrumentFile();
                          // refresh tabs
                          resetTab();
                         }
                        }
                       }
                      });
                      
                        // edit button
                        studentEditButton = new JButton("Edit");
                        studentEditButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        
                          // action listener for edit button
                          studentEditButton.addActionListener(new ActionListener() {
                           public void actionPerformed(ActionEvent arg0) {
                            // get which item is selected in the jlist
                            int selectedIndex = studentList.getSelectedIndex();
                        
                            // only execute if jlist contains a value
                            if (selectedIndex != -1) {
                             // make all textfields editable
                             studentNameField.setEditable(true);
                             studentIDField.setEditable(true);
                             studentInstrumentField.setEditable(true);
                             studentGradeField.setEditable(true);
                            }
                           }
                          });
                          
                            // confirm button
                            studentConfirmButton = new JButton("Confirm");
                            studentConfirmButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                            
                              // action listener for confirm button
                              // used to store edited info
                              studentConfirmButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                // only if there is an item to edit
                                if (studentArrList.size() > 0 && studentList.getSelectedIndex() != -1) {
                                 // get selected jlist index
                                 int selectedIndex = studentList.getSelectedIndex();
                            
                                 String name = studentNameField.getText();
                                 String id = studentIDField.getText();
                                 String instrument = studentInstrumentField.getText();
                                 String oldInstrument = studentArrList.get(selectedIndex).getInstrument().getID();
                                 String grade = studentGradeField.getText();
                            
                                 if (name.indexOf(",") > -1 || id.indexOf(",") > -1 || instrument.indexOf(",") > -1
                                   || grade.indexOf(",") > -1) {
                                  studentErrorLabel.setText("Error! Remove ',' from field(s)!");
                            
                                 } else {
                                  // set textfields to new information
                                  studentArrList.get(selectedIndex).setName(name);
                                  studentArrList.get(selectedIndex).setID(id);
                                  studentArrList.get(selectedIndex).getInstrument().setID(instrument);
                                  studentArrList.get(selectedIndex).setGrade(grade);
                                  studentErrorLabel.setText("");
                            
                                  // lock textfields
                                  studentNameField.setEditable(false);
                                  studentIDField.setEditable(false);
                                  studentInstrumentField.setEditable(false);
                                  studentGradeField.setEditable(false);
                            
                                  // read Instrument.csv file
                                  readInstrumentFile();
                            
                                  if (oldInstrument.length() == 0 && instrument.length() > 0) {
                            
                                   for (int i = 0; i < instrumentArrList.size(); i++) {
                                    if (instrument.equals(instrumentArrList.get(i).getID())) {
                                     instrumentArrList.get(i).assignStudent(studentArrList.get(selectedIndex).getName());
                                    }
                                   }
                            
                                  } else if (oldInstrument.length() > 0 && instrument.length() > 0) {
                            
                                   for (int k = 0; k < instrumentArrList.size(); k++) {
                                    if (oldInstrument.equals(instrumentArrList.get(k).getID())) {
                                     instrumentArrList.get(k).removeStudent(studentArrList.get(selectedIndex).getName());
                                    }
                            
                                    if (instrument.equals(instrumentArrList.get(k).getID())) {
                                     instrumentArrList.get(k).assignStudent(studentArrList.get(selectedIndex).getName());
                                    }
                                   }
                            
                                  } else if (instrument.length() == 0) {
                                   for (int m = 0; m < instrumentArrList.size(); m++) {
                                    instrumentArrList.get(m).removeStudent(studentArrList.get(selectedIndex).getName());
                                   }
                                  }
                            
                                  // write new information to Student.csv file
                                  writeFile();
                                  // write new information to Instrument.csv file
                                  writeInstrumentFile();
                                  // refresh pane;
                                  resetTab();
                                 }
                                }
                               }
                              });
                              
                                // refreshButton
                                studentRefreshButton = new JButton("Refresh");
                                studentRefreshButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
                                
                                  // action listener for refresh button
                                  // basically resets scroll pane, required for displaying updated info
                                  // after
                                  // adding new student
                                  studentRefreshButton.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent arg0) {
                                    // remove everything from jlist and arraylist
                                    studentListModel.removeAllElements();
                                    signOutListModel.removeAllElements();
                                    signInListModel.removeAllElements();
                                    studentArrList.clear();
                                
                                    // reinitialize jlist and arraylist by storing the new info in
                                    // the .csv file
                                    readStudentFile();
                                    studentArrList = sortStudents();
                                    writeFile();
                                   }
                                  });
                                  
                                    // sign out times search button
                                    searchSignOutButton = new JButton("Search");
                                    searchSignOutButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                    searchSignOutButton.addActionListener(new ActionListener() {
                                     public void actionPerformed(ActionEvent arg0) {
                                      // get date from search field
                                      String target = signOutField.getText();
                                      // call for findStudent method
                                      ArrayList<String> temp = searchSignOut(target);
                                      // set scroll panel to show objects from temp ArrayList
                                      signOutListModel.removeAllElements();

                                      // list search results
                                      for (int i = 0; i < temp.size(); i++) {
                                       signOutListModel.addElement(temp.get(i));
                                      }

                                      // refresh pane
                                      resetTab();
                                     }
                                    });
                                    
                                      // sign in dates search button
                                      searchSignInButton = new JButton("Search");
                                      searchSignInButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                      searchSignInButton.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                        // get date from search field
                                        String target = signInField.getText();
                                        // call for findStudent method
                                        ArrayList<String> temp = searchSignIn(target);
                                        // set scroll panel to show objects from temp ArrayList
                                        signInListModel.removeAllElements();

                                        // display search results
                                        for (int i = 0; i < temp.size(); i++) {
                                         signInListModel.addElement(temp.get(i));
                                        }

                                        // refresh pane
                                        resetTab();
                                       }
                                      });
                                      studentList = new JList<String>(studentListModel);
                                      studentList.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                      studentList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                                      studentList.setVisibleRowCount(-1);
                                      // add action listener for student j list
                                      studentList.addListSelectionListener(new studentListSelectionListener());
                                        signOutList = new JList<String>(signOutListModel);
                                        signOutList.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                        signOutList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                                        signOutList.setVisibleRowCount(-1);
                                        signInList = new JList<String>(signInListModel);
                                        signInList.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                        signInList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                                        signInList.setVisibleRowCount(-1);
                                      
                                        // group layout setup for student panel
                                        gl_studentListPanel = new GroupLayout(studentListPanel);
                                        gl_studentListPanel.setHorizontalGroup(
                                         gl_studentListPanel.createParallelGroup(Alignment.LEADING)
                                          .addGroup(gl_studentListPanel.createSequentialGroup()
                                           .addContainerGap()
                                           .addGroup(gl_studentListPanel.createParallelGroup(Alignment.LEADING)
                                            .addGroup(gl_studentListPanel.createSequentialGroup()
                                             .addComponent(studentList, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
                                             .addGap(19)
                                             .addGroup(gl_studentListPanel.createParallelGroup(Alignment.LEADING)
                                              .addGroup(gl_studentListPanel.createSequentialGroup()
                                               .addGap(305)
                                               .addGroup(gl_studentListPanel.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_studentListPanel.createSequentialGroup()
                                                 .addGap(436)
                                                 .addComponent(studentErrorLabel, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                                                .addGroup(Alignment.TRAILING, gl_studentListPanel.createSequentialGroup()
                                                 .addComponent(studentEditButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                 .addGap(18)
                                                 .addComponent(studentConfirmButton)
                                                 .addGap(21)))
                                               .addGap(21))
                                              .addGroup(gl_studentListPanel.createSequentialGroup()
                                               .addGap(47)
                                               .addGroup(gl_studentListPanel.createParallelGroup(Alignment.TRAILING)
                                                .addComponent(studentIDLabel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(studentNameLabel)
                                                .addComponent(studentInstrumentLabel, Alignment.LEADING)
                                                .addComponent(studentGradeLabel, Alignment.LEADING)
                                                .addComponent(studentSignedOutLabel, Alignment.LEADING))
                                               .addPreferredGap(ComponentPlacement.RELATED)
                                               .addGroup(gl_studentListPanel.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_studentListPanel.createSequentialGroup()
                                                 .addGroup(gl_studentListPanel.createParallelGroup(Alignment.TRAILING)
                                                  .addComponent(studentIDField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                                                  .addComponent(studentNameField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                                                  .addComponent(studentInstrumentField, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
                                                 .addGap(340))
                                                .addGroup(gl_studentListPanel.createSequentialGroup()
                                                 .addComponent(studentGradeField, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                 .addContainerGap())
                                                .addGroup(gl_studentListPanel.createSequentialGroup()
                                                 .addGroup(gl_studentListPanel.createParallelGroup(Alignment.TRAILING, false)
                                                  .addGroup(gl_studentListPanel.createSequentialGroup()
                                                   .addComponent(signOutField)
                                                   .addGap(18)
                                                   .addComponent(searchSignOutButton))
                                                  .addComponent(signOutList, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
                                                 .addGap(46)
                                                 .addComponent(studentSignedInLabel)
                                                 .addPreferredGap(ComponentPlacement.UNRELATED)
                                                 .addGroup(gl_studentListPanel.createParallelGroup(Alignment.LEADING)
                                                  .addGroup(gl_studentListPanel.createSequentialGroup()
                                                   .addComponent(signInField, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                   .addPreferredGap(ComponentPlacement.UNRELATED)
                                                   .addComponent(searchSignInButton))
                                                  .addComponent(signInList, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                                                 .addContainerGap())))))
                                            .addGroup(gl_studentListPanel.createSequentialGroup()
                                             .addComponent(studentAddButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                             .addGap(19)
                                             .addComponent(studentDeleteButton)
                                             .addGap(18)
                                             .addComponent(studentRefreshButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                             .addContainerGap())
                                            .addGroup(gl_studentListPanel.createSequentialGroup()
                                             .addComponent(studentSearchField, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
                                             .addGap(18)
                                             .addComponent(studentSearchButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                             .addContainerGap())))
                                        );
                                        gl_studentListPanel.setVerticalGroup(
                                         gl_studentListPanel.createParallelGroup(Alignment.LEADING)
                                          .addGroup(gl_studentListPanel.createSequentialGroup()
                                           .addGroup(gl_studentListPanel.createParallelGroup(Alignment.TRAILING)
                                            .addGroup(Alignment.LEADING, gl_studentListPanel.createSequentialGroup()
                                             .addGap(291)
                                             .addComponent(signOutList, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(gl_studentListPanel.createSequentialGroup()
                                             .addGap(5)
                                             .addGroup(gl_studentListPanel.createParallelGroup(Alignment.BASELINE)
                                              .addComponent(studentSearchField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                              .addComponent(studentSearchButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                             .addPreferredGap(ComponentPlacement.RELATED)
                                             .addGroup(gl_studentListPanel.createParallelGroup(Alignment.BASELINE)
                                              .addComponent(studentAddButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                              .addComponent(studentDeleteButton)
                                              .addComponent(studentRefreshButton))
                                             .addGroup(gl_studentListPanel.createParallelGroup(Alignment.LEADING)
                                              .addGroup(gl_studentListPanel.createSequentialGroup()
                                               .addGap(185)
                                               .addComponent(searchSignInButton)
                                               .addGap(198)
                                               .addComponent(studentErrorLabel))
                                              .addGroup(gl_studentListPanel.createSequentialGroup()
                                               .addGap(18)
                                               .addGroup(gl_studentListPanel.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_studentListPanel.createSequentialGroup()
                                                 .addGroup(gl_studentListPanel.createParallelGroup(Alignment.LEADING)
                                                  .addGroup(gl_studentListPanel.createSequentialGroup()
                                                   .addComponent(studentNameLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                                   .addGap(14)
                                                   .addGroup(gl_studentListPanel.createParallelGroup(Alignment.BASELINE)
                                                    .addComponent(studentIDLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(studentIDField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                                                   .addGap(15)
                                                   .addGroup(gl_studentListPanel.createParallelGroup(Alignment.BASELINE)
                                                    .addComponent(studentInstrumentLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(studentInstrumentField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                   .addGap(14)
                                                   .addGroup(gl_studentListPanel.createParallelGroup(Alignment.BASELINE)
                                                    .addComponent(studentGradeLabel)
                                                    .addComponent(studentGradeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                  .addComponent(studentNameField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                 .addGap(23)
                                                 .addGroup(gl_studentListPanel.createParallelGroup(Alignment.BASELINE)
                                                  .addComponent(studentSignedOutLabel)
                                                  .addComponent(signOutField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                  .addComponent(searchSignOutButton)
                                                  .addComponent(studentSignedInLabel)
                                                  .addComponent(signInField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                 .addGap(9)
                                                 .addComponent(signInList, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(studentList, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE))))
                                             .addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                             .addGroup(gl_studentListPanel.createParallelGroup(Alignment.BASELINE)
                                              .addComponent(studentConfirmButton)
                                              .addComponent(studentEditButton))
                                             .addGap(9)))
                                           .addGap(33))
                                        );
                                        
                                          // set studentListPanel to group layout
                                          studentListPanel.setLayout(gl_studentListPanel);
                                          // add student panel to tabbed pane
                                          tabbedPane.addTab("Student List", null, studentListPanel, null);
                                          // -------END OF STUDENT LIST PANEL-------

                                          // -------SIGN OUT PANEL-------
                                          
                                          
                                          //Initialize panel and pane
                                          signOutPanel = new JPanel();
                                          signOutPanel.setBackground(SystemColor.activeCaption);
                                          tabbedPane.addTab("Sign out", null, signOutPanel, null);
                                          signOutPanel.setLayout(null);
                                          
                                            {
                                          
                                             //Initialize button enter
                                             btnEnter = new JButton("ENTER");
                                             btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 18));
                                          
                                             //Initialize Text fields
                                             instrumentTextField = new JTextField();
                                             instrumentTextField.setColumns(10);
                                             instrumentTextField.setBounds(114, 260, 163, 20);
                                             signOutPanel.add(instrumentTextField);
                                          
                                             usernameTextField = new JTextField();
                                             usernameTextField.setBounds(114, 223, 163, 20);
                                             signOutPanel.add(usernameTextField);
                                             usernameTextField.setColumns(10);
                                          
                                             //Initialize labels
                                             lblPleaseScanStudent = new JLabel("Please scan student card and instrument ID");
                                             lblPleaseScanStudent.setFont(new Font("Tahoma", Font.PLAIN, 18));
                                             lblPleaseScanStudent.setBounds(114, 157, 325, 14);
                                             signOutPanel.add(lblPleaseScanStudent);
                                          
                                             JLabel lblStudentId = DefaultComponentFactory.getInstance().createTitle("Student ID:");
                                             lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 18));
                                             lblStudentId.setBounds(33, 226, 70, 14);
                                             signOutPanel.add(lblStudentId);
                                          
                                             JLabel lblInstrumentId = DefaultComponentFactory.getInstance().createTitle("Instrument ID:");
                                             lblInstrumentId.setFont(new Font("Tahoma", Font.PLAIN, 18));
                                             lblInstrumentId.setForeground(Color.BLACK);
                                             lblInstrumentId.setBounds(17, 263, 87, 14);
                                             signOutPanel.add(lblInstrumentId);
                                          
                                             //Add a key listener to usernameTextField to move to instrument textField when the number of characters in the usernameTextField is 10
                                             usernameTextField.addKeyListener(new KeyListener() {
                                          
                                              public void keyTyped(KeyEvent e) {
                                              }
                                          
                                              @Override
                                              public void keyReleased(KeyEvent e) {
                                               if (usernameTextField.getText().length() == 10) {
                                                usernameTextField.transferFocus();
                                               }
                                              }
                                          
                                              @Override
                                              public void keyPressed(KeyEvent e) {
                                              }
                                             });
                                          
                                             //btnEnter actionListener 
                                             btnEnter.addActionListener(new ActionListener() {
                                              public void actionPerformed(ActionEvent e) {
                                          
                                               //Try and catch to read Instrument and studennt File
                                               try {
                                                readStudentFile();
                                                readInstrumentFile();
                                               } catch (Exception e1) {
                                                e1.printStackTrace();
                                               }
                                          
                                               //initialize tempObjects for student and instrument
                                               String studentId = usernameTextField.getText();
                                               String instrumentId = instrumentTextField.getText();
                                               Student tempStudent = null;
                                               Instrument tempInstrument = null;
                                          
                                               //create tempStudent and tempInstrument objects if the id's match the arrayList.getID
                                               for (int i = 0; i < studentArrList.size(); i++) {
                                                if (studentArrList.get(i).getID().equals(studentId)) {
                                                 tempStudent = studentArrList.get(i);
                                                } else {
                                                 usernameTextField.setText("");
                                                 instrumentTextField.setText("");
                                                }
                                               }
                                               for (int i = 0; i < instrumentArrList.size(); i++) {
                                                if (instrumentArrList.get(i).getBarcode().equals(instrumentId)) {
                                                 tempInstrument = instrumentArrList.get(i);
                                                } else {
                                                 usernameTextField.setText("");
                                                 instrumentTextField.setText("");
                                                }
                                               }
                                               
                                               //If the input does not match data records, output : Error, credentials are incorrect
                                               if (tempStudent == null || tempInstrument == null) {
                                                lblPleaseScanStudent.setBounds(210, 167, 365, 14);
                                                lblPleaseScanStudent.setForeground(Color.RED);
                                                lblPleaseScanStudent.setText("Error, credentials are incorrect");
                                               } else if ((tempInstrument.getAvailability().equals("Available"))) {
                                                usernameTextField.setText("");
                                                instrumentTextField.setText("");
                                                lblPleaseScanStudent.setBounds(210, 167, 365, 14);
                                                lblPleaseScanStudent.setForeground(Color.GREEN);
                                                lblPleaseScanStudent.setText(tempInstrument.getID() + " has been successfully signed out!");
                                          
                                                //if sign out times are empty, set to ""
                                                if (tempStudent.getTimesSignedOut() == null) {
                                                 tempStudent.setTimesSignedOut("");
                                                }
                                          
                                                //set DateBorrwed, timesSignedOut and add to signout arrList
                                                tempStudent.setDateBorrowed(dateFormat.format(new Date()));
                                                tempStudent.setTimesSignedOut(
                                                  tempStudent.getTimesSignedOut() + dateFormat.format(new Date()) + " ");
                                          
                                                // add signout date to arraylist in tempStudent class
                                                ArrayList<String> signOutArrList = tempStudent.getSignOutArrList();
                                                signOutArrList.add(tempInstrument.getID().replaceAll("[\\s|\\u00A0]+", "") + "@"
                                                  + dateFormat.format(new Date()));
                                                tempStudent.setSignOutArrList(signOutArrList);
                                          
                                                //set availibility to unavailable
                                                tempInstrument.setAvailability("Unavailable");
                                          
                                                //for loop to remove the old student
                                                for (int i = 0; i < studentArrList.size(); i++) {
                                                 if (studentArrList.get(i).getName().equals(tempStudent.getName())) {
                                                  studentArrList.remove(i);
                                                 }
                                                }
                                                studentArrList.add(tempStudent);
                                          
                                                //for loop to remove the old instrument
                                                for (int i = 0; i < instrumentArrList.size(); i++) {
                                                 if (instrumentArrList.get(i).getID().equals(tempInstrument.getID())) {
                                                  instrumentArrList.remove(i);
                                                 }
                                                }
                                          
                                                readInstrumentLog(tempInstrument);
                                                instrumentLog.add(tempStudent.getName() + " signed out on " + dateFormat.format(new Date()));
                                                writeInstrumentLog(instrumentLog, tempInstrument);
                                                instrumentArrList.add(tempInstrument);
                                          
                                                // write a student report:
                                                //declare BufferedWriter
                                                BufferedWriter bw = null;
                                                try {
                                                 //boolean variable to store whether a file of a student report already exists or not
                                                 boolean existing = false;
                                                 // checks if file exists
                                                 File file = new File(new File(".").getAbsolutePath() + "/Student Reports/"
                                                   + tempStudent.getName() + "Report.csv");
                                                 if (file.exists()) {
                                                  existing = true;
                                                 }
                                          
                                                 // initialize buffered writer
                                                 bw = new BufferedWriter(new FileWriter(file, true));
                                          
                                                 // if student report is a new file write sign out information with a header, else without a header
                                                 if (existing == false) {
                                                  bw.write("Instrument,Sign Out, Sign In");
                                                  bw.newLine();
                                                  bw.write(tempStudent.getInstrument().getID() + "," + dateFormat.format(new Date()));
                                                 } else {
                                                  bw.write(tempStudent.getInstrument().getID() + "," + dateFormat.format(new Date()));
                                                 }
                                                 
                                                 bw.close();
                                                } catch (IOException f) {
                                                }
                                                //if the Availability is unavailable, the next scan will be a sign in
                                               } else if ((tempInstrument.getAvailability().equals("Unavailable"))) {
                                              //clear textFields
                                                usernameTextField.setText("");
                                                instrumentTextField.setText("");
                                                lblPleaseScanStudent.setBounds(210, 167, 365, 14);
                                                lblPleaseScanStudent.setForeground(Color.GREEN);
                                                lblPleaseScanStudent.setText( tempInstrument.getID() + " has been successfully signed in!");
                                          
                                                //set Date returned and availability
                                                tempStudent.setDateReturned(dateFormat.format(new Date()));
                                                tempInstrument.setAvailability("Available");
                                                if (tempStudent.getTimesSignedIn() == null) {
                                                 tempStudent.setTimesSignedIn("");
                                                }
                                                tempStudent
                                                  .setTimesSignedIn(tempStudent.getTimesSignedIn() + dateFormat.format(new Date()) + " ");
                                          
                                                // add sign in date to the arraylist in temp student
                                                ArrayList<String> signInArrList = tempStudent.getSignInArrList();
                                                signInArrList.add(tempInstrument.getID().replaceAll("[\\s|\\u00A0]+", "") + "@"
                                                  + dateFormat.format(new Date()));
                                                tempStudent.setSignInArrList(signInArrList);
                                          
                                                //for loop to check through the arrayLists and remove the old students and instruments
                                                for (int i = 0; i < studentArrList.size(); i++) {
                                                 if (studentArrList.get(i).getName().equals(tempStudent.getName())) {
                                                  studentArrList.remove(i);
                                                 }
                                                }
                                                studentArrList.add(tempStudent);
                                          
                                                for (int i = 0; i < instrumentArrList.size(); i++) {
                                                 if (instrumentArrList.get(i).getID().equals(tempInstrument.getID())) {
                                                  instrumentArrList.remove(i);
                                                 }
                                                }
                                                readInstrumentLog(tempInstrument);
                                                instrumentLog.add(tempStudent.getName() + " signed in on " + dateFormat.format(new Date()));
                                                writeInstrumentLog(instrumentLog, tempInstrument);
                                                instrumentArrList.add(tempInstrument);
                                          
                                                // write a student report:
                                                //declare BufferedWriter
                                                BufferedWriter bw = null;
                                                try {
                                                 //Initialize file that goes to Student Reports folder
                                                 File file = new File(new File(".").getAbsolutePath() + "/Student Reports/"
                                                   + tempStudent.getName() + "Report.csv");
                                          
                                                 // initialize buffered writer
                                                 bw = new BufferedWriter(new FileWriter(file, true));
                                          
                                                 // write sign in date
                                                 bw.write("," + dateFormat.format(new Date()));
                                                 bw.newLine();
                                          
                                                 bw.close();
                                                } catch (IOException f) {
                                                }
                                          
                                               }
                                          
                                               writeFile();
                                               writeInstrumentFile();
                                          
                                              }
                                             });
                                          
                                             btnEnter.setBounds(328, 2, 100, 23);
                                             signOutPanel.add(btnEnter);
                                          
                                             //Make the label
                                             JLabel lblRhhsMusicSignout = DefaultComponentFactory.getInstance().createTitle("RHHS Music Signout");
                                             lblRhhsMusicSignout.setForeground(new Color(0, 153, 51));
                                             lblRhhsMusicSignout.setFont(new Font("Dubai Medium", Font.BOLD, 45));
                                             lblRhhsMusicSignout.setBounds(84, 45, 396, 139);
                                             signOutPanel.add(lblRhhsMusicSignout);
                                          
                                             //Music signout groupLabel
                                             GroupLayout gl_signOutPanel = new GroupLayout(signOutPanel);
                                             gl_signOutPanel.setHorizontalGroup(
                                              gl_signOutPanel.createParallelGroup(Alignment.TRAILING)
                                               .addGroup(gl_signOutPanel.createSequentialGroup()
                                                .addGap(305)
                                                .addGroup(gl_signOutPanel.createParallelGroup(Alignment.LEADING)
                                                 .addComponent(lblInstrumentId)
                                                 .addComponent(lblStudentId, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_signOutPanel.createParallelGroup(Alignment.TRAILING, false)
                                                 .addComponent(instrumentTextField, Alignment.LEADING)
                                                 .addComponent(usernameTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                                                .addContainerGap(543, Short.MAX_VALUE))
                                               .addGroup(Alignment.LEADING, gl_signOutPanel.createSequentialGroup()
                                                .addGap(403)
                                                .addComponent(lblPleaseScanStudent, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                                .addGap(432))
                                               .addGroup(gl_signOutPanel.createSequentialGroup()
                                                .addContainerGap(782, Short.MAX_VALUE)
                                                .addComponent(btnEnter, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                                .addGap(380))
                                               .addGroup(Alignment.LEADING, gl_signOutPanel.createSequentialGroup()
                                                .addGap(352)
                                                .addComponent(lblRhhsMusicSignout, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(418, Short.MAX_VALUE))
                                             );
                                             gl_signOutPanel.setVerticalGroup(
                                              gl_signOutPanel.createParallelGroup(Alignment.TRAILING)
                                               .addGroup(gl_signOutPanel.createSequentialGroup()
                                                .addGap(111)
                                                .addComponent(lblRhhsMusicSignout, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(lblPleaseScanStudent, GroupLayout.PREFERRED_SIZE, 16, Short.MAX_VALUE)
                                                .addGap(57)
                                                .addGroup(gl_signOutPanel.createParallelGroup(Alignment.BASELINE)
                                                 .addComponent(usernameTextField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                 .addComponent(lblStudentId, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
                                                .addGap(31)
                                                .addGroup(gl_signOutPanel.createParallelGroup(Alignment.TRAILING)
                                                 .addComponent(lblInstrumentId, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
                                                 .addComponent(instrumentTextField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                                                .addGap(28)
                                                .addComponent(btnEnter)
                                                .addGap(279))
                                             );
                                             signOutPanel.setLayout(gl_signOutPanel);
                                          
                                            }
  // ----------------END OF SIGN OUT PANEL ---------------

 }// end of initialize

 // ---------------STUDENT LIST PANEL METHODS-------------
 /*
  * selection listener for studentList jlist
  */
 public class studentListSelectionListener implements ListSelectionListener {

  @Override
  public void valueChanged(ListSelectionEvent e) {
   if (e.getValueIsAdjusting()) {
    // remove all items from signout/signin jlist
    signOutListModel.removeAllElements();
    signInListModel.removeAllElements();
    // reset signout/signin search fields
    signOutField.setText("");
    signInField.setText("");

    // get which item is selected
    int selectedIndex = studentList.getSelectedIndex();
    currentStudent = selectedIndex;

    // get the string value of whats selected
    Student temp = studentArrList.get(selectedIndex);

    // set textfields to student information
    studentNameField.setText(temp.getName());
    studentIDField.setText(temp.getID());
    studentInstrumentField.setText(temp.getInstrument().getID());
    studentGradeField.setText(temp.getGrade());

    ArrayList<String> tempArrList = temp.getSignOutArrList();

    // set signout
    for (int i = 0; i < tempArrList.size(); i++) {
     signOutListModel.addElement(tempArrList.get(i));
    }

    tempArrList = temp.getSignInArrList();

    // set signin
    for (int i = 0; i < tempArrList.size(); i++) {
     signInListModel.addElement(tempArrList.get(i));
    }

    // lock textfields
    studentNameField.setEditable(false);
    studentIDField.setEditable(false);
    studentInstrumentField.setEditable(false);
    studentGradeField.setEditable(false);
   }
  }
 }// end of studentListSelectionListener

 /*
  * searchSignIn method that takes in a date as a string and finds the sign in
  * dates containing the keyword
  * 
  * @params String
  * 
  * @return ArrayList<String>
  */
 public ArrayList<String> searchSignIn(String date) {
  // temporary Student object variable
  Student temp = studentArrList.get(currentStudent);
  // temporary ArrayList of Student sign in history
  ArrayList<String> tempArrList = temp.getSignInArrList();
  // search results ArrayList
  ArrayList<String> results = new ArrayList<String>();

  // loop through tempArrList and add matches to result ArrayList
  for (int i = 0; i < tempArrList.size(); i++) {
   if (tempArrList.get(i).indexOf(date) > -1) {
    results.add(tempArrList.get(i));
   }
  }
  return results;
 }// end of searchSignIn

 /*
  * searchSignOut method that takes in a date and finds all sign out dates of
  * that date
  * 
  * @params String
  * 
  * @return ArrayList<String>
  */
 public ArrayList<String> searchSignOut(String date) {
  // temporary storage
  Student temp = studentArrList.get(currentStudent);
  ArrayList<String> tempArrList = temp.getSignOutArrList();
  // search results
  ArrayList<String> results = new ArrayList<String>();

  // find and add matches to result ArrayList
  for (int i = 0; i < tempArrList.size(); i++) {
   if (tempArrList.get(i).indexOf(date) > -1) {
    results.add(tempArrList.get(i));
   }
  }
  return results;
 }// end of searchSignOut

 /*
  * sortStudents method that sorts the jlist alphabetically based on student last
  * names
  * 
  * @param nothing
  * 
  * @return ArrayList of Student objects
  */
 public ArrayList<Student> sortStudents() {
  // temporary variables
  String name1;
  String name2;
  Student temp;
  ArrayList<Student> tempList = studentArrList;

  // rearrange studentArrList into alphabetical order
  for (int i = 1; i < tempList.size(); i++) {
   for (int j = i; j > 0; j--) {
    name1 = tempList.get(j).getName();
    name1 = name1.substring(name1.indexOf(" ") + 1);
    name2 = tempList.get(j - 1).getName();
    name2 = name2.substring(name2.indexOf(" ") + 1);

    if (name1.compareTo(name2) < 0) {
     temp = tempList.get(j);
     tempList.set(j, tempList.get(j - 1));
     tempList.set(j - 1, temp);
    }
   }
  }

  // remove and re-add jlist items
  studentListModel.removeAllElements();
  for (int i = 0; i < tempList.size(); i++) {
   studentListModel.addElement(tempList.get(i).getName());
  }
  // refresh pane
  resetTab();
  // return new sorted studentArrList
  return tempList;
 }// end of sortStudents

 /*
  * findStudent method that returns all Student objects that contain keyword from
  * user input
  * 
  * @params String
  * 
  * @return ArrayList of Student objects
  */
 public ArrayList<Student> findStudent(String target) {
  // all strings are converted to lower case to simplify comparisons
  target = target.toLowerCase();
  // ArrayList storing all Student objects containing keyword
  ArrayList<Student> found = new ArrayList<Student>();

  // run through studentArrList and add objects that match the name or
  // barcode
  for (int i = 0; i < studentArrList.size(); i++) {
   if ((studentArrList.get(i).getName()).toLowerCase().indexOf(target) > -1) {
    found.add(studentArrList.get(i));
   } else if (studentArrList.get(i).getID().indexOf(target) > -1) {
    found.add(studentArrList.get(i));
   }
  }

  return found;
 }// end of findStudent

 /*
  * resetTab method that set studentScrollPane invisible then visible
  * 
  * @params nothing
  * 
  * @returns nothing
  */
 public void resetTab() {
// turns scroll pane on and off
  studentScrollPane.setVisible(false);
  studentScrollPane.setVisible(true);
  signOutScrollPane.setVisible(false);
  signOutScrollPane.setVisible(true);
  signInScrollPane.setVisible(false);
  signInScrollPane.setVisible(true);
 }// end of resetTab

 /*
  * writeFile method thats stores all student information into a .csv file
  * 
  * @params nothing
  * 
  * @return nothing
  */
 public void writeFile() {
  BufferedWriter bw = null;

  try {
   // initialize buffered writer
   bw = new BufferedWriter(new FileWriter("students.csv"));

   // temporary storage
   Student temp = new Student();

   // header for the excel file
   bw.write("Name,Barcode,Instrument,Grade,Sign Ins,Sign Outs");
   bw.newLine();

   // go through studentArrList ArrayList and write/format each object
   // info into
   // students.csv
   for (int i = 0; i < studentArrList.size(); i++) {
    temp = studentArrList.get(i);
    bw.write(temp.getName() + "," + temp.getID() + "," + temp.getInstrument().getID() + ","
      + temp.getGrade() + ",");

    ArrayList<String> tempArrList = temp.getSignOutArrList();

    // write sign out dates
    for (int j = 0; j < tempArrList.size(); j++) {
     bw.write(tempArrList.get(j) + " ");
    }

    // separate sign out
    bw.write(",");

    tempArrList = temp.getSignInArrList();

    // write sign in dates
    for (int k = 0; k < tempArrList.size(); k++) {
     bw.write(tempArrList.get(k) + " ");
    }

    bw.newLine();
   }
   bw.close();
  } catch (IOException e) {
  }
 }

 /*
  * readFile method that reads .csv file and adds the info to studentArrList
  * andstudentListModel
  * 
  * @params nothing
  * 
  * @returns nothing
  */
 public void readStudentFile() {
  BufferedReader br = null;
  studentArrList = new ArrayList<Student>();
  try {
   // initialize buffered reader
   br = new BufferedReader(new FileReader("students.csv"));
   String line = br.readLine();// dont take the tags
   // temporary storage
   String name, id, instrument, grade, signOut, signIn;
   Student temp = new Student();

   // read line by line
   while ((line = br.readLine()) != null) {
    // get student info from file
    name = line.substring(0, line.indexOf(","));
    line = line.substring(line.indexOf(",") + 1);
    id = line.substring(0, line.indexOf(","));
    line = line.substring(line.indexOf(",") + 1);
    instrument = line.substring(0, line.indexOf(","));
    line = line.substring(line.indexOf(",") + 1);
    grade = line.substring(0, line.indexOf(","));
    line = line.substring(line.indexOf(",") + 1);
    signOut = line.substring(0, line.indexOf(","));
    line = line.substring(line.indexOf(",") + 1);
    signIn = line;

    // add student to array list
    temp = new Student(name, id, new Instrument(instrument), grade);
    // sign outs:
    // temporary variables
    ArrayList<String> tempArrList = new ArrayList<String>();

    // get all sign out dates in the line
    while (signOut.indexOf(" ") > -1) {
     tempArrList.add(signOut.substring(0, signOut.indexOf(" ")));
     signOut = signOut.substring(signOut.indexOf(" ") + 1);
    }

    // set student sign out list to what's in the file
    temp.setSignOutArrList(tempArrList);

    // sign ins:
    // temporary variable
    ArrayList<String> ArrListSignIn = new ArrayList<String>();

    // get all sign in dates in the line
    while (signIn.indexOf(" ") > -1) {
     ArrListSignIn.add(signIn.substring(0, signIn.indexOf(" ")));
     signIn = signIn.substring(signIn.indexOf(" ") + 1);
    }

    // set student sign in list to what's in the file
    temp.setSignInArrList(ArrListSignIn);

    // add to studentArrList
    studentArrList.add(temp);

    // add to j list
    studentListModel.addElement(temp.getName());
   }
   br.close();
  } catch (IOException e) {
  }
 }// end of readFile
  // ---------------------- END OF STUDENT LIST METHODS-----------------------

 // ---------------------- START INSTRUMENT LIST
 // METHODS-----------------------

 /*
  * readInstrumentFile
  *  This method reads the contents of the csv file and makes it into an array list
  * @param Nothing it uses the class ArrayList
  * @return Nothing it edits the class ArrayList
  */
 public static void readInstrumentFile() {

  instrumentArrList = new ArrayList<Instrument>();
  instrumentDisplayList = new ArrayList<Instrument>();
  int count = 0;
  String id = "";
  String barcode = "";
  String availability = "";
  String condition = "";
  String description = "";
  ArrayList<String> names = new ArrayList<String>();
  try {
   File file = new File("instruments.csv");
   FileReader fileReader = new FileReader(file);
   BufferedReader bufferedReader = new BufferedReader(fileReader);
   String line;
   line = bufferedReader.readLine();
   while ((line = bufferedReader.readLine()) != null) {

    // Every time a new instrument is added reset variables
    names = new ArrayList<String>();
    id = "";
    barcode = "";
    availability = "";
    condition = "";
    description = "";
    count = 0;

    while (line.length() > 0 && line.indexOf(",") > -1) {

     // Every comma is for different properties
     if (count == 0) {
      id = line.substring(0, line.indexOf(",")); // Add the specified property and substring it out
      line = line.substring(line.indexOf(",") + 1);
     }
     if (count == 1) {
      barcode = line.substring(0, line.indexOf(",")); // Add the specified property and substring it
                  // out
      line = line.substring(line.indexOf(",") + 1);
     }
     if (count == 2) {
      availability = line.substring(0, line.indexOf(","));
      line = line.substring(line.indexOf(",") + 1);
     }
     if (count == 3) {
      condition = line.substring(0, line.indexOf(","));
      line = line.substring(line.indexOf(",") + 1);
     }

     // fourth comma is repeated because there can be more than 1 student
     if (count == 4) {
      description = line.substring(0, line.indexOf(","));
      line = line.substring(line.indexOf(",") + 1);
     }

     if (count == 5) {
      names.add(line.substring(0, line.indexOf(",")));
      line = line.substring(line.indexOf(",") + 1);

     }

     if (count == 5) {
     } else {
      count++;
     }
    }

    // Add the instrument after all the properties have been found through the file
    // reader

    instrumentArrList.add((new Instrument(id, barcode, availability, condition, description, names)));
    instrumentDisplayList.add((new Instrument(id, barcode, availability, condition, description, names)));
    instrumentArrList = sortInstruments(instrumentArrList);
    instrumentDisplayList = sortInstruments(instrumentDisplayList);

   }
   fileReader.close();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }

 /*
  * readInstrumentLog
  *  This method reads the contents of the csv file of the instruments report
  * @param The instrument object
  * @return Nothing it edits the class ArrayList
  */
 public void readInstrumentLog(Instrument instrument) {
  BufferedReader br = null;
  instrumentLog = new ArrayList<String>();
  try {
   // initialize buffered reader
   br = new BufferedReader(new FileReader(
     new File(".").getAbsolutePath() + "//Instrument Reports//" + instrument.getID() + " Report.csv"));
   String line;

   // read line by line
   while ((line = br.readLine()) != null) {
    instrumentLog.add(line);
   }
   br.close();
  } catch (IOException e) {
  }

 }
 
 /*
  * writeInstrumentLog
  *  This method writes the contents of the instrumentLog to the csv file
  * @param The instrument object and arraylist of strings of the log
  * @return Nothing as it writes to a file
  */
 public void writeInstrumentLog(ArrayList<String> instrumentLog, Instrument instrument) {
  try {
   BufferedWriter bw = new BufferedWriter(new FileWriter(
     new File(".").getAbsolutePath() + "//Instrument Reports//" + instrument.getID() + " Report.csv"));
   for (int i = 0; i < instrumentLog.size(); i++) {
    bw.write(instrumentLog.get(i));
    bw.newLine();
   }
   bw.close(); // Close file to save

  } catch (IOException e) {
   e.printStackTrace();
  }

 }

 /*
  * sortInstruments This method sorts an array of instruments based on ID
  * 
  * @param An arraylist of unsorted instruments
  * 
  * @return An arraylist of sorted instruments
  */
 public static ArrayList<Instrument> sortInstruments(ArrayList<Instrument> tempList) {
  String name1;
  String name2;
  Instrument temp;

  if (tempList.size() < 2) {
   return tempList;
  }

  for (int i = 1; i < tempList.size(); i++) {
   for (int j = i; j > 0; j--) {
    name1 = tempList.get(j).getID();
    name1 = name1.substring(name1.indexOf(" ") + 1);
    name2 = tempList.get(j - 1).getID();
    name2 = name2.substring(name2.indexOf(" ") + 1);

    if (name1.compareTo(name2) < 0) {
     temp = tempList.get(j);
     tempList.set(j, tempList.get(j - 1));
     tempList.set(j - 1, temp);
    }
   }
  }

  return tempList;
 }

 /*
  * writeInstrumentFile This method writes the contents of the instrument
  * arraylist into a csv file
  * 
  * @param ArrayList of instruments
  * 
  * @return Nothing because it outputs to a file
  */
 public static void writeInstrumentFile() {
  try {
   File file = new File("instruments.csv");
   FileWriter fileWriter = new FileWriter(file, false);
   BufferedWriter bw = new BufferedWriter(fileWriter);

   bw.write("Instrument ID,Barcode,Availability,Condition,Description,Current Students"); // First
   // line
   // for
   // excel
   // so
   // you
   // can
   // identify
   // info
   bw.newLine();

   // Go through each instrument and write all its properties seperated
   // by commas
   for (int k = 0; k < instrumentArrList.size(); k++) {

    String description;
    String assignedStudents = "";

    Instrument temp = instrumentArrList.get(k);

    if (temp.getDescription().equals("")) {
     description = " ";
    } else {
     description = temp.getDescription();
    }

    if (temp.getAssignedStudents() == null) {
     assignedStudents = " ";
    } else {
     for (int i = 0; i < temp.getAssignedStudents().size(); i++) {
      assignedStudents = assignedStudents + temp.getAssignedStudents().get(i) + ",";
     }
    }

    bw.write(instrumentArrList.get(k).getID() + "," + instrumentArrList.get(k).getBarcode() + ","
      + instrumentArrList.get(k).getAvailability() + "," + instrumentArrList.get(k).getCondition()
      + "," + description + "," + assignedStudents);

    bw.newLine(); // New line separating each instrument
   }
   bw.close(); // Close file to save

  } catch (IOException e) {
   e.printStackTrace();
  }
 }

 public class instrumentListSelectionListener implements ListSelectionListener {

  /*
   * valueChanged This method displays the info of the instrument thats selected
   * 
   * @param ListSelectionEvent to detect the selection of the list
   * 
   * @return Nothing
   */
  public void valueChanged(ListSelectionEvent e) {
   if (e.getValueIsAdjusting()) {
    String students = "";
    // get which item is selected
    int selectedIndex = instrumentList.getSelectedIndex(); // Get the selected instrument
    Instrument temp = instrumentDisplayList.get(selectedIndex); 
    
    
    // Make the list of students into a string

    for (int m = 0; m < temp.getAssignedStudents().size(); m++) {
     if (m == temp.getAssignedStudents().size() - 1) {
      students = (students + temp.getAssignedStudents().get(m)); // Last student doesn't have a comma in the end
     } else {
      students = (students + temp.getAssignedStudents().get(m) + ", "); // Add commas inbetween
     }
    }

    //// Set the combo boxes to their appropriate options
    for (int k = 0; k < 4; k++) {
     if (temp.getAvailability().equals(availabilityComboBox.getItemAt(k))) {
      availabilityComboBox.setSelectedIndex(k);
     }
     if (temp.getCondition().equals(conditionComboBox.getItemAt(k))) {

      conditionComboBox.setSelectedIndex(k);
     }
    }
    readInstrumentLog(temp);
    // set textfields to instrument information
    instrumentIDTextField.setText(temp.getID());
    barcodeTextField.setText(temp.getBarcode());
    instrumentDescriptionTextField.setText(temp.getDescription());
    assignedOutTextField.setText(students);

    if (instrumentLog.size() > 0) {
     latestActivityTextField.setText(instrumentLog.get(instrumentLog.size() - 1));
    } else {
     latestActivityTextField.setText("");

    }

    // lock textfields
    availabilityComboBox.setEnabled(false);
    conditionComboBox.setEnabled(false);
    instrumentDescriptionTextField.setEditable(false);
    barcodeTextField.setEditable(false);
    assignedOutTextField.setEditable(false);
    instrumentIDTextField.setEditable(false);

   }
  }
 }
}// end of adminGUI