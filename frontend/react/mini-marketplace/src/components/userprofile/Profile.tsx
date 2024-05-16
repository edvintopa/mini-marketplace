import React, { useState, useEffect } from 'react';
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
    const { user, fetchUser } = useUser();
    const [editMode, setEditMode] = useState(false);
    const [editableUser, setEditableUser] = useState(user);

    useEffect(() => {
        fetchUser('johndoe');
    }, [fetchUser]);

    const handleEditToggle = () => {
        if (editMode) {
            //updateUser(editableUser);
            console.log("Saving changes:", editableUser);
        }
        setEditMode(!editMode);
    };

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setEditableUser(prev => ({ ...prev, [name]: value }));
    };

    /*
    useEffect(() => {
        const username = 'johndoe';
        fetch(`http://localhost:8080/user/get?username=${username}`)
        .then(response => response.json())
        .then(data => {
            setEditableUser(data);
        })
        .catch(error => {
            console.error('There was an error!', error);
            });
    }, []);
    */




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
                    <ProfileInfo user={editableUser || user}>
                        <button id="edit-profile-btn" onClick={handleEditToggle}>{editMode ? 'Save' : 'Edit'}</button>
                    </ProfileInfo>
                )}
                <OrderHistory orders={orders} />
            </div>
        </div>
    )
};

export default Profile;
