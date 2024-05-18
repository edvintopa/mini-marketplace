import React from 'react'
import { LoginViewContainer } from './login-view/LoginViewContainer';
import "./../CSS-files/loginsignup.css";
import './../CSS-files/index.css';

export const LoginPageApp = () => {
  return (
    <div className='LoginPageApp'>
      <h1 className='titleOfContainer'>LOG IN</h1>
      <p className='LoginDescription'>Don't have an account? <a href="http://localhost:5173/signup">Sign up!</a></p>
        <LoginViewContainer/>
  
        </div>
  )
}
