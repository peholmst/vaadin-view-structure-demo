package com.example.application.views.teams;

import com.example.application.data.Team;
import com.example.application.data.TeamService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.theme.lumo.LumoUtility;

class TeamSelectionPanel extends Composite<Div> implements AfterNavigationObserver {

    private final TeamService teamService;
    private final TextField searchField;
    private final Grid<Team> grid;

    TeamSelectionPanel(TeamService teamService) {
        this.teamService = teamService;
        var title = new Span("Teams");
        title.addClassNames(LumoUtility.FontSize.XLARGE, LumoUtility.FontWeight.SEMIBOLD);

        var addNewButton = new Button("Add New");
        addNewButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        var titleAndButton = new Div(title, addNewButton);
        titleAndButton.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.JustifyContent.BETWEEN,
                LumoUtility.AlignItems.CENTER
        );

        searchField = new TextField();
        searchField.setPlaceholder("Search");
        searchField.addClassNames(LumoUtility.Width.FULL);
        searchField.addValueChangeListener(event -> refreshGrid());
        searchField.setValueChangeMode(ValueChangeMode.LAZY);

        var header = new Div(titleAndButton, searchField);
        header.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Padding.MEDIUM,
                LumoUtility.Border.BOTTOM
        );

        grid = new Grid<>();
        grid.addColumn(new ComponentRenderer<>(TeamPanel::new));
        grid.addClassNames(LumoUtility.Height.FULL);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresentOrElse(TeamsView::selectTeam, TeamsView::clearSelection));

        getContent().add(header, grid);

        addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Background.CONTRAST_5,
                LumoUtility.Border.RIGHT
        );
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        refreshGrid();
    }

    private void refreshGrid() {
        grid.setItems(teamService.findTeams(searchField.getValue()));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        event.getRouteParameters()
                .get(TeamsView.TEAM_ID_PARAM)
                .flatMap(teamService::findTeamByPublicId)
                .ifPresent(grid::select);
    }
}
