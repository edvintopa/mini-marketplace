import "../CSS-files/index.css";
import { Link } from "react-router-dom";
import { ProductGalleryCard } from "./product-gallery/ProductGalleryCard";
import {ProductInfo} from "./product-gallery/ProductGallery.tsx";
import {useEffect, useState} from "react";

const StartPage = () => {

    const [products, setProducts] = useState<ProductInfo[]>([]);

    useEffect(() => {
        fetchProducts().then(data => {
            const limitedData = data.slice(0, 8);
            setProducts(limitedData);
        });
    }, []);
    async function fetchProducts(): Promise<ProductInfo[]> {
        try {
            const response = await fetch('http://localhost:8080/product/get');
            const data = await response.json();
            console.log('Received data:', data);

            const products: ProductInfo[] = data.map((product: any) => ({
                product_id: product.productId,
                title: product.title,
                username: product.username,
                price: product.price,
                imagePath: product.imagePath,
                url: product.url,
            }));

            console.log('Mapped products:', products);
            return products;
        } catch (error) {
            console.error('Error:', error);
            return [];
        }
    }

    return (
        <div className="w-full">
            <main className="main-content">
                <div>
                    <div className="banner">
                        <h1>Mini Market</h1>
                        <p>Buy and sell ur shit</p>
                    </div>
                    <h2>Featured Products</h2>
                    <div className="product-gallery-preview">
                        {products.map(product => (
                            <ProductGalleryCard
                                key={product.product_id}
                                imagelink={product.imagePath}
                                url={`/productview/${product.product_id}`}
                                title={product.title}
                                price={`${product.price} kr`}
                                id={product.product_id}
                            />
                        ))}
                        <Link to="/productgallery" className="show-more-btn">Show More</Link>
                    </div>
                </div>
            </main>
        </div>
    );
}

export default StartPage;

