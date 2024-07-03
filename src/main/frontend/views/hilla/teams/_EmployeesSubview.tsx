import {Grid, GridColumn, SplitLayout} from "@vaadin/react-components";
import TeamDetails from "Frontend/generated/com/example/application/data/TeamDetails";
import TeamMember from "Frontend/generated/com/example/application/data/TeamMember";
import {useSignal} from "@vaadin/hilla-react-signals";
import {ReactNode} from "react";
import EmployeeDetailsPanel from "Frontend/views/hilla/teams/_EmployeeDetailsPanel";

type ConditionalSplitLayoutProps = {
    master: ReactNode,
    detail?: ReactNode
}

function ConditionalSplitLayout(props: ConditionalSplitLayoutProps) {
    if (props.detail) {
        return (
            <SplitLayout orientation="vertical" className="w-full h-full">
                {props.master}
                {props.detail}
            </SplitLayout>
        )
    } else {
        return props.master
    }
}

export type EmployeesSubviewProps = {
    teamDetails: TeamDetails
}

export default function EmployeesSubview(props: EmployeesSubviewProps) {
    const selectedEmployee = useSignal<TeamMember | null | undefined>(undefined)

    return (
        <ConditionalSplitLayout
            master={
                <Grid className="w-full h-full"
                      items={props.teamDetails.members}
                      theme="no-border"
                      selectedItems={selectedEmployee.value ? [selectedEmployee.value] : []}
                      onActiveItemChanged={e => selectedEmployee.value = e.detail.value}>
                    <GridColumn path="employee.firstName" header="First Name"/>
                    <GridColumn path="employee.lastName" header="Last Name"/>
                    <GridColumn path="employee.role" header="Role"/>
                    <GridColumn path="joined" header="Joined"/>
                </Grid>
            }
            detail={
                selectedEmployee.value && <EmployeeDetailsPanel teamMember={selectedEmployee.value}/>
            }
        />
    )
}