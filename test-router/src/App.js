import "./App.css";
import ViewUser from "./Components/ViewUser";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import AddUser from "./Components/AddUser";
import UpdateUser from "./Components/UpdateUser";
import ViewWorkout from "./Components/ViewWorkout";
import AddWorkout from "./Components/AddWorkout";

function App() {
  return (
    <Router>
      <div>
        {/* <ul>
          <li>
            
            <Link to="/">View Employees</Link>
          </li>
          <li>
         
            <Link to="/add">Add Employee</Link>
          </li>
    
        </ul> */}

        

        <ul className="nav">
        <div className="navbar-brand ">Personal Workout Trainer</div>
          <li className="nav-item">
            <Link className="nav-link active" aria-current="page" to="/">
              View User
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/adduser">
              Add User
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/workouts">
              View Workouts
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/addworkout">
              Add Workout
            </Link>
          </li>
          {/* <li className="nav-item">
            <Link className="nav-link" to="/update">
              Update User
            </Link>
          </li> */}
        </ul>

        <hr />

        <Switch>
          <Route exact path="/">
            <ViewUser />
          </Route>
          <Route path="/adduser">
            <AddUser />
          </Route>
          <Route path="/workouts">
            <ViewWorkout />
          </Route>
          <Route path="/addworkout">
              <AddWorkout/>
          </Route>
          {/* <Route path="/update">
            <UpdateUser></UpdateUser>
          </Route> */}
          <Route path="/update/:id" component={UpdateUser}></Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
