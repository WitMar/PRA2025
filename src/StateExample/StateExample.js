import React, {useState} from 'react';

function StateExample() {
    const [count, setCount] = useState(0);

     return (
        <div>
            <p>You clicked {count} times</p>
            <p class="indented"><button onClick={() => setCount(count + 1)}>
                Click me
            </button>
            </p>
        </div>
    );
}

export default StateExample;