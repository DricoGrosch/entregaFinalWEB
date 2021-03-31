async function serializeForm(form) {
    const formData = {}
    form.serializeArray().forEach(input => {
        formData[input.name] = input.value
    })
    return formData
}