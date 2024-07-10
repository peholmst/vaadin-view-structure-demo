import {Button, Tab, Tabs} from "@vaadin/react-components";
import IconItem from "Frontend/component/IconItem";
import PersonPanel from "Frontend/component/PersonPanel";
import {SubviewId, TeamDetailsViewModel} from "Frontend/views/hilla/teams/_TeamsViewModel";
import EmployeesSubview from "Frontend/views/hilla/teams/_EmployeesSubview";
import SalariesSubview from "Frontend/views/hilla/teams/_SalariesSubview";
import DocumentsSubview from "Frontend/views/hilla/teams/_DocumentsSubview";

const tabs: SubviewId[] = ["employees", "salaries", "documents"]

function Header({viewModel}: { viewModel: TeamDetailsViewModel }) {
    console.debug("Rendering Header", viewModel)
    return (
        <div className="flex justify-between items-start p-m border-b">
            <div className="flex flex-col gap-s">
                <h3>{viewModel.name}</h3>
                <span className="text-s text-secondary whitespace-normal">
                        {viewModel.description}
                    </span>
            </div>
            <div className="flex gap-xs">
                <Button onClick={viewModel.edit}>Edit</Button>
                <Button onClick={viewModel.share}>Share</Button>
                <Button onClick={viewModel.delete} theme="error">Delete</Button>
            </div>
        </div>
    )
}

function Summary({viewModel}: { viewModel: TeamDetailsViewModel }) {
    console.debug("Rendering Summary", viewModel)
    return (
        <div className="flex flex-col p-m border-b gap-s">
            <h4>Summary</h4>
            <IconItem icon="vaadin:group"
                      text={viewModel.size.value == 1 ? "1 person" : `${viewModel.size.value} people`}/>
            <IconItem icon="vaadin:factory" text="Random info"/>
            <IconItem icon="vaadin:clock" text="Random info"/>
        </div>
    )
}

function Managers({viewModel}: { viewModel: TeamDetailsViewModel }) {
    console.debug("Rendering Managers", viewModel)
    return (
        <div className="flex flex-col p-m gap-s flex-grow">
            <h4>Managers</h4>
            {viewModel.managers.value.map(member =>
                <PersonPanel key={member.publicId}
                             name={`${member.firstName} ${member.lastName}`}
                             title={member.role}
                             image={member.picture}/>
            )}
        </div>
    )
}

function Subviews({viewModel}: { viewModel: TeamDetailsViewModel }) {
    console.debug("Rendering Subviews", viewModel)
    return (
        <div className="flex flex-col flex-grow min-h-0">
            <Tabs onSelectedChanged={e => viewModel.selectSubview(tabs[e.detail.value])}
                  selected={tabs.indexOf(viewModel.selectedSubview.value)}>
                <Tab>Employees</Tab>
                <Tab>Salaries</Tab>
                <Tab>Documents</Tab>
            </Tabs>
            <div className="flex-grow min-h-0">
                {viewModel.selectedSubview.value == "employees" &&
                    <EmployeesSubview viewModel={viewModel.employeesSubview.value}/>}
                {viewModel.selectedSubview.value == "salaries" &&
                    <SalariesSubview viewModel={viewModel.salariesSubview.value}/>}
                {viewModel.selectedSubview.value == "documents" &&
                    <DocumentsSubview viewModel={viewModel.documentsSubview.value}/>}
            </div>
        </div>
    )
}

export default function TeamDetailsPanel({viewModel}: { viewModel: TeamDetailsViewModel }) {
    console.debug("Rendering TeamDetailsPanel", viewModel)
    return (
        <div className="flex flex-col h-full flex-grow">
            <Header viewModel={viewModel}/>
            <div className="flex flex-grow min-h-0">
                <div className="flex flex-col border-r min-h-0 overflow-auto" style={{width: "380px"}}>
                    <Summary viewModel={viewModel}/>
                    <Managers viewModel={viewModel}/>
                </div>
                <Subviews viewModel={viewModel}/>
            </div>
        </div>
    )
}