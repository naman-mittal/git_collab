

const initialState = {
    users : [
       
    ],
    updateUser : {},
    message : ''
}

const reducer = (state = initialState, { type, payload }) => {

    console.log(type);
    switch (type) {
        
    case "FIND_USERS" :
        return {users : payload,message:''}

    case "FIND_USER" :
            return {users : state.users,updateUser : payload.user}

    case "ADD_USER":
        return {users: state.users,message:payload.message}

    case "DELETE_USER":
        var filteredList = state.users.filter((user)=> user.id !== payload.user.id)
        return {users: filteredList,message:''}

    case "UPDATE_USER":
        console.log(payload.message)
            return {users: state.users,message:payload.message}
    
    default:
        return state
    }
}

export default reducer