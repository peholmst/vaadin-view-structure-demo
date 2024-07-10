import {effect, Signal} from "@vaadin/hilla-react-signals";
import {GridSortColumnDirectionChangedEvent} from "@vaadin/react-components";

export type SortBy = {
    path: string,
    direction: "asc" | "desc"
}

export function updateSortBySignal(path: string, signal: Signal<SortBy | undefined>): (e: GridSortColumnDirectionChangedEvent) => void {
    return e => {
        if (e.detail.value) {
            signal.value = {path: path, direction: e.detail.value}
        } else {
            if (signal.value?.path === path) { // Otherwise we may overwrite another sort order that has already been set
                signal.value = undefined
            }
        }
    }
}

export function getDirectionFromSignal(path: string, signal: Signal<SortBy | undefined>): "asc" | "desc" | undefined {
    if (signal.value?.path === path) {
        return signal.value.direction
    } else {
        return undefined
    }
}

function serialize(sortBy: SortBy): string {
    return `${sortBy.path}:${sortBy.direction}`
}

function deserialize(string: string | null | undefined): SortBy | undefined {
    if (string) {
        const [path, direction] = string.split(":")
        if (path && (direction === "asc" || direction === "desc")) {
            return {path: path, direction: direction}
        }
    }
    return undefined
}

// TODO Refactor this to use SearchParam instead.
export function bindSortBySignalToQueryParameter(parameter: string, signal: Signal<SortBy | undefined>) {
    const urlSearchParams = new URLSearchParams(window.location.search)
    const value = deserialize(urlSearchParams.get(parameter))
    if (value) {
        signal.value = value
    }

    effect(() => {
        if (signal.value) {
            urlSearchParams.set(parameter, serialize(signal.value))
        } else {
            urlSearchParams.delete(parameter)
        }
        const newUrl = `${window.location.pathname}?${urlSearchParams.toString()}`
        window.history.replaceState({}, "", newUrl)
    })
}