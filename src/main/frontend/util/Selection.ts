import {useSignal} from "@vaadin/hilla-react-signals";
import {useEffect} from "react";

export type SingleSelectionOptions<T, ID> = {
    itemId: ID | undefined | null,
    items: T[] | undefined,
    getId: (item: T) => ID
}

export type Selection<T> = {
    readonly items: T[] | undefined
}

export type SingleSelection<T, ID> = {
    readonly item: T | undefined
} & Selection<T>

export default function useSingleSelection<T, ID>(options: SingleSelectionOptions<T, ID>): SingleSelection<T, ID> {
    const selection = useSignal<T | undefined>(undefined)
    useEffect(() => {
        if (options.itemId && options.items) {
            selection.value = options.items.find(item => options.getId(item) === options.itemId)
        } else {
            selection.value = undefined
        }
    }, [options.items, options.itemId]);
    return {
        item: selection.value,
        items: selection.value ? [selection.value] : [],
    }
}