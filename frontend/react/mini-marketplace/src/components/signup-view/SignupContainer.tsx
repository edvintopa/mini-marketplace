import React, { useState } from "react";
import { TextFieldComponent } from "../common-components/TextFieldComponent";
import { useUser } from "../../context/UserContext";
import { useNavigate } from "react-router-dom";

export const SignUpContainer: React.FC = () => {
    const { signupUser } = useUser();
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        username: '',
        password: '',
        dateOfBirth: '',
        email: ''
    });
    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const navigate = useNavigate();

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        const success = await signupUser(formData);
        if (success) {
            navigate('/');
        } else {
            setErrorMessage('Signup failed, please try again');
        }
    };

    return (
        <form className="SignUpContainer" onSubmit={handleSubmit}>
            <h1 className='titleOfContainer'>SIGN UP</h1>
            <p className='LoginDescription'>Already have an account? <a href="http://localhost:5173/login">Log in!</a></p>
            <div className="TextBoxForms">
                <div className="NameContainer">
                    <div className="FirstNameTextBox">
                        <TextFieldComponent textFieldTitle="First Name" type="text" name="firstName" onChange={handleInputChange} />
                    </div>
                    <div className="LastNameTextBox">
                        <TextFieldComponent textFieldTitle="Last Name" type="text" name="lastName" onChange={handleInputChange} />
                    </div>
                </div>
                <TextFieldComponent textFieldTitle="Username" type="text" name="username" onChange={handleInputChange} />
                <TextFieldComponent textFieldTitle="Password" type="text" name="password" onChange={handleInputChange} />
                <TextFieldComponent textFieldTitle="Date of Birth" type="date" name="dob" onChange={handleInputChange} />
                <TextFieldComponent textFieldTitle="Email" type="text" name="email" onChange={handleInputChange} />
            </div>
            <div className="SignUpButtonWrapper">
                <button type="submit">Sign Up</button>
            </div>
            {errorMessage && <p className="error-message">{errorMessage}</p>}
        </form>
    );
};
