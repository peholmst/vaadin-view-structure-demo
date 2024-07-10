import {Button, Grid, GridColumn, TextField} from "@vaadin/react-components";
import TeamPanel from "Frontend/views/hilla/teams/_TeamPanel";
import {singleValueToArray} from "Frontend/util/ArrayUtils";
import {TeamsViewModel} from "Frontend/views/hilla/teams/_TeamsViewModel";

// TODO: Would be nice if the TextField and Grid could work with Signals directly

function Header({viewModel}: { viewModel: TeamsViewModel }) {
    console.debug("Rendering Header", viewModel)
    return (
        <div className="flex flex-col p-m border-b">
            <div className="flex justify-between items-center">
                <h3>Teams</h3>
                <Button theme="primary" onClick={viewModel.addNew}>Add New</Button>
            </div>
            <TextField placeholder="Search" className="w-full" value={viewModel.teamSearchTerm.value}
                       onValueChanged={e => viewModel.teamSearchTerm.value = e.detail.value}/>
        </div>
    )
}

function TeamGrid({viewModel}: { viewModel: TeamsViewModel }) {
    console.debug("Rendering TeamGrid", viewModel)
    return (
        <Grid className="flex-grow"
              theme="no-border"
              items={viewModel.teams.value}
              selectedItems={singleValueToArray(viewModel.selectedTeam.value)}
              onActiveItemChanged={e => viewModel.selectTeam(e.detail.value?.publicId)}>
            <GridColumn>
                {({item}) => <TeamPanel key={item.publicId} team={item}/>}
            </GridColumn>
        </Grid>
    )
}

export default function TeamSelectionPanel({viewModel}: { viewModel: TeamsViewModel }) {
    console.debug("Rendering TeamSelectionPanel", viewModel)
    return (
        <div className="flex flex-col bg-contrast-5 border-r" style={{width: "300px"}}>
            <Header viewModel={viewModel}/>
            <TeamGrid viewModel={viewModel}/>
        </div>
    )
}