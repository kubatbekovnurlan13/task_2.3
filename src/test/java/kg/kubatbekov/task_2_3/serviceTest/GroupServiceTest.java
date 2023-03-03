package kg.kubatbekov.task_2_3.serviceTest;

import kg.kubatbekov.task_2_3.container.PostgresContainer;
import kg.kubatbekov.task_2_3.model.Group;
import kg.kubatbekov.task_2_3.service.GroupService;
import kg.kubatbekov.task_2_3.service.ValueInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class GroupServiceTest extends PostgresContainer {
    @MockBean
    private ValueInput valueInput;
    @Autowired
    private GroupService groupService;

    @Test
    void save_testSave_whenValueInput() {
        Group group = new Group("group_name");
        boolean actual = groupService.save(group);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void save_testSave_whenInputNull() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> groupService.save(null));
        Assertions.assertEquals("Cannot invoke \"kg.kubatbekov.task_2_3.model.Group.getGroup_name()\" because \"group\" is null", exception.getMessage());
    }

    @Test
    void getByName_testGetByName_whenValueInput() {
        Group expected = new Group(1, "group_1");
        Group actual = groupService.getByName(expected.getGroup_name());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getByName_testGetByName_whenWrongValueInput() {
        Group expected = new Group();
        Group actual = groupService.getByName("wrong_value");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = groupService.getAll().size();
        Assertions.assertEquals(9, actual);
    }

    @Test
    void findLessOrEqualStudentAccounts_testFindLessOrEqualStudentAccounts_whenThereIsValues() {
        int actual = groupService.findLessOrEqualStudentAccounts(19).size();
        Assertions.assertEquals(3, actual);
    }

    @Test
    void findLessOrEqualStudentAccounts_testFindLessOrEqualStudentAccounts_whenThereIsNoValues() {
        int actual = groupService.findLessOrEqualStudentAccounts(1).size();
        Assertions.assertEquals(0, actual);
    }

    @Test
    void update_testUpdateOfValue_whenValueInput() {
        Group group = new Group(1, "group_1");

        boolean actual = groupService.update(group);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenValueInputNotUpdated() {
        boolean actual = groupService.update(new Group());
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> groupService.update(null));
        Assertions.assertEquals("Cannot invoke \"kg.kubatbekov.task_2_3.model.Group.getGroup_name()\" because \"group\" is null", exception.getMessage());
    }

    @Test
    void deleteById_testDeleteById_whenThereIsValue() {
        boolean actual = groupService.deleteById(10);
        boolean expected = true;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteById_testDeleteValueById_whenValueNotDeleted() {
        boolean actual = groupService.deleteById(1000);
        boolean expected = false;

        Assertions.assertEquals(expected, actual);
    }
}
