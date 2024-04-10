import React from "react";
import "../index.css"; 
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser } from "@fortawesome/free-solid-svg-icons";


const Navbar = () => {
  return (
    <nav className="nav">
    <div className="nav-links">
      <a href="#">Shop</a>
      <a href="#">Login</a>
      <a href="#">Signup</a>
      </div>
      <div className="nav-right">
      <a href="#" className="nav-right"><FontAwesomeIcon icon={faUser} /></a>
      </div>
    </nav>
  );
};

export default Navbar;

