$('#exampleModalDelete').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget) // Button that triggered the modal
    // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    let modal = $(this)
    let id = button.data('id')
    modal.find('[name="userId"]').val(id)
    modal.find('[name="name"]').val(button.data('name'))
    modal.find('[name="lastName"]').val(button.data('lastname'))
    modal.find('[name="age"]').val(button.data('age'))
    modal.find('[name="email"]').val(button.data('email'))
    modal.find(('[name="buttonDelete"]')).val(button.data('id'))
    $("#buttonDelete").on('click', async () => {

        const response = await userFetch.deleteUser(id);

        if (response.ok) {
            await allUsers();
        } else {
            alert('delete не удался')
        }
    })

})