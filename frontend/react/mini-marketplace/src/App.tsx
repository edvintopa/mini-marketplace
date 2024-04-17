import React, { useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import NavbarLoggedIn from './components/common-components/NavbarLoggedIn';
import Profile from './components/userprofile/profile';
import StartPage from './components/startpage';
import Footer from './components/common-components/Footer';
import { ProductGalleryApp } from "./components/ProductGalleryApp";
import { CurrentProductView } from './components/product-view/ProductView';
import SavedProductsPanel from './components/common-components/SavedProductsPanel';

function App() {

    const [isSavedProductsVisible, setSavedProductsVisible] = useState(false);

    const toggleSavedProducts = () => {
        setSavedProductsVisible(!isSavedProductsVisible);
    };


    return (
        <div className="App">
            <NavbarLoggedIn toggleSavedProducts={toggleSavedProducts} isSavedProductsVisible={isSavedProductsVisible} />
            <SavedProductsPanel
            className={isSavedProductsVisible ? 'visible' : ''}
            toggleSavedProducts={toggleSavedProducts}
            />
            <Routes>
                <Route path="/" element={<StartPage />} />
                <Route path="/productgallery" element={<ProductGalleryApp />} />
                <Route path="/signup" element={<ProductGalleryApp />} />{" "}
                {/* to be fixed */}
                <Route path="/login" element={<ProductGalleryApp />} />{" "}
                {/* to be fixed */}
                <Route path="/productview/:id" element={<CurrentProductView id={""} />} />
                <Route path="/profile" element={<Profile name="John Doe" bio="I got the good stuff" />} />
            </Routes>
            <Footer />
        </div>
    );
}

export default App;
