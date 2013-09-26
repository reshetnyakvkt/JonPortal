package ua.com.jon.admin.client.components;

import com.github.gwtbootstrap.client.ui.*;
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ua.com.jon.admin.client.AdminService;
import ua.com.jon.admin.client.AdminServiceAsync;
import ua.com.jon.admin.shared.SprintDTO;
import ua.com.jon.admin.shared.TaskTemplateDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/4/13
 */
public class TasksManageTabPanel extends Composite {
    @UiField
    Button refreshSprintsBtn = new Button();

    final SingleSelectionModel<TaskTemplateDTO> selectionModel = new SingleSelectionModel<TaskTemplateDTO>();

    @UiField
    Button saveSprintsBtn = new Button();

    @UiField
    ProgressBar sprintsProgress;

    @UiField
    CellTable<TaskTemplateDTO> cellTable = new CellTable<TaskTemplateDTO>(5, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));

    @UiField
    TextArea textArea = new TextArea();

    private SprintDTO currentSprint;
    private List<SprintDTO> loadedSprints = new ArrayList<SprintDTO>();

    private ListDataProvider<TaskTemplateDTO> dataProvider = new ListDataProvider<TaskTemplateDTO>();

    private AdminServiceAsync adminService = GWT.create(AdminService.class);

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

    public TasksManageTabPanel(final UiBinder<Widget, TasksManageTabPanel> binder) {
        initWidget(binder.createAndBindUi(this));
        buildTable();
        loadSprints();
    }

    public void buildTable() {
        cellTable.setEmptyTableWidget(new Label("Please add data."));
        dataProvider.addDataDisplay(cellTable);

        createTaskNameColumn();
        createClassNameColumn();

        //createTestNameDropdown();

        createStatusDropdown();

        createRemoveButton();

        createSaveButton();

//        final SingleSelectionModel<TaskTemplateDTO> selectionModel = new SingleSelectionModel<TaskTemplateDTO>();
//        cellTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        TaskTemplateDTO selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            String text = selected.getText();
                            textArea.setText(text);
                        }
                    }
                });
    }

    private void createTestNameDropdown(){
        SelectionCell cell = new SelectionCell(getAvailableTaskNames()) {

            @Override
            public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {

                super.onBrowserEvent(context, parent, value, event, valueUpdater);

                if (BrowserEvents.CHANGE.equals(event.getType())) {

                    SelectElement select = parent.getFirstChild().cast();
                    String newValue = select.getValue();

                    TaskTemplateDTO dto = selectionModel.getSelectedObject();
                    if (dto == null) {
                        Window.alert("Не выбрано ни одного задания!");
                        return;
                    }
                    if (dto.getType().equals(TaskType.CLASS.name())) {
                        Window.alert("Для проверки измените статус задания на \"TEST\"");
                        return;
                    }
                    dto.setTestName(newValue);
                }
            }
        };

        Column<TaskTemplateDTO, String> statusCol = new Column<TaskTemplateDTO, String>(cell) {

            @Override
            public String getValue(TaskTemplateDTO taskTemplateDTO) {
                return taskTemplateDTO.getTestName();
            }
        };
        cellTable.addColumn(statusCol, "Имя теста");

    }

    private ArrayList<String> getAvailableTaskNames() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Str1");
        arrayList.add("Str2");
        return arrayList;
    }

    private void createStatusDropdown() {
        SelectionCell cell = new SelectionCell(getTaskTypesAsString()) {

            @Override
            public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {

                super.onBrowserEvent(context, parent, value, event, valueUpdater);

                if (BrowserEvents.CHANGE.equals(event.getType())) {

                    SelectElement select = parent.getFirstChild().cast();
                    String newValue = select.getValue();

                    TaskTemplateDTO dto = selectionModel.getSelectedObject();
                    if (dto == null) {
                        Window.alert("Не выбрано ни одного задания!");
                        return;
                    }
                    if (dto.getType().equals(TaskType.CLASS.name())) {
                        Window.alert("Для проверки измените статус задания на \"TEST\"");
                        return;
                    }
                    dto.setType(newValue);
                }
            }
        };

        Column<TaskTemplateDTO, String> statusCol = new Column<TaskTemplateDTO, String>(cell) {

            @Override
            public String getValue(TaskTemplateDTO taskTemplateDTO) {
                return taskTemplateDTO.getType();
            }
        };
        cellTable.addColumn(statusCol, "Тип");
    }

    private void createRemoveButton() {
        Column<TaskTemplateDTO, String> buttonDelCol = new Column<TaskTemplateDTO, String>(new ButtonCell(IconType.REMOVE, ButtonType.DANGER)) {
            @Override
            public String getValue(TaskTemplateDTO object) {
                return "";
            }
        };

        buttonDelCol.setFieldUpdater(new FieldUpdater<TaskTemplateDTO, String>() {
            @Override
            public void update(int index, TaskTemplateDTO object, String value) {
                dataProvider.getList().remove(object);
                dataProvider.flush();
                dataProvider.refresh();
            }
        });
        cellTable.addColumn(buttonDelCol);
    }

    private void createSaveButton() {
        Column<TaskTemplateDTO, String> buttonSaveCol = new Column<TaskTemplateDTO, String>(new ButtonCell(IconType.SAVE, ButtonType.INFO)) {
            @Override
            public String getValue(TaskTemplateDTO object) {
                return "";
            }
        };

        cellTable.addColumn(buttonSaveCol);

        buttonSaveCol.setFieldUpdater(new FieldUpdater<TaskTemplateDTO, String>() {
            @Override
            public void update(int index, TaskTemplateDTO taskTemplateDTO, String value) {
                taskTemplateDTO.setText(textArea.getText());
            }
        });
    }

    private void createTaskNameColumn() {
        Column<TaskTemplateDTO, String> nameColumn = new Column<TaskTemplateDTO, String>(new TextInputCell()) {
            @Override
            public String getValue(TaskTemplateDTO object) {
                return object.getName() == null ? "" : object.getName();
            }
        };
        nameColumn.setFieldUpdater(new FieldUpdater<TaskTemplateDTO, String>() {
            @Override
            public void update(int index, TaskTemplateDTO object, String value) {
                //Window.alert(value);
                dataProvider.getList().get(index).setName(value);
                dataProvider.flush();
                dataProvider.refresh();
            }
        });
        cellTable.addColumn(nameColumn, "Название");
    }

    private void createClassNameColumn() {
        Column<TaskTemplateDTO, String> nameColumn = new Column<TaskTemplateDTO, String>(new TextInputCell()) {
            @Override
            public String getValue(TaskTemplateDTO object) {
                return object.getClassName() == null ? "" : object.getClassName();
            }
        };
        nameColumn.setFieldUpdater(new FieldUpdater<TaskTemplateDTO, String>() {
            @Override
            public void update(int index, TaskTemplateDTO object, String value) {
                dataProvider.getList().get(index).setClassName(value);
                dataProvider.flush();
                dataProvider.refresh();
            }
        });
        cellTable.addColumn(nameColumn, "Имя класса");
    }

    private void loadSprints() {
        final AsyncCallback<ArrayList<SprintDTO>> groupCallback = new AsyncCallback<ArrayList<SprintDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                sprintsProgress.setVisible(false);
                sprintsListBox.setVisible(true);
                Window.alert("Error callback groupsListBox");
            }

            @Override
            public void onSuccess(ArrayList<SprintDTO> sprintDTOs) {
                sprintsProgress.setVisible(false);
                sprintsListBox.setVisible(true);

                //Window.alert(sprintDTOs.toString());
                sprintsListBox.setAcceptableValues(sprintDTOs);
                loadedSprints = sprintDTOs;
//                for (SpaceDTO sprintDTO : spaceDTOs) {
//                    addTasksToSprintNavList(sprintDTO.getUsers());
//                    spacesListBox.setValue(sprintDTO);
//                }
                Iterator<SprintDTO> itr = sprintDTOs.iterator();
                if (itr.hasNext()) {
                    SprintDTO sprintDTO = itr.next();
//                    Window.alert(sprintDTO.toString());
//                    addTasksToSprintNavList(spaceDTO.getTasks());
                    sprintsListBox.setValue(sprintDTO);
                    currentSprint = sprintDTO;
                }
                Window.alert(sprintsListBox.getValue().getTasks().toString());
                addSprintsToTable(sprintsListBox.getValue().getTasks(), true);
            }
        };

        adminService.getSprintsAndTasks(groupCallback);

    }

    private void addSprintsToTable(List<TaskTemplateDTO> tasks, boolean isSelectedLast) {
        final List<TaskTemplateDTO> list = dataProvider.getList();
//        Window.alert("tasks to delete " + list);
//        Window.alert("iterator " + list.iterator());
        for (Iterator<TaskTemplateDTO> itr = list.iterator(); itr.hasNext(); ) {
            TaskTemplateDTO next = itr.next();
            itr.remove();
            dataProvider.flush();
        }
        TaskTemplateDTO last = null;
        for (TaskTemplateDTO taskTemplateDTO : tasks) {
            list.add(taskTemplateDTO);
            last = taskTemplateDTO;
        }

        if (isSelectedLast && last != null) {
            selectionModel.setSelected(last, true);
        }

        dataProvider.flush();
    }

    @UiHandler("sprintsListBox")
    public void onChangeSprintPosition(ValueChangeEvent<SprintDTO> sprintEvent) {
//        clearSprintTasksList();
        try {
            SprintDTO sprint = sprintEvent.getValue();
//            Window.alert("Selected sprint: " + sprint.toString());
//        addTasksToSprintNavList(sprintEvent.getValue().getTasks());

            List<TaskTemplateDTO> currentTasks = dataProvider.getList();
            if (currentSprint != null) {
                currentSprint.setTasks(new ArrayList<TaskTemplateDTO>(currentTasks));
//                Window.alert("CabinetMain from table: " + Arrays.toString(currentTasks.toArray()));
            }
            currentSprint = sprint;
//            Window.alert("New tasks: " + Arrays.toString(sprint.getTasks().toArray()));
            addSprintsToTable(sprint.getTasks(), true);
        } catch (Exception e) {

            Window.alert(e.getMessage() + e.getCause().toString());
        }
    }

    @UiHandler("createTaskBtn")
    public void createTaskHandler(ClickEvent e) {
//        String sprintName = INITIAL_SPRINT_NAME;
//        sprintNameTextBox.setText(sprintName);
        TaskTemplateDTO task = new TaskTemplateDTO("", "");

        List<TaskTemplateDTO> taskTemplateDTOs = dataProvider.getList();
/*        if (taskTemplateDTOs.contains(task)) {
            Window.alert("Sprint with name is already exists: " + task.getName());
            return;
        }*/

        taskTemplateDTOs.add(task);
        selectionModel.setSelected(task, true);
        dataProvider.flush();
    }

    @UiHandler("saveSprintsBtn")
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
                Window.alert("!!! Error async save tasks" + throwable);
            }

            @Override
            public void onSuccess(Void aVoid) {
                loadSprints();
                Window.alert("CabinetMain posted successfully");
            }
        };
