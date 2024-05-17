import React, { useState } from 'react'
import '../../CSS-files/index.css';

const colors = [
    'BLACK', 'WHITE', 'RED', 'BLUE', 'YELLOW', 'ORANGE',
    'GREEN', 'PURPLE', 'GRAY', 'OTHER', 'PINK', 'OLIVE',
    'NAVY', 'MAROON', 'BEIGE', 'BROWN'
];

const conditions = [
    'NEW', 'VERY_GOOD', 'GOOD', 'NOT_WORKING_PROPERLY', 'USED'
];

const statuses = [
  'AVAILABLE', 'NOT_AVAILABLE', 'ON_HOLD'
];

export const RegisterProductCard: React.FC = () => {
    const [imageUrl, setImageUrl] = React.useState("https://ralfvanveen.com/wp-content/uploads/2021/06/Placeholder-_-Glossary.svg");
    const [color, setColor] = useState(colors[0]);
    const [condition, setCondition] = useState(conditions[0]);
    const [status, setStatus] = useState(statuses[0]);  
    const [formData, setFormData] = useState({
        name: '',
        price: 0,
        description: ''
    });

    const handleImageUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files && event.target.files[0]) {
            const imageUrl = URL.createObjectURL(event.target.files[0]);
            setImageUrl(imageUrl);
        }
    };

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = event.target;
        setFormData(prevData => ({ ...prevData, [name]: value }));
    };

    const handleColorChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setColor(event.target.value);
    };

    const handleConditionChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setCondition(event.target.value);
    };

    const handleStatusChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setStatus(event.target.value);
    };

    const postProduct = (event: React.FormEvent) => {
        event.preventDefault();
        //if (event.target.files && event.target.files[0]) {
            //const imageUrl = URL.createObjectURL(event.target.files[0]);
            //setImageUrl(imageUrl);
        //}
    };

    return (
        <div className="register-product-card">
            <h2 className="form-title">Register a new Product</h2>
            <form onSubmit={postProduct} className="register-product-form">
                <div className="image-upload-section">
                    <h3 className="form-section-title">Image of Product</h3>
                    <img src={imageUrl} alt="Product" className="product-image" />
                    <input type="file" onChange={handleImageUpload} />
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Name</h3>
                    <input type="text" name="name" value={formData.name} onChange={handleInputChange} />
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Price</h3>
                    <input type="number" name="price" value={formData.price} onChange={handleInputChange} />
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Description</h3>
                    <input type="text" name="description" value={formData.description} onChange={handleInputChange} />
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Color</h3>
                    <select value={color} onChange={handleColorChange} className="color-dropdown">
                        {colors.map((colorOption) => (
                            <option key={colorOption} value={colorOption}>
                                {colorOption}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Condition</h3>
                    <select value={condition} onChange={handleConditionChange} className="condition-dropdown">
                        {conditions.map((conditionOption) => (
                            <option key={conditionOption} value={conditionOption}>
                                {conditionOption}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Status</h3>
                    <select value={status} onChange={handleStatusChange} className="status-dropdown">
                        {statuses.map((statusOption) => (
                            <option key={statusOption} value={statusOption}>
                                {statusOption}
                            </option>
                        ))}
                    </select>
                </div>
                <button type="submit" className="product-submit-btn">Post product</button>
            </form>
        </div>
    );
}
