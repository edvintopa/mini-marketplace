import React, { useState } from "react";
import { TextFieldComponent } from "../common-components/TextFieldComponent";

export const SignUpContainer = () => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        username: '',
        password: '',
        dateOfBirth: '',
        email: ''
    });

    const handleInputChange = (event: any) => {
        setFormData({
            ...formData,
            [event.target.name]: event.target.value
        });
    };

    const handleSubmit = async (event: any) => {
        event.preventDefault();

        const response = await fetch('http://localhost:8080/user/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        const data = await response.json();
        console.log(data);
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
                <button type="submit">SIGN IN</button>
            </div>
        </form>
    );
};