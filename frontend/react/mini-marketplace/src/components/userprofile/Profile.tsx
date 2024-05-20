import React, { useState, useEffect } from 'react';
import '../../CSS-files/profile.css';
import ProfileInfo from '../common-components/ProfileInfo';
import OrderHistory from './OrderHistory';
import SellOrderAndListingHistory from './SellOrderAndListingHistory';
import EditProfile from './EditProfile';
import InterestsDropdown from './InterestsDropdown';
import { useUser } from '../../context/UserContext';
import { Order, User } from '../../types/types';


interface ProfileProps {
    orders: Order[];
}


const Profile: React.FC<ProfileProps> = () => {
    const { user, orders, fetchOrders } = useUser();
    const [editMode, setEditMode] = useState(false);
    const [editableUser, setEditableUser] = useState<User | null>(null);


    useEffect(() => {
        if (user) {
            setEditableUser(user);
        }
    }, [user]);

    useEffect(() => {
        if (user) {
            fetchOrders();
        }
    }, [user, fetchOrders]);

    const handleEditToggle = () => {
        if (editMode) {
            //updateUser(editableUser);
            console.log("Saving changes:", editableUser);
        }
        setEditMode(!editMode);
    };


    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setEditableUser(prev => prev ? ({ ...prev, [name]: value }) : null);
    };

    if (!editableUser) {
        return <div>Loading...</div>;
    }

    return (
        <div className="main-content">
            <div className="content-area">
                {editMode ? (
                    <EditProfile user={editableUser || user} onChange={handleChange}>
                    <button id="edit-profile-btn" onClick={handleEditToggle}>{editMode ? 'Save' : 'Edit'}</button>
                    </EditProfile>
                ) : (
                    <ProfileInfo user={editableUser || user}>
                        <button id="edit-profile-btn" onClick={handleEditToggle}>{editMode ? 'Save' : 'Edit'}</button>
                    </ProfileInfo>
                )}
                <InterestsDropdown />
                <OrderHistory orders={orders} />
                <SellOrderAndListingHistory />
            </div>
        </div>
    )
};

export default Profile;
