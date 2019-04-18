import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import Header from './header/Header';
import * as serviceWorker from './serviceWorker';

/*props   state
Can get initial value from parent Component?    Yes     Yes
Can be changed by parent Component?             Yes     No
Can set default values inside Component?*       Yes     Yes
Can change inside Component?                    No      Yes
Can set initial value for child Components?     Yes     Yes
Can change in child Components?                 Yes     No*/

ReactDOM.render(<Header />, document.getElementById('head'));
ReactDOM.render(<App />, document.getElementById('root'));


// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
