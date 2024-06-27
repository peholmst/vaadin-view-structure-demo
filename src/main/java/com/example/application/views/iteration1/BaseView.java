package com.example.application.views.iteration1;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.theme.lumo.LumoUtility;

public abstract class BaseView extends Composite<Div> {

    private final Div topLeftSection;
    private final Div topRightSection;
    private final Div bottomLeftSection;
    private final Div bottomRightSection;
    private final Main mainContent;

    public BaseView() {
        topLeftSection = createToolbarSection();
        topRightSection = createToolbarSection();
        bottomLeftSection = createToolbarSection();
        bottomRightSection = createToolbarSection();
        mainContent = new Main();

        var topToolbar = new Div(topLeftSection, topRightSection);
        topToolbar.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.ROW, LumoUtility.JustifyContent.BETWEEN);

        var bottomToolbar = new Div(bottomLeftSection, bottomRightSection);
        bottomToolbar.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.ROW, LumoUtility.JustifyContent.BETWEEN);

        mainContent.addClassNames(LumoUtility.Flex.GROW);

        getContent().add(topToolbar, mainContent, bottomToolbar);

        addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Width.FULL,
                LumoUtility.Height.FULL,
                LumoUtility.Padding.SMALL,
                LumoUtility.BoxSizing.BORDER,
                LumoUtility.Gap.SMALL
        );
    }

    private Div createToolbarSection() {
        var section = new Div();
        section.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.ROW, LumoUtility.Gap.XSMALL);
        return section;
    }

    public void addTopLeftComponents(Component... components) {
        topLeftSection.add(components);
    }

    public void addTopRightComponents(Component... components) {
        topRightSection.add(components);
    }

    public void addBottomLeftComponents(Component... components) {
        bottomLeftSection.add(components);
    }

    public void addBottomRightComponents(Component... components) {
        bottomRightSection.add(components);
    }

    public void setMainContent(Component component) {
        mainContent.removeAll();
        if (component != null) {
            mainContent.add(component);
        }
    }
}
