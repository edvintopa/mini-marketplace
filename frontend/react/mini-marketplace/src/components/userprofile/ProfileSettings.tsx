import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCog, faMoon } from '@fortawesome/free-solid-svg-icons';
import { useTheme } from '../../context/useTheme';



const ProfileSettings = () => {
    const { toggleTheme } = useTheme();

    return (
            <div>
            <div className="settings-item">
                <button>
                    <FontAwesomeIcon icon={faCog} />
                    <span>Settings</span>
                </button>
            </div>
            <div className="settings-item">
                <button onClick={toggleTheme}>
                    <FontAwesomeIcon icon={faMoon} />
                    <span>Dark Mode</span>
                </button>
            </div>
        </div>
    );
};

export default ProfileSettings;
