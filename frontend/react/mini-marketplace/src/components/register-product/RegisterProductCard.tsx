import React from 'react'
import { TextFieldComponent } from '../common-components/TextFieldComponent';

export const RegisterProductCard = () => {
  const [imageUrl, setImageUrl] = React.useState("https://ralfvanveen.com/wp-content/uploads/2021/06/Placeholder-_-Glossary.svg");

  const handleImageUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files && event.target.files[0]) {
      const imageUrl = URL.createObjectURL(event.target.files[0]);
      setImageUrl(imageUrl);
    }
  };
  
  const postProduct = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files && event.target.files[0]) {
      const imageUrl = URL.createObjectURL(event.target.files[0]);
      setImageUrl(imageUrl);
    }
  };

  return (
    <div>
        <h2>Image of product</h2>
        <img src={imageUrl} alt="" />
        <input type="file" onChange={handleImageUpload} />
        <TextFieldComponent textFieldTitle="Name of Product" type="text"/>
        <TextFieldComponent textFieldTitle="Price" type="number"/>
        <TextFieldComponent textFieldTitle="Description" type="textarea"/>
        <TextFieldComponent textFieldTitle="Color" type="text"/>
        <TextFieldComponent textFieldTitle="Condition" type="text"/>
        <TextFieldComponent textFieldTitle="Status" type="text"/>
        <button>Post product</button>
    </div>
  )
}