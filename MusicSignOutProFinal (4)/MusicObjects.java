/*
 * MusicObjects
 * The program has the class methods for Student and Instrument objects
 * @Authors: Andrew,Naymul,Connor
 * Date: November 28, 2017
 */

//imports
import java.util.ArrayList;

class MusicObjects {
 public static void main(String[] args) {

 }// end of main
}//end of DataStructures

// Student object class
class Student {
 // declarations
 private String name;
 private String dateBorrowed;
 private String dateReturned;
 private Instrument instrument;
 private String ID;// student barcode ID
 private String timesSignedOut;
 private String timesSignedIn;
 // stores the signout/signin history
 private ArrayList<String> signOutArrList;
 private ArrayList<String> signInArrList;
 private String grade;

 /*
  * blank Student constructor used for creating temporary values of Student
  * objects in other files
  */
 public Student() {

 }

	/*
	 * Student
	 * This method creates the student object
	 * @param Multiple strings containing the name,barcode,grade of the student
	 * and an Instrument object they own
	 * @return Nothing
	 */
 public Student(String name, String barcode, Instrument instrument, String grade) {
  this.name = name;
  this.ID = barcode;
  this.instrument = instrument;
  this.grade = grade;
  this.signOutArrList = new ArrayList<String>();
  this.signInArrList = new ArrayList<String>();
 }

 // setters
	/*
	 * setSignOuyArrList
	 * This method sets the SignOutArrList of the student
	 * @param A arraylist that is the list of signou times of the student
	 * @return Nothing
	 */
 public void setSignOutArrList(ArrayList<String> signOutArrList) {
  this.signOutArrList = signOutArrList;
 }

	/*
	 * setSignInArrList
	 * This method sets the SignInArrList of the student
	 * @param A arraylist that is the list of signin times of the student
	 * @return Nothing
	 */
 public void setSignInArrList(ArrayList<String> signInArrList) {
  this.signInArrList = signInArrList;
 }

	/*
	 * setGrade
	 * This method sets the grade of the student
	 * @param A string that is the grade of the student
	 * @return Nothing
	 */
 public void setGrade(String grade) {
  this.grade = grade;
 }

	/*
	 * setInstrument
	 * This method sets the instrument of the student
	 * @param A string that is the instrument of the student
	 * @return Nothing
	 */
 public void setInstrument(Instrument instrument) {
  this.instrument = instrument;
 }

	/*
	 * setID
	 * This method sets the ID of the student
	 * @param A string that is the ID of the student
	 * @return Nothing
	 */
 public void setID(String ID) {
  this.ID = ID;
 }

	/*
	 * setTimesSignedOut
	 * This method sets the amount of times signed out of the student
	 * @param A int that is the amount of times signed of the student
	 * @return Nothing
	 */
 public void setTimesSignedOut(String timesSignedOut) {
  this.timesSignedOut = timesSignedOut;
 }

 public void setTimesSignedIn(String timesSignedIn) {
  this.timesSignedIn = timesSignedIn;
 }

	/*
	 * setName
	 * This method sets the name of the student
	 * @param A string that is the name of the student
	 * @return Nothing
	 */
 public void setName(String name) {
  this.name = name;
 }

	/*
	 * setDateBorrowed
	 * This method sets the date borrowed of the student
	 * @param A string that is the date borrowed of the student
	 * @return Nothing
	 */
 public void setDateBorrowed(String dateBorrowed) {
  this.dateBorrowed = dateBorrowed;
 }

 // getters
 
	/*
	 * getSignOutArrList
	 * This method gets the arraylist of signout times
	 * @param Nothing
	 * @return An arraylist of signout times
	 */
 public ArrayList<String> getSignOutArrList() {
  return this.signOutArrList;
 }
	/*
	 * getSignInArrList
	 * This method gets the arraylist of signin times
	 * @param Nothing
	 * @return An arraylist of signin times
	 */
 public ArrayList<String> getSignInArrList() {
  return this.signInArrList;
 }

	/*
	 * getGrade
	 * This method gets the grade of the student
	 * @param Nothing
	 * @return A string that is the grade of the student
	 */
 public String getGrade() {
  return this.grade;
 }

	/*
	 * getDateBorrowed
	 * This method gets the date borrowed of the student
	 * @param Nothing
	 * @return A string that is the date borrowed of the student
	 */
 public String getdateBorrowed() {
  return this.dateBorrowed;
 }

	/*
	 * getInstrument
	 * This method gets the grade of the student
	 * @param Nothing
	 * @return An instrument that is borrowed by the student
	 */
 public Instrument getInstrument() {
  return this.instrument;
 }

	/*
	 * getName
	 * This method gets the name of the student
	 * @param Nothing
	 * @return A string that is the name of the student
	 */
 public String getName() {
  return this.name;
 }

	/*
	 * getID
	 * This method gets the ID of the student
	 * @param Nothing
	 * @return A string that is the ID of the student
	 */
 public String getID() {
  return this.ID;
 }

	/*
	 * getTimesSignedOut
	 * This method gets the amount signed out of the student
	 * @param Nothing
	 * @return An int that is the amount of times signed out of the student
	 */
 public String getTimesSignedOut() {
  return this.timesSignedOut;
 }

