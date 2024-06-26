import { useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import Navbar from './components/common-components/Navbar';
import NavbarLoggedIn from './components/common-components/NavbarLoggedIn';
import StartPage from './components/startpage';
import Footer from './components/common-components/Footer';
import { ProductGalleryApp } from "./components/ProductGalleryApp";
import { LoginPageApp } from "./components/LoginPageApp";
import { SignUpApp } from "./components/SignUpApp";
import SavedProductsPanel from './components/common-components/SavedProductsPanel';
import Profile from './components/userprofile/Profile';
import { useUser } from './context/UserContext';
import axios from 'axios';
import { RegisterProductApp } from './components/RegisterProductApp.tsx';
import {ProductViewApp} from "./components/ProductViewApp.tsx";
import Cart from './components/userprofile/Cart';

axios.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

const App = () => {
    const [isSavedProductsVisible, setSavedProductsVisible] = useState(false);


    const toggleSavedProducts = () => {
        setSavedProductsVisible(!isSavedProductsVisible);
    };

    const { user } = useUser();

    return (
        <div className="App">
            { user ? (
                <NavbarLoggedIn toggleSavedProducts={toggleSavedProducts} isSavedProductsVisible={isSavedProductsVisible} />
            ) : (
                <Navbar />
            )}

            <SavedProductsPanel
            className={isSavedProductsVisible ? 'visible' : ''}
            toggleSavedProducts={toggleSavedProducts}
            />
            <Routes>
                <Route path="/" element={<StartPage />} />
                <Route path="/productgallery" element={<ProductGalleryApp />} /> {''}
                <Route path="/signup" element={<SignUpApp />} />{" "}

                {/* to be fixed */}
                <Route path="/login" element={<LoginPageApp />} />{" "}
                {/* to be fixed */}
                <Route path="/productview/:id" element={<ProductViewApp />} />
                <Route path="/cart" element={<Cart />} /> 
               <Route path="/createproduct" element={<RegisterProductApp />} />
               <Route path="/profile" element={<Profile />} />
               
            </Routes>
            <Footer />
        </div>
    );
}

export default App;
