import "./App.css";
import {Route, Routes } from "react-router-dom";
import StartPage from "./components/startpage";
import { ProductGalleryApp } from "./components/ProductGalleryApp";
import Navbar from "./components/Navbar";

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
        </Routes>
    </div>
  );
}

export default App;
