import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import "../../CSS-files/productview.css";
import {useEffect, useState} from "react";
import { useUser } from "../../context/UserContext";
import { useNavigate } from "react-router-dom";
import axios from "axios";

interface ProductViewProps {
  id: string;
}

export interface ProductInfo {
productId: string;
    title: string;
    price: number;
    status: string;
    sellerName: string;
    description: string;
    productSize : string;
    productCondition : string;
    date_posted: string;
    imagePath: string;
url: string;
type: string;
manufacturer: string;
sex: string;
season: string;
}


async function fetchProductById(id: string): Promise<ProductInfo | null> {
    try {
        const response = await axios.get<ProductInfo>(`http://localhost:8080/product/getProduct/` + id);
        //const response = await fetch(`http://localhost:8080/product/getProduct/${id}`);

        if (response.data) {
            const data = response.data;
            console.log(JSON.stringify(data) + " received from database"); // Log the received data

            const productInfo: ProductInfo = {
                productId: data.productId,
                sellerName: data.sellerName,
                price: data.price,
                title: data.title,
                imagePath: data.imagePath,
                url: data.url,
                description: data.description,
                date_posted: data.date_posted.split('T')[0], //removed timestamp
                status: data.status,
                productSize : data.productSize,
                productCondition : data.productCondition,
                type : data.type,
                manufacturer : data.manufacturer,
                sex : data.sex,
                season : data.season,
            };

            console.log(productInfo.imagePath + " is this ?")
            console.log(JSON.stringify(productInfo) + " is the product info");
            console.log('Response: ', response.data);
            return productInfo;
        }
        return null;
    } catch (error) {
        console.error('Error:', error);
        return null;
    }
}


export const CurrentProductView: React.FC<ProductViewProps> = ({ id }) => {
    const [currentProduct, setCurrentProduct] = useState<ProductInfo | null>(null);
    const { token, addToCart } = useUser();
    const navigate = useNavigate();


    useEffect(() => {
        fetchProductById(id).then(product => setCurrentProduct(product));
        console.log('Product:', currentProduct);
    }, [id]);

   /* if (currentProduct) {
        console.log(JSON.stringify(currentProduct.username) + " is the seller");
    }*/

    if (!currentProduct) {
        return <div>Loading...</div>;
    }

    const handleAddToCart = async (event: React.MouseEvent) => {
        if (!token) {
            console.log("User is not logged in");
            navigate("/login");
            return;
        }
        console.log('Product to be added to cart:', currentProduct.productId);
        const success = await addToCart(currentProduct.productId);
        if (success) {
            console.log("Product added to cart");
        } else {
            console.log("Failed to add product to cart");
        }
    };

    return (
        <div className="GeneralProductView">
            <div className="ProductViewImage">
                <img src={currentProduct.imagePath} alt="" />
            </div>
            <div className="ProductViewInfo">
                <h3 className="TitleOfProduct">{currentProduct.title}</h3>
                <h4 className="PriceOfProduct">{currentProduct.price} kr</h4>
                <div className="ProductViewButtons">
                    <button className="StatusBtn">{currentProduct.status}</button>
                    <button className="AddToCartBtn" onClick={handleAddToCart}>Add to cart</button>
                </div>
                <div className="DescriptionInfo">
                    <a href="/profile" className="userProfile" id="profileIcon"><FontAwesomeIcon
                        icon={faUser}/> {currentProduct.sellerName}</a>
                    <p>{currentProduct.description}</p>
                    <div className="productAttributes">
                        <span className="bubble">Size: {currentProduct.productSize}</span>
                        <span className="bubble">Condition: {currentProduct.productCondition}</span>
                        <span className="bubble">Type: {currentProduct.type}</span>
                        <span className="bubble">Manufacturer: {currentProduct.manufacturer}</span>
                        <span className="bubble">Sex: {currentProduct.sex}</span>
                        <span className="bubble">Season: {currentProduct.season}</span>
                    </div>

                    <p id="dateposted">{currentProduct.date_posted}</p>
                </div>
            </div>
        </div>
    );
};

