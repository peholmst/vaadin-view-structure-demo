import {Button, Grid, GridColumn, TextField} from "@vaadin/react-components";
import {Signal, useSignal} from "@vaadin/hilla-react-signals";
import Team from "Frontend/generated/com/example/application/data/Team";
import {useEffect} from "react";
import {TeamService} from "Frontend/generated/endpoints";
import TeamPanel from "Frontend/views/hilla/teams/_TeamPanel";

export type TeamSelectionPanelProps = {
    selectedTeam: Signal<Team | undefined | null>
}

export default function TeamSelectionPanel(props: TeamSelectionPanelProps) {
    const teams = useSignal<Team[]>([])
    const searchTerm = useSignal<string>("")

    useEffect(() => {
        const handle = window.setTimeout(() => {
            TeamService.findTeams(searchTerm.value).then(result => teams.value = result).catch(console.error)
        }, 200)
        return () => window.clearTimeout(handle)
    }, [searchTerm.value])

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
                  items={teams.value}
                  selectedItems={props.selectedTeam.value ? [props.selectedTeam.value] : []}
                  onActiveItemChanged={e => props.selectedTeam.value = e.detail.value}>
                <GridColumn>
                    {({item}) => <TeamPanel key={item.publicId} team={item}/>}
                </GridColumn>
            </Grid>
        </div>
    )
}