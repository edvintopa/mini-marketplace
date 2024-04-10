import './App.css';
import { Link, Route, Routes } from 'react-router-dom';
import StartPage from './components/startpage';
import Navbar from './components/Navbar';

function App() {
    return (
        <div className="App">
            <Navbar />
            <Routes>
                <Route path="/" element={<StartPage />} />
            </Routes>
        </div>
    );
}


export default App;
