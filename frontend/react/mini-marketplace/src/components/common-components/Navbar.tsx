import "../../CSS-files/index.css"; 
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser, faShop, faCartShopping } from "@fortawesome/free-solid-svg-icons";



const Navbar = () => {
    return (
        <nav className="nav">
            <div className="nav-left">
                <a href="/"><FontAwesomeIcon icon={faShop} /><p>Home</p></a>
            </div>
            <div className="nav-center">
            <a href="/productgallery.html">Marketplace</a>
            <a href="#">Login</a>
                <a href="#">Signup</a>

            </div>
            <div className="nav-right">
            <input className="nav-search" type="text" placeholder="Search" />
                <a href="#"><FontAwesomeIcon icon={faCartShopping} /></a>
                <a href="/profile" className="nav-right" id="profileIcon"><FontAwesomeIcon icon={faUser} /></a>
            </div>
        </nav>
    );
};

export default Navbar;

