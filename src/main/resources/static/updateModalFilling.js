$('#exampleModal').on('show.bs.modal', function (event) {

    let button = $(event.relatedTarget)
    let modal = $(this)
    let id = button.data('id')
    modal.find('[name="id"]').val(id)
    modal.find('[name="name"]').val(button.data('name'))
    modal.find('[name="lastName"]').val(button.data('lastname'))
    modal.find('[name="age"]').val(button.data('age'))
    modal.find('[name="email"]').val(button.data('email'))

    $("#buttonEdit").on('click', async () => {

        let firstName = modal.find('[id="nameUser"]').val()
        let lastName = modal.find('[id="lastNameUser"]').val()
        let age = modal.find('[id="ageUser"]').val()
        let email = modal.find('[id="emailUser"]').val()
        let password = modal.find('[id="passwordUser"]').val()
        let role = modal.find('[id="roleUser"]').val()

        let userDTO = {
            id: id,
            name: firstName,
            lastName: lastName,
            age: age,
            email: email,
            password: password,
            role: role
        }

        const response = await userFetch.updateUser(userDTO, id)

        if (response.ok) {
            await allUsers()
        } else {
            alert('Изменения не сохранились')
        }
    })
})