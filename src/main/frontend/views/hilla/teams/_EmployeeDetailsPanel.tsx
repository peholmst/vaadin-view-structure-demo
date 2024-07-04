import TeamMember from "Frontend/generated/com/example/application/data/TeamMember";
import PersonPanel from "Frontend/component/PersonPanel";
import IconItem from "Frontend/component/IconItem";
import {daysSince, formatDate, localTime} from "Frontend/util/TimeUtils";

export type EmployeeDetailsPanelProps = {
    teamMember: TeamMember
}

export default function EmployeeDetailsPanel(props: EmployeeDetailsPanelProps) {
    return (
        <div className="flex flex-col p-m" style={{"min-height": "230px"}}>
            <PersonPanel name={`${props.teamMember.employee.firstName} ${props.teamMember.employee.lastName}`}
                         title={props.teamMember.employee.role}
                         image={props.teamMember.employee.picture}
                         theme="large"/>
            <div className="grid grid-cols-2 grid-rows-2 py-m gap-s">
                <IconItem icon="vaadin:phone" text={props.teamMember.employee.phone}/>
                <IconItem icon="vaadin:envelope-o" text={props.teamMember.employee.email}/>
                <IconItem icon="vaadin:calendar-o"
                          text={formatDate(props.teamMember.joined)}/>
                <IconItem icon="vaadin:user-clock" text={daysSince(new Date(props.teamMember.joined))}/>
            </div>
            <div className="border-t flex gap-l py-m">
                <IconItem icon="vaadin:briefcase" text="Permanent"/>
                <IconItem icon="vaadin:money" text="Random info"/>
                <IconItem icon="vaadin:building" text="Random info"/>
                {props.teamMember.employee.timeZone &&
                    <IconItem icon="vaadin:clock" text={localTime(props.teamMember.employee.timeZone)}/>}
            </div>
        </div>
    )
}