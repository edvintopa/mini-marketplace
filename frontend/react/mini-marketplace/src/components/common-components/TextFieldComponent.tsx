import React from 'react';
import '../../CSS-files/index.css';

//
export const TextFieldComponent = ({textFieldTitle, type}: {textFieldTitle: string, type: string} ) => {
  return (
    <div className="textFieldBox">
      <h3 className="textFieldTitle">{textFieldTitle}</h3>
      <input type={type} />
    </div>
  )
}

