import React, { useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import NavbarLoggedIn from './components/common-components/NavbarLoggedIn';
import StartPage from './components/startpage';
import Footer from './components/common-components/Footer';
import { ProductGalleryApp } from "./components/ProductGalleryApp";
import { CurrentProductView } from './components/product-view/ProductView';
import SavedProductsPanel from './components/common-components/SavedProductsPanel';
import Profile from './components/userprofile/profile';

function App() {

    const [isSavedProductsVisible, setSavedProductsVisible] = useState(false);

    const toggleSavedProducts = () => {
        setSavedProductsVisible(!isSavedProductsVisible);
    };

const testUser = {
    id: 1,
    name: "Elliot",
    bio: "hello friend",
    avatarUrl: "https://i.pinimg.com/originals/7b/7b/7b/"
}

const arrayOfOrders = [
    {
    id: 1,
    description: "reverse shell script",
    date: "2024-04-18",
    }
]

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
                <Route path="/profile" element={<Profile user={testUser} orders={arrayOfOrders} />} />
            </Routes>
            <Footer />
        </div>
    );
}

export default App;
