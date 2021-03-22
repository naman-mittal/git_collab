import React, { Component } from "react";

export default class Workout extends Component {
  //   deleteUser(id) {
  //     //console.log("deleting user "  + id)

  //     const url = "http://localhost:8080/api/v1/users/" + id;

  //     fetch(url, {
  //       method: "DELETE",
  //     })
  //       .then((response) => {
  //         console.log(response);
  //         this.props.fetchData()
  //         //this.setState({ users: response, message: "Successfully Deleted !!" });
  //       })
  //       .catch((err) => {
  //         console.log(err);
  //       });

  //   }

  render() {
    return (
      <div className="card">
        <img src="image_workout.jpg" className="card-img-top" alt="..." />
        <div className="card-body">
          <span className="badge rounded-pill bg-dark">{this.props.id}</span>
          <h5 className="card-title strong">{this.props.title}</h5>
          <p className="card-text">
            <strong>note : </strong>
            {this.props.note}
          </p>
          <p className="card-text">
            <strong>caloriesburnt/m : </strong>
            {this.props.caloriesburnt}
          </p>
          <button className="btn btn-success">Start</button>
          {/* <Link to={"/update/"+this.props.id}><button  className="btn btn-primary">Edit</button></Link> */}
        </div>
      </div>
    );
  }
}
