import {Button, Grid, GridColumn, TextField} from "@vaadin/react-components";
import {useSignal} from "@vaadin/hilla-react-signals";
import Team from "Frontend/generated/com/example/application/data/Team";
import {TeamService} from "Frontend/generated/endpoints";
import TeamPanel from "Frontend/views/hilla/teams/_TeamPanel";
import useSingleSelection from "Frontend/util/Selection";
import {useServiceQuery} from "Frontend/util/Service";
import {useSearchParam} from "Frontend/util/SearchParam";
import {useEffect} from "react";

export type TeamSelectionPanelProps = {
    selectedTeamId: string | undefined | null,
    onTeamSelected: (team: Team | undefined | null) => void
}

export default function TeamSelectionPanel(props: TeamSelectionPanelProps) {
    const searchTermParam = useSearchParam({parameter: "search"})
    const searchTerm = useSignal<string>(searchTermParam.value || "")
    const teams = useServiceQuery({
        service: TeamService.findTeams,
        params: [searchTerm.value]
    })
    const selectedTeam = useSingleSelection<Team, string>({
        itemId: props.selectedTeamId,
        items: teams.data,
        getId: item => item.publicId
    })

    useEffect(() => {
        searchTermParam.setValue(searchTerm.value)
    }, [searchTerm.value]);

    return (
        <div className="flex flex-col bg-contrast-5 border-r" style={{width: "300px"}}>
            <div className="flex flex-col p-m border-b">
                <div className="flex justify-between items-center">
                    <h3>Teams</h3>
                    <Button theme="primary">Add New</Button>
                </div>
                <TextField placeholder="Search" className="w-full" value={searchTerm.value}
                           onValueChanged={e => searchTerm.value = e.detail.value}/>
            </div>
            <Grid className="flex-grow"
                  theme="no-border"
                  items={teams.data}
                  selectedItems={selectedTeam.items}
                  onActiveItemChanged={e => props.onTeamSelected(e.detail.value)}>
                <GridColumn>
                    {({item}) => <TeamPanel key={item.publicId} team={item}/>}
                </GridColumn>
            </Grid>
        </div>
    )
}