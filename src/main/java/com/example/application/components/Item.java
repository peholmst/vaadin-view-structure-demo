package com.example.application.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.IconFactory;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class Item extends Composite<Div> {

    public Item(IconFactory iconFactory, String text) {
        var icon = iconFactory.create();
        var textSpan = new Span(text);
        getContent().add(icon, textSpan);

        addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.TextColor.SECONDARY,
                LumoUtility.Gap.SMALL,
                LumoUtility.FontSize.SMALL,
                LumoUtility.Padding.Vertical.XSMALL,
                LumoUtility.AlignItems.CENTER
        );
    }
}
