import {computed, ReadonlySignal, Signal, signal} from "@vaadin/hilla-react-signals";
import Team from "Frontend/generated/com/example/application/data/Team";
import TeamDetails from "Frontend/generated/com/example/application/data/TeamDetails";
import Employee from "Frontend/generated/com/example/application/data/Employee";
import {serviceQuery} from "Frontend/util/Service";
import {TeamService} from "Frontend/generated/endpoints";
import {useMemo} from "react";
import TeamMember from "Frontend/generated/com/example/application/data/TeamMember";
import {SortBy} from "Frontend/util/GridSortUtils";
import {delay} from "Frontend/util/SignalUtils";

export type SubviewId = "employees" | "salaries" | "documents"

export type EmployeesSubviewModel = {
    // Properties
    readonly members: ReadonlySignal<TeamMember[]>
    readonly selectedMember: ReadonlySignal<TeamMember | undefined>
    readonly orderBy: Signal<SortBy | undefined>
    // Actions:
    readonly selectMember: (employeeId: string | undefined) => void
}

function createEmployeesSubviewModel(teamDetails: TeamDetails, orderBy: Signal<SortBy | undefined>): EmployeesSubviewModel {
    const members = signal(teamDetails.members)
    const selectedMemberId = signal<string | undefined>(undefined)
    const selectedMember = computed(() => {
        const memberId = selectedMemberId.value
        return memberId ? members.value.find(member => member.employee.publicId === memberId) : undefined
    })
    return {
        members: members,
        selectedMember: selectedMember,
        orderBy: orderBy,
        selectMember: (employeeId: string | undefined) => selectedMemberId.value = employeeId
    }
}

export type SalariesSubviewModel = {
    // TODO Define me
}

function createSalariesSubviewModel(): SalariesSubviewModel {
    return {}
}

export type DocumentsSubviewModel = {
    // TODO Define me
}

function createDocumentsSubviewModel(): DocumentsSubviewModel {
    return {}
}

export type TeamDetailsViewModel = {
    // Properties
    readonly name: ReadonlySignal<string | undefined>
    readonly description: ReadonlySignal<string | undefined>
    readonly size: ReadonlySignal<number>
    readonly managers: ReadonlySignal<Employee[]>
    readonly employeesSubview: ReadonlySignal<EmployeesSubviewModel>
    readonly salariesSubview: ReadonlySignal<SalariesSubviewModel>
    readonly documentsSubview: ReadonlySignal<DocumentsSubviewModel>
    readonly selectedSubview: ReadonlySignal<SubviewId>
    // Actions
    readonly selectSubview: (subview: SubviewId) => void
    readonly edit: () => void
    readonly share: () => void
    readonly delete: () => void
}

function createTeamDetailsViewModel(details: TeamDetails, selectedSubview: Signal<SubviewId>, orderEmployeesBy: Signal<SortBy | undefined>): TeamDetailsViewModel {
    const name = signal(details.name)
    const description = signal(details.description)
    const size = signal(details.members.length)
    const managers = signal(details.members.filter(member => member.manager).map(member => member.employee))
    const employeesSubview = signal(createEmployeesSubviewModel(details, orderEmployeesBy))
    const documentsSubview = signal(createDocumentsSubviewModel())
    const salariesSubview = signal(createSalariesSubviewModel())
    return {
        name: name,
        description: description,
        size: size,
        managers: managers,
        employeesSubview: employeesSubview,
        documentsSubview: documentsSubview,
        salariesSubview: salariesSubview,
        selectedSubview: selectedSubview,
        selectSubview: (subview: SubviewId) => {
            selectedSubview.value = subview
        },
        edit: () => {
            console.error("Editing teams is not implemented")
        },
        share: () => {
            console.error("Sharing teams is not implemented")
        },
        delete: () => {
            console.error("Deleting teams is not implemented")
        }
    }
}

export type TeamsViewModel = {
    // Properties
    readonly teams: ReadonlySignal<Team[] | undefined>
    readonly teamSearchTerm: Signal<string | undefined>
    readonly selectedTeam: ReadonlySignal<Team | undefined>
    readonly teamDetails: ReadonlySignal<TeamDetailsViewModel | undefined>
    // Actions
    readonly selectTeam: (teamId: string | undefined) => void
    readonly addNew: () => void
}

function createTeamsViewModel(): TeamsViewModel {
    const teamSearchTerm = signal<string | undefined>(undefined)
    const teamsQuery = serviceQuery({
        service: TeamService.findTeams,
        params: computed(() => {
            // TODO How can I get rid of the type cast here?
            return [teamSearchTerm.value] as [searchTerm: string | undefined]
        })
    })
    const selectedTeamId = signal<string | undefined>(undefined)
    const teamDetailsQuery = serviceQuery({
        service: async (teamId) => teamId ? await TeamService.findTeamDetailsByPublicId(teamId) : undefined,
        params: computed(() => {
            // TODO How can I get rid of the type cast here?
            return [selectedTeamId.value] as [teamId: string | undefined]
        })
    })
    const selectedTeam = computed(() => {
        const teamId = selectedTeamId.value
        if (teamId == null) {
            return undefined
        } else {
            return teamsQuery.data.value?.find(team => team.publicId === teamId)
        }
    })
    const selectTeam = (teamId: string | undefined) => {
        selectedTeamId.value = teamId
    }
    const selectedSubview = signal<SubviewId>("employees")
    const orderEmployeesBy = signal<SortBy | undefined>(undefined)
    const teamDetails = computed(() => {
        const details = teamDetailsQuery.data.value
        if (details == null) {
            return undefined
        } else {
            return createTeamDetailsViewModel(details, selectedSubview, orderEmployeesBy)
        }
    })

    // TODO Error handling and loading indicators?

    const viewModel: TeamsViewModel = {
        teams: teamsQuery.data,
        teamSearchTerm: delay(teamSearchTerm, 300),
        selectedTeam: selectedTeam,
        teamDetails: teamDetails,
        selectTeam: selectTeam,
        addNew: () => {
            console.error("Adding new teams is not implemented")
        }
    }
    return viewModel
}

export function useTeamsViewModel(): TeamsViewModel {
    return useMemo(() => createTeamsViewModel(), [])
}