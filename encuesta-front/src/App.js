import React from "react";
import "./App.css";
import Messages from "./components/messages";
import Navbar from "./components/navbar";
import Say from "./components/say";
import { BrowserRouter, Route } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navbar />
        <br />
        <br />
        <br />
        <Route path="/say/:userId" component={Say} />
        <Route path="/messages" component={Messages} />
      </div>
    </BrowserRouter>
  );
}

export default App;
