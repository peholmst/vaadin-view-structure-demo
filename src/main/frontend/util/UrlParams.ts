import {createContext, useContext, useMemo} from "react";
import {effect, ReadonlySignal, Signal, signal} from "@vaadin/hilla-react-signals";

export type UrlParams = {
    readonly searchParameter: (parameter: string) => Signal<string | undefined>
    readonly pathParameter: (parameter: string) => Signal<string | undefined>
    readonly pathTemplate: string | undefined
    readonly path: ReadonlySignal<string | undefined>
    readonly buildPath: () => string
}

export type UrlParamsOptions = {
    parent?: UrlParams,
    pathTemplate?: string
}

function extractPathParameters(pathTemplate: string): string[] {
    const paramRegex = /:(\w+)/g
    const parameters: string[] = []
    let match: RegExpExecArray | null
    while ((match = paramRegex.exec(pathTemplate)) !== null) {
        parameters.push(match[1])
    }
    return parameters
}

type NamedSignal = {
    name: string,
    value: Signal<string | undefined>
}

function createUrlParams(options?: UrlParamsOptions): UrlParams {
    const pathParameters = options?.pathTemplate ? extractPathParameters(options.pathTemplate) : []
    const pathParameterSignals = signal<NamedSignal[]>([])
    const path = signal<string | undefined>(undefined)

    const searchParameter = (parameter: string): Signal<string | undefined> => {
        throw Error("Not implemented yet") // TODO Implement me
    }

    const pathParameter = (parameter: string) => {
        if (pathParameters.indexOf(parameter) == -1) {
            if (options?.parent) {
                return options.parent.pathParameter(parameter)
            } else {
                throw Error("No such path parameter")
            }
        }
        let namedSignal = pathParameterSignals.value.find(np => np.name === parameter)
        if (!namedSignal) {
            const s = signal<string | undefined>(undefined)
            namedSignal = {
                name: parameter,
                value: s
            }
            effect(() => {
                console.log(parameter, s.value)
            })
            pathParameterSignals.value = [...pathParameterSignals.value, namedSignal]
        }
        return namedSignal.value
    }

    const buildPath = () => {
        const start = options?.parent ? options.parent.buildPath() : ""
        if (options?.pathTemplate) {
            let pathSegment = options.pathTemplate
            for (const parameterName of pathParameters) {
                const parameterSignal = pathParameter(parameterName)
                if (parameterSignal.value) {
                    pathSegment = pathSegment.replace(`:${parameterName}`, encodeURIComponent(parameterSignal.value))
                } else {
                    pathSegment = pathSegment.substring(0, pathSegment.indexOf(`:${parameterName}`))
                    break;
                }
            }
            return pathSegment.length > 1 ? start + pathSegment : start
        }
        return start
    }

    /*const path = computed(() => {
        const start = options?.parent ? options.parent.path.value : ""
        if (options?.pathTemplate) {
            let pathSegment = options.pathTemplate
            for (const parameterName of pathParameters) {
                const parameterSignal = pathParameter(parameterName)
                if (parameterSignal.value) {
                    pathSegment = pathSegment.replace(`:${parameterName}`, encodeURIComponent(parameterSignal.value))
                } else {
                    pathSegment = pathSegment.substring(0, pathSegment.indexOf(`:${parameterName}`))
                    break;
                }
            }
            return pathSegment.length > 1 ? start + pathSegment : start
        }
        return start
    })*/

    return {
        searchParameter: searchParameter,
        pathParameter: pathParameter,
        buildPath: buildPath,
        path: path,
        pathTemplate: options?.pathTemplate
    }
}

export function useUrlParams(options?: UrlParamsOptions) {
    if (options) {
        return useMemo(() => createUrlParams(options), [])
    } else {
        return useContext(UrlParamsContext)
    }
}

export const UrlParamsContext = createContext<UrlParams>({
    searchParameter: () => {
        throw Error("No UrlParams available")
    },
    pathParameter: () => {
        throw Error("No UrlParams available")
    },
    buildPath: () => {
        throw Error("No UrlParams available")
    },
    path: signal<string | undefined>(undefined),
    pathTemplate: ""
})
