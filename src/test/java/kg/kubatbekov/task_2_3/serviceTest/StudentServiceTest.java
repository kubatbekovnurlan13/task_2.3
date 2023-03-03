package kg.kubatbekov.task_2_3.serviceTest;

import kg.kubatbekov.task_2_3.container.PostgresContainer;
import kg.kubatbekov.task_2_3.model.Student;
import kg.kubatbekov.task_2_3.service.StudentService;
import kg.kubatbekov.task_2_3.service.ValueInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceTest extends PostgresContainer {
    @MockBean
    private ValueInput valueInput;
    @Autowired
    private StudentService studentService;

    @Test
    void save_testSave_whenValueInput() {
        Student student = new Student("new_student","new_desc",1);
        boolean actual = studentService.save(student);
        assertTrue(actual);
    }

    @Test
    void save_testSave_whenInputNull() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> studentService.save(null));
        assertEquals("Cannot invoke \"kg.kubatbekov.task_2_3.model.Student.getFirst_name()\" because \"student\" is null", exception.getMessage());
    }

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = studentService.getAll().size();
        assertEquals(109, actual);
    }

    @Test
    void getByName_testGetByName_whenValueInput() {
        Student student = new Student(2, "first_2", "last_2", 1);

        Student actual = studentService.getByName(student.getFirst_name());
        assertEquals(student, actual);
    }


    @Test
    void getByName_testGetByName_whenWrongValueInput() {
        Student student = new Student();
        Student actual = studentService.getByName("wrong_value");
        assertEquals(student, actual);
    }


    @Test
    void update_testUpdateOfValue_whenValueInput() {
        Student student = new Student(1, "first_name_new", "last_name_new", 5);

        boolean actual = studentService.update(student);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenValueInputNotUpdated() {
        boolean actual = studentService.update(new Student());
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> studentService.update(null));
        assertEquals("Cannot invoke \"kg.kubatbekov.task_2_3.model.Student.getFirst_name()\" because \"student\" is null", exception.getMessage());
    }

    @Test
    void deleteById_testDeleteById_whenThereIsValue() {
        boolean actual = studentService.deleteById(10);
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    void deleteById_testDeleteValueById_whenValueNotDeleted() {
        boolean actual = studentService.deleteById(1000);
        boolean expected = false;

        assertEquals(expected, actual);
    }
}
