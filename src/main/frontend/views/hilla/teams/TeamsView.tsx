import TeamSelectionPanel from "Frontend/views/hilla/teams/_TeamSelectionPanel";
import TeamDetailsPanel from "Frontend/views/hilla/teams/_TeamDetailsPanel";
import {TeamsViewModel, useTeamsViewModel} from "Frontend/views/hilla/teams/_TeamsViewModel";

function TeamDetailsContainer({viewModel}: { viewModel: TeamsViewModel }) {
    console.debug("Rendering TeamDetailsContainer", viewModel)
    const teamDetails = viewModel.teamDetails.value
    if (teamDetails != null) {
        return <TeamDetailsPanel viewModel={teamDetails}/>
    } else {
        return <div className="p-m">Please select a team.</div>
    }
}

export default function TeamsView() {
    const viewModel = useTeamsViewModel()
    console.debug("Rendering TeamsView", viewModel)
    return (
        <div className="flex h-full w-full overflow-hidden">
            <TeamSelectionPanel viewModel={viewModel}/>
            <TeamDetailsContainer viewModel={viewModel}/>
        </div>
    )
}