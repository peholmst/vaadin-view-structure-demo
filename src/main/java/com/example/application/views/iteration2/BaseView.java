package com.example.application.views.iteration2;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.theme.lumo.LumoUtility;

public abstract class BaseView extends Composite<Div> {

    private final Toolbar topToolbar;
    private final Toolbar bottomToolbar;
    private final Main mainContent;

    public BaseView() {
        topToolbar = new Toolbar();
        bottomToolbar = new Toolbar();
        mainContent = new Main();

        mainContent.addClassNames(LumoUtility.Flex.GROW, LumoUtility.BoxShadow.XSMALL);

        getContent().add(topToolbar, mainContent, bottomToolbar);

        addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Width.FULL,
                LumoUtility.Height.FULL,
                LumoUtility.Padding.SMALL,
                LumoUtility.BoxSizing.BORDER,
                LumoUtility.Gap.SMALL,
                LumoUtility.Background.CONTRAST_5
        );
    }

    public void addTopLeftComponents(Component... components) {
        topToolbar.withLeftComponents(components);
    }

    public void addTopRightComponents(Component... components) {
        topToolbar.withRightComponents(components);
    }

    public void addBottomLeftComponents(Component... components) {
        bottomToolbar.withLeftComponents(components);
    }

    public void addBottomRightComponents(Component... components) {
        bottomToolbar.withRightComponents(components);
    }

    public void setMainContent(Component component) {
        mainContent.removeAll();
        if (component != null) {
            mainContent.add(component);
        }
    }
}
