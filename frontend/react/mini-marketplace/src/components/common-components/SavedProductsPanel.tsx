import '../../CSS-files/index.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTimes } from '@fortawesome/free-solid-svg-icons';

interface SavedProductsPanelProps {
    className: string;
    toggleSavedProducts: () => void;
}

const SavedProductsPanel: React.FC<SavedProductsPanelProps> = ({ className, toggleSavedProducts }) => {
    return (
            <aside className={`saved-products-panel ${className}`}>
                <button className="close-panel-btn" onClick={toggleSavedProducts}>
                    <FontAwesomeIcon icon={faTimes} />
                </button>
                <h4>Saved Products</h4>
                <ul>
                    <li>Product 1</li>
                    <li>Product 2</li>
                    <li>Product 3</li>
                    <li>Product 4</li>
                    <li>Product 5</li>
                </ul>
            </aside>
    );
};

export default SavedProductsPanel;
