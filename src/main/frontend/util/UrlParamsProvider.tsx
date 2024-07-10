import {PropsWithChildren} from "react";
import {UrlParams, UrlParamsContext} from "Frontend/util/UrlParams";

export type UrlParamsProviderProps = {
    urlParams: UrlParams
} & PropsWithChildren

export default function UrlParamsProvider(props: UrlParamsProviderProps) {
    return (
        <UrlParamsContext.Provider value={props.urlParams}>
            {props.children}
        </UrlParamsContext.Provider>
    )
}