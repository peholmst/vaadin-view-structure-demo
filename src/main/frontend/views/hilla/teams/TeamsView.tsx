import TeamSelectionPanel from "Frontend/views/hilla/teams/_TeamSelectionPanel";
import TeamDetailsPanel from "Frontend/views/hilla/teams/_TeamDetailsPanel";
import {TeamService} from "Frontend/generated/endpoints";
import {useNavigate, useParams} from "react-router-dom";
import {useServiceQuery} from "Frontend/util/Service";

export default function TeamsView() {
    const {teamId} = useParams()
    const teamDetails = useServiceQuery({
        service: async (teamId) => teamId ? await TeamService.findTeamDetailsByPublicId(teamId) : undefined,
        params: [teamId]
    })
    const navigate = useNavigate()

    return (
        <div className="flex h-full w-full overflow-hidden">
            <TeamSelectionPanel selectedTeamId={teamId}
                                onTeamSelected={team => team ? navigate(`/hilla/teams/${team.publicId}`) : navigate("/hilla/teams")}/>
            {teamDetails.data && <TeamDetailsPanel teamDetails={teamDetails.data}/>}
            {!teamDetails.data && <div className="p-m">Please select a team.</div>}
        </div>
    )
}