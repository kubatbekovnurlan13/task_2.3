package kg.kubatbekov.task_2_3.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Group {
    private int group_id;
    private String group_name;

    public Group() {
    }

    public Group(int group_id, String group_name) {
        this.group_id = group_id;
        this.group_name = group_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public Group(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    @Override
    public String toString() {
        return "Group: " +
                "group_id=" + group_id +
                ", group_name='" + group_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return group_id == group.group_id && Objects.equals(group_name, group.group_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group_id, group_name);
    }
}
