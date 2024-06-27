package com.example.application.views.iteration2;

import com.example.application.data.ExampleDTO;
import com.example.application.data.ExampleEnum;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("flow/iteration2")
public class SecondIterationView extends BaseView {

    private final Grid<ExampleDTO> grid = new Grid<>();
    private final TextField search = new TextField(null, "Search");
    private final Button refresh = new Button(VaadinIcon.REFRESH.create());
    private final Button add = new Button("Create", VaadinIcon.PLUS_CIRCLE.create());
    private final Button edit = new Button(VaadinIcon.EDIT.create());
    private final Button remove = new Button(VaadinIcon.MINUS_CIRCLE.create());
    private final Button alerts = new Button(VaadinIcon.ALARM.create());
    private final Button settings = new Button(VaadinIcon.COGS.create());
    private final Button users = new Button(VaadinIcon.USERS.create());
    private final Select<ExampleEnum> select = new Select<>();

    public SecondIterationView() {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        select.setItems(ExampleEnum.values());

        grid.addColumn(ExampleDTO::first).setHeader("First");
        grid.addColumn(ExampleDTO::second).setHeader("Second");
        grid.addColumn(ExampleDTO::third).setHeader("Third");
        grid.addColumn(ExampleDTO::fourth).setHeader("Fourth");
        grid.addColumn(ExampleDTO::fifth).setHeader("Fifth");
        grid.addColumn(ExampleDTO::sixth).setHeader("Sixth");
        grid.setSizeFull();
        grid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_NO_BORDER);
        grid.setItems(ExampleDTO.generate(200));

        addTopLeftComponents(alerts, users, add);
        addTopRightComponents(search, refresh);
        addBottomLeftComponents(settings, select);
        addBottomRightComponents(edit, remove);
        setMainContent(grid);
    }
}
