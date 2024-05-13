import React, { useState } from 'react';
import '../../CSS-files/profile.css';
import ProfileInfo from '../common-components/ProfileInfo';
import OrderHistory from './OrderHistory';
import ProfileSettings from './ProfileSettings';
import EditProfile from './EditProfile';
import { useUser } from '../../context/UserContext';
import { Order } from '../../types/types';



interface ProfileProps {
    orders: Order[];
}

const Profile: React.FC<ProfileProps> = ({ orders  }) => {
    const { user, updateUser } = useUser();
    const [editMode, setEditMode] = useState(false);
    const [editableUser, setEditableUser] = useState(user);

    const handleEditToggle = () => {
        if (editMode) {
            updateUser(editableUser);
            console.log("Saving changes:", editableUser);
        }
        setEditMode(!editMode);
    };

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setEditableUser(prev => ({ ...prev, [name]: value }));
    };


    return (
        <div className="main-content">
            <div className="profile-settings">
                <ProfileSettings />
            </div>
            <div className="content-area">
                {editMode ? (
                    <EditProfile user={editableUser} onChange={handleChange}>
                    <button id="edit-profile-btn" onClick={handleEditToggle}>{editMode ? 'Save' : 'Edit'}</button>
                    </EditProfile>
                ) : (
                    <ProfileInfo user={user}>
                        <button id="edit-profile-btn" onClick={handleEditToggle}>{editMode ? 'Save' : 'Edit'}</button>
                    </ProfileInfo>
                )}
                <OrderHistory orders={orders} />
            </div>
        </div>
    )
};

export default Profile;
