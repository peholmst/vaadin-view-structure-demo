package com.example.application.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class PersonPanel extends Composite<Div> {

    public PersonPanel(String name, String title) { // TODO Avatar picture
        var avatar = new Avatar(name);

        var nameSpan = new Span(name);
        nameSpan.addClassNames(LumoUtility.FontSize.MEDIUM);
        var titleSpan = new Span(title);
        titleSpan.addClassNames(LumoUtility.TextColor.SECONDARY, LumoUtility.FontSize.SMALL);

        var nameAndTitle = new Div(nameSpan, titleSpan);
        nameAndTitle.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN);
        getContent().add(avatar, nameAndTitle);

        addClassNames(LumoUtility.Display.FLEX, LumoUtility.AlignItems.CENTER, LumoUtility.Gap.SMALL);
    }

}
