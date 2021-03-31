async function serializeForm(form) {
    const formData = {}
    form.serializeArray().forEach(input => {
        formData[input.name] = input.value
    })
    return formData
}
  async function redirectToEdit(id,formTemplate) {
        window.location.href = `http://localhost:8080/Trabalho1/pages/${formTemplate}?id=${id}`
    }