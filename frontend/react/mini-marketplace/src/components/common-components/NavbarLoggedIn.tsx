import React, { useEffect, useState, useRef } from "react";
import "../../CSS-files/index.css"; 
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser, faShop, faCartShopping, faTimes, faHeart, faMoon, faSun } from "@fortawesome/free-solid-svg-icons";
import { useUser } from "../../context/UserContext";
import { useNavigate } from "react-router-dom";
import { useTheme } from "../../context/useTheme";

interface NavbarLoggedInProps {
    toggleSavedProducts: () => void;
    isSavedProductsVisible: boolean;
}

const NavbarLoggedIn: React.FC<NavbarLoggedInProps> = ({ toggleSavedProducts, isSavedProductsVisible }) => {

    const [isDropdownVisible, setDropdownVisible] = useState(false);
    const dropdownRef = useRef<HTMLDivElement>(null);
    const { user, logoutUser } = useUser();
    const navigate = useNavigate();
    const { theme, toggleTheme } = useTheme();

    const toggleDropdown = (event: React.MouseEvent) => {
        event.stopPropagation();
        setDropdownVisible(prev => !prev);
    };

    useEffect(() => {
        const handleClickOutside = (event: MouseEvent) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
                setDropdownVisible(false);
            }
        };

        document.addEventListener("mousedown", handleClickOutside);
        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, []);

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
                <button className="toggle-saved-products-btn" onClick={toggleSavedProducts}>
                    {isSavedProductsVisible ? <FontAwesomeIcon icon = {faTimes} /> : <FontAwesomeIcon icon = {faHeart} />}
                </button>
                <a href="#"><FontAwesomeIcon icon={faCartShopping} /></a>
                <a href="#" onClick={(event) => toggleDropdown(event)} className="nav-right" id="profileIcon"><FontAwesomeIcon icon={faUser} /></a>
                <div className={`dropdown-menu ${isDropdownVisible ? 'visible' : ''}`} ref={dropdownRef}>
                    <a href="/profile">View Profile</a>
                    <a href="/createproduct">Create Product</a>
                    <div className="row-buttons">
                        <button onClick={toggleTheme} className="dark-mode-button">
                        {theme === 'dark' ? <FontAwesomeIcon icon={faSun} /> 
                            : <FontAwesomeIcon icon={faMoon} />}
                        </button>
                        <button onClick={logoutUser} className="logout-button">Logout</button>
                    </div>
                </div>
            </div>
        </nav>
    );
};

export default NavbarLoggedIn;


