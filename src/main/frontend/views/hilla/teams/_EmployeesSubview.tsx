import {Grid, GridSortColumn, SplitLayout} from "@vaadin/react-components";
import TeamMember from "Frontend/generated/com/example/application/data/TeamMember";
import {ReactNode} from "react";
import EmployeeDetailsPanel from "Frontend/views/hilla/teams/_EmployeeDetailsPanel";
import {formatDate} from "Frontend/util/TimeUtils";
import {getDirectionFromSignal, updateSortBySignal} from "Frontend/util/GridSortUtils";
import {EmployeesSubviewModel} from "Frontend/views/hilla/teams/_TeamsViewModel";
import {singleValueToArray} from "Frontend/util/ArrayUtils";

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

export default function EmployeesSubview({viewModel}: { viewModel: EmployeesSubviewModel }) {
    console.debug("Rendering EmployeesSubview", viewModel)
    // TODO Store size of the detail view in case the user has resized it
    // TODO Why do I have to click on a selected row twice to unselect it?
    return (
        <ConditionalSplitLayout
            master={
                <Grid className="w-full h-full"
                      theme="no-border row-borders"
                      items={viewModel.members.value}
                      selectedItems={singleValueToArray(viewModel.selectedMember.value)}
                      onActiveItemChanged={e => viewModel.selectMember(e.detail.value?.employee.publicId)}>
                    <GridSortColumn path="employee.firstName" header="First Name"
                                    onDirectionChanged={updateSortBySignal("firstName", viewModel.orderBy)}
                                    direction={getDirectionFromSignal("firstName", viewModel.orderBy)}/>
                    <GridSortColumn path="employee.lastName" header="Last Name"
                                    onDirectionChanged={updateSortBySignal("lastName", viewModel.orderBy)}
                                    direction={getDirectionFromSignal("lastName", viewModel.orderBy)}/>
                    <GridSortColumn path="employee.role" header="Role"
                                    onDirectionChanged={updateSortBySignal("role", viewModel.orderBy)}
                                    direction={getDirectionFromSignal("role", viewModel.orderBy)}/>
                    <GridSortColumn path="joined" header="Joined"
                                    onDirectionChanged={updateSortBySignal("joined", viewModel.orderBy)}
                                    direction={getDirectionFromSignal("joined", viewModel.orderBy)}
                                    renderer={item => formatDate((item.item as TeamMember).joined)}/>
                </Grid>
            }
            detail={
                viewModel.selectedMember.value && <EmployeeDetailsPanel teamMember={viewModel.selectedMember.value}/>
            }
        />
    )
}