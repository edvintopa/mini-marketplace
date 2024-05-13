import "../../CSS-files/index.css"; 
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faShop, faCartShopping } from "@fortawesome/free-solid-svg-icons";



const Navbar = () => {
    return (
        <nav className="nav">
            <div className="nav-left">
                <a href="/"><FontAwesomeIcon icon={faShop} /><p>Home</p></a>
                <a href="/productgallery.html">Marketplace</a>
            </div>
            <div className="nav-center">
            <input className="nav-search" type="text" placeholder="Search" />
            </div>
            <div className="nav-right">
                <a href="#"><FontAwesomeIcon icon={faCartShopping} /></a>
                <a href="#">Signup</a>
                <a href="#">Login</a>
            </div>
        </nav>
    );
};

export default Navbar;

