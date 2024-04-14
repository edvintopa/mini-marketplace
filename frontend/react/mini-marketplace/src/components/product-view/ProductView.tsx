import { arrayOfProducts } from "../product-gallery/ProductGallery"; // Import the missing 'ProductType' interface
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import "../../CSS-files/productview.css";
interface ProductViewProps {
  id: string;
}

function fetchProductById(id: string) {
  console.log("id: " + id);
  arrayOfProducts.map((product) => console.log(product.id));

  if (arrayOfProducts.find((product) => product.id === parseInt(id))) {
    return arrayOfProducts.find((product) => product.id === parseInt(id));
  } else {
    console.log("failed to find the product with the given array");
  }
}

export const CurrentProductView: React.FC<ProductViewProps> = ({ id }) => {
  const currentProduct = fetchProductById(id);

  return (
    <div className="GeneralProductView">
      <div className="ProductViewImage">
        <img
          src="https://media-photos.depop.com/b1/8343507/1791399827_079d48edd2134d9b946e3252d59ea14a/P0.jpg"
          alt=""
        />
      </div>
      <div className="ProductViewInfo">
        <h3 className="TitleOfProduct">Cowboy boots </h3>
        <h4 className="PriceOfProduct">800 kr</h4>
        <div className="ProductViewButtons">
          <button className="AddToCartBtn">ADD TO CART</button>
          <button className="PlaceholderBtn">PLACEHOLDER</button>
        </div>
        <div className="DescriptionInfo">
        <a href="/profile" className="userProfile" id="profileIcon"><FontAwesomeIcon icon={faUser} /> Seller's info</a>

            <p>
            Vintage Abilene women’s cowboy boots 🤠 <br></br>
Very good condition — some wear as shown in photos.<br></br>
<br></br>
Marked size 6M. I’m a size 6, but these were just a bit too tight on me unfortunately 🥲
Fit like size 5 1/2, even 5.<br></br>

** Please read shop policies and ask any questions before purchasing. Free shipping on bundles!
Shipping is a bit more for these because of weight and size.
<br></br><br></br>
Thank you for supporting my shop!! 🫶🏼
            </p>
        </div>
        {/* user icon + sellers info here */}
        {/* tags ? */}
      </div>
    </div>
  );
};
