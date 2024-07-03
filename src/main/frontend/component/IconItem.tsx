import {Icon} from "@vaadin/react-components";

export type IconItemProps = {
    icon: string,
    text?: string
}

export default function IconItem(props: IconItemProps) {
    return (
        <div className="flex text-secondary gap-s text-s py-xs items-center">
            <Icon icon={props.icon}/>
            <span>{props.text}</span>
        </div>
    )
}