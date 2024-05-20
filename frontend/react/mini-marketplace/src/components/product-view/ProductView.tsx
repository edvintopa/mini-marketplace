import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import "../../CSS-files/productview.css";
import {useEffect, useState} from "react";
import axios from "axios";
import {User} from "../../types/types.ts";
interface ProductViewProps {
  id: string;
}

export interface ProductInfo {
    product_id: string;
    title: string;
    sellerName: string;
    description: string;
    manufacturer: string;
    date_posted: string;
    price: number;
    imagePath: string;
    url: string;
    productStatus: string;
    productSize : string;
    productCondition : string;
}

async function fetchProductById(id: string): Promise<ProductInfo | null> {
    try {
        const response = await axios.get<ProductInfo>(`http://localhost:8080/product/getProduct/` + id);
        //const response = await fetch(`http://localhost:8080/product/getProduct/${id}`);

        if (response.data) {
            const data = response.data;
            console.log(JSON.stringify(data) + " received from database"); // Log the received data

            const productInfo: ProductInfo = {
                product_id: data.product_id,
                sellerName: data.sellerName,
                price: data.price,
                title: data.title,
                manufacturer: data.manufacturer,
                imagePath: data.imagePath,
                url: data.url,
                description: data.description,
                date_posted: data.date_posted.split('T')[0], //removed timestamp
                productStatus: data.productStatus,
                productSize : data.productSize,
                productCondition : data.productCondition,
            };

            console.log(productInfo.imagePath + " is this ?")
            console.log(JSON.stringify(productInfo.productStatus) + " is the product info");
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

    useEffect(() => {
        fetchProductById(id).then(product => setCurrentProduct(product));
    }, [id]);

   /* if (currentProduct) {
        console.log(JSON.stringify(currentProduct.username) + " is the seller");
    }*/

    if (!currentProduct) {
        return <div>Loading...</div>;
    }

    return (
        <div className="GeneralProductView">
            <div className="ProductViewImage">
                <img src={currentProduct.imagePath} alt="" />
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
                        icon={faUser}/> {currentProduct.sellerName}</a>
                    <p>{currentProduct.description}</p>
                    <div className="productAttributes">
                        <span className="bubble">Size: {currentProduct.productSize}</span>
                        <span className="bubble">Condition: {currentProduct.productCondition}</span>
                    </div>
                    <p id="dateposted">{currentProduct.date_posted}</p>
                </div>
            </div>
        </div>
    );
};