	/*
	 * getTimesSignedIn
	 * This method gets the amount signed in of the student
	 * @param Nothing
	 * @return An int that is the amount of times signed in of the student
	 */
 public String getTimesSignedIn() {
  return this.timesSignedIn;
 }
	/*
	 * getdateReturned
	 * This method gets the date returned of the instrument belonging to the student
	 * @param Nothing
	 * @return An string that is the date returned
	 */
 public String getdateReturned() {
  return this.dateReturned;
 }

	/*
	 * setdateReturned
	 * This method sets the date returned of the instrument belonging to the student
	 * @param An string that is the date returned
	 * @return Nothing
	 */
 public void setDateReturned(String dateReturned) {
  this.dateReturned = dateReturned;
 }
}// end of Student class

// Instrument object class
class Instrument {
 // declarations
 private String id;// name of instrument
 private String barcode;// instrument ID
 private String available;
 private String description;
 private ArrayList<String> assignedStudents;
 private String signedOutTo;
 private boolean active;
 private String condition;

	/*
	 * Instrument
	 * This method creates the instrument object
	 * @param A string that is the Id of the instrument
	 * @return Nothing
	 */
 public Instrument(String id) {
  this.id = id;
  this.available = "available";
  this.assignedStudents = new ArrayList<String>();
 }

	/*
	 * Instrument
	 * This method creates the instrument object
	 * @param Multiple strings for the ID, availability, condition, and description
	 * and an arraylist of strings for the students
	 * @return Nothing
	 */
	public Instrument(String id, String barcode, String available, String condition, String description,
			ArrayList<String> assignedStudents) {
		this.id = id;
		this.barcode = barcode;
		this.available = available;
		this.condition = condition;
		this.description = description;
		this.assignedStudents = assignedStudents;
	}

	/*
	 * Instrument
	 * This method creates the instrument object
	 * @param Multiple strings for the ID, availability, condition, and description
	 * @return Nothing
	 */
	public Instrument(String id, String barcode, String available, String condition, String description) {
		this.id = id;
		this.barcode = barcode;
		this.available = available;
		this.condition = condition;
		this.description = description;
		this.assignedStudents = new ArrayList<String>();
	}

	/*
	 * setCondition
	 * This method sets the condition of the instrument
	 * @param A string that is the condition of the instrument
	 * @return Nothing
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/*
	 * setBarcode
	 * This method sets the barcode of the instrument
	 * @param A string that is the barcode of the instrument
	 * @return Nothing
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;

	}
	/*
	 * setSignedOutTo
	 * This method sets the name of the student that took the instrument
	 * @param A string that is the name of the student
	 * @return Nothing
	 */
	public void setSignedOutTo(String name) {
		this.signedOutTo = name;
	}
	/*
	 * setActive
	 * This method sets if the instrument is active or not
	 * @param A boolean that is true if available and false otherwise
	 * @return Nothing
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/*
	 * setDescription
	 * This method sets the description of the instrument
	 * @param A string that is the description of the instrument
	 * @return Nothing
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/*
	 * setAvailability
	 * This method sets the availability of the instrument
	 * @param A string that is the availability of the instrument
	 * @return Nothing
	 */
	public void setAvailability(String available) {
		this.available = available;
	}

	/*
	 * setID
	 * This method sets the ID of the instrument
	 * @param A string that is the ID of the instrument
	 * @return Nothing
	 */
	public void setID(String id) {
		this.id = id;
	}

	// getters
	
	/*
	 * getCondition
	 * This method gets the condition of the instrument
	 * @param Nothing
	 * @return A string that is the condition of the instrument
	 */
	public String getCondition() {
		return this.condition;
	}

	
	/*
	 * getBarcode
	 * This method gets the barcode of the instrument
	 * @param Nothing
	 * @return A string that is the barcode of the instrument
	 */
	
	public String getBarcode() {
		return this.barcode;

	}

	/*
	 * getSignedOutTo
	 * This method gets the student who took the instrument
	 * @param Nothing
	 * @return A string that is the name of the student who took the instrument
	 */
	public String getSignedOutTo() {
		return this.signedOutTo;
	}

	
	/*
	 * getActive
	 * This method checks if the instrument is active
	 * @param nothing
	 * @return A boolean value if its active
	 */
	public boolean getActive() {
		return this.active;
	}

	/*
	 * getDescription
	 * This method gets the description of the instrument
	 * @param Nothing
	 * @return A string that is the description of the instrument
	 */
	public String getDescription() {
		return this.description;
	}

	/*
	 * getAvailability
	 * This method gets the availability of the instrument
	 * @param Nothing
	 * @return A string that is the availability of the instrument
	 */
	public String getAvailability() {
		return this.available;
	}

	/*
	 * getID
	 * This method gets the ID of the instrument
	 * @param Nothing
	 * @return A string that is the ID of the instrument
	 */
	public String getID() {
		return this.id;
	}

	/*
	 * assignStudent
	 * This method sets the student of the instrument
	 * @param A string of the name of the student
	 * @return Nothing
	 */
	public void assignStudent(String name) {
		assignedStudents.add(name);
	}


	/*
	 * getAssignedStudents
	 * This method gets the arraylist of students
	 * @param nothing
	 * @return ArrayList<String> of students
	 */
	public ArrayList<String> getAssignedStudents() {
		return this.assignedStudents;
	}
	/*
	 * removeStudent
	 * This method removes a student from the instrument
	 * @param A string the name of the student
	 * @return Nothing
	 */
	public void removeStudent(String name) {
		assignedStudents.remove(name);
	}
}