
import { useEffect, useState } from 'react';

function useCustomExample(counter) {
    const [word, setWord] = useState('Parzyste');

    useEffect(() => {
        if (counter % 2 === 0) {
            setWord('Parzyste');
        } else {
            setWord('Nieparzyste');
        }
    }, [counter]);

    return word;
}

export default useCustomExample;