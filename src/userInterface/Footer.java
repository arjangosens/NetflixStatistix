package userInterface;

import javax.swing.*;

public class Footer {
    private JLabel projectName;
    private JLabel courseName;
    private JLabel courseYear;
    private JLabel className;
    private JLabel studentNames;

    public Footer(JLabel projectName, JLabel courseName, JLabel courseYear, JLabel className, JLabel studentNames) {
        this.projectName = projectName;
        this.courseName = courseName;
        this.courseYear = courseYear;
        this.className = className;
        this.studentNames = studentNames;
    }

    public JLabel getProjectName() {
        return projectName;
    }

    public JLabel getCourseName() {
        return courseName;
    }

    public JLabel getCourseYear() {
        return courseYear;
    }

    public JLabel getClassName() {
        return className;
    }

    public JLabel getStudentNames() {
        return studentNames;
    }
}
