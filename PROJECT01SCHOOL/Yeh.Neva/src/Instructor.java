/**
 * Title: Instructor.java
 * Abstract: An object that represents an instructor in the structure of a school system.
 *           An instructor has the ability to "teach" more classes (I also added the ability
 *           to "drop" a course).
 * Author: Neva Yeh
 * Date: February 11-12, 2019
 */

import java.util.ArrayList;

public class Instructor {

    /**
     * Class Fields
     *
     * An ArrayList holds all the courses that an instructor is teaching
     */
    private int employeeNumber;
    private String instructorName;
    private String emailAddress;
    private String phoneNumber;

    private ArrayList<Course> instructorCourses;

    /**
     * Parameterized Constructor
     * Initializes instructor's fields by splitting a given String that contains
     * all necessary information, with respective fields separated by commas
     *
     * Also initializes the ArrayList to prepare it for holding the instructor's courses
     *
     * @param details - a single String containing all of the instructor's information
     */
    Instructor(String details) {
        String[] instructorDetails = details.split(",");
        setEmployeeNumber(Integer.parseInt(instructorDetails[0]));
        setInstructorName(instructorDetails[1]);
        setEmailAddress(instructorDetails[2]);
        setPhoneNumber(instructorDetails[3]);
        instructorCourses = new ArrayList<>();
    }

    /**
     * Adds a course to the instructor's (Array)List of courses that they are teaching
     *
     * @param course - the Course object to add
     */
    public void addCourse(Course course) {
        instructorCourses.add(course);
    }

    /**
     * (NOT a required method)
     * Removes a course from the instructor's (Array)List of courses that they are teaching
     *
     * @param course - the Course object to remove
     */
    public void removeCourse(Course course) {
        instructorCourses.remove(course);
    }

    /**
     * (Overriding toString)
     * Returns a String representation of this class
     * In this case, just the instructor's name is needed
     * ( For use with School.schoolInfo() )
     *
     * @return String representation of this class
     */
    @Override
    public String toString() {
        return instructorName;
    }

    /*
     * =====================================================
     *                GETTERS AND SETTERS
     * =====================================================
     */

    /**
     * GETTER: employeeNumber
     * @return employeeNumber (int)
     */
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * SETTER: employeeNumber
     * @param employeeNumber - instructor's new unique employee number
     */
    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * GETTER: instructorName
     * @return instructorName (String)
     */
    public String getInstructorName() {
        return instructorName;
    }

    /**
     * SETTER: instructorName
     * @param instructorName - instructor's new name
     */
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    /**
     * GETTER: emailAddress
     * @return emailAdress (String)
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * SETTER: emailAddress
     * @param emailAddress - instructor's new email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * GETTER: phoneNumber
     * @return phoneNumber (String)
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * SETTER: phoneNumber
     * @param phoneNumber - instructor's new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
