import { ProductGalleryWrapper } from './product-gallery/ProductGalleryWrapper'
import "../CSS-files/productgallery.css";
import './../CSS-files/index.css';
import '../script/Search.tsx';

import { useLocation } from "react-router-dom";

export const ProductGalleryApp = () => {
    const location = useLocation();
    const searchTerm = location.state ? location.state.searchTerm : '';
    console.log(searchTerm + " is PGA");
    return (
        <div className='ProductGalleryWrapper'>
            <ProductGalleryWrapper searchTerm={searchTerm} />
        </div>
    )
}