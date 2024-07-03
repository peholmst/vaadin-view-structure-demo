export function daysSince(date: Date): string {
    const now = new Date()
    let years = now.getFullYear() - date.getFullYear()
    let months = now.getMonth() - date.getMonth()
    let days = now.getDate() - date.getDate()

    if (days < 0) {
        months--
        const prevMonth = new Date(now.getFullYear(), now.getMonth(), 0)
        days += prevMonth.getDate()
    }

    if (months < 0) {
        years--
        months += 12
    }

    return `${years} ${years == 1 ? 'year' : 'years'}, ${months} ${months == 1 ? 'month' : 'months'} and ${days} ${days == 1 ? 'day' : 'days'}`
}

export function localTime(timeZone: string) {
    return new Date().toLocaleTimeString(navigator.language, {timeZone})
}