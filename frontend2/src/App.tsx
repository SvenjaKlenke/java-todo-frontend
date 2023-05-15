import React from 'react';
import './App.css';
import Gallery from "./Gallery";


function App() {
  return (
    <div className="App">
        <header>
        <ul>
          <li>Home</li>
          <li>ToDo</li>
          <li>Doing</li>
          <li>Done</li>
        </ul>
        </header>
        <Gallery/>
    </div>
  );
}

export default App;
