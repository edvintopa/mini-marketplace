import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import "../../CSS-files/productview.css";
import {useEffect, useState} from "react";
interface ProductViewProps {
  id: string;
}

export interface ProductInfo {
    product_id: string;
    title: string;
    username: string;
    description: string;
    manufacturer: string;
    datePosted: string;
    price: number;
    product_image: string;
    url: string;
    productStatus: string;
}

async function fetchProductById(id: string): Promise<ProductInfo | null> {
    try {
        const response = await fetch(`http://localhost:8080/product/getProduct/${id}`);
        const data = await response.json();
        console.log(JSON.stringify(data) + " received from database"); // Log the received data

        // Map the received data to the ProductInfo interface
        const productInfo: ProductInfo = {
            product_id: data.product_id,
            username: data.username,
            price: data.price,
            title: data.title,
            manufacturer: data.manufacturer,
            product_image: data.product_image,
            url: data.url,
            description: data.description,
            datePosted: data.datePosted,
            productStatus: data.productStatus,
        };

        console.log(JSON.stringify(productInfo.productStatus) + " is the product info");
        return productInfo;
    } catch (error) {
        console.error('Error:', error);
        return null;
    }
}
export const CurrentProductView: React.FC<ProductViewProps> = ({ id }) => {
    const [currentProduct, setCurrentProduct] = useState<ProductInfo | null>(null);

    useEffect(() => {
        fetchProductById(id).then(product => setCurrentProduct(product));
    }, [id]);

    if (currentProduct) {
        console.log(JSON.stringify(currentProduct.username) + " is the status");
    }

    if (!currentProduct) {
        return <div>Loading...</div>;
    }

    return (
        <div className="GeneralProductView">
            <div className="ProductViewImage">
                <img src={currentProduct.product_image} alt="" />
            </div>
            <div className="ProductViewInfo">
                <h3 className="TitleOfProduct">{currentProduct.title}</h3>
                <h4 className="PriceOfProduct">{currentProduct.price} kr</h4>
                <div className="ProductViewButtons">
                    <button className="StatusBtn">{currentProduct.productStatus}</button>
                    <button className="AddToCartBtn">Add to cart</button>
                </div>
                <div className="DescriptionInfo">
                <a href="/profile" className="userProfile" id="profileIcon"><FontAwesomeIcon
                        icon={faUser}/>{currentProduct.username}</a>
                    <p>{currentProduct.description}</p>
                    <p>{currentProduct.datePosted}</p>
                </div>
            </div>
        </div>
    );
};
