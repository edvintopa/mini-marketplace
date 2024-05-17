import React, { useState } from 'react'
import '../../CSS-files/index.css';

const conditions = [
    'NEW', 'VERY_GOOD', 'GOOD', 'NOT_WORKING_PROPERLY', 'USED',
];

const colors = [
    'BLACK', 'WHITE', 'RED', 'BLUE', 'YELLOW', 'ORANGE',
    'GREEN', 'PURPLE', 'GRAY', 'OTHER', 'PINK', 'OLIVE',
    'NAVY', 'MAROON', 'BEIGE', 'BROWN',
];

const seasons = [
    'SPRING', 'SUMMER', 'AUTUMN', 'WINTER',
];

const types = [
    'TSHIRT', 'SHIRT', 'HOODIE', 'TROUSERS', 'SHORTS', 'SKIRT',
    'DRESS', 'FOOTWEAR', 'HEADWEAR', 'ACCESSORY', 'OTHER',
];

const sexes = [
    'MEN', 'WOMEN', 'UNISEX',
];


export const RegisterProductCard: React.FC = () => {
    const [imageUrl, setImageUrl] = React.useState("https://ralfvanveen.com/wp-content/uploads/2021/06/Placeholder-_-Glossary.svg");
    const [color, setColor] = useState(colors[0]);
    const [condition, setCondition] = useState(conditions[0]);
    const [type, setType] = useState(types[0]);  
    const [season, setSeason] = useState(seasons[0]);
    const [sex, setSex] = useState(sexes[0]);
    const [formData, setFormData] = useState({
        title: '',
        description: '',
        manufacturer: '',
        price: 0,
    });

    // color, condition, status

    const handleImageUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files && event.target.files[0]) {
            const imageUrl = URL.createObjectURL(event.target.files[0]);
            setImageUrl(imageUrl);
        }
    };

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { title, value } = event.target;
        setFormData(prevData => ({ ...prevData, [title]: value }));
    };

    const handleColorChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setColor(event.target.value);
    };

    const handleConditionChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setCondition(event.target.value);
    };

    const handleTypeChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setType(event.target.value);
    };

    const handleSeasonChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSeason(event.target.value);
    };

    const handleSexChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSex(event.target.value);
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
                    <input type="text" title="title" value={formData.title} onChange={handleInputChange} />
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Price</h3>
                    <input type="number" title="price" value={formData.price} onChange={handleInputChange} />
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Description</h3>
                    <input type="text" title="description" value={formData.description} onChange={handleInputChange} />
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Condition</h3>
                    <select value={condition} onChange={handleConditionChange} className="dropdown">
                        {conditions.map((conditionOption) => (
                            <option key={conditionOption} value={conditionOption}>
                            {conditionOption}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Color</h3>
                    <select value={color} onChange={handleColorChange} className="dropdown">
                        {colors.map((colorOption) => (
                            <option key={colorOption} value={colorOption}>
                            {colorOption}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Season</h3>
                    <select value={season} onChange={handleSeasonChange} className="dropdown">
                        {seasons.map((seasonOption) => (
                            <option key={seasonOption} value={seasonOption}>
                                {seasonOption}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Sex</h3>
                    <select value={sex} onChange={handleSexChange} className="dropdown">
                        {sexes.map((sexOption) => (
                            <option key={sexOption} value={sexOption}>
                                {sexOption}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="textFieldBox">
                    <h3 className="form-section-title">Type</h3>
                    <select value={type} onChange={handleTypeChange} className="dropdown">
                        {types.map((typeOption) => (
                            <option key={typeOption} value={typeOption}>
                                {typeOption}
                            </option>
                        ))}
                    </select>
                </div>
                <button type="submit" className="product-submit-btn">Post product</button>
            </form>
        </div>
    );
}
