/*
 * AddStudentGui
 * GUI program for adding new students
 * @Author: Andrew
 * Date: November 28, 2017
 */

//imports
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//swing
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

//io
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//util
import java.util.ArrayList;

public class AddStudentGui {
 // frames
 private JFrame frame;

 // textfields
 private JTextField addStudentNameField;
 private JTextField addStudentIDField;
 private JTextField addStudentInstrumentField;
 private JTextField addStudentGradeField;

 // labels
 private JLabel addStudentNameLabel;
 private JLabel addStudentIDLabel;
 private JLabel addStudentInstrumentLabel;
 private JLabel addStudentGradeLabel;
 private JLabel studentErrorLabel;

 // buttons
 private JButton addStudentAddButton;
 private JButton addStudentCancelButton;

 // array list
 private ArrayList<Student> studentArrList;
 private static ArrayList<Instrument> instrumentArrList;

 /**
  * Create the application.
  */
 public AddStudentGui() {
  //create new ArrayList of Student objects
  studentArrList = new ArrayList<Student>();
  //initialize GUI
  initialize();
  frame.setVisible(true);
 }

 /**
  * Initialize the contents of the frame.
  */
 private void initialize() {
  //initialize frame
  frame = new JFrame();
  frame.setBounds(100, 100, 600, 350);
  frame.setTitle("Add Student");
  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

  // Labels
  addStudentNameLabel = new JLabel("Name:");
  addStudentNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
  addStudentIDLabel = new JLabel("ID Number:");
  addStudentIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
  addStudentInstrumentLabel = new JLabel("Instrument:");
  addStudentInstrumentLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
  addStudentGradeLabel = new JLabel("Grade:");
  addStudentGradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
  studentErrorLabel = new JLabel("");
  studentErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

  // Text Fields
  addStudentNameField = new JTextField();
  addStudentNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
  addStudentNameField.setColumns(10);
  addStudentIDField = new JTextField();
  addStudentIDField.setFont(new Font("Tahoma", Font.PLAIN, 14));
  addStudentIDField.setColumns(10);
  addStudentInstrumentField = new JTextField();
  addStudentInstrumentField.setFont(new Font("Tahoma", Font.PLAIN, 14));
  addStudentInstrumentField.setColumns(10);
  addStudentGradeField = new JTextField();
  addStudentGradeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
  addStudentGradeField.setColumns(10);

  // add button
  addStudentAddButton = new JButton("Add Student");
  addStudentAddButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
  addStudentAddButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    // get new Student info
    String name = addStudentNameField.getText();
    String id = addStudentIDField.getText();
    String instrument = addStudentInstrumentField.getText();
    String grade = addStudentGradeField.getText();
    
    //error check, user cannot enter commas
    if (name.indexOf(",") > -1 || id.indexOf(",") > -1 || instrument.indexOf(",") > -1
      || grade.indexOf(",") > -1) {
     studentErrorLabel.setText("Error! Remove ',' from field(s)!");

    } else {
     //read students.csv file
     readFile();
     //read instrument.csv file
     readInstrumentFile();
     
     if (instrument.length() > 0) {
      for (int i = 0; i < instrumentArrList.size(); i++) {
       if (instrument.equals(instrumentArrList.get(i).getID())) {
        instrumentArrList.get(i).assignStudent(name);
       }
      }
     }
     // make new Student object
     Student temp = new Student(name, id, new Instrument(instrument), grade);
     // add to student array list
     studentArrList.add(temp);
     // update newStudent object
     writeFile();
     writeInstrumentFile();
     frame.setVisible(false);
     frame.dispose();
    }
   }
  });

  // cancel button
  addStudentCancelButton = new JButton("Cancel");
  addStudentCancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
  addStudentCancelButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    frame.setVisible(false);
    frame.dispose();
   }
  });

  // group layout setup
  GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
  groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
    .createSequentialGroup().addGap(39)
    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(addStudentAddButton)
      .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        .addGroup(groupLayout.createSequentialGroup()
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(addStudentIDLabel).addComponent(addStudentNameLabel,
              GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
          .addGap(18)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
            .addComponent(addStudentNameField, GroupLayout.PREFERRED_SIZE, 322,
              GroupLayout.PREFERRED_SIZE)
            .addComponent(addStudentIDField)))
        .addGroup(groupLayout.createSequentialGroup()
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(addStudentInstrumentLabel)
            .addComponent(addStudentGradeLabel))
          .addGap(18)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(addStudentInstrumentField)
            .addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
              .createParallelGroup(Alignment.TRAILING)
              .addComponent(addStudentCancelButton)
              .addComponent(addStudentGradeField, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
              .addGap(48).addComponent(studentErrorLabel,
                GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))))))
    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
  groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    .addGroup(groupLayout.createSequentialGroup().addGap(32)
      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(addStudentNameLabel)
        .addComponent(addStudentNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
          GroupLayout.PREFERRED_SIZE))
      .addGap(18)
      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(addStudentIDLabel)
        .addComponent(addStudentIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
          GroupLayout.PREFERRED_SIZE))
      .addGap(18)
      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        .addComponent(addStudentInstrumentLabel).addComponent(addStudentInstrumentField,
          GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
          GroupLayout.PREFERRED_SIZE))
      .addGap(18)
      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(addStudentGradeLabel)
        .addComponent(addStudentGradeField, GroupLayout.PREFERRED_SIZE,
          GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
      .addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
      .addGroup(
        groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(addStudentAddButton)
          .addComponent(addStudentCancelButton, GroupLayout.PREFERRED_SIZE, 25,
            GroupLayout.PREFERRED_SIZE)
          .addComponent(studentErrorLabel))
      .addGap(52)));
  frame.getContentPane().setLayout(groupLayout);
 }

 /*
  * writeFile 
  * method thats stores all student information into a .csv file
  * @params nothing
  * @return nothing
  */
 public void writeFile() {
  //declare BufferedWriter
  BufferedWriter bw = null;

  try {
   //initialize BufferedWriter
   bw = new BufferedWriter(new FileWriter("students.csv"));
   //temporary Student variable
   Student temp = new Student();
   
   //file header
   bw.write("Name,Barcode,Instrument,Grade,Sign Ins,Sign Outs");
   bw.newLine();
   
   //loop through all Student objects
   for (int i = 0; i < studentArrList.size(); i++) {
    //temporary variables
    String timesSignedIn;
    String timesSignedOut;
    
    //get signout/signin dates from ArrayList within Student object
    if (temp.getTimesSignedIn() == null) {
     timesSignedIn = " ";//set to space because the readFile method checks for spaces, avoid index out of bound     
    } else {
     timesSignedIn = temp.getTimesSignedIn();
    }

    if (temp.getTimesSignedOut() == null) {
     timesSignedOut = " ";
    } else {
     timesSignedOut = temp.getTimesSignedOut();
    }
    
    //write Student information
    temp = studentArrList.get(i);
    bw.write(temp.getName() + "," + temp.getID() + "," + temp.getInstrument().getID() + ","
      + temp.getGrade() + "," + timesSignedIn + "," + timesSignedOut);
    bw.newLine();
   }

   bw.close();
  } catch (IOException e) {
  }
 }

 /*
  * readFile 
  * method that reads .csv file and adds the info to studentArrList and studentListModel 
  * @params nothing
  * @returns nothing
  */
 public void readFile() {
  //declare BufferedReader
  BufferedReader br = null;

  try {
   //initialize BufferedReader
   br = new BufferedReader(new FileReader("students.csv"));
   String line = br.readLine();// dont take the tags
   //temporary variables
   String name, id, instrument, grade;
   
   //go through entire text file
   while ((line = br.readLine()) != null) {
    // get student info from file
    name = line.substring(0, line.indexOf(","));
    line = line.substring(line.indexOf(",") + 1);
    id = line.substring(0, line.indexOf(","));
    line = line.substring(line.indexOf(",") + 1);
    instrument = line.substring(0, line.indexOf(","));
    line = line.substring(line.indexOf(",") + 1);
    grade = line.substring(0, line.indexOf(","));
    // add student to array list
    studentArrList.add(new Student(name, id, new Instrument(instrument), grade));
   }
   br.close();
  } catch (IOException e) {
  }
 }

 /*
  * readInstrumentFile 
  * This method reads the contents of the .csv file and makes it into an arraylist
  * @param Nothing it uses the class ArrayList
  * @return Nothing it edits the class ArrayList
  */
 public static void readInstrumentFile() {
  instrumentArrList = new ArrayList<Instrument>();
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
   }
   fileReader.close();
  } catch (IOException e) {
   e.printStackTrace();
  }
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

    bw.newLine(); // New line seperating each instrument
   }
   bw.close(); // Close file to save

  } catch (IOException e) {
   e.printStackTrace();
  }
 }

}