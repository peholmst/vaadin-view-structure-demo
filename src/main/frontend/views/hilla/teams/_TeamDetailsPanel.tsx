import {Button, Tab, Tabs} from "@vaadin/react-components";
import IconItem from "Frontend/component/IconItem";
import PersonPanel from "Frontend/component/PersonPanel";
import TeamDetails from "Frontend/generated/com/example/application/data/TeamDetails";
import {useSignal} from "@vaadin/hilla-react-signals";
import EmployeesSubview from "Frontend/views/hilla/teams/_EmployeesSubview";
import SalariesSubview from "Frontend/views/hilla/teams/_SalariesSubview";
import DocumentsSubview from "Frontend/views/hilla/teams/_DocumentsSubview";

export type TeamDetailsProps = {
    teamDetails: TeamDetails
}

type Subview = "employees" | "salaries" | "documents"
// This array is only needed because Tabs uses indexes to determine the selected tab.
const tabs: Subview[] = ["employees", "salaries", "documents"]

export default function TeamDetailsPanel(props: TeamDetailsProps) {
    const subview = useSignal<Subview>("employees")

    return (
        <div className="flex flex-col h-full flex-grow">
            <div className="flex justify-between items-start p-m border-b">
                <div className="flex flex-col gap-s">
                    <h3>{props.teamDetails.name}</h3>
                    <span className="text-s text-secondary whitespace-normal">
                        {props.teamDetails.description}
                    </span>
                </div>
                <div className="flex gap-xs">
                    <Button>Edit</Button>
                    <Button>Share</Button>
                    <Button theme="error">Delete</Button>
                </div>
            </div>
            <div className="flex flex-grow min-h-0">
                <div className="flex flex-col border-r min-h-0 overflow-auto" style={{width: "380px"}}>
                    <div className="flex flex-col p-m border-b gap-s">
                        <h4>Summary</h4>
                        <IconItem icon="vaadin:group" text={`${props.teamDetails.members.length} people`}/>
                        <IconItem icon="vaadin:factory" text="Random info"/>
                        <IconItem icon="vaadin:clock" text="Random info"/>
                    </div>
                    <div className="flex flex-col p-m gap-s flex-grow">
                        <h4>Managers</h4>
                        {props.teamDetails.members.filter(member => member.manager).map(member =>
                            <PersonPanel key={member.employee.publicId}
                                         name={`${member.employee.firstName} ${member.employee.lastName}`}
                                         title={member.employee.role}
                                         image={member.employee.picture}/>
                        )}
                    </div>
                </div>
                <div className="flex flex-col flex-grow min-h-0">
                    <Tabs onSelectedChanged={e => subview.value = tabs[e.detail.value]}
                          selected={tabs.indexOf(subview.value)}>
                        <Tab>Employees</Tab>
                        <Tab>Salaries</Tab>
                        <Tab>Documents</Tab>
                    </Tabs>
                    <div className="flex-grow min-h-0">
                        {subview.value == "employees" && <EmployeesSubview teamDetails={props.teamDetails}/>}
                        {subview.value == "salaries" && <SalariesSubview/>}
                        {subview.value == "documents" && <DocumentsSubview/>}
                    </div>
                </div>
            </div>
        </div>
    )
}