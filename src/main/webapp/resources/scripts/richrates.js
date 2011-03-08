// disables weekends in all calendars so that these days cannot be selected
function disablementFunction(day) {
    if (day.isWeekend)
        return false;

    return true;
}

// styles disabled days in calendar (weekends)
function disabledClassesProv(day) {
    var result = '';
    if (!disablementFunction(day))
        result += 'disabled-day';
    return result;
}