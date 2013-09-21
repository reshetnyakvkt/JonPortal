package ua.com.jon.admin.client.components;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.NavHeader;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.NavList;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.github.gwtbootstrap.client.ui.WellNavList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import ua.com.jon.admin.client.AdminService;
import ua.com.jon.admin.client.AdminServiceAsync;
import ua.com.jon.admin.shared.GroupDTO;
import ua.com.jon.admin.shared.SprintDTO;
import ua.com.jon.admin.shared.TaskTemplateDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/1/13
 */
public class TasksTabPanel extends Composite {
/*    interface TasksTabPanelUiBinder extends UiBinder<Widget, TasksTabPanel> {
    }

    private static TasksTabPanelUiBinder uiBinder = GWT.create(TasksTabPanelUiBinder.class);*/

//    @UiField
//    com.google.gwt.user.client.ui.HTMLPanel tasksHolderPanel;

    @UiField
    WellNavList availableTasks = new WellNavList();

    @UiField
    WellNavList groupTasks = new WellNavList();

    @UiField
    Button add = new Button();

    @UiField
    Button remove = new Button();

//    @UiField
//    DropdownButton groupsListBox = new DropdownButton();

    @UiField(provided = true)
    ValueListBox<GroupDTO> groupsListBox = new ValueListBox<GroupDTO>(new AbstractRenderer<GroupDTO>() {
        @Override
        public String render(GroupDTO groupDTO) {
            if (groupDTO == null) {
                return "";
            } else {
                return groupDTO.getName();
            }
        }
    });

    @UiField(provided = true)
    ValueListBox<SprintDTO> sprintsListBox = new ValueListBox<SprintDTO>(new AbstractRenderer<SprintDTO>() {
        @Override
        public String render(SprintDTO sprintDTO) {
            if (sprintDTO == null) {
                return "";
            } else {
                return sprintDTO.getName();
            }
        }
    });

    private AdminServiceAsync adminService = GWT.create(AdminService.class);

    private ClickHandler handler = new ClickHandler() {
        @Override
        public void onClick(ClickEvent clickEvent) {

            Widget icon = (Widget) clickEvent.getSource();
            NavLink navLink = (NavLink) icon.getParent();
            if (navLink.isActive()) {
                navLink.setActive(false);
            } else {
                navLink.setActive(true);
            }
        }
    };

/*    public TasksTabPanel() {
        initWidget(uiBinder.createAndBindUi(this));
    }*/

    public TasksTabPanel(final UiBinder<Widget, TasksTabPanel> binder) {
        initWidget(binder.createAndBindUi(this));
/*        handler = new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

                Widget icon = (Widget) clickEvent.getSource();
                NavLink navLink = (NavLink) icon.getParent();
                if (navLink.isActive()) {
                    navLink.setActive(false);
                } else {
                    navLink.setActive(true);
                }
            }
        };*/
        loadGroupsAndTasks();
        loadSprintsAndTasks();
    }

    public void addWidgetToList(WellNavList list, Widget widget) {
        NavLink navLink = ((NavLink) widget);
        navLink.addClickHandler(handler);
        list.add(navLink);
    }

    @UiHandler("sprintsListBox")
    public void onChangeSprintPosition(ValueChangeEvent<SprintDTO> sprint) {
        clearSprintTasksList();
        addTasksToSprintNavList(sprint.getValue().getTasks());
    }

    private void clearSprintTasksList() {
        availableTasks.clear();
        availableTasks.add(new NavHeader("Доступные задания"));
    }

    @UiHandler("groupsListBox")
    public void onChangeGroupPosition(ValueChangeEvent<GroupDTO> group) {
        clearGroupTasksList();
        addTaskToGroupNavList(group.getValue().getTasks());
    }

    private void clearGroupTasksList() {
        groupTasks.clear();
        groupTasks.add(new NavHeader("Задания для группы"));
    }

    private void addTaskToGroupNavList(ArrayList<TaskTemplateDTO> tasks) {
        for (TaskTemplateDTO taskTemplateDTO : tasks) {
            NavLink taskNL = new NavLink(taskTemplateDTO.getName());
            taskNL.addClickHandler(handler);
            groupTasks.add(taskNL);
        }
    }

    private void addTasksToSprintNavList(List<TaskTemplateDTO> tasks) {
        if (tasks != null) {
            for (TaskTemplateDTO taskTemplateDTO : tasks) {
                NavLink taskNL = new NavLink(taskTemplateDTO.getId()+"-"+taskTemplateDTO.getName());
                taskNL.addClickHandler(handler);
                availableTasks.add(taskNL);
            }
        }
    }

    private void clearSprints() {
        availableTasks.clear();
        availableTasks.add(new NavHeader("Доступные задания"));
    }

    @UiHandler("add")
    void handleAddClick(ClickEvent e) {

        Widget widget = availableTasks.getWidget(0);
        List<Widget> children = getChildren(widget);

        for (Widget child : children) {
            if (child instanceof NavLink) {
                NavLink navLink = (NavLink) child;
                if (navLink.isActive()) {
                    navLink.setActive(false);
                    String taskText = navLink.getText();
                    NavLink newNavLink = new NavLink(taskText);
                    addWidgetToList(groupTasks, newNavLink);
                    groupsListBox.getValue().getTasks().add(new TaskTemplateDTO(taskText));
                }
            }
        }
    }

