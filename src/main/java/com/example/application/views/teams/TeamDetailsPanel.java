package com.example.application.views.teams;

import com.example.application.components.Item;
import com.example.application.components.PersonPanel;
import com.example.application.data.TeamDetails;
import com.example.application.data.TeamMember;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.theme.lumo.LumoUtility;

class TeamDetailsPanel extends Composite<Div> {

    TeamDetailsPanel(TeamDetails teamDetails) {
        var name = new H3(teamDetails.name());

        var description = new Span(teamDetails.description());
        description.addClassNames(
                LumoUtility.FontSize.SMALL,
                LumoUtility.TextColor.SECONDARY,
                LumoUtility.Whitespace.NORMAL
        );

        var edit = new Button("Edit");
        var share = new Button("Share");
        var delete = new Button("Delete");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        var nameAndDescription = new Div(name, description);
        nameAndDescription.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Gap.SMALL
        );

        var buttons = new Div(edit, share, delete);
        buttons.addClassNames(LumoUtility.Display.FLEX, LumoUtility.Gap.XSMALL);

        var header = new Div(nameAndDescription, buttons);
        header.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.JustifyContent.BETWEEN,
                LumoUtility.AlignItems.START,
                LumoUtility.Padding.MEDIUM,
                LumoUtility.Border.BOTTOM
        );

        var sidebar = new Div();
        sidebar.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Border.RIGHT,
                LumoUtility.MinHeight.NONE,
                LumoUtility.Overflow.AUTO
        );
        sidebar.getStyle().setWidth("380px");

        var summary = new Div();
        summary.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Padding.MEDIUM,
                LumoUtility.Border.BOTTOM,
                LumoUtility.Gap.SMALL
        );
        summary.add(new H4("Summary"));
        summary.add(new Item(VaadinIcon.GROUP, "%d people".formatted(teamDetails.members().size())));
        summary.add(new Item(VaadinIcon.FACTORY, "Random info"));
        summary.add(new Item(VaadinIcon.CLOCK, "Random info"));

        var teamManagers = new Div();
        teamManagers.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Padding.MEDIUM,
                LumoUtility.Gap.SMALL,
                LumoUtility.Flex.GROW
        );
        teamManagers.add(new H4("Team Managers"));
        teamDetails.members().stream().filter(TeamMember::manager).forEach(member -> {
            var panel = new PersonPanel(member.employee().firstName() + " " + member.employee().lastName(), member.employee().role());
            teamManagers.add(panel);
        });

        sidebar.add(summary, teamManagers);

        var tabsArea = new Div();
        tabsArea.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Flex.GROW
        );

        var tabs = new Tabs(new Tab("Employees"), new Tab("Salaries"), new Tab("Documents"));
        tabsArea.add(tabs);

        var sidebarAndTabsArea = new Div(sidebar, tabsArea);
        sidebarAndTabsArea.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.Flex.GROW,
                LumoUtility.MinHeight.NONE
        );

        getContent().add(header, sidebarAndTabsArea);
        addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Height.FULL
        );
    }
}
