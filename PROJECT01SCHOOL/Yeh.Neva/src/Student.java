/**
 * Title: Student.java
 * Abstract: An object that represents a student in the structure of a school system.
 *           A Student can both add and drop a course.
 *           A Student can also update the scores in a given course and calculate the
 *           average of all the scores of the courses that the student is enrolled in.
 * Author: Neva Yeh
 * Date: February 11-12, 2019
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Student {

    /**
     * Class Fields
     *
     * An ArrayList holds all of the student's courses
     * A HashMap holds all of the courses that the student is enrolled in as well as
     * their respective scores
     */
    private int studentId;
    private String studentName;

    private ArrayList<Course> courses;
    private HashMap<Course, Double> studentScores;

    /**
     * Parameterized Constructor
     * Initializes students's fields by splitting a given String that contains
     * all necessary information, with respective fields separated by commas
     *
     * Also initializes the ArrayList to prepare it for holding the students's courses
     * as well as the HashMap to prepare it for holding all the students' scores in their
     * respective course(s)
     *
     * @param details - a single String containing all of the student's information
     */
    Student(String details) {
        String[] studentDetails = details.split(",");
        setStudentId(Integer.parseInt(studentDetails[0]));
        setStudentName(studentDetails[1]);
        courses = new ArrayList<>();
        studentScores = new HashMap<Course, Double>();
    }

    /**
     * Adds a course to the student's (Array)List of courses
     * Also "initializes" the course's spot in the HashMap
     *
     * @param course - Course object to be added
     */
    public void addCourse(Course course) {
        courses.add(course);
        studentScores.put(course, 0.0);
    }

    /**
     * Removes the student from a specified course
     * Also removes the course's key from the HashMap
     *
     * @param course - ID of course that student is to be removed from
     */
    public void dropCourse(Course course) {
        courses.remove(course);
        studentScores.remove(course);
    }

    /**
     * Updates the score that a student got in a specific course
     *
     * @param course - Course that the student's grade is to be updated in
     * @param score - student's new score to be recorded
     */
    public void updateScore(Course course, double score) {
        studentScores.put(course, score);
    }

    /**
     * Calculates the average of all of the student's recorded scores
     * (If their score has not been recorded (0.0), it will not be counted)
     *
     * @return - average of the student's grades in all applicable courses
     */
    public double calculateStudentAverage() {
        int coursesEnrolled = getNumberEnrolled();

        if(coursesEnrolled > 0) {
            int courseCount = 0;
            double totalScore = 0;

            for(Course course : courses) {
                if(!(studentScores.get(course) == 0.0)) {
                    totalScore += studentScores.get(course);
                    courseCount++;
                }
            }
            return totalScore / courseCount;
        } else {
            return 0.0;
        }
    }

    /**
     * (Overriding toString)
     * Returns a String representation of this class
     * In this case:
     *      the student's name
     *      number of courses enrolled in, and
     *      average scores in said applied courses
     * are all needed
     * ( For use with System.out.println(studentObject) )
     *
     * @return String representation of this class
     */
    @Override
    public String toString() {
        return studentName + "\n" +
                "Courses enrolled: " + getNumberEnrolled() + "\n" +
                "Average: " + calculateStudentAverage();
    }

    /*
     * =====================================================
     *                GETTERS AND SETTERS
     * =====================================================
     */

    /**
     * GETTER: studentId
     * @return studentId (int)
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * SETTER: studentId
     * @param studentId - student's new unique student ID
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * GETTER: studentName
     * @return studentName (String)
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * SETTER: studentName
     * @param studentName - student's new name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * GETTER: getCourses
     * @return - ArrayList of type Course ; all the courses a student is enrolled in
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * GETTER: getNumberEnrolled
     * @return - number of courses the student is enrolled in (int)
     */
    public int getNumberEnrolled() {
        return courses.size();
    }
}
