package com.example.application.views.teams;

import com.example.application.data.TeamDetails;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.theme.lumo.LumoUtility;

class TeamDetailsPanel extends Composite<Div> {

    TeamDetailsPanel(TeamDetails teamDetails) {
        var name = new Span(teamDetails.name());
        name.addClassNames(
                LumoUtility.FontSize.XLARGE,
                LumoUtility.FontWeight.SEMIBOLD,
                LumoUtility.Whitespace.NORMAL
        );

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
                LumoUtility.Border.RIGHT
        );
        sidebar.getStyle().setWidth("380px");

        var tabsArea = new Div();
        tabsArea.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Flex.GROW,
                LumoUtility.Height.FULL
        );

        var tabs = new Tabs(new Tab("Employees"), new Tab("Salaries"), new Tab("Documents"));
        tabsArea.add(tabs);

        var sidebarAndTabsArea = new Div(sidebar, tabsArea);
        sidebarAndTabsArea.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.Width.FULL,
                LumoUtility.Height.FULL
        );

        getContent().add(header, sidebarAndTabsArea);
        addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Height.FULL
        );
    }
}
