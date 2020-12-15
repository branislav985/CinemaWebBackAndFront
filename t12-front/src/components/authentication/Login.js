import React from "react";

import {login} from '../../services/auth';

class Login extends React.Component {
  constructor() {
    super();

    this.state = { username: "", password: "" };
  }

  valueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let change = {};
    change[name] = value;
    this.setState(change);
  }

  doLogin(e){
    e.preventDefault();

    login(this.state.username, this.state.password);
  }

  // TODO: Ulepšati: - centrirati, udaljiti od vrha, staviti jumbotron
  // TODO: Završiti implementaciju
  render() {
    return (
      <div>
        <h1>Welcome!</h1>
       <form>
          <div>
            <label>Username</label>
            <input
              onChange={(e) => {
                this.valueInputChange(e);
              }}
              name="username"
              
            ></input>
          </div>
          <div>
            <label>Password</label>
            <input
              onChange={(e) => {
                this.valueInputChange(e);
              }}
              name="password"
              type="password"
            ></input>
          </div>
          <button onClick={(e) => {this.doLogin(e);}}>Log in</button>
        </form>
      </div>
    );
  }
}

export default Login;
