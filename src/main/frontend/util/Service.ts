import {effect, ReadonlySignal, signal} from "@vaadin/hilla-react-signals";

export type ServiceStatus = "pending" | "success" | "error"

export type ServiceOptions<A extends Parameters<any>, R> = {
    service: (...args: A) => Promise<R>
    params: ReadonlySignal<A>
    onSuccess?: (data: R) => void
    onError?: (error: any) => void
    onDone?: (data: R | undefined, error: any | undefined) => void
}

export type ServiceResult<R> = {
    readonly isPending: boolean
    readonly isSuccess: boolean
    readonly isError: boolean
    readonly data: ReadonlySignal<R | undefined>
    readonly error: ReadonlySignal<any | undefined>
    readonly status: ReadonlySignal<ServiceStatus>
    readonly reset: () => void
}

export function serviceQuery<A extends Parameters<any>, R>(options: ServiceOptions<A, R>): ServiceResult<R> {
    console.debug("Creating service query", options.service)
    const status = signal<ServiceStatus>("pending")
    const data = signal<R | undefined>(undefined)
    const error = signal<any | undefined>(undefined)

    effect(() => {
        const callId = '[' + Math.random().toString(36).substring(2) + ']'
        let cancelled = false
        status.value = "pending"
        error.value = undefined
        console.debug(callId, "Calling service", options.service, " with parameters ", options.params.peek())
        options.service(...options.params.value)
            .then(result => {
                if (!cancelled) {
                    console.debug(callId, "Result:", result)
                    data.value = result
                    status.value = "success"
                    if (options.onSuccess) {
                        options.onSuccess(result)
                    }
                    if (options.onDone) {
                        options.onDone(result, undefined)
                    }
                } else {
                    console.debug(callId, "Cancelled")
                }
            })
            .catch(reason => {
                if (!cancelled) {
                    console.debug(callId, "Error:", reason)
                    error.value = reason
                    status.value = "error"
                    if (options.onError) {
                        options.onError(reason);
                    }
                    if (options.onDone) {
                        options.onDone(undefined, reason)
                    }
                } else {
                    console.debug(callId, "Cancelled")
                }
            })
        return () => {
            cancelled = true
        }
    });

    return {
        isPending: status.value === "pending",
        isSuccess: status.value === "success",
        isError: status.value === "error",
        status: status,
        data: data,
        error: error,
        reset: () => {
            status.value = "pending"
            data.value = undefined
            error.value = undefined
        }
    }
}