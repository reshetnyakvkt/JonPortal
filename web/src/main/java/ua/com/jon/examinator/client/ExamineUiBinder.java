package ua.com.jon.examinator.client;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.SelectionCell;
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
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ua.com.jon.examinator.shared.SprintDTO;
import ua.com.jon.examinator.shared.TaskDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExamineUiBinder extends Composite {
    @UiField
    CellTable<TaskDTO> cellTable = new CellTable<TaskDTO>(5, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));//new CellTable<TaskDTO>();

    final SingleSelectionModel<TaskDTO> selectionModel = new SingleSelectionModel<TaskDTO>();
//    @UiField
//    DropdownButton sprintBtn = new DropdownButton();

    @UiField
    TextArea result;

    @UiField
    TextArea taskText;

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

    interface ExampleUiBinderUiBinder extends UiBinder<HTMLPanel, ExamineUiBinder> {
    }

    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);
    private ua.com.jon.examinator.client.ExamineServiceAsync tasksService = GWT.create(ExamineService.class);

    public ExamineUiBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
        buildTable();
        loadSprintsAndTasks();
//        selectFirstTaskIfExists();
//        loadTasksToTable();
//        alert.setVisible(false);
    }

    private void selectFirstTaskIfExists() {
        for (TaskDTO task : selectionModel.getSelectedSet()) {
            Window.alert("selected task: " + task);
            selectionModel.setSelected(task, true);
            break;
        }
    }

//    }

    //    @UiHandler("submit")
//    void handleClick(ClickEvent e) {
//        Window.alert("Hello, UiBinder");
//    }
//    @UiHandler("sprintBtn")
//    void handleClick(ClickEvent e) {
//
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
//        selectFirstTaskIfExists();
    }

    private void loadSprintsAndTasks() {
        final AsyncCallback<ArrayList<SprintDTO>> callback = new AsyncCallback<ArrayList<SprintDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error async get sprints");
            }

            @Override
            public void onSuccess(ArrayList<SprintDTO> sprints) {
//                Window.alert("loadSprintsAndTasks sprints " + sprints);

                for (SprintDTO sprint : sprints) {
                    if(sprint.isActive()) {
//                        Window.alert("sprint active " + sprint.getName());
                        addTasksToTable(sprint.getTasks(), true);
                        sprintsListBox.setValue(sprint);
                    }
                }
                sprintsListBox.setAcceptableValues(sprints);
//                selectFirstTaskIfExists();
            }
        };

        tasksService.getSprints(callback);

    }

    private void loadTasksToTable() {
        final AsyncCallback<ArrayList<TaskDTO>> callback = new AsyncCallback<ArrayList<TaskDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error callback");
            }

            @Override
            public void onSuccess(ArrayList<TaskDTO> tasks) {
                addTasksToTable(tasks, true);
            }
        };

        tasksService.getUserTasks(callback);
    }

    private void addTasksToTable(List<TaskDTO> tasks, boolean isSelectLast) {
        cellTable.setRowData(tasks);
        ListDataProvider<TaskDTO> dataProvider = new ListDataProvider<TaskDTO>();

        // Connect the table to the data provider.
        dataProvider.addDataDisplay(cellTable);
        final List<TaskDTO> list = dataProvider.getList();
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

        TextColumn<TaskDTO> nameColumn = new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO contact) {
                return contact.getName();
            }
        };
        // Make the name column sortable.
        nameColumn.setSortable(true);

        TextColumn<TaskDTO> addressColumn = new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO contact) {
                return contact.getText();
            }
        };

        ButtonCell buttonCell1 = new ButtonCell();


        cellTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        TaskDTO selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            result.setText(selected.getResult());
                            taskText.setText(selected.getText());
                        }
                    }
                });

        // Add the columns.
        cellTable.addColumn(nameColumn, "Название");
        cellTable.addColumn(addressColumn, "Текст задания");
        SelectionCell cell = new SelectionCell(getAcceptableValues()) {

            @Override
            public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {

                super.onBrowserEvent(context, parent, value, event, valueUpdater);

                if (BrowserEvents.CHANGE.equals(event.getType())) {

                    SelectElement select = parent.getFirstChild().cast();
                    String newValue = select.getValue();

                    TaskDTO dto = selectionModel.getSelectedObject();

                    if(dto == null){
                        Window.alert("Не выбрано ни одного задания!");
                        return;
                    }
                    dto.setStatus(newValue);


                    tasksService.taskStatusChanged(dto, new AsyncCallback<Void>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            Window.alert("Status changed with error");
                        }

                        @Override
                        public void onSuccess(Void aVoid) {
                            Window.alert("Status changed successfully");
                        }
                    });
                }
            }
        };

        cellTable.addColumn(new Column<TaskDTO, String>(cell) {

            @Override
            public String getValue(TaskDTO taskDto) {
                return taskDto.getStatus();
            }
        }, "Статус");

        TextColumn<TaskDTO> resultColumn = new TextColumn<TaskDTO>() {

            @Override
            public String getValue(TaskDTO taskDto) {
                int newLineMarkIdx = taskDto.getResult().indexOf('\n');
                return taskDto.getResult().substring(0, newLineMarkIdx);
            }
        };

        cellTable.addColumn(resultColumn, "Результат");
    }

    private List<String> getAcceptableValues() {
        return Arrays.asList("NEW", "IN_PROGRESS", "TEST", "RESOLVED");
    }
}

