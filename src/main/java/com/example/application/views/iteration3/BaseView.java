package com.example.application.views.iteration3;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayoutVariant;
import com.vaadin.flow.theme.lumo.LumoUtility;

public abstract class BaseView extends Composite<Div> {

    private final Toolbar topToolbar;
    private final Toolbar bottomToolbar;
    private final Main mainContent;
    private final Div rightContent;

    public BaseView() {
        topToolbar = new Toolbar();
        topToolbar.addClassNames(LumoUtility.Margin.Horizontal.SMALL, LumoUtility.Margin.Top.SMALL);
        bottomToolbar = new Toolbar();
        bottomToolbar.addClassNames(LumoUtility.Margin.Horizontal.SMALL, LumoUtility.Margin.Bottom.SMALL);

        mainContent = new Main();
        mainContent.addClassNames(LumoUtility.Flex.GROW, LumoUtility.BoxShadow.XSMALL, LumoUtility.Margin.SMALL);

        rightContent = new Div();
        rightContent.addClassNames(LumoUtility.Flex.GROW, LumoUtility.BoxShadow.XSMALL, LumoUtility.Margin.SMALL);

        var splitLayout = new SplitLayout(mainContent, rightContent);
        splitLayout.addThemeVariants(SplitLayoutVariant.LUMO_MINIMAL);
        splitLayout.setSplitterPosition(80);
        splitLayout.addClassNames(LumoUtility.Flex.GROW);

        getContent().add(topToolbar, splitLayout, bottomToolbar);

        addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Width.FULL,
                LumoUtility.Height.FULL,
                LumoUtility.BoxSizing.BORDER,
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

    public void setRightContent(Component component) {
        rightContent.removeAll();
        if (component != null) {
            rightContent.add(component);
        }
    }
}
