import React from 'react';
import ReactDOM from 'react-dom';
import {App} from "./App"
import {store} from "kotlin-library"

console.log(store.state) // undefined, why?

ReactDOM.render(
    <App store={store}/>,
    document.getElementById('app')
);