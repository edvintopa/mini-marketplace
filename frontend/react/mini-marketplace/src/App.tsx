import { Route, Routes } from 'react-router-dom';
import Navbar from './components/common-components/Navbar';
import Profile from './components/userprofile/profile';
import StartPage from './components/startpage';
import Footer from './components/common-components/Footer';

function App() {
    return (
        <div className="App">
            <Navbar />
            <Routes>
                <Route path="/" element={<StartPage />} />
                <Route path="/profile" element={<Profile />} />
            </Routes>
            <Footer />
        </div>
    );
}


export default App;
