package kg.kubatbekov.task_2_3.service;

import kg.kubatbekov.task_2_3.dao.GroupDAO;
import kg.kubatbekov.task_2_3.model.Group;
import kg.kubatbekov.task_2_3.rowMapper.GroupRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupDAO groupDAO;

    @Autowired
    public GroupService(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public boolean save(Group group) {
        return groupDAO.save(group);
    }

    public Group getByName(String name) {
        return groupDAO.getByName(name);
    }

    public List<Group> findLessOrEqualStudentAccounts(int count) {
        return groupDAO.findLessOrEqualStudentAccounts(count);
    }

    public List<Group> getAll() {
        return groupDAO.getAll();
    }

    public List<Group> findLessOrEqualStudentAccount(int count) {
        return groupDAO.findLessOrEqualStudentAccounts(count);
    }

    public boolean update(Group group) {
        return groupDAO.update(group);
    }

    public boolean deleteById(int group_id) {
        return groupDAO.deleteById(group_id);
    }
}
