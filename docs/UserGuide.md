---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# StudentManagerPro User Guide

StudentManagerPro (SMP) is a **desktop app for managing students, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SMP can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your computer.

1. Download the latest `.jar` file from [here](https://github.com/AY2425S1-CS2103-F12-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for StudentManagerPro.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar studentmanagerpro.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all student contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/1A s/M r/1` : Adds a student named `John Doe` to the student list.

   * `delete 3` : Deletes the 3rd student contact shown in the current list.

   * `clear` : Deletes all student contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

* The command words are all case-sensitive.
  e.g. if the command specifies `add`, inputting `Add` will be treated as an unknown command.
</box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a student: `add`

Adds a student to the student list.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CLASS s/SEX r/REGISTER_NUMBER [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A student can have any number of tags (including 0)
</box>

* Adds a student to the student list with attributes as specified in the command.
* The name should contain only alphanumeric characters and spaces, and it should not be blank.
* The phone number should only contain numbers, and it should be at least 3 digits long.
* The email should only contain alphanumeric characters and select special characters, and it should not be blank. (The specifics will be described only if the wrong format is provided for email)
* The address can take any values, and it should not be blank.
* The class should be a non-zero digit followed by a capital alphabet.
* The sex should only be "M" or "F".
* The register number should be a value between 1 and 40.

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/1A s/M r/1`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal c/2C s/F r/2`

### Listing all students : `list`

Shows a list of all students in the student list.

Format: `list`

### Editing a student : `edit`

Edits an existing student in the student list.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS] [s/SEX] [r/REGISTER_NUMBER] [en/ECNAME] [ep/ECNUMBER] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* The index has an upper bound for large numbers (beyond MAX_INT).

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Filtering persons by name: `filter`

Finds persons whose attributes contain any of the given keywords.

Format: `filter [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS] [s/SEX] [r/REGISTER_NUMBER] [en/ECNAME] [ep/ECNUMBER] [t/TAG]…​`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans` e.g. both `filter n/Alex Yeoh` and `filter n/Yeoh Alex` will return the student, Alex Yeoh
* Only full words will be matched e.g. `Han` will not match `Hans`, `example.com` will not match `alexyeoh@example.com`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
* Similar for emergency contact names and addresses
* As for phone numbers and emergency phone numbers, the entire number does not have to be provided to filter. 
  e.g. `99` will return `99999999`, `99278888`
* As for register numbers and class, the entire number or class name must be provided in the command to filter
* For emergency contact names and numbers, if a student does not have existing values in these fields, filtering for those with no emergency contact names or numbers using `filter en/` or `filter ep/` will not return results.
* Support for Multiple Predicates: The filter command allows users to specify multiple values for a single attribute (e.g.multiple names) or combine multiple attributes for more refined filtering. 
  e.g. `filter n/Alex Bernice` and `filter n/Alex n/Bernice` will both display details for Alex and Bernice.
  e.g. `filter s/F p/99999999` will display details of a female student with the phone number 99999999.
* Special Considerations for Address Filtering: Unlike other fields, addresses with multiple words and spaces (e.g.123 Geylang Street) require careful handling in filters. 
  * Filtering multiple addresses in one command works best for single-word addresses (e.g. `filter a/Geylang a/Lorong`).
  * For addresses with spaces or longer phrases, apply filters one address at a time, such as `filter a/20 Geylang Road` followed by `filter a/30 Lorong Street`.
* When multiple predicates are filtered e.g. `filter s/F p/99999999`, an `AND` search is run to return the student with all of the attributes mentioned
* When only one predicate is used but multiple values are provided e.g. `filter n/Alex Bernice`, an 'OR' search is run to return the students who are either Alex or Bernice.
* When multiple predicates and multiple values are to be filtered, both an `OR` and an`AND` search is run:
  * e.g. Student 1 - name: Alex & phone number: 99999999, Student 2 - name: Bernice & phone number: 92443567, Student 3 - name: Christine & phone number: 88888888
  * e.g. `filter n/Alex Bernice p/99999999 92443567` where the order of the names and phone numbers match, an AND search is run to make sure that the student has matched both a name and a phone number, and an OR search is run to see if multiple students match a name and a phone number. Hence, both Alex and Bernice are returned as depicted in the image below.
![Filter2 - Sucess.png](images%2FFilter2%20-%20Sucess.png)
  * e.g. `filter n/Alex Bernice p/92443567 99999999` where the order of the phone numbers are reversed, still, both Alex and Bernice are returned. 
  * e.g. `filter n/Alex Bernice p/99999999 92443567 88888888`, only Alex and Bernice are returned.
  * e.g. `filter n/Alex Bernice Christine p/99999999 92443567 00000000`, only Alex and Bernice are returned, as depicted in the image below.
  ![Filter1 - Failure.png](images%2FFilter1%20-%20Failure.png)
* All attribute values will be validated to check if the format is correct, otherwise an error message will be displayed to show the correct format.
  * e.g. `filter p/hello` will display an error message stating that phone numbers can only contain numbers.

Examples:
* `filter n/John` returns `john` and `John Doe`
* `filter p/99999999` returns `Alex Yeoh`
* `filter n/John Alex` returns `John Doe` and `Alex Yeoh` 
* This image shows how students can be filtered using their phone number (99999999 - Alex Yeoh)
![filter_by_phone.png](images%2Ffilter_by_phone.png)

### Deleting a person : `delete`

Deletes the specified person from the student list.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `filter Bernice` followed by `delete 1` deletes the 1st person in the results of the `filter` command.

### Adding an Emergency contact's name : `addEcName`

Adds an emergency contact's name to the specified person in the student list.

Format: `addEcName INDEX en/[ECNAME]`

<box type="tip" seamless>

**Tip:** You can delete the emergency contact's name by leaving the `ECNAME` field empty.
</box>

* Adds the emergency contact's name `ECNAME` to the person at the specified `INDEX`
* Deletes the emergency contact's name at the specified `INDEX`
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `addEcName 1 en/John Doe` to add the emergency contact's name "John Doe" to the 1st person in the list.
* `addEcName 2 en/` to delete the emergency contact's name from the 2nd person in the list.
* `addEcName 1 en/Jack Doe` to update a prior emergency contact's name "John Doe" of the 1st person in the list to "Jack Doe"

### Adding an Emergency contact's number : `addEcNumber`

Adds an emergency contact's number to the specified person in the student list.

Format: `addEcNumber INDEX [ep/ECNUMBER]`

<box type="tip" seamless>

**Tip:** You can delete the emergency contact's number by leaving the `ECNUMBER` field empty.
</box>

* Adds the emergency contact's number `ECNUMBER` to the person at the specified `INDEX`
* Deletes the emergency contact's number at the specified `INDEX`
* The index **must be a positive integer** 1, 2, 3, …​
* The number **must be an 8 digit number** or **left empty**

Examples:
* `addEcNumber 1 ep/91234567` to add the emergency contact's number 91234567 to the 1st person in the list.
* `addEcNumber 2 ep/` to delete the emergency contact's number from the 2nd person in the list.
* `addEcNumber 1 ep/87654321` to update the emergency contact's number 87654321 to the 1st person in the list.

### Adding Attendance : `addAttendance`

Adds the date and reason as to why the specified person in the student list is absent.

Format: `addAttendance INDEX ad/[DATE] ar/[REASON]`

<box type="tip" seamless>

**Tip:** You can delete the attendance by leaving the `REASON` field empty.
</box>

* Adds the date where student is absent `DATE` and the reason `REASON` to the person at the specified `INDEX`
* Deletes the attendance at the specified `INDEX`
* The index **must be a positive integer** 1, 2, 3, …​
* The date **must be in the form of DD-MM-YYYY** and within the current year.

Examples:
* `addAttendance 1 ad/24-09-2024 ar/Sick` to add the date where the 1st person in the list is absent and the reason.
* `addAttendance 1 ad/24-09-2024 ar/` to delete the attendance from the 1st person in the list.

### Adding an Exam : `addExam`

Adds an exam to every person in the student list.

Format: `addExam ex/EXAM_NAME`

<box type="tip" seamless>

**Tip:** If a new student is added after an exam is added, the exam has to be added again for it to be reflected for the new student.
</box>

* The exam name can only contain alphanumeric characters and spaces.
* The exam name is case-sensitive. e.g. "Midterm" will be treated differently from "midterm".

Examples:
* `addExam ex/Midterm`

### Adding an Exam Score: `addExamScore`

Adds an exam score for the specified exam for the person at the specified index.

Format: `addExamScore INDEX ex/EXAM_NAME sc/EXAM_SCORE`

* The exam score must be a percentage accurate to one decimal point, or `NIL`.
* The exam score can be edited using the same command with a different exam score.
* The exam score can be deleted by entering the exam score as `NIL`.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `addExamScore 1 ex/Midterm sc/70.0`
* `addExamScore 1 ex/Midterm sc/NIL`

### Deleting an Exam : `deleteExam`

Deletes the specified exam from every student in the student list.

Format: `deleteExam ex/EXAM_NAME`

* The exam name can only contain alphanumeric characters and spaces.
* The exam name is case-sensitive. e.g. "Physics" will be treated differently from "physics".

Examples:
* `deleteExam ex/Midterm`

### Adding a Submission : `addSubmission`

Adds a submission to every student in the student list.

Format: `addSubmission sm/SUBMISSION_NAME`

<box type="tip" seamless>

**Tip:** If a new student is added after a submission is added, the same submission has to be added again for it to be reflected for the new student.
</box>

* The submission name can only contain alphanumeric characters and spaces.
* The submission name is case-sensitive. e.g. "Assignment 1" will be treated differently from "assignment 1".

Examples:
* `addSubmission sm/Assignment 1`

### Adding a Submission Status: `addSubmissionStatus`

Adds a submission status for the specified submission for the student at the specified index.

Format: `addSubmissionStatus INDEX sm/SUBMISSION_NAME ss/SUBMISSION_STATUS`

<box type="tip" seamless>

**Tip:** The submission status can be deleted by entering the submission status as `NIL`.
</box>

* The submission status must be "Y", "N" or `NIL`.
* The submission status can be edited using the same command with a different submission status.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `addSubmissionStatus 1 sm/Assignment 1 ss/Y`
* `addSubmissionStatus 1 sm/Tutorial 2 ss/NIL`

### Deleting a Submission : `deleteSubmission`

Deletes the specified submission from every student in the student list.

Format: `deleteSubmission sm/SUBMISSION_NAME`

* The submission name can only contain alphanumeric characters and spaces.
* The submission name is case-sensitive. e.g. "Assignment 1" will be treated differently from "assignment 1".

Examples:
* `deleteSubmission sm/Assignment 1`

### Sorting the list : `sort`

Sorts the list of students based on the students attributes.

Format: `sort [ATTRIBUTE]`

<box type="tip" seamless>

**Tip:** Students attributes include: name, phone, email, address, sex, register number, student class, emergency contact name, emergency contact number.
</box>

* Sorts the list based on the ATTRIBUTE lexicographically in increasing order
* Sorts the list based on one attribute at a time
* Empty attributes will be shifted to the end of the list (only for emergency contact name and emergency contact number)
* Unsort the list when the attribute is `none`

Examples:
* `sort name` to sort the list based on student's names
* `sort register number` to sort the list based on student's register numbers
* `sort none` to unsort the list

### Clearing all entries : `clear`

Clears all entries from the student list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

StudentManagerPro data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

StudentManagerPro data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, StudentManagerPro will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the StudentManagerPro to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous StudentManagerPro home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                           | Format, Examples                                                                                                                                                                                                   |
|----------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                          | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CLASS s/SEX r/REGISTER_NUMBER [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 c/1A s/M r/1 t/friend t/colleague` |
| **Clear**                        | `clear`                                                                                                                                                                                                            |
| **Delete**                       | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                |
| **Edit**                         | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS] [s/SEX] [r/REGISTER_NUMBER] [en/ECNAME] [ep/ECNUMBER] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                        |
| **Filter**                       | `filter [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS] [s/SEX] [r/REGISTER_NUMBER] [en/ECNAME] [ep/ECNUMBER] [t/TAG]…​`<br> e.g., `filter n/James p/90332234`                                           |
| **List**                         | `list`                                                                                                                                                                                                             |
| **Help**                         | `help`                                                                                                                                                                                                             |
| **Add Emergency Contact Name**   | `addEcName INDEX [en/EMERGENCY_CONTACT_NAME]` <br> e.g., `addEcName 1 en/John Doe`                                                                                                                                 |
| **Add Emergency Contact Number** | `addEcNumber INDEX [ep/EMERGENCY_CONTACT_NUMBER]`<br> e.g., `addEcNumber 2 ep/91231234`                                                                                                                            |
| **Add Exam**                     | `addExam ex/EXAM_NAME` <br> e.g., `addExam ex/Midterm`                                                                                                                                                             |
| **Add Exam Score**               | `addExamScore INDEX ex/EXAM_NAME sc/EXAM_SCORE` <br> e.g., `addExamScore 1 ex/Midterm sc/70.0`                                                                                                                     |
| **Delete Exam**                  | `deleteExam ex/EXAM_NAME` <br> e.g., `deleteExam ex/Midterm`                                                                                                                                                       |
| **Add Attendance**               | `addAttendance INDEX ad/[DATE] ar/[REASON]`<br> e.g., `addAttendance 1 ad/24-09-2024 ar/Sick`                                                                                                                      |
| **Add Submission**               | `addSubmission sm/SUBMISSION_NAME` <br> e.g., `addSubmission sm/Assignment 1`                                                                                                                                      |
| **Add Submission Status**        | `addSubmissionStatus INDEX sm/SUBMISSION_NAME ss/SUBMISSION_STATUS` <br> e.g., `addSubmissionStatus 1 sm/Assignment 1 ss/Y`                                                                                        |
| **Delete Submission**            | `deleteSubmission sm/SUBMISSION_NAME` <br> e.g., `deleteSubmission sm/Assignment 1`                                                                                                                                |
| **Sort**                         | `sort [ATTRIBUTE]` <br> e.g., `sort student class`                                                                                                                                                                 |
