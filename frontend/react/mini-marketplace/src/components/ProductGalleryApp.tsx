import { ProductGalleryWrapper } from './product-gallery/ProductGalleryWrapper'
import "../CSS-files/productgallery.css";
import './../CSS-files/index.css';
import '../script/Search.tsx';

export const ProductGalleryApp = (searchTerm: string) => {
    console.log(searchTerm + " is PGA");
    return (
        <div className='ProductGalleryWrapper'>
            <ProductGalleryWrapper searchTerm={searchTerm.valueOf()} />
        </div>
    )
}