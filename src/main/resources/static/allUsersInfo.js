async function allUsers() {
    let html = ''
    let tBody = document.querySelector('#tabBody')
    let users = await (await userFetch.findAllUsers()).json()
    users.forEach(user => {

        html += `<tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.lastName}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>${user['roles'].map(e => " " + e.name)}</td>
                <td><button type="button" class="btn btn-info"data-action="edit"
                data-toggle="modal" data-target="#exampleModal"
                data-id="${user.id}"
                data-password = "${user.password}"
                data-name="${user.name}"
                data-lastname="${user.lastName}"
                data-age="${user.age}"
                data-email="${user.email}">Edit</button></td>
                <td><button type="button" class="btn btn-danger" data-toggle="modal"
                data-target="#exampleModalDelete"
                data-id="${user.id}"
                data-name="${user.name}"
                data-lastname="${user.lastName}"
                data-age="${user.age}"
                data-email="${user.email}"
                >Delete</button></td></tr>`

    })
    tBody.innerHTML = html
}