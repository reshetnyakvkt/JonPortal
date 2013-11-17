package ua.com.jon.admin.client.components;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ButtonCell;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.ProgressBar;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ua.com.jon.admin.client.AdminService;
import ua.com.jon.admin.client.AdminServiceAsync;
import ua.com.jon.admin.shared.GroupAndUsersDTO;
import ua.com.jon.admin.shared.UserDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 8/12/13
 */
public class GroupsManageTabPanel extends Composite {
    @UiField
    Button refreshGroupsBtn = new Button();

    @UiField
    Button delGroupBtn = new Button();

    @UiField
    Button createStudentBtn = new Button();

    @UiField
    Button saveGroupBtn = new Button();

    @UiField
    ProgressBar sprintsProgress;

    @UiField
    CellTable<UserDTO> cellTable = new CellTable<UserDTO>(5, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));

    @UiField
    TextArea textArea = new TextArea();

    private GroupAndUsersDTO currentGroup;
    private ArrayList<GroupAndUsersDTO> loadedGroups = new ArrayList<GroupAndUsersDTO>();

    private ListDataProvider<UserDTO> dataProvider = new ListDataProvider<UserDTO>();

    private AdminServiceAsync adminService = GWT.create(AdminService.class);

    @UiField(provided = true)
    ValueListBox<GroupAndUsersDTO> groupsListBox = new ValueListBox<GroupAndUsersDTO>(new AbstractRenderer<GroupAndUsersDTO>() {
        @Override
        public String render(GroupAndUsersDTO GroupAndUsersDTO) {
            if (GroupAndUsersDTO == null) {
                return "";
            } else {
                return GroupAndUsersDTO.getName();
            }
        }
    });

    public GroupsManageTabPanel(final UiBinder<Widget, GroupsManageTabPanel> binder) {
        initWidget(binder.createAndBindUi(this));
        buildTable();
        loadGroups();
    }

    public void buildTable() {
        cellTable.setEmptyTableWidget(new Label("Please add data."));
        dataProvider.addDataDisplay(cellTable);
        com.google.gwt.user.cellview.client.Column<UserDTO, String> nameColumn = new com.google.gwt.user.cellview.client.Column<UserDTO, String>(new TextInputCell()) {
            @Override
            public String getValue(UserDTO object) {
                return object.getName() == null ? "" : object.getName();
            }
        };
        nameColumn.setFieldUpdater(new FieldUpdater<UserDTO, String>() {
            @Override
            public void update(int index, UserDTO object, String value) {
                Window.alert(value);
                dataProvider.getList().get(index).setName(value);
                dataProvider.flush();
                dataProvider.refresh();
            }
        });
        cellTable.addColumn(nameColumn, "Название");

        cellTable.addColumn(new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO contact) {
                return String.valueOf(contact.getName());
            }
        }, "Текст");

        com.google.gwt.user.cellview.client.Column<UserDTO, String> buttonDelCol = new com.google.gwt.user.cellview.client.Column<UserDTO, String>(new ButtonCell(IconType.REMOVE, ButtonType.DANGER)) {
            @Override
            public String getValue(UserDTO object) {
                return "";
            }
        };

        buttonDelCol.setFieldUpdater(new FieldUpdater<UserDTO, String>() {
            @Override
            public void update(int index, UserDTO object, String value) {
                dataProvider.getList().remove(object);
                dataProvider.flush();
                dataProvider.refresh();
            }
        });
        cellTable.addColumn(buttonDelCol);

        com.google.gwt.user.cellview.client.Column<UserDTO, String> buttonSaveCol = new com.google.gwt.user.cellview.client.Column<UserDTO, String>(new ButtonCell(IconType.SAVE, ButtonType.INFO)) {
            @Override
            public String getValue(UserDTO object) {
                return "";
            }
        };

        cellTable.addColumn(buttonSaveCol);

        buttonSaveCol.setFieldUpdater(new FieldUpdater<UserDTO, String>() {
            @Override
            public void update(int index, UserDTO taskTemplateDTO, String value) {
                taskTemplateDTO.setName(textArea.getText());
            }
        });

        final SingleSelectionModel<UserDTO> selectionModel = new SingleSelectionModel<UserDTO>();
        cellTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        UserDTO selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            String text = selected.getName();
                            textArea.setText(text);
                        }
                    }
                });


    }

    private void loadGroups() {
        final AsyncCallback<ArrayList<GroupAndUsersDTO>> groupCallback = new AsyncCallback<ArrayList<GroupAndUsersDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                sprintsProgress.setVisible(false);
                groupsListBox.setVisible(true);
                Window.alert("Error load groups");
            }

            @Override
            public void onSuccess(ArrayList<GroupAndUsersDTO> groupAndUsersDTOs) {
                sprintsProgress.setVisible(false);
                groupsListBox.setVisible(true);

                //Window.alert(groupAndUsersDTOs.toString());
                groupsListBox.setAcceptableValues(groupAndUsersDTOs);
                //Window.alert(groupAndUsersDTOs.toString());
                loadedGroups = groupAndUsersDTOs;
                Iterator<GroupAndUsersDTO> itr = groupAndUsersDTOs.iterator();
                /*
                if (itr.hasNext()) {
                    GroupAndUsersDTO groupAndUsersDTO = itr.next();
                    groupsListBox.setValue(groupAndUsersDTO);
                    currentGroup = groupAndUsersDTO;

                }
                */
                //addSprintsToTable(groupsListBox.getValue().getTasks());
            }
        };

        adminService.getGroupsAndUsers(groupCallback);

    }

    private void addSprintsToTable(HashSet<UserDTO> tasks) {


        // Connect the table to the data provider.
//        Window.alert("remove old tasks " + dataProvider.getList());
        final List<UserDTO> list = dataProvider.getList();
//        Window.alert("tasks to delete " + list);
//        Window.alert("iterator " + list.iterator());
        for (Iterator<UserDTO> itr = list.iterator(); itr.hasNext(); ) {
            UserDTO next = itr.next();
//            Window.alert("removable element " + next);
            itr.remove();
            dataProvider.flush();
//            dataProvider.refresh();
        }
//        dataProvider.setList(tasks);
        for (UserDTO taskTemplateDTO : tasks) {
            list.add(taskTemplateDTO);
        }
//        Window.alert("added new tasks: " + tasks);
        dataProvider.flush();
//        dataProvider.refresh();
//        cellTable.flush();
    }

