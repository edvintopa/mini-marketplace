import "../CSS-files/index.css";
import { Link } from "react-router-dom";
import { ProductGalleryCard } from "./product-gallery/ProductGalleryCard";

const StartPage = () => {

    const products = [
        { id: 1, name: 'Hoodie', price: 200, imagelink: 'https://media-photos.depop.com/b1/11985249/1825399086_03666561d28c44f09dfdcd78b8cd5ca1/P0.jpg', url: '/productview/1' },
        { id: 2, name: 'Comme de Garcon', price: 1000, imagelink: 'https://media-photos.depop.com/b1/14563374/1813639761_27c530aaa2f84b2c89eb00f9bb0c2381/P0.jpg', url: '/productview/2' },
        { id: 3, name: 'Ballet shoes', price: 350, imagelink: 'https://media-photos.depop.com/b1/39105082/1792970550_701cc8458f0a499fb86b01d7ad7ccae9/P0.jpg', url: '/productview/3' },
        { id: 4, name: 'Hoodie', price: 275, imagelink: 'https://media-photos.depop.com/b1/36997595/1826378382_2aeeaf9316d5404c88d91aa6b0f56355/P0.jpg', url: '/productview/4' },
        { id: 5, name: 'Hollister hoodie', price: 300, imagelink: 'https://media-photos.depop.com/b1/6260622/1820567173_661acb1583064a6e9e3f39edb8eaecbb/P0.jpg', url: '/productview/5' },
        { id: 6, name: 'Fendi bag', price: 700, imagelink: 'https://media-photos.depop.com/b1/47780301/1822121408_4be6229f80994c80a83ccd3fc0288126/P0.jpg', url: '/productview/6' },
        { id: 7, name: 'Leather bag', price: 400, imagelink: 'https://media-photos.depop.com/b1/23033242/1826419642_32259e2a5765428d8e12b4c0e480a318/P0.jpg', url: '/productview/7' },
        { id: 8, name: 'Leather jacket', price: 650, imagelink: 'https://media-photos.depop.com/b1/21291151/1826390941_449b3bf72c404dfa87dda0f1d1913f5d/P0.jpg', url: '/productview/8' },

    ].slice(0, 8);

    return (
        <div className="w-full">
            <main className="main-content">
                <div>
                    <div className="banner">
                        <h1>Mini Market</h1>
                        <p>Explore our collection of amazing products</p>
                    </div>
                    <h2>Featured Products</h2>
                    <div className="product-gallery-preview">
                        {products.map(product => (
                            <ProductGalleryCard
                            key={product.id}
                            imagelink={product.imagelink}
                            url={product.url}
                            title={product.name}
                            price={product.price + " kr"}
                            />
                        ))}
                    </div>
                    <Link to="/productgallery" className="show-more-btn">Show More</Link>
                </div>
            </main>
        </div>
    );
}

export default StartPage;

