import React, { Component } from "react";

export default class AddWorkout extends Component {
  constructor() {
    super();
    this.title = React.createRef();
    this.note = React.createRef();
    this.cbpm = React.createRef();
    this.category = React.createRef();
    this.state = { message: "", categories: [] };
  }

  componentDidMount() {
    fetch("http://localhost:8080/api/v1/categories", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((response) => {
        console.log(response);
        if (response.length > 0) this.setState({ categories: response });
        else this.setState({ categories: [] });
      })
      .catch((err) => {
        console.log(err);
      });
  }

  addWorkout() {
    var workout = {
      title: this.title.current.value,
      note: this.note.current.value,
      caloriesBurntPerMinute: this.cbpm.current.value,

      category: {
        name: this.category.current.value,
      },
    };

    const url = "http://localhost:8080/api/v1/workouts";

    fetch(url, {
      method: "POST",
      headers: {
        "content-type": "application/json",
        accept: "application/json",
      },
      body: JSON.stringify(workout),
    })
      .then((response) => {
        console.log(response);
        if (response.status === 201) this.setState({ message: "Inserted..." });
      })
      .catch((err) => {
        console.log(err);
      });
  }

  render() {
    var categoriesList = this.state.categories.map((cat, i) => {
      return (
        <option key={cat.id} className="dropdown-item" value={cat.name}>
          {cat.name}
        </option>
      );
    });

    return (
      <div>
        <div className="alert alert-success" role="alert">
          {this.state.message}
        </div>
        <div className="w-50 user-form">
          <div className="input-group mb-3">
            <input
              ref={this.title}
              type="text"
              className="form-control"
              placeholder="Title"
            />
          </div>
          <div className="input-group mb-3">
            <input
              ref={this.note}
              type="text"
              className="form-control"
              placeholder="Note"
            />
          </div>
          <div className="input-group mb-3">
            <input
              ref={this.cbpm}
              type="number"
              className="form-control"
              placeholder="Calories Burnt per Minute"
            />
          </div>
          <label>Choose a category : </label>

          <select name="category" ref={this.category}>
            {categoriesList}
          </select>
          <br />
          <br />
          <button
            className="add-btn btn btn-primary"
            onClick={this.addWorkout.bind(this)}
          >
            Add
          </button>
        </div>
      </div>
    );
  }
}
