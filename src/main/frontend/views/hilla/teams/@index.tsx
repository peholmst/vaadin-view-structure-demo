import TeamSelectionPanel from "Frontend/views/hilla/teams/_TeamSelectionPanel";
import TeamDetailsPanel from "Frontend/views/hilla/teams/_TeamDetailsPanel";
import {useSignal} from "@vaadin/hilla-react-signals";
import Team from "Frontend/generated/com/example/application/data/Team";
import {TeamService} from "Frontend/generated/endpoints";
import {useEffect} from "react";
import TeamDetails from "Frontend/generated/com/example/application/data/TeamDetails";

export default function TeamsView() {
    const selectedTeam = useSignal<Team | null | undefined>(undefined)
    const selectedTeamDetails = useSignal<TeamDetails | undefined>(undefined)

    useEffect(() => {
        if (selectedTeam.value) {
            TeamService.findTeamDetailsByPublicId(selectedTeam.value.publicId)
                .then(result => selectedTeamDetails.value = result)
                .catch(console.error)
        } else {
            selectedTeamDetails.value = undefined
        }
    }, [selectedTeam.value])

    return (
        <div className="flex h-full w-full overflow-hidden">
            <TeamSelectionPanel selectedTeam={selectedTeam}/>
            {selectedTeamDetails.value && <TeamDetailsPanel teamDetails={selectedTeamDetails.value}/>}
            {!selectedTeamDetails.value && <div className="p-m">Please select a team.</div>}
        </div>
    )
}