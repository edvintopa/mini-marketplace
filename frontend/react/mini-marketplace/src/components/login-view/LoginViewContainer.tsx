import React from 'react';
import { TextFieldComponent } from '../common-components/TextFieldComponent';


export const LoginViewContainer = () => {
  return (
    <div className="loginContainer">
        <TextFieldComponent textFieldTitle="Email or Username" type="text"/>
        <TextFieldComponent textFieldTitle="Password" type="text"/>
        <div className='LoginButtonContainer'>
        <button className='LoginButton'>Log in</button>
        </div>
        
    </div>
  )
}

