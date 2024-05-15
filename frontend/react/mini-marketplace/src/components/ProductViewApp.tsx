import Navbar from "./Navbar";
import "../../CSS-files/productview.css";
import { CurrentProductView } from './product-view/ProductView';

export const ProductViewApp = ({id} : {id: string}) => {
  console.log('id1: ' + id);
  return (
    
    <div className='ProductViewWrapper'>
        <CurrentProductView id={id} /> 
    </div>
  
  )
}
