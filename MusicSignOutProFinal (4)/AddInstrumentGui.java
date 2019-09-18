/*
 * AddInstrumentGui.java
 * @Author Naymul
 * @Date Nov. 2017
 * A program to add instruments to the file
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class AddInstrumentGui {
 //frames
 private JFrame frame;
 
 private static ArrayList<Instrument> instrumentList;

 
 // JLabels
 private JLabel instrumentIDLabel;
 private JLabel availabilityLabel;
 private JLabel conditionLabel;
 private JLabel descriptionLabel;
 private JLabel barcodeLabel;


 private JTextField instrumentIDTextField;
 private JComboBox<Object> availabilityComboBox;
 private JComboBox<Object> conditionComboBox;
 private JTextField descriptionTextField;
 private JButton btnAdd;
 private JButton btnCancel;
 private JLabel addInstrumentErrorLabel;
 private JTextField barcodeTextField;
 
  
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     readInstrumentFile();
     AddInstrumentGui window = new AddInstrumentGui();
     window.frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }

 /*
  * readInstrumentFile
  * This method reads the contents of the csv file and makes it into an arraylist
  * @param Nothing it uses the class ArrayList
  * @return Nothing it edits the class ArrayList
  */
 public static void readInstrumentFile() {

  instrumentList = new ArrayList<Instrument>();
  int count = 0;
  String id = "";           //Declare string variables
  String barcode="";
  String availability = "";
  String condition = "";
  String description = "";
  ArrayList<String> names = new ArrayList<String>();
  try {
   File file = new File("instruments.csv");      //Attempt to find instrument file
   FileReader fileReader = new FileReader(file);
   BufferedReader bufferedReader = new BufferedReader(fileReader);
   String line;
   line = bufferedReader.readLine();
   while ((line = bufferedReader.readLine()) != null) {

    //Every time a new instrument is added reset variables
    names = new ArrayList<String>();
    id = "";
    barcode="";
    availability = "";
    condition = "";
    description = "";
    count = 0;
    
    
    while (line.length() > 0 && line.indexOf(",") > -1) {

     //Every comma is for different properties
     if (count == 0) {
      id = line.substring(0, line.indexOf(","));   //Add the specified property and substring it out
      line = line.substring(line.indexOf(",") + 1);
     }
     if (count == 1) {
      barcode = line.substring(0, line.indexOf(","));   //Add the specified property and substring it out
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

     //fourth comma is repeated because there can be more than 1 student
     if (count == 4) {
      description = line.substring(0, line.indexOf(","));
         line = line.substring(line.indexOf(",") + 1);
     }
     
     if(count == 5) {
      names.add(line.substring(0, line.indexOf(",")));
         line = line.substring(line.indexOf(",") + 1);
      
     }

     if (count == 5) {
     } else {
      count++;
     }
    }
    
    //Add the instrument after all the properties have been found through the file reader
    instrumentList.add((new Instrument(id, barcode, availability, condition, description, names)));
   }
   fileReader.close();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
 /*
  * writeInstrumentFile This method writes the contents of the instrument arraylist
  * into a csv file
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

   bw.write("Instrument ID,Barcode,Availability,Condition,Description,Current Students"); // First line of info for excel
   bw.newLine();

   // Go through each instrument and write all its properties seperated
   // by commas
   for (int k = 0; k < instrumentList.size(); k++) {

    String description;
    String assignedStudents = "";

    Instrument temp = instrumentList.get(k);

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

    bw.write(instrumentList.get(k).getID() + "," +instrumentList.get(k).getBarcode() + "," + instrumentList.get(k).getAvailability() + ","
      + instrumentList.get(k).getCondition() + "," + description + "," + assignedStudents);

    bw.newLine(); // New line seperating each instrument
   }
   bw.close(); // Close file to save

  } catch (IOException e) {
   e.printStackTrace();
  }
 }

 /**
  * addInstrument
  * This method opens the window
  *@param Nothing
  *@return Nothing
  */
 public AddInstrumentGui() {
  initialize();
  frame.setVisible(true);
 }

 /**
  * initialize
  * This method starts up the frame
  * @param Nothing
  * @return Nothing
  */

private void initialize() {
  frame = new JFrame();
  frame.setBounds(100, 100, 562, 462);
  frame.setTitle("Add Instrument");
  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  
  instrumentIDLabel = new JLabel("Instrument ID: "); 
  availabilityLabel = new JLabel("Availability: "); 
  conditionLabel = new JLabel("Condition: "); 
  descriptionLabel = new JLabel("Description: ");
  
  instrumentIDTextField = new JTextField();
  instrumentIDTextField.setHorizontalAlignment(SwingConstants.LEFT);
  instrumentIDTextField.setColumns(10); 
  
  descriptionTextField = new JTextField();
  descriptionTextField.setHorizontalAlignment(SwingConstants.LEFT);
  descriptionTextField.setColumns(10);
  
  String[] availabilityChoices = { "Available", "Unavailable", "In Repair"};
  String[] conditionChoices = { "Good", "Mediocre", "Bad", "Broken" };
  availabilityComboBox = new JComboBox<Object>(availabilityChoices);
  conditionComboBox = new JComboBox<Object>(conditionChoices);

  
  btnAdd = new JButton("Add");
  btnAdd.addActionListener(new ActionListener() {
   
   /*
    * actionPerformed
    * This method adds the new instrument to the file
    * @param ActionEvent to detect the press of the button
    * @return Nothing
    */
   public void actionPerformed(ActionEvent e) {
    String id=instrumentIDTextField.getText();        //Set strings to info in fields
    String barcode=barcodeTextField.getText();
    String availability =availabilityComboBox.getSelectedItem().toString();
    String condition =conditionComboBox.getSelectedItem().toString();
    String description= descriptionTextField.getText();
    
    if( (description.indexOf(",")>-1)|| (id.indexOf(",")>-1) || (barcode.indexOf(",")>-1)) { //Check for commas 
    addInstrumentErrorLabel.setVisible(true);  //If commas are there show an error label
    }else {
    
    readInstrumentFile();  //Read file of instruments to update arraylist of instruments
    Instrument addingInstrument= new Instrument(id,barcode,availability,condition,description);   
    instrumentList.add(addingInstrument);  //Add instrument to arraylist
    writeInstrumentFile();  //Write new arraylist to file
    frame.dispose();   
    }
   }
  });
  
  btnCancel = new JButton("Cancel");
  btnCancel.addActionListener(new ActionListener() {
   
   /*
    * actionPerformed
    * This method closes the window 
    * @param ActionEvent to detect the press of the button
    * @return Nothing
    */
   public void actionPerformed(ActionEvent e) {
    frame.dispose();   
   }
  });
  
  addInstrumentErrorLabel = new JLabel("Error: Remove commas \r\nfrom fields");
  addInstrumentErrorLabel.setForeground(Color.RED);
  addInstrumentErrorLabel.setVisible(false);
  
  barcodeLabel = new JLabel("Barcode:");
  
  barcodeTextField = new JTextField();
  barcodeTextField.setColumns(10);
  
  //GroupLayout placement of everything
  GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
  groupLayout.setHorizontalGroup(
   groupLayout.createParallelGroup(Alignment.TRAILING)
    .addGroup(groupLayout.createSequentialGroup()
     .addContainerGap()
     .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
      .addGroup(groupLayout.createSequentialGroup()
       .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
         .addComponent(descriptionLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
         .addPreferredGap(ComponentPlacement.RELATED)
         .addComponent(descriptionTextField, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE))
        .addGroup(groupLayout.createSequentialGroup()
         .addComponent(conditionLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
         .addPreferredGap(ComponentPlacement.UNRELATED)
         .addComponent(conditionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        .addGroup(groupLayout.createSequentialGroup()
         .addComponent(instrumentIDLabel)
         .addGap(18)
         .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
          .addComponent(barcodeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
          .addComponent(instrumentIDTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
       .addContainerGap(70, Short.MAX_VALUE))
      .addGroup(groupLayout.createSequentialGroup()
       .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
       .addGap(100)
       .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
       .addGap(92))
      .addGroup(groupLayout.createSequentialGroup()
       .addComponent(availabilityLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
       .addPreferredGap(ComponentPlacement.UNRELATED)
       .addComponent(availabilityComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
       .addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
       .addComponent(addInstrumentErrorLabel)
       .addGap(23))
      .addGroup(groupLayout.createSequentialGroup()
       .addComponent(barcodeLabel)
       .addContainerGap(456, Short.MAX_VALUE))))
  );
  groupLayout.setVerticalGroup(
   groupLayout.createParallelGroup(Alignment.LEADING)
    .addGroup(groupLayout.createSequentialGroup()
     .addContainerGap()
     .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
      .addComponent(instrumentIDLabel)
      .addComponent(instrumentIDTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
     .addPreferredGap(ComponentPlacement.UNRELATED)
     .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
      .addComponent(barcodeLabel)
      .addComponent(barcodeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
     .addGap(19)
     .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
      .addComponent(addInstrumentErrorLabel)
      .addComponent(availabilityLabel)
      .addComponent(availabilityComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
     .addGap(18)
     .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
      .addComponent(conditionLabel)
      .addComponent(conditionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
     .addGap(32)
     .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
      .addComponent(descriptionLabel)
      .addComponent(descriptionTextField, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
     .addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
     .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
      .addComponent(btnCancel)
      .addComponent(btnAdd))
     .addContainerGap())
  );
  frame.getContentPane().setLayout(groupLayout);
  
 }
}