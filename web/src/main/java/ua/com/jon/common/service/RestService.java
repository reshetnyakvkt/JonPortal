package ua.com.jon.common.service;

import com.jon.tron.service.processor.ClassProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.jon.common.domain.Group;
import ua.com.jon.common.dto.GroupDTO;
import ua.com.jon.common.dto.mapper.GroupAndTaskDtoMapper;
import ua.com.jon.common.repository.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 1/15/14
 */
@Service
public class RestService {
    private static final Logger log = Logger.getLogger(RestService.class);

    @Autowired
    private ClassProcessor classProcessor;

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private SprintRepository sprintRepository;

    @Resource
    private TaskTemplateRepository templateRepository;

    @Resource
    private GroupRepository groupRepository;

    public GroupDTO getGroupDtoWithTasks(String groupName) {
        Group group = groupRepository.findGroupAndUsersByName(groupName);
        if(group == null) {
            throw new RuntimeException("Group not found: " + groupName);
        }
        return GroupAndTaskDtoMapper.domainToDto(group);
    }
}
