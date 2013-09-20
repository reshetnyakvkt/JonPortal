package ua.com.jon.cabinet.client.components;

import com.github.gwtbootstrap.client.ui.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
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
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.cabinet.client.TasksServiceAsync;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 8/12/13
 */
public class GroupTasksTabPanel extends Composite {
    @UiField
    Button refreshTasksBtn = new Button();

    @UiField
    ProgressBar sprintsProgress;

    @UiField
    CellTable<TaskDTO> cellTable = new CellTable<TaskDTO>(5, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));

    @UiField
    TextArea textArea = new TextArea();

    private SprintDTO currentSprint;
    private List<SprintDTO> loadedSprints = new ArrayList<SprintDTO>();

    private ListDataProvider<TaskDTO> dataProvider = new ListDataProvider<TaskDTO>();

    private TasksServiceAsync adminService = GWT.create(TasksService.class);

    public GroupTasksTabPanel(final UiBinder<Widget, GroupTasksTabPanel> binder) {
        initWidget(binder.createAndBindUi(this));
        buildTable();
        loadTasks();
    }

    public void buildTable() {
        cellTable.setEmptyTableWidget(new Label("Please add data."));
        //dataProvider.addDataDisplay(cellTable);
/*        com.google.gwt.user.cellview.client.Column<TaskDTO, String> nameColumn = new com.google.gwt.user.cellview.client.Column<TaskDTO, String>(new TextInputCell()) {
            @Override
            public String getValue(TaskDTO object) {
                return object.getName() == null ? "" : object.getName();
            }
        };*/

        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                return String.valueOf(taskDTO.getName());
            }
        }, "Название");

        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                return String.valueOf(taskDTO.getUserName());
            }
        }, "Студент");

        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                String result = taskDTO.getResult();
                int newLinePos = result.indexOf('\n');
                return String.valueOf(taskDTO.getResult().substring(0, newLinePos));
            }
        }, "Оценка");

        final SingleSelectionModel<TaskDTO> selectionModel = new SingleSelectionModel<TaskDTO>();
        cellTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        TaskDTO selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            String code = selected.getCode();
                            textArea.setText(code);
                        }
                    }
                });
    }

    private void loadTasks() {
        final AsyncCallback<ArrayList<TaskDTO>> groupCallback = new AsyncCallback<ArrayList<TaskDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                sprintsProgress.setVisible(false);
                Window.alert("Error callback groupsListBox");
            }

            @Override
            public void onSuccess(ArrayList<TaskDTO> taskDTOs) {
                sprintsProgress.setVisible(false);
                addSprintsToTable(taskDTOs);
                //Window.alert(taskDTOs.toString());
            }
        };

        adminService.getTasksByUserGroup(groupCallback);

    }

    private void addSprintsToTable(List<TaskDTO> tasks) {
        dataProvider.addDataDisplay(cellTable);
        final List<TaskDTO> list = dataProvider.getList();
        TaskDTO last = null;
        for (TaskDTO task : tasks) {
            list.add(task);
            last = task;
        }
//        if(isSelectLast && last != null) {
//            selectionModel.setSelected(last, true);
//        }
    }

    @UiHandler("refreshTasksBtn")
    public void refreshSprintsHandler(ClickEvent e) {
        loadTasks();
    }

    private void relocateTasks(List<SprintDTO> loadedSprints) {
        for (SprintDTO loadedSprint : loadedSprints) {
            List<TaskDTO> newTasks = new ArrayList<TaskDTO>(loadedSprint.getTasks().size());
            for (TaskDTO taskTemplateDTO : loadedSprint.getTasks()) {
                newTasks.add(taskTemplateDTO);
            }
            loadedSprint.setTasks(newTasks);
        }
    }
}
