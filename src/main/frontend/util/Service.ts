import {ReadonlySignal, useSignal} from "@vaadin/hilla-react-signals";
import {useEffect} from "react";

export type UseServiceStatus = "pending" | "success" | "error"

export type UseServiceOptions<A extends Array<any>, R> = {
    service: (...args: A) => Promise<R>
    params: A
    onSuccess?: (data: R) => void
    onError?: (error: any) => void
    onDone?: (data: R | undefined, error: any | undefined) => void
}

export type UseServiceResult<R> = {
    readonly isPending: boolean
    readonly isSuccess: boolean
    readonly isError: boolean
    readonly status: UseServiceStatus
    readonly data: R | undefined
    readonly error: any | undefined
    readonly reset: () => void
    readonly dataSignal: ReadonlySignal<R | undefined>
    readonly errorSignal: ReadonlySignal<any | undefined>
    readonly statusSignal: ReadonlySignal<UseServiceStatus>
}

export function useServiceQuery<A extends Array<any>, R>(options: UseServiceOptions<A, R>): UseServiceResult<R> {
    const status = useSignal<UseServiceStatus>("pending")
    const data = useSignal<R | undefined>(undefined)
    const error = useSignal<any | undefined>(undefined)

    let cancelled = false

    useEffect(() => {
        status.value = "pending"
        error.value = undefined

        options.service(...options.params)
            .then(result => {
                if (!cancelled) {
                    data.value = result
                    status.value = "success"
                    if (options.onSuccess) {
                        options.onSuccess(result)
                    }
                    if (options.onDone) {
                        options.onDone(result, undefined)
                    }
                }
            })
            .catch(reason => {
                if (!cancelled) {
                    error.value = reason
                    status.value = "error"
                    if (options.onError) {
                        options.onError(reason);
                    }
                    if (options.onDone) {
                        options.onDone(undefined, reason)
                    }
                }
            })
        return () => {
            cancelled = true
        }
    }, [...options.params]);

    return {
        isPending: status.value === "pending",
        isSuccess: status.value === "success",
        isError: status.value === "error",
        status: status.value,
        data: data.value,
        error: error.value,
        reset: () => {
            status.value = "pending"
            data.value = undefined
            error.value = undefined
        },
        dataSignal: data,
        errorSignal: error,
        statusSignal: status
    }
}