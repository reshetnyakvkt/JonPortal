package ua.com.jon.admin.client.components;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ButtonCell;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.ProgressBar;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
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
import ua.com.jon.admin.shared.AdminNotificationEvent;
import ua.com.jon.admin.shared.AdminNotificationEventHandler;
import ua.com.jon.admin.shared.GroupAndUsersDTO;
import ua.com.jon.admin.shared.SpaceDTO;
import ua.com.jon.admin.shared.UserDTO;

import java.util.ArrayList;
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
    Button saveStudentBtn = new Button();

    @UiField
    ProgressBar sprintsProgress;

    @UiField
    ProgressBar groupsProgress;

    @UiField
    CellTable<UserDTO> cellTable = new CellTable<UserDTO>(5, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));

    @UiField
    TextArea textArea = new TextArea();

    @UiField
    Modal addUserModal = new Modal();

    private GroupAndUsersDTO currentGroup;
    private ArrayList<GroupAndUsersDTO> loadedGroups = new ArrayList<GroupAndUsersDTO>();

    private ListDataProvider<UserDTO> dataProvider = new ListDataProvider<UserDTO>();

    private AdminServiceAsync adminService = GWT.create(AdminService.class);

    private GlobalData globalData;

    private SelectionCell cell;

    final SingleSelectionModel<UserDTO> selectionModel = new SingleSelectionModel<UserDTO>();

    // final SingleSelectionModel<SpaceDTO> spaceSelectionModel = new SingleSelectionModel<SpaceDTO>();

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

    @UiField(provided = true)
    ValueListBox<UserDTO> studentsDropdown = new ValueListBox<UserDTO>(new AbstractRenderer<UserDTO>() {
        @Override
        public String render(UserDTO userDTO) {
            if (userDTO == null) {
                return "";
            } else {
                return userDTO.getName();
            }
        }
    });

    @UiField(provided = true)
    ValueListBox<SpaceDTO> spacesDropdown = new ValueListBox<SpaceDTO>(new AbstractRenderer<SpaceDTO>() {
        @Override
        public String render(SpaceDTO spaceDTO) {
            if (spaceDTO == null) {
                return "";
            } else {
                return spaceDTO.getName();
            }
        }
    });

    public GroupsManageTabPanel(final UiBinder<Widget, GroupsManageTabPanel> binder, GlobalData globalData) {
        this.globalData = globalData;
        initWidget(binder.createAndBindUi(this));
        RootPanel.ADMIN_EVENT_BUS.addHandler(AdminNotificationEvent.TYPE, new AdminNotificationEventHandler() {
            @Override
            public void onNotificationChanged(AdminNotificationEvent authenticationEvent) {
                //buildTable();
                loadGroups();
                showAndInitDataComponents();
            }
        });
    }

    public void showAndInitDataComponents() {
        groupsProgress.setVisible(false);
        spacesDropdown.setVisible(true);
        studentsDropdown.setVisible(true);
        spacesDropdown.setAcceptableValues(globalData.getSpacesDtos());
    }

    public void buildTable() {
        cellTable.setEmptyTableWidget(new Label("Please add data."));
        dataProvider.addDataDisplay(cellTable);

        com.google.gwt.user.cellview.client.Column<UserDTO, String> nameColumn =
                new com.google.gwt.user.cellview.client.Column<UserDTO, String>(new TextInputCell()) {
                    @Override
                    public String getValue(UserDTO object) {
                        return object.getName() == null ? "" : object.getName();
                    }

                };
        nameColumn.setFieldUpdater(new FieldUpdater<UserDTO, String>() {
            @Override
            public void update(int index, UserDTO object, String value) {
                dataProvider.getList().get(index).setName(value);
                dataProvider.flush();
                dataProvider.refresh();
            }
        });

        createStudentDropdown();

        /*
        cellTable.addColumn(nameColumn, "Название");
        */
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
                groupsListBox.getValue().getUsers().remove(object);
                dataProvider.flush();
                dataProvider.refresh();
                // TODO call async del
                deleteUser(object.getId());
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

        // final SingleSelectionModel<UserDTO> selectionModel = new SingleSelectionModel<UserDTO>();
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

    @UiHandler("spacesDropdown")
    public void initStudentsDropDown(ValueChangeEvent<SpaceDTO> event) {
        SpaceDTO spaceDTO = spacesDropdown.getValue();
        studentsDropdown.setAcceptableValues(spaceDTO.getUsers());
        studentsDropdown.setEnabled(true);
    }

    private void deleteUser(Long userId) {
        final AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("Error delete user " + throwable);
            }

            @Override
            public void onSuccess(Void aVoid) {
//                loadGroups();
                Window.alert("User deleted successfully");
            }
        };

        adminService.deleteUserFromGroup(currentGroup.getId(), userId, callback);
    }

    @UiHandler("studentsDropdown")
    public void studentsDropDownHandler(ValueChangeEvent<UserDTO> event) {
        Window.alert("value: " + event.getValue() + ";");
        saveStudentBtn.setEnabled(true);
    }

    private void createStudentDropdown() {
        Window.alert("spacesDtos: " + globalData.getSpacesDtos());
        List<String> names = getStudentNamesFromSpaces(globalData.getSpacesDtos());
        Window.alert("names: " + names);
        cell = new SelectionCell(names) {

            @Override
            public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {

                super.onBrowserEvent(context, parent, value, event, valueUpdater);

                if (BrowserEvents.CHANGE.equals(event.getType())) {
                    SelectElement select = parent.getFirstChild().cast();
                    String newValue = select.getValue();
                    Window.alert("newValue: " + newValue.toString());
                    UserDTO selected = selectionModel.getSelectedObject();
                    int selectedIndex = dataProvider.getList().indexOf(selected);
                    if (selectedIndex >= 0) {
                        selected = dataProvider.getList().get(selectedIndex);
                    } else {
                        Window.alert("Добавлен новый объект " + selected.toString());
                        dataProvider.getList().add(selected);
                    }
                    if (selected != null) {
                        selected.setName(newValue);
                        Window.alert("selected: " + selected);
                        //textArea.setText(text);
                    } else {
                        Window.alert("Выберите студента из списка");
                    }
                }
            }
        };


        Column<UserDTO, String> statusCol = new Column<UserDTO, String>(cell) {

            @Override
            public String getValue(UserDTO userDTO) {
                return userDTO.getName();
            }
        };
        cellTable.addColumn(statusCol, "Имя");


    }

    private List<String> getStudentNamesFromSpaces(List<SpaceDTO> spaceDTOs) {
        String currentGroupName = groupsListBox.getValue().getName();
        ArrayList<String> studentNames = new ArrayList<String>();
        SpaceDTO currentSpace = null;
        Window.alert("currentGroupName: " + currentGroupName);
        for (SpaceDTO spaceDTO : spaceDTOs) {
            String name = spaceDTO.getName();

            if (name.equals(currentGroupName)) {
                Window.alert("name: " + name);
                currentSpace = spaceDTO;
                break;
            }
        }
        Window.alert("currentSpace: " + currentSpace);
        if (currentSpace != null) {
            for (UserDTO userDTO : currentSpace.getUsers()) {
                studentNames.add(userDTO.getName());
            }
        } else {
            Window.alert("No sprints with name: " + currentGroupName);
        }
        return studentNames;
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
                groupsListBox.setAcceptableValues(groupAndUsersDTOs);
                loadedGroups = groupAndUsersDTOs;
                Iterator<GroupAndUsersDTO> itr = groupAndUsersDTOs.iterator();

                if (itr.hasNext()) {
                    GroupAndUsersDTO groupAndUsersDTO = itr.next();
                    Window.alert("Users for adding to table: " + groupAndUsersDTO.getUsers());
                    addSprintsToTable(groupAndUsersDTO.getUsers());
                }
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

    @UiHandler("studentsDropdown")
    public void onUserSelected(ValueChangeEvent<UserDTO> event) {

    }

    @UiHandler("groupsListBox")
    public void onChangeGroupPosition(ValueChangeEvent<GroupAndUsersDTO> groupEvent) {
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
            buildTable();
            loadGroups();
            addSprintsToTable(group.getUsers());
        } catch (Exception e) {
            Window.alert(e.getMessage() + e.getCause().toString());
        }
    }

    @UiHandler("createStudentBtn")
    public void createStudentHandler(ClickEvent e) {
        addUserModal.show();
    }

    @UiHandler("saveStudentBtn")
    public void saveStudentHandler(ClickEvent e) {
        final AsyncCallback<Void> callback = new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("!!! Error save student" + throwable);
            }

            @Override
            public void onSuccess(Void aVoid) {

            }
        };

        adminService.addStudentToGroup(spacesDropdown.getValue().getName(), studentsDropdown.getValue().getName(), callback);
    }

    @UiHandler("saveGroupBtn")
    public void saveGroupHandler(ClickEvent e) {

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
        currentGroup = groupsListBox.getValue();
//        SprintDTO sprintDTO = currentGroup;
        currentGroup.getUsers().addAll(dataProvider.getList());
//        currentGroup.setUsers(dataProvider.getList());
//        ArrayList<SprintDTO> newSprints = new ArrayList<SprintDTO>();
//        newSprints.add(currentGroup);
//        relocateTasks(loadedGroups);
        Window.alert("Group to save: " + currentGroup);
//        adminService.saveGroups(loadedGroups, callback);
        for (UserDTO userDTO : currentGroup.getUsers()) {
            if (userDTO.getName() == null) {
                Window.alert("Не задано имя пользователя");
                return;
            }
        }
        adminService.saveGroup(currentGroup, callback);
    }

    @UiHandler("refreshGroupsBtn")
    public void refreshSprintsHandler(ClickEvent e) {
        loadGroups();
    }

    @UiHandler("delGroupBtn")
    public void deleteCurrentGroupHandler(ClickEvent e) {
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
