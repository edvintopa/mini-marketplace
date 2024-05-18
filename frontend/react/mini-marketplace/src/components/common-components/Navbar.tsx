import "../../CSS-files/index.css"; 
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faShop, faCartShopping } from "@fortawesome/free-solid-svg-icons";
//import '../script/Search.tsx';
// @ts-ignore
import { useNavigate } from "react-router-dom";

const Navbar: React.FC = () => {
    const navigate = useNavigate();

    const handleSearch = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.key === 'Enter') {
            const searchTerm  = event.currentTarget.value;
            navigate(`/productgallery`, { state: { searchTerm } });
            console.log(searchTerm + " was searched")
            event.currentTarget.value = '';
        }
    };

    return (
        <nav className="nav">
            <div className="nav-left">
                <a href="/"><FontAwesomeIcon icon={faShop} /><p>Home</p></a>
                <a href="/productgallery">Marketplace</a>
            </div>
            <div className="nav-center">
            <input className="nav-search" type="text" placeholder="Search" onKeyDown={handleSearch} />
            </div>
            <div className="nav-right">
                <a href="#"><FontAwesomeIcon icon={faCartShopping} /></a>
                <a href="/signup">Signup</a>
                <a href="/login">Login</a>
            </div>
        </nav>
    );
};

export default Navbar;