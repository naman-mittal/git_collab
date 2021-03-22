import React, { Component } from 'react'
import Workout from './Workout'

export default class ViewWorkout extends Component {
    constructor() {
        super();
        this.state = { workouts: [] };
      }
      
      fetchData()
      {
    
        console.log("fetching data for workouts.....")
    
        fetch("http://localhost:8080/api/v1/workouts", {
          method: "GET",
        })
          .then((response) => response.json())
          .then((response) => {
            console.log(response)
            if(response.length>0)
            this.setState({ workouts: response });
            else
            this.setState({workouts:[]})
          })
          .catch((err) => {
            console.log(err);
          });
    
      }
    
      componentDidMount() {
        this.fetchData()
      }
    
     
    
      render() {
        var workoutsList = this.state.workouts.map((workout, i) => {
          return (
            <Workout
              key={workout.id}
              id={workout.id}
              title={workout.title}
              note={workout.note}
              caloriesburnt={workout.caloriesBurntPerMinute}
              fetchData={this.fetchData.bind(this)}
            ></Workout>
          );
        });
    
        return (
          <div>
            <div>{workoutsList}</div>
          </div>
        );
      }
    
}
