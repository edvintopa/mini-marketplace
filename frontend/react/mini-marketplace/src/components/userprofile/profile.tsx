import React from 'react';
import '../../CSS-files/profile.css';
import ProfileInfo from '../common-components/ProfileInfo';
import OrderHistory from './OrderHistory';
import ProfileSettings from './ProfileSettings';


interface User {
id: number;
name: string;
bio: string;
avatarUrl: string;
}

interface Order {
id: number;
description: string;
date: string;
}

interface ProfileProps {
    user: User;
    orders: Order[];
}


const Profile: React.FC<ProfileProps> = ({ user, orders }) => {


    return (
        <div className="main-content">
            <div className="profile-settings">
                <ProfileSettings />
            </div>
            <div className="content-area">
                <ProfileInfo user={user} />
                <OrderHistory orders={orders} />
            </div>
        </div>
    )
};

export default Profile;
