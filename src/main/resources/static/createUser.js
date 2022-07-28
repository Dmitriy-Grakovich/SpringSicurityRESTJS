async function createUser() {
    const buttAddNewUser = $('#addNewUser')

    buttAddNewUser.click(async function (){
        let addUserForm = $('#newUserForm')

        let firstName = document.querySelector('#firstName')
        let lastName = document.querySelector('#lastName')
        let age = document.querySelector('#age')
        let email = document.querySelector('#email')
        let password =  document.querySelector('#password')
        let role =  document.querySelector('#role')

        let userDTO = {
            name: firstName.value,
            lastName: lastName.value,
            age: age.value,
            email: email.value,
            password: password.value,
            role: role.value
        }

        let response = await userFetch.addNewUser(userDTO)

        if(response.ok){
            await allUsers()
            firstName.value=''
            lastName.value =''
            age.value = ''
            email.value=''
            password.value=''
            $('.nav-tabs a[href="#tab1"]').tab('show');

        } else {
            alert("нас постигла неудача");}
    })

}