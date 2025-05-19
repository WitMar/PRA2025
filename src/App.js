import './App.css';
import React from "react";
import StateExample from "./StateExample/StateExample";
import {ThemeContext} from "./ContextExample/ThemeProvider";

function App() {

    const {theme, toggle, dark} = React.useContext(ThemeContext)

    return (
        <div>
            Test Application
            <StateExample/>
        </div>
    );
}

export default App;
