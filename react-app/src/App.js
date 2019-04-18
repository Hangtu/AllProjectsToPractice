import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

import Header from './header/Header';

class App extends Component {

  constructor() {
    super();
    this.state = {names:[{
      name: "Paco",
    },
    {
      name: "Juan"
    }
    ]}
  }



  render() {
    const rends = this.state.names.map(element => {
      return (
        <p>{element.name}</p>
     )
    });
  
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
            {rends}
        </header>
        <Header title="Hello Hang Tu" />
      </div>
    );
  }
}

export default App;
