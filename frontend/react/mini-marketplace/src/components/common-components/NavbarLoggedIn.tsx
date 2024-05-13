import React, { useState } from "react";
import "../../CSS-files/index.css"; 
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser, faShop, faCartShopping, faTimes, faHeart } from "@fortawesome/free-solid-svg-icons";

interface NavbarLoggedInProps {
    toggleSavedProducts: () => void;
    isSavedProductsVisible: boolean;
}

const NavbarLoggedIn: React.FC<NavbarLoggedInProps> = ({ toggleSavedProducts, isSavedProductsVisible }) => {

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
            <button className="toggle-saved-products-btn" onClick={toggleSavedProducts}>
                {isSavedProductsVisible ? <FontAwesomeIcon icon = {faTimes} /> : <FontAwesomeIcon icon = {faHeart} />}
            </button>
                <a href="#"><FontAwesomeIcon icon={faCartShopping} /></a>
                <a href="/profile" className="nav-right" id="profileIcon"><FontAwesomeIcon icon={faUser} /></a>
            </div>
        </nav>
    );
};

export default NavbarLoggedIn;


