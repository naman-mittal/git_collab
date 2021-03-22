import React, { Component } from "react";
import { connect } from "react-redux";
import User from "./User.js";
import * as actions from '../actions/user'

class ViewUser extends Component {
  constructor() {
    super();
    this.state = { users: [] };
  }
  
  fetchData()
  {

    console.log("fetching data.....")

    // fetch("http://localhost:8080/api/v1/users", {
    //   method: "GET",
    // })
    //   .then((response) => response.json())
    //   .then((response) => {
    //     console.log(response)
    //     if(response.length>0)
    //     this.setState({ users: response });
    //     else
    //     this.setState({users:[]})
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });

  }

  componentDidMount() {
    this.props.onFetchUsers()
  }

 

  render() {
    var usersList = this.props.users.map((user, i) => {
      return (
        <User
          key={user.id}
          id={user.id}
          name={user.username}
          email={user.email}
          fetchData={this.fetchData.bind(this)}
        ></User>
      );
    });

    return (
      <div>
        <div>{usersList}</div>
      </div>
    );
  }
}


const mapStateToProps = (state) =>{
  return{
    users : state.users
  }
}

const mapDispatchToState = (dispatch) =>{
  return {
    onFetchUsers : () => dispatch(actions.fetchUsers())
  }
}

export default connect(mapStateToProps,mapDispatchToState)(ViewUser);