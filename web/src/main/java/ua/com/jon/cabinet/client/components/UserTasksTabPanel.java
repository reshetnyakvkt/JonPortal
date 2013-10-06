package ua.com.jon.cabinet.client.components;

import com.github.gwtbootstrap.client.ui.ButtonCell;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
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
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.cabinet.client.TasksServiceAsync;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class UserTasksTabPanel extends Composite {
    @UiField
    CellTable<TaskDTO> cellTable = new CellTable<TaskDTO>(5, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));//new CellTable<TaskDTO>();

    final SingleSelectionModel<TaskDTO> selectionModel = new SingleSelectionModel<TaskDTO>();

    private Long selectedTaskTemplateId;

    @UiField
    TextArea result;

    @UiField
    TextArea taskText;

    @UiField
    TextArea code;

    @UiField(provided=true)
    ValueListBox<SprintDTO> sprintsListBox = new ValueListBox<SprintDTO>(new AbstractRenderer<SprintDTO>() {
        @Override
        public String render(SprintDTO sprintDTO) {
            if(sprintDTO == null) {
                return "";
            } else {
                return sprintDTO.getName();
            }
        }
    });

    Column<TaskDTO, String> statusCol;

    ListDataProvider<TaskDTO> dataProvider = new ListDataProvider<TaskDTO>();

    interface ExampleUiBinderUiBinder extends UiBinder<HTMLPanel, UserTasksTabPanel> {
    }

    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);
    private TasksServiceAsync tasksService = GWT.create(TasksService.class);

    public UserTasksTabPanel(final UiBinder<Widget, UserTasksTabPanel> binder) {
        initWidget(binder.createAndBindUi(this));
        buildTable();
        loadSprintsAndTasks();
    }

    @UiHandler("refreshTasksBtn")
    public void onClick(ClickEvent e) {
        result.setText("");
        taskText.setText("");
        loadSprintsAndTasks();
    }

    @UiHandler("sprintsListBox")
    public void onChangeTabPosition(ValueChangeEvent<SprintDTO> sprint) {
        result.setText("");
        taskText.setText("");
        addTasksToTable(sprint.getValue().getTasks(), true);
    }

    private void loadSprintsAndTasks() {
        final AsyncCallback<ArrayList<SprintDTO>> callback = new AsyncCallback<ArrayList<SprintDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка загрузки этапов в сервера");
            }

            @Override
            public void onSuccess(ArrayList<SprintDTO> sprints) {

                SprintDTO currentSprint;
                sprintsListBox.setAcceptableValues(sprints);
                Iterator<SprintDTO> iterator = sprints.iterator();
                if(iterator.hasNext()) {
                    currentSprint = iterator.next();
                    addTasksToTable(currentSprint.getTasks(), true);
                    sprintsListBox.setValue(currentSprint);
                }
//                Window.alert("loadSprintsAndTasks sprints " + sprints);
            }
        };

        tasksService.getSprints(callback);
    }

    private void addTasksToTable(List<TaskDTO> tasks, boolean isSelectLast) {
        final List<TaskDTO> list = dataProvider.getList();
        list.clear();
        TaskDTO last = null;
        for (TaskDTO task : tasks) {
            list.add(task);
            last = task;
        }
        if(isSelectLast && last != null) {
            selectionModel.setSelected(last, true);
        }
    }

    public void buildTable() {
        dataProvider.addDataDisplay(cellTable);
        TextColumn<TaskDTO> nameColumn = new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO contact) {
                return contact.getName();
            }
        };
        // Make the name column sortable.
        nameColumn.setSortable(true);

        TextColumn<TaskDTO> textColumn = new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO contact) {
                if(contact.getText() != null){
                    return contact.getText().substring(0, 51);
                }
                return "";
            }
        };

        cellTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        TaskDTO selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            restructureTable(selected.getName());
                            result.setText(selected.getResult());
                            taskText.setText(selected.getText());
                            selectedTaskTemplateId = selected.getTaskTemplateId();
                        }
                    }
                });

        // Add the columns.
        cellTable.addColumn(nameColumn, "Название");
        cellTable.addColumn(textColumn, "Текст задания");
        SelectionCell cell = new SelectionCell(getAcceptableValues()) {

            @Override
            public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {

                super.onBrowserEvent(context, parent, value, event, valueUpdater);

                if (BrowserEvents.CHANGE.equals(event.getType())) {

                    SelectElement select = parent.getFirstChild().cast();
                    String newValue = select.getValue();

                    final TaskDTO dto = selectionModel.getSelectedObject();
                    if(dto == null){
                        Window.alert("Не выбрано ни одного задания!");
                        return;
                    }
                    if(!dto.getType().equals(TaskType.SVN.name())) {
                        Window.alert("Для проверки измените статус задания на \"TEST\"");
                        return;
                    }
                    dto.setStatus(newValue);

                    tasksService.taskStatusChanged(dto, new AsyncCallback<Void>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            Window.alert("Status changed with error");
                        }

                        @Override
                        public void onSuccess(Void resTaskDto) {
                            Window.alert("Status changed successfully");
                        }
                    });
                }
            }
        };

        statusCol =  new Column<TaskDTO, String>(cell) {

            @Override
            public String getValue(TaskDTO taskDto) {
                return taskDto.getStatus();
            }
        };
        cellTable.addColumn(statusCol, "Статус");

        Column<TaskDTO, String> buttonTestCol = new Column<TaskDTO, String>(new ButtonCell(ButtonType.WARNING)) {
            @Override
            public String getValue(TaskDTO object) {
                return "Проверить";
            }
        };

        buttonTestCol.setFieldUpdater(new FieldUpdater<TaskDTO, String>() {
            @Override
            public void update(int index, final TaskDTO taskDTO, String value) {
                if(!taskDTO.getType().equals(TaskType.CLASS.name())) {
                    Window.alert("Для проверки нажмите кнопку \"Проверить\"");
                    return;
                }

                taskDTO.setCode(code.getText());
                final AsyncCallback<String> callback = new AsyncCallback<String>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Error callback");
                    }

                    @Override
                    public void onSuccess(String testResult) {
                       // Window.alert(testResult.toString());
                        taskDTO.setResult(testResult);
                        result.setText(testResult);
                        //dataProvider.flush();
                        dataProvider.refresh();
                        restructureTable(null);
                    }
                };
                taskDTO.setText("");
                taskDTO.setResult("");

                tasksService.postForTest(taskDTO, callback);
            }
        });

        cellTable.addColumn(buttonTestCol);

        TextColumn<TaskDTO> resultColumn = new TextColumn<TaskDTO>() {

            @Override
            public String getValue(TaskDTO taskDto) {
                int newLineMarkIdx = taskDto.getResult().indexOf('\n');
                String resStr;
                if(newLineMarkIdx < 0){
                    resStr = taskDto.getResult();
                }else {
                    resStr = taskDto.getResult().substring(0, newLineMarkIdx);
                }
              //  Window.alert("!!! "+resStr + taskDto);
                return resStr;//taskDto.getResult().substring(0, newLineMarkIdx);
            }
        };

        cellTable.addColumn(resultColumn, "Результат");
    }

    private void restructureTable(String taskName) {
        final int TEST_COLUMN = 3;
        final int STATUS_COLUMN = 2;
        for(int i=0; i<cellTable.getRowCount(); i++){
            String nameText = cellTable.getRowElement(i).getInnerText();
            if(taskName == null || nameText.contains(taskName)){
                int columnIndex = TEST_COLUMN;
                TaskDTO taskDTO = cellTable.getVisibleItem(i);
                //Window.alert("taskDTO = "+taskDTO.toString());
                if(TaskType.CLASS.name().equals(taskDTO.getType())) {
                    columnIndex = STATUS_COLUMN;
                }
                cellTable.getRowElement(i).deleteCell(columnIndex);
                cellTable.getRowElement(i).insertCell(columnIndex);
            }
        }
    }

    private List<String> getAcceptableValues() {
        return Arrays.asList("NEW", "IN_PROGRESS", "TEST");
    }

    public enum TaskType {
        SVN, CLASS
    }

    public Long getSelectedTaskTemplateId() {
        return selectedTaskTemplateId;
    }

    public void setSelectedTaskTemplateId(Long selectedTaskTemplateId) {
        this.selectedTaskTemplateId = selectedTaskTemplateId;
    }
}

