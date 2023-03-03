package kg.kubatbekov.task_2_3.service;

import kg.kubatbekov.task_2_3.model.Course;
import kg.kubatbekov.task_2_3.model.Group;
import kg.kubatbekov.task_2_3.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Component
public class ValueInput implements CommandLineRunner {
    private final Scanner in = new Scanner(System.in);
    private final GroupService groupService;
    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public ValueInput(GroupService groupService, CourseService courseService, StudentService studentService) {
        this.groupService = groupService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) {
        System.out.println("-------------------------------");
        List<Group> groups = groupService.findLessOrEqualStudentAccount(19);
        System.out.println("find Less Or Equal Student Account of group! ");
        for (Group group : groups) {
            System.out.println(group);
        }
        System.out.println("-------------------------------");
        List<Student> students = courseService.findAllStudents("course_1");
        System.out.println("find All Students of course!");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("-------------------------------");
        boolean check = courseService.addStudent(1, 15);
        System.out.println("Student with id: " + 15 + " added to course: " + 1 + " check: " + check);
        System.out.println("-------------------------------");
        boolean rmCheck = courseService.deleteStudent(1, 15);
        System.out.println("Student with id: " + 15 + " removed from course: " + 1 + " check: " + rmCheck);
        System.out.println("-------------------------------");

        doStuff();
    }

    private void doStuff() {
        while (true) {
            int command = getCommand();
            int modelName = getModelName();
            String value = getValue();
            if (Objects.equals(value, "stop")) {
                in.close();
                System.exit(0);
            }
            if (modelName == 1) {
                studentService(command, value);
            } else if (modelName == 2) {
                groupService(command, value);
            } else {
                courseService(command, value);
            }
        }
    }

    private void studentService(int command, String value) {
        if (command == 1) {
            Student student = new Student(value, "Default Last Name", 1);
            studentService.save(student);
        } else if (command == 2) {
            boolean check = studentService.deleteById(Integer.parseInt(value));
            System.out.println("Success: " + check);
        } else if (command == 3) {
            Student student = studentService.getByName(value);
            System.out.println("Result: " + student);
        }
    }

    private void groupService(int command, String value) {
        if (command == 1) {
            Group group = new Group(value);
            groupService.save(group);
        } else if (command == 2) {
            boolean check = groupService.deleteById(Integer.parseInt(value));
            System.out.println("Success: " + check);
        } else if (command == 3) {
            Group group = groupService.getByName(value);
            System.out.println("Result: " + group);
        }
    }

    private void courseService(int command, String value) {
        if (command == 1) {
            Course course = new Course(value, "Default Desc");
            courseService.save(course);
        } else if (command == 2) {
            boolean check = courseService.deleteById(Integer.parseInt(value));
            System.out.println("Success: " + check);
        } else if (command == 3) {
            Course course = courseService.getByName(value);
            System.out.println("Result: " + course);
        }
    }

    private int getCommand() {
        String info = """

                What would you like to do?
                0 - exit
                1 - save
                2 - delete by id
                3 - get by name
                Enter number of command:""";
        System.out.print(info);
        int command = in.nextInt();
        if (command == 0) {
            System.exit(0);
            in.close();
        } else if (command > 3 || command < 1) {
            System.out.println("Wrong command!");
            getCommand();
        }
        return command;
    }

    private int getModelName() {
        String info = """

                To whom do you want to perform this operation?
                0 - exit
                1 - Student
                2 - Group
                3 - Course
                Enter number of command:""";
        System.out.print(info);

        int command = in.nextInt();
        if (command == 0) {
            System.exit(0);
            in.close();
        } else if (command > 3 || command < 1) {
            System.out.println("Wrong command!");
            getModelName();
        }

        return command;
    }

    private String getValue() {
        in.nextLine();

        String info = """

                Enter 'stop' to exit.
                Enter value:""";
        System.out.print(info);
        return in.nextLine();
    }
}
