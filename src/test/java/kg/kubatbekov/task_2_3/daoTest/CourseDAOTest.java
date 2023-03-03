package kg.kubatbekov.task_2_3.daoTest;

import kg.kubatbekov.task_2_3.container.PostgresContainer;
import kg.kubatbekov.task_2_3.dao.CourseDAO;
import kg.kubatbekov.task_2_3.model.Course;
import kg.kubatbekov.task_2_3.service.ValueInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CourseDAOTest extends PostgresContainer {
    @MockBean
    private ValueInput valueInput;
    @Autowired
    private CourseDAO courseDAO;

    @Test
    void save_testSave_whenValueInput() {
        Course course = new Course("new_course", "new_course_description");
        boolean actual = courseDAO.save(course);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void save_testSave_whenNullInput() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> courseDAO.save(null));
        Assertions.assertEquals("Cannot invoke \"kg.kubatbekov.task_2_3.model.Course.getCourse_name()\" because \"course\" is null", exception.getMessage());
    }

    @Test
    void addStudent_testAddStudentToCourse_whenValueInput() {
        boolean actual = courseDAO.addStudent(1,20);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addStudent_testAddStudentToCourse_whenCourseWrongValueInput() {
        Exception exception = assertThrows(DataIntegrityViolationException.class,
                () -> courseDAO.addStudent(1000,20));
        Assertions.assertEquals("PreparedStatementCallback; SQL [insert into students_courses (student_id, course_id) VALUES (?,?);]; ERROR: insert or update on table \"students_courses\" violates foreign key constraint \"students_courses_course_id_fkey\"\n" +
                "  Detail: Key (course_id)=(1000) is not present in table \"courses\".", exception.getMessage());
    }

    @Test
    void addStudent_testAddStudentToCourse_whenStudentWrongValueInput() {
        Exception exception = assertThrows(DataIntegrityViolationException.class,
                () -> courseDAO.addStudent(10,10000));
        Assertions.assertEquals("PreparedStatementCallback; SQL [insert into students_courses (student_id, course_id) VALUES (?,?);]; ERROR: insert or update on table \"students_courses\" violates foreign key constraint \"students_courses_student_id_fkey\"\n" +
                        "  Detail: Key (student_id)=(10000) is not present in table \"students\".", exception.getMessage());
    }

    @Test
    void getAllStudents_testGetAllStudentsOfCourse_whenThereIsCourseName() {
        int actual = courseDAO.getAllStudents("course_1").size();
        Assertions.assertEquals(4, actual);
    }

    @Test
    void getAllStudents_testGetAllStudentsOfCourse_whenThereIsWrongCourseName() {
        int actual = courseDAO.getAllStudents("course_10000").size();
        Assertions.assertEquals(0, actual);
    }

    @Test
    void getAll_testGetAllCourses_whenThereIsCourses() {
        int actual = courseDAO.getAll().size();
        Assertions.assertEquals(11, actual);
    }

    @Test
    void getByName_testGetCourseByName_whenCourseNameInput() {
        Course course = new Course(7, "course_7", "course_description_7");

        Course actual = courseDAO.getByName(course.getCourse_name());
        Assertions.assertEquals(course, actual);
    }

    @Test
    void getByName_testGetCourseByName_whenWrongCourseNameInput() {
        Course excepted = new Course();
        Course actual = courseDAO.getByName("wrong_name");
        Assertions.assertEquals(excepted, actual);
    }


    @Test
    void update_testUpdateOfValue_whenValueInput() {
        Course course = new Course(1, "course_1_new", "course_description_1_new");
        boolean actual = courseDAO.update(course);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenValueInputNotUpdated() {
        boolean actual = courseDAO.update(new Course());
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> courseDAO.update(null));
        Assertions.assertEquals("Cannot invoke \"kg.kubatbekov.task_2_3.model.Course.getCourse_name()\" because \"course\" is null", exception.getMessage());
    }

    @Test
    void deleteById_testDeleteCourseById_whenThereIsCourse() {
        boolean actual = courseDAO.deleteById(10);
        boolean expected = true;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteById_testDeleteCourseById_whenCourseNotDeleted() {
        boolean actual = courseDAO.deleteById(1000);
        boolean expected = false;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteStudent_testDeleteStudentOfCourseById_whenThereIsValues() {
        boolean actual = courseDAO.deleteStudent(3, 1);
        boolean expected = true;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteStudent_testDeleteStudentOfCourseById_whenWrongCourseValuesInput() {
        boolean actual = courseDAO.deleteStudent(1000, 2);
        boolean expected = false;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteStudent_testDeleteStudentOfCourseById_whenWrongStudentValuesInput() {
        boolean actual = courseDAO.deleteStudent(3, 1000);
        boolean expected = false;

        Assertions.assertEquals(expected, actual);
    }
}
