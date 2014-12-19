package ua.com.jon.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import ua.com.jon.common.dto.GroupDTO;
import ua.com.jon.common.dto.TaskDTO;
import ua.com.jon.common.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

/**
 * Date: 01.01.13
 * Time: 23:58
 */
@Repository
public class GroupDAOJdbcImpl implements GroupDAO {
    private static Logger log = Logger.getLogger(GroupDAOJdbcImpl.class.getName());
    private static final String GROUPS_QUERY =
            "SELECT u.id, u.login, u.ignore_statistic, " +
            "g.id, g.name, g.active, g.REPOSITORY_URL, g.code, t.id, t.status, tt.name, tt.testName, tt.module_suffix\n" +
            "FROM USERS u, GROUPS g, TASKS t, TASK_TEMPLATES tt\n" +
            "WHERE t.group_id = g.id AND t.user_id = u.id AND t.template_id = tt.id\n" +
            "AND t.status = 'TEST' AND g.active = 1 AND g.REPOSITORY_URL <> '' AND g.REPOSITORY_URL IS NOT NULL";

    private static final String GROUP_INFO_QUERY =
            "SELECT u.login, FLOOR(SUM(result) / count(*))\n" +
                    "FROM USERS u, GROUPS g, TASKS t\n" +
                    "WHERE t.group_id = g.id AND t.user_id = u.id\n" +
                    "\tAND u.ignore_statistic = 0 AND t.group_id = ?\n" +
                    "GROUP BY u.id, t.sprint_id\n" +
                    "ORDER BY u.id, t.sprint_id";

    private static final String SPRINTS_COUNT_GROUP = "";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<GroupDTO> findActiveGroupAndTasksAndUsers() {

        return jdbcTemplate.query(GROUPS_QUERY, new ResultSetExtractor<List<GroupDTO>>() {
            @Override
            public List<GroupDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<Long, GroupDTO> groups = new HashMap<Long, GroupDTO>();
                Map<Long, UserDTO> users = new HashMap<Long, UserDTO>();
                int userIdIndex = 1;
                int loginIndex = 2;
                int ignoreIndex = 3;
                int groupIdIndex = 4;
                int gNameIndex = 5;
                int gActiveIndex = 6;
                int repoUrlIndex = 7;
                int codeIndex = 8;
                int taskIdIndex = 9;
                int taskStatusIndex = 10;
                int taskNameIndex = 11;
                int testNameIndex = 12;
                int suffixIndex = 13;
                while (rs.next()) {
                    long groupId = rs.getLong(groupIdIndex);
                    GroupDTO group;
                    UserDTO user;
                    long userId = rs.getLong(userIdIndex);
                    String userName = rs.getString(loginIndex);
                    if (!users.containsKey(userId)) {
                        user = new UserDTO(userId, userName, false,
                                rs.getBoolean(ignoreIndex));
                        users.put(userId, user);
                    } else {
                        user = users.get(userId);
                    }

                    if (!groups.containsKey(groupId)) {
                        group = new GroupDTO(groupId, new ArrayList<TaskDTO>(), new ArrayList<UserDTO>(), rs.getString(gNameIndex), rs.getBoolean(gActiveIndex),
                                rs.getString(repoUrlIndex), rs.getString(codeIndex));
                        groups.put(groupId, group);
                    } else {
                        group = groups.get(groupId);
                    }
                    TaskDTO task = new TaskDTO(rs.getLong(taskIdIndex), rs.getString(taskNameIndex), null, rs.getString(taskStatusIndex),
                            userName, rs.getString(testNameIndex), rs.getString(suffixIndex));
                    group.getTasks().add(task);
                    group.getUsers().add(user);
                }
                return new ArrayList<>(groups.values());
            }
        });
    }

    @Override
    public List<List<String>> findByGroupIdAndUserNotIgnore(Long selectedGroupId) throws Exception {
        return jdbcTemplate.query(GROUP_INFO_QUERY, new ResultSetExtractor<List<List<String>>>() {
            @Override
            public List<List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
                final int userNameIndex = 1;
                final int markSprintIndex = 2;

                Map<String, LinkedList<Integer>> users = new HashMap<String, LinkedList<Integer>>();
                while (rs.next()) {
//                    long groupId = rs.getInt(userIdIndex);
                    String userName = rs.getString(userNameIndex);
//                    long sprintId = rs.getLong(sprintIdIndex);
                    int markSprint = rs.getInt(markSprintIndex);

                    LinkedList<Integer> sprints = null;
                    if (!users.containsKey(userName)) {
                        sprints = new LinkedList<Integer>();
                        users.put(userName, sprints);
                    } else {
                        sprints = users.get(userName);
                    }

                    sprints.addLast(markSprint);
                }
                LinkedList<List<String>> sprints = new LinkedList<List<String>>();
                for (Map.Entry<String, LinkedList<Integer>> user : users.entrySet()) {
                    LinkedList<String> sprint = new LinkedList<>();
                    int sum = 0;
                    sprints.addLast(sprint);
                    for (Integer mark : user.getValue()) {
                        sum += mark;
                        sprint.addLast(String.valueOf(mark));
                    }
                    sprint.addFirst(String.valueOf(Math.round(sum / sprint.size())));
                    sprint.addFirst(user.getKey());
                }
                return sprints;
            }
        }, selectedGroupId);
    }
}
