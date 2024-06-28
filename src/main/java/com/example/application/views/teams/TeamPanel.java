package com.example.application.views.teams;

import com.example.application.data.Team;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.theme.lumo.LumoUtility;

class TeamPanel extends Composite<Div> {

    public TeamPanel(Team team) {
        var name = new Span(team.name());
        name.addClassNames(
                LumoUtility.FontSize.MEDIUM,
                LumoUtility.FontWeight.SEMIBOLD,
                LumoUtility.Whitespace.NORMAL
        );

        var description = new Span(team.description());
        description.addClassNames(
                LumoUtility.TextColor.SECONDARY,
                LumoUtility.FontSize.XSMALL,
                LumoUtility.Whitespace.NORMAL
        );

        getContent().add(name, description);
        addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Gap.SMALL,
                LumoUtility.Padding.Vertical.SMALL
        );
    }
}
