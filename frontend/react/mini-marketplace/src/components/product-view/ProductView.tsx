import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import "../../CSS-files/productview.css";
import {useEffect, useState} from "react";
interface ProductViewProps {
  id: string;
}

export interface ProductInfo {
    id: string;
    name: string;
    price: number;
    title: string;
    product_image: string;
    url: string;
    description: string;
    seller: string;
    date: string;
    status: string;
}

async function fetchProductById(id: string): Promise<ProductInfo | null> {
    try {
        console.log(id.valueOf() + " is the id")
        const response = await fetch(`http://localhost:8080/product/getProduct/${id}`);
        const data = await response.json();
        return data;
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
        console.log(currentProduct.status + " is the status");
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
                    <button className="StatusBtn">{currentProduct.status}</button>
                    <button className="AddToCartBtn">Add to cart</button>
                </div>
                <div className="DescriptionInfo">
                <a href="/profile" className="userProfile" id="profileIcon"><FontAwesomeIcon
                        icon={faUser}/>{currentProduct.seller.valueOf().toString()}</a>
                    <p>{currentProduct.description}</p>
                    <p>{currentProduct.date}</p>
                </div>
            </div>
        </div>
    );
};
