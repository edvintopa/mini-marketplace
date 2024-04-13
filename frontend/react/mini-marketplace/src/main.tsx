import { BrowserRouter } from 'react-router-dom';
import ReactDOM from 'react-dom';
import React from 'react';
import App from './App';
import './CSS-files/index.css';


ReactDOM.render(
    <React.StrictMode>
    <BrowserRouter>
        <App />
    </BrowserRouter>
    </React.StrictMode>,
    document.getElementById('root')
);
