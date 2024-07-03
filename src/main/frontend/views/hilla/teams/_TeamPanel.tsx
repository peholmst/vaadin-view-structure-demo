import Team from "Frontend/generated/com/example/application/data/Team";

export type TeamPanelProps = {
    team: Team
}

export default function TeamPanel(props: TeamPanelProps) {
    return (
        <div className="flex flex-col gap-s py-s">
            <span className="text-m font-semibold whitespace-normal">{props.team.name}</span>
            <span className="text-secondary text-xs whitespace-normal">{props.team.description}</span>
        </div>
    )
}