//        GroupDTO groupDTO = groupsListBox.getValue();
//        SprintDTO sprintDTO = currentSprint;
        sprintsListBox.getValue().setTasks(dataProvider.getList());
        //currentSprint.setTasks(dataProvider.getList());
//        ArrayList<SprintDTO> newSprints = new ArrayList<SprintDTO>();
//        newSprints.add(currentSprint);
        relocateTasks(loadedSprints);
        Window.alert("Sprints to save: " + loadedSprints);
        adminService.saveSprints(loadedSprints, callback);
    }

    @UiHandler("refreshSprintsBtn")
    public void refreshSprintsHandler(ClickEvent e) {
        loadSprints();
    }

    private void relocateTasks(List<SprintDTO> loadedSprints) {
        for (SprintDTO loadedSprint : loadedSprints) {
            List<TaskTemplateDTO> newTasks = new ArrayList<TaskTemplateDTO>(loadedSprint.getTasks().size());
            for (TaskTemplateDTO taskTemplateDTO : loadedSprint.getTasks()) {
                newTasks.add(taskTemplateDTO);
            }
            loadedSprint.setTasks(newTasks);
        }
    }

    private List<String> getTaskTypesAsString() {
        List<String> values = new ArrayList<String>(TaskType.values().length);
        for (TaskType taskType : TaskType.values()) {
            values.add(taskType.name());
        }
        return values;
    }

    public enum TaskType {
        SVN, CLASS
    }
}
