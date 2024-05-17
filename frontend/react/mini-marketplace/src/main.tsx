import { BrowserRouter } from 'react-router-dom';
import ReactDOM from 'react-dom';
import React from 'react';
import App from './App';
import './CSS-files/index.css';
import { UserProvider } from './context/UserContext';
import { ThemeProvider } from './context/ThemeContext';


ReactDOM.render(
    <React.StrictMode>
        <ThemeProvider>
            <UserProvider>
                <BrowserRouter>
                    <App />
                </BrowserRouter>
            </UserProvider>
        </ThemeProvider> 
    </React.StrictMode>,
    document.getElementById('root')
);
