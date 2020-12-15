import React from "react";
import ReactDOM from "react-dom";
import { Route, Link, HashRouter as Router, Switch, Redirect } from "react-router-dom";
import Home from "./components/Home";
import AddMovie from "./components/movies/AddMovie";
import EditMovie from "./components/movies/EditMovie";
import Movies from "./components/movies/Movies";
import Projections from "./components/projections/Projections";
import AddProjection from "./components/projections/AddProjection";
import NotFound from "./components/NotFound";

import { Navbar, Nav, Container, Button } from "react-bootstrap";
import Login from "./components/authentication/Login";
import {logout} from './services/auth';

class App extends React.Component {
  render() {

    let token = window.localStorage.getItem("token");

    if(token){
      return (
        <div>
          <Router>
            <Navbar bg="dark" variant="dark" expand fixed="top">
              <Navbar.Brand>
                <Link to="/">JWD</Link>
              </Navbar.Brand>
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/movies">
                  Movies
                </Nav.Link>
                <Nav.Link as={Link} to="/projections">
                  Projections
                </Nav.Link>
              </Nav>
              <Button onClick={()=>logout()}>Logout</Button>
            </Navbar>
  
            <br />
            <br />
            <br />
  
            <Container>
              <Switch>
                <Route exact path="/" component={Home} />
                <Route exact path="/movies" component={Movies} />
                <Route exact path="/movies/add" component={AddMovie} />
                <Route exact path="/movies/edit/:id" component={EditMovie} />
                <Route exact path="/projections" component={Projections} />
                <Route exact path="/projections/add" component={AddProjection} />
                <Route exact path="/login" render={()=><Redirect to="/" />} />
                <Route component={NotFound} />
              </Switch>
            </Container>
          </Router>
        </div>
      );
    }else{

      return (
        <Container>
          <Router>
            <Switch>
              <Route exact path="/login" component={Login} />
              <Route render={()=><Redirect to="/login" />} />
            </Switch>
          </Router>
        </Container>
      );

    }

    
  }
}

ReactDOM.render(<App />, document.querySelector("#root"));
