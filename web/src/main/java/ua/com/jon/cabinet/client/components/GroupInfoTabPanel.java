package ua.com.jon.cabinet.client.components;

import com.github.gwtbootstrap.client.ui.*;
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
import com.google.gwt.view.client.SingleSelectionModel;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.cabinet.client.TasksServiceAsync;
import ua.com.jon.cabinet.shared.GroupDTO;
import ua.com.jon.cabinet.shared.GroupEvent;
import ua.com.jon.cabinet.shared.GroupEventHandler;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;
import ua.com.jon.cabinet.shared.UserDTO;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 19.04.14
 */
public class GroupInfoTabPanel extends Composite {
    @UiField
    Button refreshGroupsBtn = new Button();

    @UiField
    ProgressBar sprintsProgress;

    @UiField
    Strong groupRate;

    final SingleSelectionModel<List<String>> selectionModel = new SingleSelectionModel<List<String>>();

    @UiField
    CellTable<List<String>> studentsGrid = new CellTable<List<String>>(5, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));

    /*@UiField
    DataGrid<List<String>> studentsGrid = new DataGrid<List<String>>(20, GWT.<DataGrid.SelectableResources>create(DataGrid.SelectableResources.class));
    */

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

    private ListDataProvider<List<String>> dataProvider = new ListDataProvider<List<String>>();

    private TasksServiceAsync taskService = GWT.create(TasksService.class);

    private UserTasksTabPanel userPanel;

    public GroupInfoTabPanel(final UiBinder<Widget, GroupInfoTabPanel> binder, UserTasksTabPanel userPanel) {
        this.userPanel = userPanel;
        initWidget(binder.createAndBindUi(this));
        studentsGrid.setEmptyTableWidget(new Label("Please add data."));
        RootPanel.CABINET_EVENT_BUS.addHandler(GroupEvent.TYPE, new GroupEventHandler()     {
            @Override
            public void onGroupChanged(GroupEvent groupEvent) {
                List<List<String>> sprintNames = null;
                try {
                    load();
                } catch (Exception e) {
                    Window.alert(e.getMessage() + e.getCause().getMessage());
                    e.printStackTrace();
                }
                //buildTable(sprintNames);
                //addSprintsToTable(sprintNames);
            }
        });
    }

    public void buildTable(List<List<String>> sprints) {
//        Window.alert(sprints.toString());
        final int userNameIdx = 0;
        final int globalRateIdx = 1;
        studentsGrid.setEmptyTableWidget(new Label("Please add data."));
        /*dataProvider.addDataDisplay(studentsGrid);*/

        studentsGrid.addColumn(new TextColumn<List<String>>() {
            @Override
            public String getValue(List<String> userList) {
                return String.valueOf(userList.get(userNameIdx));
            }
        }, "Студент");


        studentsGrid.addColumn(new TextColumn<List<String>>() {
            @Override
            public String getValue(List<String> userList) {
                return String.valueOf(userList.get(globalRateIdx));
            }
        }, "Общий рейтинг");

        //int i = 0;
        for (int i=2; i<sprints.get(0).size(); i++) {
            //final String session = sprints.get(i).get(888);
            final int sprintIdx = i;
            studentsGrid.addColumn(new TextColumn<List<String>>() {
                //private int columnIdx = sprintIdx;
                @Override
                public String getValue(List<String> userList) {
                    //Window.alert(userList.toString());
                    return userList.get(sprintIdx);
                }
            }, String.valueOf(sprintIdx - 1));
        }

        //final SingleSelectionModel<List<String>> selectionModel = new SingleSelectionModel<List<String>>();
        //studentsGrid.setSelectionModel(selectionModel);
        /*selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        List<String> selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            String login = selected.get(userNameIdx);
                            Window.alert("Selected " + login);
                        }
                    }
                });*/

        dataProvider.addDataDisplay(studentsGrid);
        //Window.alert("buildTable finished");
    }

    private void load() throws Exception {

        final AsyncCallback<List<List<String>>> groupCallback = new AsyncCallback<List<List<String>>>() {
            private static final int RATE_INDEX = 1;
            @Override
            public void onFailure(Throwable caught) {
                sprintsProgress.setVisible(false);
                Window.alert("Error callback groupsListBox");
            }

            @Override
            public void onSuccess(List<List<String>> groupInfo) {
                sprintsProgress.setVisible(false);
                /*for (List<String> taskDTO : taskDTOs) {
                    Window.alert(taskDTO.toString());
                }*/
                /*
                List<List<String>> sprintNames = new ArrayList<List<String>>();*/
                // TODO fill
                /*if(taskDTOs != null && taskDTOs.size() > 0) {
                    Set<String> sprints = taskDTOs.get(0).getMarks().keySet();
                    for (String sprintName : sprints) {
                        sprintNames.add(Arrays.asList(sprintName));
                    }
                    addSprintsToTable(sprintNames);
                    //buildTable(sprints);
                }
                buildTable(sprintNames);*/
                int grpRate = 0;
                for (List<String> user : groupInfo) {
                    grpRate += Integer.parseInt(user.get(RATE_INDEX));
                }
                grpRate /= groupInfo.size();
                groupRate.setText(String.valueOf(grpRate));
                Collections.sort(groupInfo, new Comparator<List<String>>() {

                    @Override
                    public int compare(List<String> o1, List<String> o2) {
                        int firstRate = Integer.parseInt(o1.get(RATE_INDEX));
                        int secondRate = Integer.parseInt(o2.get(RATE_INDEX));
                        return firstRate - secondRate;
                    }
                });
                addSprintsToTable(groupInfo);
                buildTable(groupInfo);
            }
        };

/*        HashMap<String, Integer> marks = new HashMap<String, Integer>();
        marks.put("1", 50);
        marks.put("2", 75);
        marks.put("3", 100);
        HashMap<String, Boolean> presents = new HashMap<String, Boolean>();
        presents.put("1", true);
        presents.put("2", false);
        presents.put("3", true);
        UserDTO user1 = new UserDTO("user1", marks, presents);
        List<UserDTO> taskDTOs = new ArrayList<UserDTO>();
        taskDTOs.add(user1);

        List<List<String>> users = new ArrayList<List<String>>();
        for(UserDTO userDTO : taskDTOs) {
            List<String> user = new ArrayList<String>();
            user.add(userDTO.getLogin());
            user.add("99");
            for(String session : userDTO.getMarks().keySet()) {
                user.add(String.valueOf(userDTO.getMarks().get(session)));
            }
            users.add(user);
        }
*/
/*        if(userPanel.getSelectedTaskTemplateId() != null) {
            taskService.getTasksByUserGroup(userPanel.getSelectedTaskTemplateId(), userPanel.getSelectedGroup().getId(),
                    userPanel.getSelectedSprint().getId(), groupCallback);
        }*/

/*        if(taskDTOs != null && taskDTOs.size() > 0) {
            Set<String> sprints = taskDTOs.get(0).getMarks().keySet();
            Window.alert(sprints.toString());
            for (String sprintName : sprints) {
                sprintNames.add(Arrays.asList(sprintName));
            }
            Window.alert(sprintNames.toString());
        }*/
        taskService.getGroupInfo(userPanel.getSelectedGroup().getId(), groupCallback);
//        return null;
    }

    private void loadGroupAndUsers() {
        final AsyncCallback<List<List<String>>> groupCallback = new AsyncCallback<List<List<String>>>() {
            private static final int RATE_INDEX = 1;
            @Override
            public void onFailure(Throwable caught) {
                sprintsProgress.setVisible(false);
                Window.alert("Error callback groupsListBox");
            }

            @Override
            public void onSuccess(List<List<String>> groupInfo) {
                sprintsProgress.setVisible(false);
                int grpRate = 0;
                for (List<String> user : groupInfo) {
                    grpRate += Integer.parseInt(user.get(RATE_INDEX));
                }
                grpRate /= groupInfo.size();
                groupRate.setText(String.valueOf(grpRate));
                Collections.sort(groupInfo, new Comparator<List<String>>() {

                    @Override
                    public int compare(List<String> o1, List<String> o2) {
                        int firstRate = Integer.parseInt(o1.get(RATE_INDEX));
                        int secondRate = Integer.parseInt(o2.get(RATE_INDEX));
                        return firstRate - secondRate;
                    }
                });
                addSprintsToTable(groupInfo);
//                buildTable(groupInfo);
            }
        };

/*
        final AsyncCallback<ArrayList<UserDTO>> groupCallback = new AsyncCallback<ArrayList<UserDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                sprintsProgress.setVisible(false);
                Window.alert("Error callback loadGroupAndUsers");
            }

            @Override
            public void onSuccess(ArrayList<UserDTO> usersDTOs) {

                sprintsProgress.setVisible(false);
                for (UserDTO userDTO : usersDTOs) {
                    Window.alert(userDTO.toString());
                }
                List<List<String>> sprintNames = new ArrayList<List<String>>();
                // TODO fill
                if(usersDTOs != null && usersDTOs.size() > 0) {
                    Set<String> sprints = usersDTOs.get(0).getMarks().keySet();

                    for (String sprintName : sprints) {
                        sprintNames.add(Arrays.asList(sprintName));
                    }
                    addSprintsToTable(sprintNames);
                    //buildTable(sprints);
                }
            }
        };
*/
/*        if(userPanel.getSelectedTaskTemplateId() != null) {
            taskService.getTasksByUserGroup(userPanel.getSelectedTaskTemplateId(), userPanel.getSelectedGroup().getId(),
                    userPanel.getSelectedSprint().getId(), groupCallback);
        }*/
/*
        HashMap<String, Integer> marks = new HashMap<String, Integer>();
        marks.put("1", 50);
        marks.put("2", 75);
        marks.put("3", 100);
        HashMap<String, Boolean> presents = new HashMap<String, Boolean>();
        presents.put("1", true);
        presents.put("2", false);
        presents.put("3", true);
        UserDTO user1 = new UserDTO("user1", marks, presents);
        ArrayList<UserDTO> usersDTOs = new ArrayList<UserDTO>();
        usersDTOs.add(user1);
        groupCallback.onSuccess(usersDTOs);
*/
    }

    private void addSprintsToTable(List<List<String>> tasks) {
        dataProvider.setList(tasks);
        //studentsGrid.setSelectionModel(selectionModel);
        //dataProvider.addDataDisplay(studentsGrid);
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
        loadGroupAndUsers();
/*
        try {
            load();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
*/
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
