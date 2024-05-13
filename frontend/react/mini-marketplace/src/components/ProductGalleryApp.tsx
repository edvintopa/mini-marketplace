import { ProductGalleryWrapper } from './product-gallery/ProductGalleryWrapper'
import Navbar from "./common-components/Navbar";
import "../CSS-files/productgallery.css";

export const ProductGalleryApp = () => {
  return (
    <>
    <Navbar/>
    <div className='ProductGalleryWrapper'>
        <ProductGalleryWrapper />
    </div>
    </>
  )
}