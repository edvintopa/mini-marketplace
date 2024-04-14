import { Route, Routes } from 'react-router-dom';
import Navbar from './components/common-components/Navbar';
import Profile from './components/userprofile/profile';
import StartPage from './components/startpage';
import Footer from './components/common-components/Footer';
import { ProductGalleryApp } from "./components/ProductGalleryApp";
import { CurrentProductView } from './components/product-view/ProductView';

function App() {
    return (
        <div className="App">
            <Navbar />
            <Routes>
                <Route path="/" element={<StartPage />} />
                <Route path="/productgallery" element={<ProductGalleryApp />} />
                <Route path="/signup" element={<ProductGalleryApp />} />{" "}
                {/* to be fixed */}
                <Route path="/login" element={<ProductGalleryApp />} />{" "}
                {/* to be fixed */}
                <Route path="/productview/:id" element={<CurrentProductView id={""} />} />
                {/*<Route path="/profile" element={<Profile />} />*/}
            </Routes>
            <Footer />
        </div>
    );
}

export default App;
