package ua.com.jon.cabinet.client.components;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.ProgressBar;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
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
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.cabinet.client.TasksServiceAsync;
import ua.com.jon.cabinet.shared.GroupDTO;
import ua.com.jon.cabinet.shared.NotificationEvent;
import ua.com.jon.cabinet.shared.NotificationEventHandler;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 19.04.14
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
public class GroupInfoTabPanel extends Composite {
    @UiField
    Button refreshGroupsBtn = new Button();

    @UiField
    ProgressBar sprintsProgress;

    @UiField
    CellTable<TaskDTO> cellTable = new CellTable<TaskDTO>(5, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));
 /*
    @UiField
    DataGrid<UserDTO> students = new DataGrid<UserDTO>();*/

    @UiField(provided=true)
    ValueListBox<GroupDTO> groupsListBox = new ValueListBox<GroupDTO>(new AbstractRenderer<GroupDTO>() {
        @Override
        public String render(GroupDTO sprintDTO) {
            if(sprintDTO == null) {
                return "";
            } else {
                return sprintDTO.getName();
            }
        }
    });

    private ListDataProvider<TaskDTO> dataProvider = new ListDataProvider<TaskDTO>();

    private TasksServiceAsync adminService = GWT.create(TasksService.class);

    public GroupInfoTabPanel(final UiBinder<Widget, GroupInfoTabPanel> binder) {
        initWidget(binder.createAndBindUi(this));
        buildTable();
        loadTasks();

        RootPanel.CABINET_EVENT_BUS.addHandler(NotificationEvent.TYPE, new NotificationEventHandler()     {
            @Override
            public void onNotificationChanged(NotificationEvent authenticationEvent) {
                loadTasks();
            }
        });
    }

    public void buildTable() {
        cellTable.setEmptyTableWidget(new Label("Please add data."));
        dataProvider.addDataDisplay(cellTable);


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

        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                return String.valueOf(taskDTO.getRate());
            }
        }, "Рейтинг");

        final SingleSelectionModel<TaskDTO> selectionModel = new SingleSelectionModel<TaskDTO>();
        cellTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        TaskDTO selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            String code = selected.getCode();
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
                //Window.alert(taskDTOs.toString());
                addSprintsToTable(taskDTOs);
            }
        };
/*        if(userPanel.getSelectedTaskTemplateId() != null) {
            adminService.getTasksByUserGroup(userPanel.getSelectedTaskTemplateId(), userPanel.getSelectedGroup().getId(),
                    userPanel.getSelectedSprint().getId(), groupCallback);
        }*/
    }

    private void addSprintsToTable(List<TaskDTO> tasks) {
//        dataProvider.addDataDisplay(cellTable);
        dataProvider.setList(tasks);
/*
        TaskDTO last = null;
        for (TaskDTO task : tasks) {
            list.add(task);
            last = task;
        }
*/
//        if(isSelectLast && last != null) {
//            selectionModel.setSelected(last, true);
//        }
    }

    @UiHandler("refreshGroupsBtn")
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
