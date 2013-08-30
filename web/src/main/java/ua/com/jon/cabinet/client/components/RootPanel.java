package ua.com.jon.cabinet.client.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/1/13
 */
public class RootPanel extends Composite {
    @UiTemplate("RootPanel.ui.xml")
    interface RootPanelUiBinder extends UiBinder<Widget, RootPanel> {
    }

    @UiTemplate("UserTasksTabPanel.ui.xml")
    interface UserTasksTabPanelUiBinder extends UiBinder<Widget, UserTasksTabPanel> {
    }

    @UiTemplate("GroupTasksTabPanel.ui.xml")
    interface GroupTasksTabPanelUiBinder extends UiBinder<Widget, GroupTasksTabPanel> {
    }

    @UiField
    com.google.gwt.user.client.ui.FlowPanel userTasksHolderPanel;

    @UiField
    com.google.gwt.user.client.ui.FlowPanel groupTasksHolderPanel;

    private static RootPanelUiBinder rootUIBinder = GWT.create(RootPanelUiBinder.class);

    private static UserTasksTabPanelUiBinder userTasksUIBinder = GWT.create(UserTasksTabPanelUiBinder.class);
    private static GroupTasksTabPanelUiBinder groupTasksUIBinder = GWT.create(GroupTasksTabPanelUiBinder.class);

    public RootPanel() {
        initWidget(rootUIBinder.createAndBindUi(this));

        UserTasksTabPanel userTasksTabPanel = new UserTasksTabPanel(userTasksUIBinder);
        GroupTasksTabPanel groupTasksTabPanel = new GroupTasksTabPanel(groupTasksUIBinder);

        userTasksHolderPanel.add(userTasksTabPanel);
        groupTasksHolderPanel.add(groupTasksTabPanel);
    }
}
