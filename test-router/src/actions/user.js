
const saveUser = () =>{

    return {type : "ADD_USER",payload : {message : "Successfully added user"}}

}

export const addUser = (payload) =>{
     const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    };
    return dispatch => {
        fetch('http://localhost:8080/api/v1/users', requestOptions)
            .then(res => {
                console.log(res)
                // console.log(res.json())
                if(res.status === 201){
                    console.log("success");
                    dispatch(saveUser())
                }
            })
}}

const findUsers = (payload) =>{
    return {type : "FIND_USERS",payload}
}

export const fetchUsers = () => {

    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    };
    return dispatch => {
        fetch('http://localhost:8080/api/v1/users', requestOptions)
            .then(res => {
                console.log(res);
                return res.json();
            })
            .then(data => {
                console.log(data);
                dispatch(findUsers(data));
            })
        
    }

}

const removeUser = (payload) =>{

    return {type : "DELETE_USER",payload}

}

export const deleteUser = (id) =>{
     const requestOptions = {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' }
    };
    return dispatch => {
        let message = ''
        fetch("http://localhost:8080/api/v1/users/" + id, requestOptions)
            .then(res => {
                console.log(res)
                
                if(res.status === 200){
                   message = 'succesfully deleted user'
                }
                else
                message = 'failed'

                return res.json()
            }).then(data=>{
                console.log(data)
                dispatch(removeUser({user : data,message}))
            })
            
}}


// const findUser = (user) =>{
//     return {type : "FIND_USER",payload : {user}}
// }

// export const fetchUser = (id) => {

//     const requestOptions = {
//         method: 'GET',
//         headers: { 'Content-Type': 'application/json' }
//     };
//     return dispatch => {
//         fetch('http://localhost:8080/api/v1/users/'+id, requestOptions)
//             .then(res => {
//                 console.log(res);
//                 return res.json();
//             })
//             .then(data => {
//                 console.log(data);
//                 dispatch(findUser(data));
//             })
        
//     }

// }

const updateUser = () =>{
console.log('inside update user')
    return {type : "UPDATE_USER",payload : {message : "Successfully updated user"}}

}

export const EditUser = (payload) =>{
     const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    };
    return dispatch => {
        fetch('http://localhost:8080/api/v1/user', requestOptions)
            .then(res => {
                console.log(res)
                // console.log(res.json())
                if(res.status === 204){
                    console.log("success");
                    dispatch(updateUser())
                }
            })
}}