//    private void addTasksToSprintNavList(List<TaskTemplateDTO> tasks) {
//        if (tasks != null) {
//            for (TaskTemplateDTO userDTO : tasks) {
//                NavLink userNL = new NavLink(userDTO.getName());
////                userNL.addClickHandler(handler);
//                sprintTasks.add(userNL);
//            }
//        }
//    }

    @UiHandler("groupsListBox")
    public void onChangeSprintPosition(ValueChangeEvent<GroupAndUsersDTO> groupEvent) {
//        clearSprintTasksList();
        try {
            //Window.alert("loadedGroups " + loadedGroups.toString());
            GroupAndUsersDTO group = groupEvent.getValue();
            //Window.alert("Selected group: " + group.toString());
//        addTasksToSprintNavList(sprintEvent.getValue().getTasks());

            List<UserDTO> currentGroupUsers = dataProvider.getList();
            if (currentGroup != null) {
                currentGroup.setUsers(new HashSet<UserDTO>(currentGroupUsers));
//                Window.alert("CabinetMain from table: " + Arrays.toString(currentTasks.toArray()));
            }
            currentGroup = group;
            //Window.alert("New users: " + Arrays.toString(group.getUsers().toArray()));
            addSprintsToTable(group.getUsers());
        } catch (Exception e) {
            Window.alert(e.getMessage() + e.getCause().toString());
        }
    }

    @UiHandler("createStudentBtn")
    public void createTaskHandler(ClickEvent e) {
//        String sprintName = INITIAL_SPRINT_NAME;
//        sprintNameTextBox.setText(sprintName);
        UserDTO task = new UserDTO();

        List<UserDTO> taskTemplateDTOs = dataProvider.getList();
        if (taskTemplateDTOs.contains(task)) {
            Window.alert("Sprint with name is already exists: " + task.getName());
        } else {
            taskTemplateDTOs.add(task);
            dataProvider.flush();
        }

//        dataProvider.refresh();
    }

    @UiHandler("saveGroupBtn")
    public void saveSprintsHandler(ClickEvent e) {
/*
        ArrayList<NavLink> links = getActiveElementsFromNavList(groupTasks, false);
        ArrayList<String> taskNames = new ArrayList<String>();
        for (NavLink link : links) {
            taskNames.add(link.getText());
        }
*/
        final AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("!!! Error save tasks" + throwable);
            }

            @Override
            public void onSuccess(Void aVoid) {
                loadGroups();
                Window.alert("Group saved successfully");
            }
        };
//        GroupAndUsersDTO GroupAndUsersDTO = groupsListBox.getValue();
//        SprintDTO sprintDTO = currentGroup;
        groupsListBox.getValue().getUsers().addAll(dataProvider.getList());
        //currentGroup.setTasks(dataProvider.getList());
//        ArrayList<SprintDTO> newSprints = new ArrayList<SprintDTO>();
//        newSprints.add(currentGroup);
        relocateTasks(loadedGroups);
        //Window.alert("Sprints to save: " + loadedGroups);
        adminService.saveGroups(loadedGroups, callback);
    }

    @UiHandler("refreshGroupsBtn")
    public void refreshSprintsHandler(ClickEvent e){
        loadGroups();
    }

    @UiHandler("delGroupBtn")
    public void deleteCurrentGroupHandler(ClickEvent e){
        deleteCurrentGroup();
    }

    private void deleteCurrentGroup() {
        final AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("Error delete tasks" + throwable);
            }

            @Override
            public void onSuccess(Void aVoid) {
                loadGroups();
                Window.alert("Group deleted successfully");
            }
        };

        adminService.deleteGroup(currentGroup.getId(), callback);
    }

    private void relocateTasks(List<GroupAndUsersDTO> loadedSprints) {
        for (GroupAndUsersDTO loadedSprint : loadedSprints) {
            HashSet<UserDTO> newTasks = new HashSet<UserDTO>(loadedSprint.getUsers().size());
            for (UserDTO userDTO : loadedSprint.getUsers()) {
                newTasks.add(userDTO);
            }
            loadedSprint.setUsers(newTasks);
        }
    }
}
