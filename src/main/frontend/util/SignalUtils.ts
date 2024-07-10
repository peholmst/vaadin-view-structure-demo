import {effect, Signal} from "@vaadin/hilla-react-signals";

export function delay<V>(signal: Signal<V>, timeoutMs: number): Signal<V> {
    const delayingSignal = new Signal<V>(signal.value)
    effect(() => {
        if (signal.peek() !== delayingSignal.value) {
            const handler = setTimeout(() => {
                signal.value = delayingSignal.value
            }, timeoutMs)
            return () => clearTimeout(handler)
        }
        return
    })
    effect(() => {
        if (delayingSignal.peek() !== signal.peek()) {
            delayingSignal.value = signal.value
        }
    })
    return delayingSignal
}