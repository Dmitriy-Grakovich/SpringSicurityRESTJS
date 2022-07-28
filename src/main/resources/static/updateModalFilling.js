$('#exampleModal').on('show.bs.modal', function (event) {

    let button = $(event.relatedTarget)
    // Button that triggered the modal
    // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    let modal = $(this)
    let id = button.data('id')
    modal.find('[name="id"]').val(id)
    modal.find('[name="name"]').val(button.data('name'))
    modal.find('[name="lastName"]').val(button.data('lastname'))
    modal.find('[name="age"]').val(button.data('age'))
    modal.find('[name="email"]').val(button.data('email'))
    // modal.find('[name="password"]').val(button.data('password'))

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