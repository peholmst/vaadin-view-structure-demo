import {Grid, GridSortColumn, SplitLayout} from "@vaadin/react-components";
import TeamDetails from "Frontend/generated/com/example/application/data/TeamDetails";
import TeamMember from "Frontend/generated/com/example/application/data/TeamMember";
import {useSignal} from "@vaadin/hilla-react-signals";
import {ReactNode} from "react";
import EmployeeDetailsPanel from "Frontend/views/hilla/teams/_EmployeeDetailsPanel";
import {formatDate} from "Frontend/util/TimeUtils";
import {
    bindSortBySignalToQueryParameter,
    getDirectionFromSignal,
    SortBy,
    updateSortBySignal
} from "Frontend/util/GridSortUtils";

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
    const orderEmployeesBy = useSignal<SortBy | undefined>(undefined)
    bindSortBySignalToQueryParameter("orderEmployeesBy", orderEmployeesBy)
    return (
        <ConditionalSplitLayout
            master={
                <Grid className="w-full h-full"
                      theme="no-border row-borders"
                      items={props.teamDetails.members}
                      selectedItems={selectedEmployee.value ? [selectedEmployee.value] : []}
                      onActiveItemChanged={e => selectedEmployee.value = e.detail.value}>
                    <GridSortColumn path="employee.firstName" header="First Name"
                                    onDirectionChanged={updateSortBySignal("firstName", orderEmployeesBy)}
                                    direction={getDirectionFromSignal("firstName", orderEmployeesBy)}/>
                    <GridSortColumn path="employee.lastName" header="Last Name"
                                    onDirectionChanged={updateSortBySignal("lastName", orderEmployeesBy)}
                                    direction={getDirectionFromSignal("lastName", orderEmployeesBy)}/>
                    <GridSortColumn path="employee.role" header="Role"
                                    onDirectionChanged={updateSortBySignal("role", orderEmployeesBy)}
                                    direction={getDirectionFromSignal("role", orderEmployeesBy)}/>
                    <GridSortColumn path="joined" header="Joined"
                                    onDirectionChanged={updateSortBySignal("joined", orderEmployeesBy)}
                                    direction={getDirectionFromSignal("joined", orderEmployeesBy)}
                                    renderer={item => formatDate((item.item as TeamMember).joined)}/>
                </Grid>
            }
            detail={
                selectedEmployee.value && <EmployeeDetailsPanel teamMember={selectedEmployee.value}/>
            }
        />
    )
}