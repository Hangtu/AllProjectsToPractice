import React, { Component } from 'react';
import './Header.css';

class Header extends Component {

    constructor(){
        super();
        this.state = {
            hello : "A"
        }
    }

    render() {
      return (
        <div className="header">
            {this.state.hello}
        </div>
      );
    }
  }
  
  export default Header;
  