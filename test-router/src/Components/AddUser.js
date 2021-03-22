import React, { Component } from "react";
import { connect } from "react-redux";
import * as actions from "../actions/user";

class AddUser extends Component {
  constructor() {
    super();
    this.name = React.createRef();
    this.email = React.createRef();
    this.password = React.createRef();
    this.state = { message: "" };
  }

  addUser() {
    var user = {
      username: this.name.current.value,
      email: this.email.current.value,
      password: this.password.current.value,
    };


    this.props.onAddUser(user)

    // const url = "http://localhost:8080/api/v1/users";

    // fetch(url, {
    //   method: "POST",
    //   headers: {
    //     "content-type": "application/json",
    //     accept: "application/json",
    //   },
    //   body: JSON.stringify(user),
    // })
    //   .then((response) => {
    //     console.log(response);
    //     if (response.status === 201) this.setState({ message: "Inserted..." });
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
  }

  render() {
    return (
      <div>
        <div className="w-50 user-form">
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
            onClick={this.addUser.bind(this)}
          >
            Add
          </button>
        </div>
        <div className="alert alert-success" role="alert">
          {this.props.message}
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state) =>{

  return{
    message : state.message,
    users : state.users
  }

}

const mapDispatchToState = (dispatch) =>{
  return{
    onAddUser : (payload) => dispatch(actions.addUser(payload))
  }
}

export default connect(mapStateToProps,mapDispatchToState)(AddUser)