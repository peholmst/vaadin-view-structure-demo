export function singleValueToArray<V>(value: V | undefined): V[] {
    return value != null ? [value] : []
}