import React, { Component } from "react";
import { connect } from "react-redux";
import * as actions from '../actions/user'

class UpdateUser extends Component {
  constructor() {
    super();
    this.id = React.createRef();
    this.name = React.createRef();
    this.email = React.createRef();
    this.password = React.createRef();
    this.state = { user : {},message: "" };
  }

  updateUser() {
    console.log("updating...");

    var user = {
      id: this.id.current.value,
      username: this.name.current.value,
      email: this.email.current.value,
      password: this.password.current.value,
    };

    this.props.onUpdateUser(user)

    // const url = "http://localhost:8080/api/v1/user";

    // fetch(url, {
    //   method: "PUT",
    //   headers: {
    //     "content-type": "application/json",
    //     accept: "application/json",
    //   },
    //   body: JSON.stringify(user),
    // })
    //   .then((response) => {
    //     console.log(response);
    //     if (response.status === 204) this.setState({ message: "Updated..." });
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
  }

  componentDidMount()
  {
    console.log("fetching user")
    this.props.onFetchUser(this.props.match.params.id)

    // this.name.current.value = this.props.user.username
    //       this.email.current.value = this.props.user.email
    //       this.password.current.value = this.props.user.password

    // const url =  "http://localhost:8080/api/v1/users/"+this.props.match.params.id

    // fetch(url, {
    //   method: "GET",
    // })
    //   .then((response) => response.json())
    //   .then((response) => {
    //     console.log(response)
    //     if(response!=null)
    //     {
    //       //this.setState({ user: response });
    //       this.name.current.value = response.username
    //       this.email.current.value = response.email
    //       this.password.current.value = response.password
    //     }
        
    //     else
    //     this.setState({workouts:[]})
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });

  }

  render() {

    //this.name.current.value = this.props.user===undefined ? '' : this.props.user.username

    return (
      <div>
        <div className="w-50 user-form">
          <div className="input-group mb-3">
            <input
              ref={this.id}
              value = {this.props.match.params.id}
              disabled
              type="text"
              className="form-control"
              placeholder="Id"
            />
          </div>
          <div className="input-group mb-3">
            <input
              ref={this.name}
              type="text"
              className="form-control"
              placeholder="Name"
            />
          </div>
          <div className="input-group mb-3">
            <input
              ref={this.email}
              type="text"
              className="form-control"
              placeholder="Email"
            />
          </div>
          <div className="input-group mb-3">
            <input
              ref={this.password}
              type="password"
              className="form-control"
              placeholder="Password"
            />
          </div>
          <button
            className="add-btn btn btn-primary"
            onClick={this.updateUser.bind(this)}
          >
            Update
          </button>
        </div>

        <div className="alert alert-success" role="alert">
          {this.props.message}
        </div>
      </div>
    );
  }
}


const mapStateToProps = (state)=>{
  
  return {
   
    message : state.message,
    user : state.updateUser
  }
}

const mapDispatchToState = (dispatch)=>{

  return {
    onUpdateUser : (payload) => dispatch(actions.EditUser(payload)),
    onFetchUser : (id) => dispatch(actions.fetchUser(id))
  }

}

export default connect(mapStateToProps,mapDispatchToState)(UpdateUser)