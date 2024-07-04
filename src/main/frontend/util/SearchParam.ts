import {useSignal} from "@vaadin/hilla-react-signals";
import {useMemo} from "react";

export type UseSearchParamOptions = {
    parameter: string
}

export type UseSearchParamResult = {
    readonly value: string | null
    readonly setValue: (value: string | undefined | null) => void
}

export function useSearchParam(options: UseSearchParamOptions): UseSearchParamResult {
    const urlSearchParams = useMemo(() => new URLSearchParams(window.location.search), [window.location.search])
    const value = useSignal(urlSearchParams.get(options.parameter))

    return {
        value: value.value,
        setValue: (value) => {
            if (value) {
                urlSearchParams.set(options.parameter, value)
            } else {
                urlSearchParams.delete(options.parameter)
            }
            const newUrl = urlSearchParams.size == 0 ? window.location.pathname : `${window.location.pathname}?${urlSearchParams.toString()}`
            window.history.replaceState({}, "", newUrl)
        }
    }
}