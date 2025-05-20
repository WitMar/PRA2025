import './App.css';
import React from "react";
import StateExample from "./StateExample/StateExample";
import {ThemeContext} from "./ContextExample/ThemeProvider";
import Form from "./Form/Form";

function App() {

    const {theme, toggle, dark} = React.useContext(ThemeContext)

    return (
        <div className="App" style={{backgroundColor: theme.backgroundColor, color: theme.color}}>
            Test
            <StateExample/>
            <button
                type="button"
                onClick={toggle}
                style={{
                    backgroundColor: theme.backgroundColor,
                    color: theme.color,
                    outline: 'none'
                }}
            >
                Toggle to {!dark ? 'Dark' : 'Light'} theme
            </button>
            <Form/>
        </div>
    );
}

export default App;