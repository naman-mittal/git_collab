
var id = 1;

const initialState = {
    users : [
        {id : 1, username : 'Redux' , email : 'redux@react.com' , password : 'react'}
    ],
    message : ''
}

const reducer = (state = initialState, { type, payload }) => {

    console.log(type);
    switch (type) {
        
    case "ADD_USER":
        id++;
        payload = {...payload,id}
        let newUsers = [...state.users, payload]
        return {users: newUsers,message:'Successfully added... to redux'}
    case "DELETE_USER":
        
        var filteredList = state.users.filter((user)=> user.id !== payload.id)
        console.log(filteredList)
        return { users: filteredList , message : 'Successfully deleted... from redux'}
    default:
        return state
    }
}

export default reducer