import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCog, faPenToSquare, faMoon } from '@fortawesome/free-solid-svg-icons';




const ProfileSettings = () => {
    return (
            <div>
            <div className="settings-item">
                <FontAwesomeIcon icon={faPenToSquare} />
                <span>Edit Profile</span>
            </div>
            <div className="settings-item">
            <FontAwesomeIcon icon={faCog} />
            <span>Settings</span>
            </div>
            <div className="settings-item">
            <FontAwesomeIcon icon={faMoon} />
            <span>Dark Mode</span>
            </div>
        </div>
    );
};

export default ProfileSettings;
