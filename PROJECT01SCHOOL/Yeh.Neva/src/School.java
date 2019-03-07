/**
 * Title: School.java
 * Abstract: An object that represents the entirety of a school. This object acts as a container
 *           for all the general information of instructors, courses, and students at the specified school.
 *           The School has the abilities--which are not limited--to:
 *              read in data from a text file,
 *              display general information of school overall
 *              display information of overall courses or of a specific course in detail
 *              add instructors, courses, and students
 *              assign instructors to courses
 *              remove students (simulating graduation)
 * Author: Neva Yeh
 * Date: February 11-12, 2019
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class School {

    /**
     * Class Fields
     *
     * ArrayLists not only hold instructors, courses, and student objects themselves, but also their
     * respective identification numbers to ensure that there will not be any duplicate entries for any.
     */
    private String schoolName;
    private ArrayList<Instructor> instructors;
    private ArrayList<Course> courses;
    private ArrayList<Student> students;

    private ArrayList<Integer> instructorNumbers;
    private ArrayList<Integer> courseNumbers;
    private ArrayList<Integer> studentNumbers;

    /**
     * Parameterized Constructor
     * Sets name of school and initializes all ArrayLists
     *
     * @param name - sets the name of the school to the given name
     */
    School(String name) {
        schoolName = name;
        instructors = new ArrayList<>();
        courses = new ArrayList<>();
        students = new ArrayList<>();

        instructorNumbers = new ArrayList<>();
        courseNumbers = new ArrayList<>();
        studentNumbers = new ArrayList<>();
    }

    /**
     * Reads in data from a file in which the name is given using a Scanner
     * Assuming the file is given in a proper, consistent, and error-free manner,
     * this function adds all the instructors, courses, and students accordingly
     *
     * Each "section" creates a new object of its respective type
     * Before officially adding the object to the overall School object, it double
     * checks to make sure there will be no duplicated specific objects
     *
     * Prints "Done" at the end to signify the completion of reading in input
     *
     * @param filename - name of the file in which the data is to be read in from
     * @throws FileNotFoundException - declaring that an exception may be thrown
     */
    public void readData(String filename) throws FileNotFoundException {
        System.out.println("======== Reading Data ========");

        File file = new File(filename);
        Scanner in = new Scanner(file);
        in.useDelimiter("\\Z");

        String details;

        int amountInstructors = Integer.parseInt(in.nextLine());
        for (int i = 0; i < amountInstructors; i++) {
            details = in.nextLine();
            Instructor instructorToAdd = new Instructor(details);

            if(instructorNumbers.contains(instructorToAdd.getEmployeeNumber())){
                System.out.println("Instructor info reading failed -- Employee number "
                        + instructorToAdd.getEmployeeNumber() + " already used.");
            } else {
                instructors.add(instructorToAdd);
                instructorNumbers.add(instructorToAdd.getEmployeeNumber());
            }
        }

        int amountCourses = Integer.parseInt(in.nextLine());
        for (int i = 0; i < amountCourses; i++) {
            details = in.nextLine();
            Course courseToAdd = new Course(details);

            if(courseNumbers.contains(courseToAdd.getCourseNumber())) {
                System.out.println("Course info reading failed -- Course number "
                        + courseToAdd.getCourseNumber() + " already used.");
            } else {
                courses.add(courseToAdd);
                courseNumbers.add(courseToAdd.getCourseNumber());
            }
        }

        int amountStudents = Integer.parseInt(in.nextLine());
        for(int i = 0; i < amountStudents; i++) {
            details = in.nextLine();
            Student studentToAdd = new Student(details);

            if(studentNumbers.contains(studentToAdd.getStudentId())) {
                System.out.println("Student info reading failed -- Student ID "
                        + studentToAdd.getStudentId() + " already used.");
            } else {
                students.add(studentToAdd);
                studentNumbers.add(studentToAdd.getStudentId());
            }
        }

        System.out.println("Done\n");
    }

    /**
     * Displays overall information about the School
     * Shows:
     *      Instructor names
     *      Course Titles
     *      Student Names
     */
    public void schoolInfo() {
        System.out.println("======== School Info ========");

        System.out.println("School name: " + getSchoolName());

        System.out.println("\nInstructor Information");
        for(Instructor instructor : instructors) {
            System.out.println(instructor);
        }

        System.out.println("\nCourse Information");
        for(Course course : courses) {
            System.out.println(course);
        }

        System.out.println("\nStudent Information");
        for(Student student : students) {
            System.out.println(student.getStudentName());
        }

        System.out.println();
    }

    /*
     * =====================================================
     *                INSTRUCTOR FUNCTIONS
     * =====================================================
     */

    /**
     * Adds an instructor to the ArrayList for instructors
     * This is called directly from the client code
     * (This data is manually entered as opposed to read in from file)
     *
     * Contains validation to prevent duplication of unique employee number
     *
     * @param number - the instructor's unique employee number
     * @param name - the instructor's name
     * @param email - the instructor's email
     * @param phone - the instructor's phone number
     */
    public void addInstructor(int number, String name, String email, String phone) {
        String details = String.valueOf(number) + "," + name + "," + email + "," + phone;
        Instructor instructorToAdd = new Instructor(details);

        if(instructorNumbers.contains(instructorToAdd.getEmployeeNumber())){
            System.out.println("* Employee number "
                    + instructorToAdd.getEmployeeNumber() + " already in use.");
        } else {
            instructors.add(instructorToAdd);
            instructorNumbers.add(instructorToAdd.getEmployeeNumber());
        }
    }

    /**
     * Assigns an instructor to a course
     * Prints error codes where appropriate
     *
     * Contains validation to ensure that both the instructor and course are valid
     * before officially assigning
     *
     * @param courseId - course that will be assigned an instructor to teach it
     * @param instructorId - instructor that will be assigned a course to teach
     */
    public void assignInstructor(int courseId, int instructorId) {
        Instructor instructorToAssign = getInstructor(instructorId);
        Course courseToAssign = getCourse(courseId);

        if (instructorToAssign == null) {
            System.out.println("* Instructor (" + instructorId + ") does not exist");
        } else if(courseToAssign == null) {
            System.out.println("* Course (" + courseId + ") does not exist");
        } else {
            if(instructorNumbers.contains(instructorToAssign.getEmployeeNumber())
                    && courseNumbers.contains(courseToAssign.getCourseNumber())) {
                courseToAssign.setCourseInstructor(instructorToAssign);
                instructorToAssign.addCourse(courseToAssign);
            }
        }
    }

    /**
     * Searches for email
     * In this instance, the user did not give an email to search for
     * They are then prompted for an email to search for
     */
    public void searchByEmail() {
        Scanner in = new Scanner(System.in);

        System.out.print("Search key: ");
        String emailToSearch = in.nextLine();

        searchByEmail(emailToSearch);
    }

    /**
     * (Overloaded searchByEmail)
     * Searches through all the instructors and compares the given email to all
     * the instructors' email addresses
     *
     * Uses an overloaded getInstructor function to retrieve the instructor
     * If the instructor does not exist (null returned), prints out error
     * If instructor does exist, prints out information of said instructor
     *
     * @param email - email to search for
     */
    public void searchByEmail(String email) {
        Instructor instructorInQuestion = getInstructor(email);

        if(instructorInQuestion == null) {
            System.out.println("No employee with email " + email);
        }
        else {
            System.out.println("Employee Number: " + instructorInQuestion.getEmployeeNumber());
            System.out.println("Name: " + instructorInQuestion.getInstructorName());
            System.out.println("Phone: " + instructorInQuestion.getPhoneNumber());
        }
    }

    /*
     * =====================================================
     *                  COURSE FUNCTIONS
     * =====================================================
     */

    /**
     * Adds course to the overall courses at the school
     * This is called directly from the client code
     * (This data is manually entered as opposed to read in from file)
     *
     * NOT to be confused with Instructor.addCourse or Student.addCourse
     *
     * If the given course location starts with ERROR, we know that the input is invalid
     * Therefore, it will print out an error message
     * Otherwise, it will create the course, validate, and add course if valid
     *
     * @param number - the course's unique course number
     * @param title - the course's title
     * @param capacity - the course's capacity
     * @param location - the course's location
     */
    public void addCourse(int number, String title, int capacity, String location) {
        if(location.substring(0, 4).toUpperCase().equals("ERROR")) {
            System.out.println("* Failed to add course due to input error");
        } else {
            String details = String.valueOf(number) + "," + title + ","
                    + String.valueOf(capacity) + "," + location;
            Course courseToAdd = new Course(details);

            if(courseNumbers.contains(courseToAdd.getCourseNumber())) {
                System.out.println("* Failed to add course " + courseToAdd.getCourseNumber());
            } else {
                courses.add(courseToAdd);
                courseNumbers.add(courseToAdd.getCourseNumber());
            }
        }
    }

    /**
     * Displays information of all the courses in general
     * Will display each course title with the number of students enrolled
     */
    public void courseInfo() {
        System.out.println("Number of courses: " + courses.size());
        for(Course course : courses) {
            System.out.println(course.getCourseNumber() + ": "
                    + course.getNumberEnrolled() + " enrolled");
        }
    }

    /**
     * (Overloaded courseInfo)
     * Displays specific information about a specified course, if it exists
     *
     * @param number - course ID to provide information for
     */
    public void courseInfo(int number) {
        Course courseInQuestion = getCourse(number);

        if(courseInQuestion != null) {
            System.out.println("Course number: " + number);
            System.out.println("Instructor: " + courseInQuestion.getCourseInstructor());
            System.out.println("Course Title: " + courseInQuestion.getCourseTitle());
            System.out.println("Room: " + courseInQuestion.getCourseLocation());
            System.out.println("Total Enrolled: " + courseInQuestion.getNumberEnrolled());

            double courseAverage = courseInQuestion.calculateCourseAverage();
            String toPrint = courseAverage == 0.0 ? "NA" : String.valueOf(courseAverage);
            System.out.println("Course Average: " + toPrint);
        } else {
            System.out.println("* Course does not exist");
        }
    }

    /**
     * Deletes the course from the school, IF there is no one enrolled in it still
     * If there are still students enrolled, an error message will be printed
     *
     * @param number - course ID of course to be deleted
     */
    public void deleteCourse(int number) {
        Course courseToDelete = getCourse(number);

        if(courseToDelete != null && courseToDelete.getNumberEnrolled() == 0) {
            courses.remove(courseToDelete);
            courseNumbers.remove((Integer)courseToDelete.getCourseNumber());
        } else {
            System.out.println("* Course not removed. Students are still enrolled.");
        }
    }

    /*
     * =====================================================
     *                  STUDENT FUNCTIONS
     * =====================================================
     */

    /**
     * Creates and adds a new Student to the School
     * This is called directly from the client code
     * (This data is manually entered as opposed to read in from file)
     *
     * @param number - the student's unique student ID
     * @param name - the student's name
     */
    public void addStudent(int number, String name) {
        String details = String.valueOf(number) + "," + name;
        students.add(new Student(details));
    }

    /**
     * Enrolls a student into a course, given that the course is not yet at capacity
     *
     * @param courseId - ID of course
     * @param studentId - ID of student to be added to course
     */
    public void register(int courseId, int studentId) {
        Course courseToAssign = getCourse(courseId);
        Student studentToAssign = getStudent(studentId);

        if(courseToAssign == null) {
            System.out.println("* Course " + courseId + " does not exist");
        } else if(studentToAssign == null) {
            System.out.println("* Student " + studentId + " does not exist");
        } else {
            if(courseToAssign.getNumberEnrolled() < courseToAssign.getCourseCapacity()) {
                courseToAssign.registerStudent(studentToAssign);
                studentToAssign.addCourse(courseToAssign);
            }
        }
    }

    /**
     * Removes a student from a course
     *
     * @param courseId
     * @param studentId
     */
    public void unRegister(int courseId, int studentId) {
        Course courseInQuestion = getCourse(courseId);
        Student studentInQuestion = getStudent(studentId);

        if(courseInQuestion == null) {
            System.out.println("* Course " + courseId + " does not exist");
        } else if (studentInQuestion == null) {
            System.out.println("* Student " + studentId + " does not exist");
        } else {
            courseInQuestion.removeStudent(studentInQuestion);
            studentInQuestion.dropCourse(courseInQuestion);
        }
    }

    /**
     * "Records" a score for a student in a specific course
     *
     * @param courseId - ID of course in which the student got the score in
     * @param studentId - ID of student whose score is to be recorded
     * @param score - score value to be recorded for a specific student for a specific course
     */
    public void putScore(int courseId, int studentId, double score) {
        Course courseInQuestion = getCourse(courseId);
        Student studentInQuestion = getStudent(studentId);

        if(courseInQuestion == null) {
            System.out.println("* Course " + courseId + " does not exist");
        } else if (studentInQuestion == null) {
            System.out.println("* Student " + studentId + " does not exist");
        } else {
            courseInQuestion.updateScore(studentInQuestion, score);
            studentInQuestion.updateScore(courseInQuestion, score);
        }
    }

    /**
     * Simulates the student graduating from the school
     * Effectively removes them from every course and then removes them from the School
     *
     * @param studentId - ID of student to graduate
     */
    public void graduateStudent(int studentId) {
        Student studentInQuestion = getStudent(studentId);
        ArrayList<Course> studentCourses = studentInQuestion.getCourses();

        // Shortcut to removes student from all their courses
        for (Course course : studentCourses) {
            course.removeStudent(studentInQuestion);
        }

        students.remove(studentInQuestion);
    }

    /*
     * =====================================================
     *                GETTERS AND SETTERS
     * =====================================================
     */

    /**
     * GETTER: schoolName
     * @return - schoolName (String)
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * GETTER: Instructor
     *
     * Searches through all instructors and compares each employee ID to the given one
     *
     * @param number - ID of instructor that is being searched for
     * @return - respective Instructor (if exists)
     *           null (if does not exist)
     */
    public Instructor getInstructor(int number) {

        for(int i = 0; i < instructors.size(); i++) {
            if(instructors.get(i).getEmployeeNumber() == number) {
                return instructors.get(i);
            }
        }
        return null;
    }

    /**
     * (Overloaded getInstructor)
     * GETTER: Instructor
     *
     * Searches through all instructors and compares each employee email to the given one
     * (Used for searchByEmail)
     *
     * @param email - email of instructor that is being searched for
     * @return - respective Instructor (if exists)
     *           null (if does not exist)
     */
    public Instructor getInstructor(String email) {
        for(int i = 0; i < instructors.size(); i++) {
            if(instructors.get(i).getEmailAddress().equals(email)) {
                return instructors.get(i);
            }
        }
        return null;
    }

    /**
     * GETTER: Course
     *
     * Searches through all courses and compares each course  ID to the given one
     *
     * @param number - ID of course that is being searched for
     * @return - respective Course (if exists)
     *           null (if does not exist)
     */
    public Course getCourse(int number) {
        for(int i = 0; i < courses.size(); i++) {
            if(courses.get(i).getCourseNumber() == number) {
                return courses.get(i);
            }
        }
        return null;
    }

    /**
     * GETTER: Student
     *
     * Searches through all students and compares each student ID to the given one
     *
     * @param number - ID of student that is being searched for
     * @return - respective Instructor (if exists)
     *           null (if does not exist)
     */
    public Student getStudent(int number) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getStudentId() == number) {
                return students.get(i);
            }
        }
        return null;
    }
}
