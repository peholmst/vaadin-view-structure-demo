package com.example.application.views;

import com.example.application.views.teams.TeamsView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends Composite<Div> implements RouterLayout {

    private final Div contentContainer;

    public MainLayout() {
        var logo = new Image("images/vaadin.png", "Vaadin");
        logo.setHeight("44px");

        var nav = new SideNav();
        nav.addItem(createNavItem("Employees", null));
        nav.addItem(createNavItem("Teams", TeamsView.class));
        nav.addItem(createNavItem("Locations", null));

        var avatar = new Avatar("FL");
        avatar.setColorIndex(1);

        var headerLeftPart = new Div(logo, nav);
        headerLeftPart.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.Gap.LARGE,
                LumoUtility.AlignItems.CENTER,
                LumoUtility.Overflow.HIDDEN
        );

        var headerRightPart = new Div(avatar);
        headerRightPart.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.Gap.SMALL,
                LumoUtility.AlignItems.CENTER
        );

        var header = new Div(headerLeftPart, headerRightPart);
        header.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.ROW,
                LumoUtility.JustifyContent.BETWEEN,
                LumoUtility.Padding.Horizontal.MEDIUM,
                LumoUtility.BoxSizing.BORDER
        );
        header.getElement().setAttribute("theme", "dark");

        contentContainer = new Div();
        contentContainer.addClassNames(
                LumoUtility.MinHeight.NONE,
                LumoUtility.Flex.GROW,
                LumoUtility.Overflow.AUTO
        );

        getContent().add(header, contentContainer);
        addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Width.FULL,
                LumoUtility.Height.FULL
        );
    }

    private SideNavItem createNavItem(String label, Class<? extends Component> routeTarget) {
        var item = new SideNavItem(label, routeTarget);
        item.addClassNames(LumoUtility.Display.INLINE_BLOCK, LumoUtility.Height.FULL);
        return item;
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        contentContainer.removeAll();
        contentContainer.getElement().appendChild(content.getElement());
    }
}
