import React from 'react'
import { TextFieldComponent } from '../common-components/TextFieldComponent';

export const SignUpContainer = () => {
  return (
    <div>
        <TextFieldComponent textFieldTitle="First Name" />
        <TextFieldComponent textFieldTitle="Last Name" />
        <TextFieldComponent textFieldTitle="Username" />
        <TextFieldComponent textFieldTitle="Password" />
        <TextFieldComponent textFieldTitle="Date of Birth" />
        <TextFieldComponent textFieldTitle="Email" />
        <button>LOG IN</button>
    </div>
  )
}

