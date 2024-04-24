import React from 'react';
import { TextFieldComponent } from '../common-components/TextFieldComponent';


export const loginViewContainer = () => {
  return (
    <div className="loginContainer">
        <TextFieldComponent textFieldTitle="Email or Username" />
        <TextFieldComponent textFieldTitle="Password" />
        <button>LOG IN</button>
    </div>
  )
}

