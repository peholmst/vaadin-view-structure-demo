package com.example.application.views.iteration3;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class Toolbar extends Composite<Div> {

    private final Div left;
    private final Div right;

    public Toolbar() {
        left = createToolbarSection();
        right = createToolbarSection();

        getContent().add(left, right);
        addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.ROW, LumoUtility.JustifyContent.BETWEEN);
    }

    public Toolbar withLeftComponents(Component... components) {
        left.add(components);
        return this;
    }

    public Toolbar withRightComponents(Component... components) {
        right.add(components);
        return this;
    }

    private Div createToolbarSection() {
        var section = new Div();
        section.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.ROW, LumoUtility.Gap.XSMALL);
        return section;
    }
}
