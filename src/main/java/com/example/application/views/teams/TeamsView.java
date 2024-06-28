package com.example.application.views.teams;

import com.example.application.data.Team;
import com.example.application.data.TeamDetails;
import com.example.application.data.TeamService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route(value = "teams/:teamId?", layout = MainLayout.class)
public class TeamsView extends Composite<Div> implements AfterNavigationObserver {

    public static final String TEAM_ID_PARAM = "teamId";

    private final TeamService teamService;
    private final TeamSelectionPanel teamSelectionPanel;
    private final Div contentContainer;

    public TeamsView(TeamService teamService) {
        this.teamService = teamService;

        this.teamSelectionPanel = new TeamSelectionPanel(teamService);
        teamSelectionPanel.getStyle().setWidth("300px");

        this.contentContainer = new Div();
        contentContainer.addClassNames(
                LumoUtility.Height.FULL,
                LumoUtility.Flex.GROW
        );

        getContent().add(teamSelectionPanel, contentContainer);
        addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.Height.FULL,
                LumoUtility.Width.FULL
        );
    }

    public static void selectTeam(Team team) {
        UI.getCurrent().navigate(TeamsView.class, new RouteParam(TEAM_ID_PARAM, team.publicId()));
    }

    public static void clearSelection() {
        UI.getCurrent().navigate(TeamsView.class);
    }

    private void showTeamDetailsPanel(TeamDetails teamDetails) {
        contentContainer.removeAll();
        contentContainer.add(new TeamDetailsPanel(teamDetails));
    }

    private void showNoTeamSelectedPanel() {
        contentContainer.removeAll();
        var noTeamSelected = new Div();
        noTeamSelected.add(new Span("Please select a team."));
        noTeamSelected.addClassNames(LumoUtility.Padding.MEDIUM);
        contentContainer.add(noTeamSelected);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        event.getRouteParameters().get(TEAM_ID_PARAM)
                .flatMap(teamService::findTeamDetailsByPublicId)
                .ifPresentOrElse(
                        this::showTeamDetailsPanel,
                        this::showNoTeamSelectedPanel
                );
    }
}
