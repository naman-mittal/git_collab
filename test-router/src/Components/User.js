import React, { Component } from 'react'
import { connect } from 'react-redux';
import {Link } from "react-router-dom";
import * as actions from '../actions/user'

class User extends Component {

  deleteUser(id) {
    //console.log("deleting user "  + id)


    this.props.onDeleteUser(id)

    // const url = "http://localhost:8080/api/v1/users/" + id;

    // fetch(url, {
    //   method: "DELETE",
    // })
    //   .then((response) => {
    //     console.log(response);
    //     this.props.fetchData()
    //     //this.setState({ users: response, message: "Successfully Deleted !!" });
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });

     
  }
  
    render() {
        return (
            <div className="card">
            <img src="img_avatar.png" className="card-img-top" alt="..."/>
            <div className="card-body">
            <span className="badge rounded-pill bg-dark">{this.props.id}</span>
              <h5 className="card-title">{this.props.name}</h5>
              <p className="card-text">{this.props.email}</p>
              <button  className="btn btn-danger" onClick={this.deleteUser.bind(this,this.props.id)}>Delete</button>
              <Link to={"/update/"+this.props.id}><button  className="btn btn-primary">Edit</button></Link>
            </div>
          </div>
        )
    }
}

const mapStateToProps = (state) =>{
  return{
    users : state.users
  }
}

const mapDispatchToState = (dispatch)=>{
  return{
    onDeleteUser : (id) => dispatch(actions.deleteUser(id))
  }
}

export default connect(mapStateToProps,mapDispatchToState)(User)