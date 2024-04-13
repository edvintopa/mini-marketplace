
import Navbar from "./Navbar";
import "../CSS-files/productgallery.css";
import { CurrentProductView } from './product-view/ProductView';

export const ProductViewApp = ({id} : {id: string}) => {
  console.log('id1: ' + id);
  return (
    <>
    <Navbar/>
    <div className='ProductViewWrapper'>
        <CurrentProductView id={id} /> 
    </div>
    </>
  )
}
