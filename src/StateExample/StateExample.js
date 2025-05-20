import React, {useEffect, useRef, useState} from 'react';
import useCustomExample from "./UseCustomExample";

function StateExample() {
    const [count, setCount] = useState(0);
    const parity = useCustomExample(count);

    const initialRenderTimeRef = useRef(new Date().toLocaleString());

    useEffect(() => {
        console.log("side effect 1", count);
        return () => {
            console.log("DESTROYED 1");
        };
    });

    function refreshDate(param) {
        initialRenderTimeRef.current = new Date().toLocaleString();
    }

    return (
        <div>
            <p>You clicked {count} times</p>
            <p>Initial render time (stored in ref): {initialRenderTimeRef.current}</p>
            <p class="indented">
                <button onClick={() => setCount(count + 1)}>
                    Click me
                </button>
                <button onClick={() => refreshDate()}>
                    Refresh Time
                </button>
                <p></p>
                <p class="indented">{parity}</p>
            </p>
        </div>
    );
}

export default StateExample;