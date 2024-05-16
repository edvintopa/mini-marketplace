import React from 'react';
import '../../CSS-files/index.css';
import { TextFieldComponentTypes } from "../../types/types.ts";

//
export const TextFieldComponent = ({textFieldTitle, type, name, onChange}: TextFieldComponentTypes ) => {
    return (
        <div className="textFieldBox">
            <h3 className="textFieldTitle">{textFieldTitle}</h3>
            <input type={type} name={name} onChange={onChange} />
        </div>
    )
}

