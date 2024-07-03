import {Avatar} from "@vaadin/react-components";

export type PersonPanelProps = {
    name?: string,
    image?: string,
    title?: string,
    theme?: "large"
}

export default function PersonPanel(props: PersonPanelProps) {
    return (
        <div className="flex items-center gap-s">
            {props.theme == "large" ? <Avatar name={props.name} img={props.image} theme="xlarge"/> :
                <Avatar name={props.name} img={props.image}/>}
            <div className="flex flex-col">
                {props.theme == "large" ? <span className="text-xl font-semibold">{props.name}</span> :
                    <span className="text-m">{props.name}</span>}
                {props.theme == "large" ? <span className="text-secondary text-m">{props.title}</span> :
                    <span className="text-secondary text-s">{props.title}</span>}
            </div>
        </div>
    )
}