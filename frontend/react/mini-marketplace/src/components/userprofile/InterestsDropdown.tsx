import React, {useEffect, useState} from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleDown, faAngleUp } from '@fortawesome/free-solid-svg-icons';
import { useUser } from '../../context/UserContext';
import '../../CSS-files/profile.css';

const interestsOptions = [
    'TSHIRT',
    'SHIRT',
    'HOODIE',
    'TROUSERS',
    'SHORTS',
    'SKIRT',
    'DRESS',
    'FOOTWEAR',
    'HEADWEAR',
    'ACCESSORY',
    'OTHER',
];

const InterestsDropdown: React.FC = () => {
    const { user, setUserInterests, fetchInterests, fetchedInterests } = useUser();
    const [isOpen, setIsOpen] = useState(false);
    const [selectedInterests, setSelectedInterests] = useState<string[]>(user?.interests || []);
    const [isSaving, setIsSaving] = useState(false);
    const [saveMessage, setSaveMessage] = useState('');

    useEffect(() => {
        if(user) {
            fetchInterests();
        }
    }, [user, fetchInterests]);

    useEffect(() => {
        setSelectedInterests(fetchedInterests)
    }, [fetchedInterests]);

    const toggle = () => {
        setIsOpen(!isOpen);
    };

    const handleInterestChange = (interest: string) => {
        setSelectedInterests(prev => {
            if (prev.includes(interest)) {
                return prev.filter(i => i !== interest);
            } else {
                return [...prev, interest];
            }
        });
    };

    const handleSaveInterests = async () => {
        setIsSaving(true);
        const success = await setUserInterests(selectedInterests);
        setIsSaving(false);
        setSaveMessage(success ? 'Interests saved!' : 'Failed to save interests');
        if (success) {
            setIsOpen(false);
        }
        setTimeout(() => setSaveMessage(''), 3000);
    };

    return (
            <div className="interests-parent">
            <div className="interests-action-button">
                <button onClick={toggle}>
                    <span>Interests</span>
                    {isOpen ? <FontAwesomeIcon className="interests-icon" icon={faAngleUp} />
                    : <FontAwesomeIcon className="interests-icon" icon={faAngleDown} /> }
                </button>
            </div>
            <div className={`interests-container ${isOpen ? 'visible' : ''}`}>
                {isOpen && (
                    <div>
                        {interestsOptions.map(interest => (
                            <div key={interest} className='interest-option'>
                                <label>
                                    <input
                                        type="checkbox"
                                        checked={selectedInterests.includes(interest)}
                                        onChange={() => handleInterestChange(interest)}
                                    />
                                    {interest}
                                </label>
                            </div>
                        ))}
                        <button onClick={handleSaveInterests} disabled={isSaving}>
                            {isSaving ? 'Saving...' : 'Save'}
                        </button>
                        {saveMessage && <div className="save-message">{saveMessage}</div>}
                    </div>
                )}
            </div>
        </div>
    );
};

export default InterestsDropdown;
