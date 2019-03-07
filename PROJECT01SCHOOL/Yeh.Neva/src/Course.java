/**
 * Title: Course.java
 * Abstract: An object that represents a class in the structure of a school system.
 *           A course can both add and remove a student as well as update its location.
 *           A course can also update the scores of its students and calculate the
 *           average of all the scores of the students that have a score recorded
 *           "in their name."
 * Author: Neva Yeh
 * Date: February 11-12, 2019
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Course {

    /**
     * Class Fields
     *
     * An ArrayList holds all the students enrolled in the course
     * A HashMap is used to store students and their respective scores recorded in the course
     */
    private int courseNumber;
    private String courseTitle;
    private int courseCapacity;
    private String courseLocation;

    private Instructor courseInstructor;
    private ArrayList<Student> students;
    private HashMap<Student, Double> courseScores;

    /**
     * Parameterized Constructor
     * Initializes courses's fields by splitting a given String that contains
     * all necessary information, with respective fields separated by commas
     *
     * Also initializes the ArrayList to prepare it for holding the courses's students
     * as well as the HashMap to prepare it for holding all the students' respective scores
     *
     * @param details - a single String containing all of the courses's information
     */
    Course(String details) {
        String courseDetails[] = details.split(",");
        setCourseNumber(Integer.parseInt(courseDetails[0]));
        setCourseTitle(courseDetails[1]);
        setCourseCapacity(Integer.parseInt(courseDetails[2]));
        setCourseLocation(courseDetails[3]);
        courseInstructor = null;
        students = new ArrayList<>();
        courseScores = new HashMap<Student, Double>();
    }

    /**
     * Updates the location of the course
     *
     * @param newLocation - the course's new location
     */
    public void updateLocation(String newLocation) {
        courseLocation = newLocation;
    }

    /**
     * Registers a student into the course
     * Also "initializes" the student's spot in the HashMap
     *
     * @param student - Student to be enrolled
     */
    public void registerStudent(Student student) {
        students.add(student);
        courseScores.put(student, 0.0);
    }

    /**
     * Removes a student from the course
     * Also removes the student's key from the HashMap
     *
     * @param student - Student to be removed
     */
    public void removeStudent(Student student) {
        students.remove(student);
        courseScores.remove(student);
    }

    /**
     * Updates the score that a specific student got in the course
     *
     * @param student - student whose grade is going to be changed
     * @param score - student's new score to be recorded
     */
    public void updateScore(Student student, double score) {
        courseScores.put(student, score);
    }

    /**
     * Calculates the average of all of the students' recorded scores
     * (If their score has not been recorded (0.0), it will not be counted)
     *
     * @return - average of the all the scores of all applicable students in this course
     */
    public double calculateCourseAverage() {
        int studentsEnrolled = getNumberEnrolled();

        if(studentsEnrolled > 0) {
            int studentCount = 0;
            double totalScores = 0;

            for (Student student : students) {
                if(!(courseScores.get(student) == 0.0)) {
                    totalScores += courseScores.get(student);
                    studentCount++;
                }
            }
            return totalScores / studentCount;
        }
        else {
            return 0.0;
        }
    }

    /**
     * (Overriding toString)
     * Returns a String representation of this class
     * In this case, just the course's title is needed
     * ( For use with School.schoolInfo() )
     *
     * @return String representation of this class
     */
    @Override
    public String toString() {
        return courseTitle;
    }

    /*
     * =====================================================
     *                GETTERS AND SETTERS
     * =====================================================
     */

    /**
     * GETTER: courseNumber
     * @return courseNumber (int)
     */
    public int getCourseNumber() {
        return courseNumber;
    }

    /**
     * SETTER: courseNumber
     * @param courseNumber - course's new unique course number
     */
    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    /**
     * GETTER: courseTitle
     * @return courseTitle (String)
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * SETTER: courseTitle
     * @param courseTitle - course's new title
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     * GETTER: courseCapacity
     * @return courseCapacity (int)
     */
    public int getCourseCapacity() {
        return courseCapacity;
    }

    /**
     * SETTER: courseCapacity
     * @param courseCapacity - course's new capacity
     */
    public void setCourseCapacity(int courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    /**
     * GETTER: courseLocation
     * @return courseLocation (String)
     */
    public String getCourseLocation() {
        return courseLocation;
    }

    /**
     * SETTER: courseLocation
     * @param courseLocation - course's new location
     */
    public void setCourseLocation(String courseLocation) {
        this.courseLocation = courseLocation;
    }

    /**
     * GETTER: courseInstructor
     * @return courseInstructor (Instructor object)
     */
    public Instructor getCourseInstructor() {
        return courseInstructor;
    }

    /**
     * SETTER: courseInstructor
     * @param courseInstructor - course's new Instructor
     */
    public void setCourseInstructor(Instructor courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    /**
     * GETTER: getNumberEnrolled
     * @return - number of students currently enrolled in the course (int)
     */
    public int getNumberEnrolled() {
        return students.size();
    }
}
