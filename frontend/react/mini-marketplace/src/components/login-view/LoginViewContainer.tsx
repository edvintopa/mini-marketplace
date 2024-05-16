import React, { useState } from 'react';
import { TextFieldComponent } from '../common-components/TextFieldComponent';

export const LoginViewContainer = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleInputChange = (event: any) => {
        if (event.target.name === 'username') {
            setUsername(event.target.value);
        } else if (event.target.name === 'password') {
            setPassword(event.target.value);
        }
    };

    const handleLogin = async (event: any) => {
        event.preventDefault();

        const response = await fetch('http://localhost:8080/user/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });

        const data = await response.json();
        console.log(data);
    };

    return (
        <div className="loginContainer">
            <TextFieldComponent textFieldTitle="Email or Username" type="text" name="username" onChange={handleInputChange} />
            <TextFieldComponent textFieldTitle="Password" type="password" name="password" onChange={handleInputChange} />
            <div className='LoginButtonContainer'>
                <button className='LoginButton' onClick={handleLogin}>Log in</button>
            </div>
        </div>
    )
}