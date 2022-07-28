$(async function () {
    await inAccount()
    await allUsers()
    await createUser()
})
const userFetch = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },
    findAllUsers: async () => await fetch('api/users'),
    findUserByUsername: async () => await fetch('api/user'),
    addNewUser: async (userDTO) => await fetch(`api/user`, {method: 'POST', headers: userFetch.head, body: JSON.stringify(userDTO)}),
    updateUser: async (userDTO, id) => await fetch(`api/user/${id}`, {method: 'PUT', headers: userFetch.head, body: JSON.stringify(userDTO)}),
    deleteUser: async (id) => await fetch(`api/user/${id}`, {method: 'DELETE', headers: userFetch.head})
}
