function alarmCheckBox(e) {
    const items = document.getElementsByName("userAlarm");
    items.forEach((item) => {
        item.checked = false;
    })
    e.checked = true;
}