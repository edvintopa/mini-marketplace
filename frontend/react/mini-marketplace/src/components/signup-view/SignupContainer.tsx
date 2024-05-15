import React from "react";
import { TextFieldComponent } from "../common-components/TextFieldComponent";

export const SignUpContainer = () => {
  return (
    <div className="SignUpContainer">
      <h1 className='titleOfContainer'>SIGN UP</h1>
      <p className='LoginDescription'>Already have an account? <a href="http://localhost:5173/login">Log in!</a></p>
      <div className="TextBoxForms">
        <div className="NameContainer">
          <div className="FirstNameTextBox"><TextFieldComponent textFieldTitle="First Name" type="text" /></div>
          <div className="LastNameTextBox"><TextFieldComponent textFieldTitle="Last Name" type="text"/></div>
        </div>
        <TextFieldComponent textFieldTitle="Username" type="text"/>
        <TextFieldComponent textFieldTitle="Password" type="text"/>
        <TextFieldComponent textFieldTitle="Date of Birth" type="date"/>
        <TextFieldComponent textFieldTitle="Email" type="text"/>
      </div>
      <div className="SignUpButtonWrapper">
        <button>SIGN IN</button>
      </div>
    </div>
  );
};
