
async function inAccount() {

    let accountEmail = document.querySelector('#emailAccount')
    let accountRoles = document.querySelector('#roleAccount')
    let account = await (await userFetch.findUserByUsername()).json()
    let userEmail = account.email
    let userRole = account['roles'].map(e => " " + e.name)
    accountEmail.innerHTML = userEmail
    accountRoles.innerHTML =  userRole
    let userId = document.querySelector('#UserId')
    if (userId!=null) {
        userId.innerHTML = account.id
        let userName = document.querySelector('#UserName')
        let userLastName = document.querySelector('#UserLastName')
        let userAge = document.querySelector('#UserAge')
        let userEmailTab = document.querySelector('#UserEmail')
        let userRoleTab = document.querySelector('#UserRole')
        userName.innerHTML = account.name
        userLastName.innerHTML = account.lastName
        userAge.innerHTML = account.age
        userEmailTab.innerHTML = userEmail
        userRoleTab.innerHTML = userRole
    }

}