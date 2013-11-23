package ua.com.jon.admin.service;

import com.jon.tron.service.reflect.ReflectionUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.jon.admin.client.AdminService;
import ua.com.jon.admin.shared.GroupAndUsersDTO;
import ua.com.jon.admin.shared.GroupDTO;
import ua.com.jon.admin.shared.SpaceDTO;
import ua.com.jon.admin.shared.SprintDTO;
import ua.com.jon.admin.shared.TaskDTO;
import ua.com.jon.admin.shared.TaskTemplateDTO;
import ua.com.jon.admin.shared.UserDTO;
import ua.com.jon.auth.domain.AssemblaSpace;
import ua.com.jon.auth.domain.AssemblaUser;
import ua.com.jon.auth.service.AuthService;
import ua.com.jon.common.domain.Group;
import ua.com.jon.common.domain.Sprint;
import ua.com.jon.common.domain.SprintType;
import ua.com.jon.common.domain.Status;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.domain.TaskTemplate;
import ua.com.jon.common.domain.User;
import ua.com.jon.common.dto.mapper.GroupAndUsersDtoMapper;
import ua.com.jon.common.dto.mapper.GroupDtoMapper;
import ua.com.jon.common.dto.mapper.SpaceDtoMapper;
import ua.com.jon.common.dto.mapper.SprintDtoMapper;
import ua.com.jon.common.dto.mapper.TaskDtoMapper;
import ua.com.jon.common.repository.GroupRepository;
import ua.com.jon.common.repository.SprintRepository;
import ua.com.jon.common.repository.TaskRepository;
import ua.com.jon.common.repository.TaskTemplateRepository;
import ua.com.jon.common.repository.UserRepository;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 15.06.13
 * Time: 20:33
 */

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    private static final Logger log = Logger.getLogger(AdminServiceImpl.class);

    @Resource
    private GroupRepository groupRepository;

    @Resource
    private TaskTemplateRepository taskTemplateRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private SprintRepository sprintRepository;

    @Autowired
    private AuthService authService;

    @Value( "${tests.package}" )
    private String testsPackage;

    @Override
    public List<TaskTemplateDTO> getTaskTemplates() {
        List<TaskTemplateDTO> taskTemplates = new ArrayList<TaskTemplateDTO>();
        TaskTemplateDTO task1 = new TaskTemplateDTO("Задание 1", "Текст задания 1", "CLASS");
        TaskTemplateDTO task2 = new TaskTemplateDTO("Задание 2", "Текст задания 2", "CLASS");
        TaskTemplateDTO task3 = new TaskTemplateDTO("Задание 3", "Текст задания 3", "CLASS");
        TaskTemplateDTO task4 = new TaskTemplateDTO("Задание 4", "Текст задания 4", "CLASS");
        TaskTemplateDTO task5 = new TaskTemplateDTO("Задание 5", "Текст задания 4", "CLASS");

        taskTemplates.add(task1);
        taskTemplates.add(task2);
        taskTemplates.add(task3);
        taskTemplates.add(task4);
        taskTemplates.add(task5);

        return taskTemplates;
    }

    @Override
    public List<GroupDTO> getGroupsAndTasks() {
        log.info("getGroupsAndTasks()");
        ArrayList<GroupDTO> groupDTOs = new ArrayList<GroupDTO>();
        Iterable<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            ArrayList<TaskTemplate> tasks = taskTemplateRepository.findByGroupName(group.getName());
            GroupDTO groupDTO = GroupDtoMapper.domainToDto(group, tasks);
            groupDTOs.add(groupDTO);

        }
        log.info("= " + groupDTOs + " =");
        return groupDTOs;

    }

    @Override
    public ArrayList<SpaceDTO> getSpaces() {
        log.info("-- getSpaces()");
//        List<AssemblaSpace> assemblaSpaces = authService.getSpaces();
        List<AssemblaSpace> assemblaSpaces = authService.getSpaces();
        ArrayList<SpaceDTO> spaceDTOs = new ArrayList<SpaceDTO>(assemblaSpaces.size());
        for (AssemblaSpace space : assemblaSpaces) {
            List<AssemblaUser> usersInSpace = authService.getUsersBySpace(space.getName());
            spaceDTOs.add(SpaceDtoMapper.spaceToDto(space, usersInSpace));
        }
        log.info("-- getSpaces(), return space to client: " + spaceDTOs);
        return spaceDTOs;
    }

    @Override
    @Transactional
    public void createGroup(SpaceDTO groupDto) throws Exception {
        log.info("-- createGroup() " + groupDto);
        try {
//            for (UserDTO userDTO : groupDto.getUsers()) {
            Set<String> nameSet = userToNamesWithTrim(groupDto.getUsers());
            List<User> users = userRepository.findByNames(nameSet);
            for (User user : users) {
                nameSet.remove(user.getLogin());
            }
            Group group = new Group(groupDto.getName(), new Date(), false, new HashSet<User>());
            Set<Group> groups = new HashSet<Group>();
            groups.add(group);
            for (String userName : nameSet) {
                users.add(new User(userName, userName, new Date(), groups));
            }
            groupRepository.save(group);
//            userRepository.save(users);
            for (User user : users) {
                List<Group> tmpGroups = groupRepository.findByUsersIn(user.getLogin());
                user.getGroups().addAll(tmpGroups);
                user.getGroups().add(group);
            }
            group.getUsers().addAll(users);
            groupRepository.save(group);
            //userRepository.save(users);
            log.info("-- created group " + group);
//            }
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    @Override
    public void saveSprints(List<SprintDTO> newSprints) {
        log.info("-- saveSprints() " + newSprints);
        List<SprintDTO> sprintDTOs = trimSprintsAndConvertToSet(newSprints);
        Set<Long> ids = getIdsFromSprintsWithoutNull(sprintDTOs);
        List<Sprint> sprints = new ArrayList<Sprint>();
        if(!ids.isEmpty()) {
            sprints = sprintRepository.findByIds(ids);
        }

        List<Sprint> sprintsToSave = SprintDtoMapper.convertSprintDtosToEntity(sprints, sprintDTOs);
        sprintRepository.save(sprintsToSave);
        log.info("-- saved sprints " + sprintsToSave);
    }

    @Override
    public void saveGroups(ArrayList<GroupAndUsersDTO> newSprints) {

    }

    private Set<Long> getIdsFromSprintsWithoutNull(List<SprintDTO> sprintDTOs) {
        Set<Long> keys = new HashSet<Long>();
        for(SprintDTO sprintDTO : sprintDTOs){
            if(sprintDTO.getId() !=  null){
               keys.add(sprintDTO.getId());
            }
        }
        return keys;
    }

    private Set<String> userToNamesWithTrim(ArrayList<UserDTO> users) {
        Set<String> userNames = new HashSet<String>(users.size());
        for (UserDTO user : users) {
            userNames.add(user.getName().trim());
        }
        return userNames;
    }

    private List<SprintDTO> trimSprintsAndConvertToSet(List<SprintDTO> sprints) {
        List<SprintDTO> sprintDTOs = new ArrayList<SprintDTO>(sprints.size());
        for (SprintDTO sprint : sprints) {
            String trimmedName = sprint.getName().trim();
            sprint.setName(trimmedName);
            sprintDTOs.add(sprint);
        }
        return sprintDTOs;
    }

    @Override
    public ArrayList<SprintDTO> getSprintsAndTasks() {
        log.info("-- getSprintsAndTasks() ");

        Iterable<Sprint> sprintIterable = sprintRepository.findAll();
        ArrayList<SprintDTO> sprints = new ArrayList<SprintDTO>();
        for (Sprint sprint : sprintIterable) {
//            List<Task> tasks = taskRepository.findBySprintName(sprint.getName());
            sprints.add(SprintDtoMapper.domainToDto(sprint));
        }
        log.info("-- return sprint to client " + sprints);
        return sprints;
    }

    @Override
    public void sprintTypeChanged(SprintDTO dto) {
        log.info("sprintTypeChanged: " + dto);
        Sprint sprint = sprintRepository.findOne(dto.getId());
        SprintType newType = SprintType.valueOf(dto.getType());
        sprint.setType(newType);
        sprintRepository.save(sprint);

        System.out.println("Changed sprint is " + dto);
    }

    @Override
    public ArrayList<String> getAvailableTestNames() {
        try {
            return ReflectionUtil.findAllTests(testsPackage);
        } catch (IOException e) {
            log.error("Get tests error ", e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            log.error("No tests found ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TaskDTO> getTasksbyGroup(String name) {
        Group group = groupRepository.findByName(name);
        List<Task> tasks = taskRepository.findByGroupId(group.getId());
        return TaskDtoMapper.domainsToAdminDtos(tasks);
    }

    @Override
    public ArrayList<GroupAndUsersDTO> getGroupsAndUsers() {
        Set<Group> groups = new HashSet();
        groups.addAll(groupRepository.findAllGroupsAndUsers());
        List<Group> groupsList = new ArrayList<Group>(groups.size());
        groupsList.addAll(groups);
        return GroupAndUsersDtoMapper.domainsToDtos(groupsList);
    }

    @Override
    @Transactional
    public void deleteGroup(Long id) {
        Group group = groupRepository.findOne(id);
        for (User user : group.getUsers()) {
            user.getGroups().remove(group);
        }
        group.getUsers().clear();
        userRepository.save(group.getUsers());
        groupRepository.save(group);
        groupRepository.delete(id);
        log.info("-- Deleting group " + id + " was successfuly");
    }


    @Override
    public void postTasksByNames(GroupDTO groupDto, ArrayList<Long> taskIds, SprintDTO sprintDto) {
        log.info("-- Post tasks " + taskIds + " to groupDto " + groupDto);
     //   System.out.println("-- Post tasks " + taskIds + " to groupDto " + groupDto);
//        System.out.println("Post tasks " + taskNames + " to groupDto " + groupName);
        Sprint sprint = sprintRepository.findOne(sprintDto.getId());
        Group group = groupRepository.findOne(groupDto.getId());
        try {
            //trimListElements(taskNames);
            List<User> usersInGroup = userRepository.findByGroupName(group.getName());
            Iterable<TaskTemplate> taskTemplates = taskTemplateRepository.findAll(taskIds);

            for (TaskTemplate taskTemplate : taskTemplates) {
                for (User user : usersInGroup) {
                    Task task = new Task(user, taskTemplate, sprint, Status.NEW, "", "", group);
                    taskRepository.save(task);
                    //log.info("-- Posted task " + task);
                }
            }
        } catch (Exception e) {
            log.error("Post tasks error " + e);
            throw new RuntimeException(e);
        }
    }

    private void trimListElements(ArrayList<String> taskNames) {
        for (int i = 0; i < taskNames.size(); i++) {
            taskNames.set(i, taskNames.get(i).trim());
        }
    }
}