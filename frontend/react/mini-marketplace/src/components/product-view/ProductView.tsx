import { arrayOfProducts  } from '../product-gallery/ProductGallery'; // Import the missing 'ProductType' interface

interface ProductViewProps {
    id: string;
}

function fetchProductById(id: string) {
    console.log('id: ' + id);
    arrayOfProducts.map(product => console.log(product.id));
    
    if (arrayOfProducts.find(product => product.id === parseInt(id))) {
        return arrayOfProducts.find(product => product.id === parseInt(id));
    }
    else {
        console.log("failed to find the product with the given array");
    }
    
}

export const CurrentProductView: React.FC<ProductViewProps> = ({id}) => {
    
    const currentProduct = fetchProductById(id);
 
    return (
        <div className='GeneralProductView'>
            {currentProduct && (
                <div className='ProductViewImage'><img src={currentProduct.imagelink} alt="" /></div>
            )}
            <div className='ProductViewInfo'>
                <h3 className='TitleOfProduct'>{currentProduct?.name} </h3>
                <h4 className='PriceOfProduct'>{currentProduct?.price} kr</h4>
                <button className='AddToCartBtn'>Add to Cart</button>
                {/* user icon + sellers info here */}
                {/* tags ? */}
            </div>
        </div>
    );
};

