import React, { useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import NavbarLoggedIn from './components/common-components/NavbarLoggedIn';
import StartPage from './components/startpage';
import Footer from './components/common-components/Footer';
import { ProductGalleryApp } from "./components/ProductGalleryApp";
import { CurrentProductView } from './components/product-view/ProductView';
import SavedProductsPanel from './components/common-components/SavedProductsPanel';
import Profile from './components/userprofile/Profile';
import { ThemeProvider } from './ThemeContext';
import { UserProvider } from './context/UserContext';

function App() {
    const [isSavedProductsVisible, setSavedProductsVisible] = useState(false);

    const [orders, setOrders] = useState([
        {
            id: 1,
            description: 'Black Hoodie',
            date: '2024-04-18',
        },
        {
            id: 2,
            description: 'Black jeans',
            date: '2024-04-29',
        },
    ]);

    const toggleSavedProducts = () => {
        setSavedProductsVisible(!isSavedProductsVisible);
    };



    return (
        <ThemeProvider>
        <UserProvider>
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
                <Route path="/profile" element={<Profile orders={orders} />} />
            </Routes>
            <Footer />
        </div>
        </UserProvider>
        </ThemeProvider>
    );
}

export default App;