    @UiHandler("remove")
    void handleRemoveClick(ClickEvent e) {
        // if remove flag setted then remove all active elements
        getActiveElementsFromNavList(groupTasks, true);
/*        List<Widget> children = getChildren(widget);
        WellNavList newChildren = new WellNavList();
        for (Widget child : children) {
            if (child instanceof NavLink) {
                NavLink navLink = (NavLink) child;

//                if (navLink.isActive()) {
//                    groupTasks.remove(navLink);
//                }

                if (!navLink.isActive()) {
                    newChildren.add(navLink);
                }
            } else {
                newChildren.add(child);
            }
        }*/
//        groupTasks.clear();
//        groupTasks.add(newChildren);
    }

    private ArrayList<NavLink> getActiveElementsFromNavList(WellNavList navList, boolean isRemove) {
        ArrayList<NavLink> elements = new ArrayList<NavLink>();
        Widget widget = navList.getWidget(0);
        if (widget instanceof NavList) {
            NavList list = (NavList) widget;
//            Window.alert("widget " + widget);
            Iterator<Widget> itr = list.iterator();
            while (itr.hasNext()) {
                Widget el = itr.next();
                if (el instanceof NavLink) {
                    NavLink navLink = (NavLink) el;
//                    Window.alert("Element " + navLink + ", class " + navLink.getClass().getName() + ", " + navLink.isActive());
//                    if (navLink.isActive()) {
                    elements.add(navLink);
                    if (isRemove) {
                        itr.remove();
                    }
//                        Window.alert("Added " + el);
//                    }
                }
            }
        }
        return elements;
    }


    @UiHandler("refresAllBtn")
    void refresh(ClickEvent e) {
        clearSprints();
        loadGroupsAndTasks();
        loadSprintsAndTasks();
    }

    @UiHandler("sendTasksBtn")
    void saveChanges(ClickEvent e) {
        ArrayList<NavLink> links = getActiveElementsFromNavList(groupTasks, false);
        ArrayList<Long> taskNames = new ArrayList<Long>();
        for (NavLink link : links) {
            String name = link.getText();
            Long val;
            try {
                String[]array = name.split("-");
                val = Long.valueOf(array[0].trim());
            }
            catch (Exception ex){
                continue;
            }
            taskNames.add(val);
        }
        final AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("!!! Error async save tasks" + throwable);
            }

            @Override
            public void onSuccess(Void aVoid) {
                //loadGroupsAndTasks();
                Window.alert("CabinetMain tasks posted successfully");
            }
        };
        GroupDTO groupDTO = groupsListBox.getValue();
        SprintDTO sprintDTO = sprintsListBox.getValue();
        // TODO: replace taskNames by tasks
        adminService.postTasksByNames(groupDTO, taskNames, sprintDTO, callback);
    }

    List<Widget> getChildren(Widget parent) {

        List<Widget> result = new ArrayList<Widget>();
        if (parent instanceof HasWidgets) {
            Iterator<Widget> iter = ((NavList) parent).iterator();
            while (iter.hasNext()) {
                Widget next = iter.next();
//                Window.alert(next.toString());
                result.add(next);
            }
        }

        return result;
    }

    private void loadSprintsAndTasks() {
        final AsyncCallback<ArrayList<SprintDTO>> callback = new AsyncCallback<ArrayList<SprintDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error async get sprints");
            }

            @Override
            public void onSuccess(ArrayList<SprintDTO> sprints) {
//                Window.alert("sprints " + sprints);
                sprintsListBox.setAcceptableValues(sprints);
                for (SprintDTO sprint : sprints) {
                    if (sprint.isActive()) {
                        addTasksToSprintNavList(sprint.getTasks());
                        sprintsListBox.setValue(sprint);
                        break;
                    }
//                    Window.alert("Added sprint " + sprint.getName());
                }
            }
        };

        adminService.getSprintsAndTasks(callback);
    }

    private void loadGroupsAndTasks() {

        final AsyncCallback<List<GroupDTO>> groupCallback = new AsyncCallback<List<GroupDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error callback groupsListBox");
            }

            @Override
            public void onSuccess(List<GroupDTO> groupDTOs) {
                //Window.alert("groups: " + groupDTOs);

                groupsListBox.setValue(null);
                groupsListBox.setAcceptableValues(groupDTOs);
                if (groupDTOs.size() > 0) {
                    GroupDTO group = groupDTOs.get(0);
                    //addTaskToGroupNavList(groupTextBox.getTasks());
                    groupsListBox.setValue(group);
                }
            }
        };

        adminService.getGroupsAndTasks(groupCallback);
    }

    private void createGetTasksCallback() {
        final AsyncCallback<List<TaskTemplateDTO>> callback = new AsyncCallback<List<TaskTemplateDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error callback");
            }

            @Override
            public void onSuccess(List<TaskTemplateDTO> tasksTemplateDTOs) {

                for (TaskTemplateDTO taskTemplateDTO : tasksTemplateDTOs) {
                    NavLink taskNL = new NavLink(taskTemplateDTO.getName());
                    taskNL.addClickHandler(handler);
                    availableTasks.add(taskNL);
                }
            }
        };

        adminService.getTaskTemplates(callback);
    }
//
//    public HTMLPanel getGroupPanel() {
//        return tasksHolderPanel;
//    }

}
