import '../../CSS-files/index.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTimes } from '@fortawesome/free-solid-svg-icons';
import { useUser } from '../../context/UserContext';
import {useEffect} from "react";

interface SavedProductsPanelProps {
    className: string;
    toggleSavedProducts: () => void;
}

const SavedProductsPanel: React.FC<SavedProductsPanelProps> = ({ className, toggleSavedProducts }) => {

    const { notifications, user, fetchNotifications } = useUser();

    useEffect(() => {
        if (user) {
            fetchNotifications();
        }
    }, [user, fetchNotifications]);

    return (
            <aside className={`saved-products-panel ${className}`}>
                <button className="close-panel-btn" onClick={toggleSavedProducts}>
                    <FontAwesomeIcon icon={faTimes} />
                </button>
                <h4>Notifications</h4>
                <ul>
                    {notifications.map((notification, index) => (
                        <li key={index}>
                            <div>{notification.category}</div>
                            <div>{notification.dateOfNotification}</div>
                        </li>
                    ))}
                </ul>
            </aside>
    );
};

export default SavedProductsPanel;
