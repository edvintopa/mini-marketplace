import React, { useState } from 'react';
import { TextFieldComponent } from '../common-components/TextFieldComponent';
import { useUser } from '../../context/UserContext';
import { useNavigate } from 'react-router-dom';

export const LoginViewContainer: React.FC = () => {
    const { loginUser } = useUser();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const navigate = useNavigate();

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        if (name === 'username') {
            setUsername(value);
        } else if (name === 'password') {
            setPassword(value);
        }
    };

    const handleLogin = async (event: React.FormEvent) => {
        event.preventDefault();
        const success = await loginUser(username, password);
        if (success) {
            navigate('/');
        } else {
            setErrorMessage('Username or password was incorrect');
        }
    };

    return (
        <div className="loginContainer">
            <TextFieldComponent textFieldTitle="Email or Username" type="text" name="username" onChange={handleInputChange} />
            <TextFieldComponent textFieldTitle="Password" type="password" name="password" onChange={handleInputChange} />
            <div className="LoginButtonContainer">
                <button className="LoginButton" onClick={handleLogin}>Log in</button>
                {errorMessage && <div className="error-message">{errorMessage}</div>}
            </div>
        </div>
    );
}
