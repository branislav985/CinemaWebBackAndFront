import React from 'react'

class Home extends React.Component {
  render() {
    
    let username = window.localStorage.getItem("username");
    
    return <h1>Welcome, {username}</h1>
  }
}

export default Home;