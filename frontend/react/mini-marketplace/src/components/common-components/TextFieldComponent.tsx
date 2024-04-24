import React from 'react';
import '../../CSS-files/index.css';

//
export const TextFieldComponent = ({textFieldTitle}: {textFieldTitle: string} ) => {
  return (
    <div className="textFieldBox">
      <h3 className="textFieldTitle">{textFieldTitle}</h3>
      <input type="submit" value="Submit" />
    </div>
  )
}

