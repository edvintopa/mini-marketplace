import React, { useState } from 'react';
import '../../CSS-files/index.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faCog, faHistory, faPenToSquare, } from '@fortawesome/free-solid-svg-icons';

interface UserProfileProps {
    name: string;
    bio: string;
}

interface ProfileSectionProps {
    title: string;
    children: React.ReactNode;
}

const ProfileSettings = () => {
    return (
        <div className="profile-settings">
            <div className="settings-item">
                <FontAwesomeIcon icon={faPenToSquare} />
                <span>Edit Profile</span>
            </div>
            <div className="settings-item">
            <FontAwesomeIcon icon={faCog} />
            <span>Settings</span>
            </div>
        </div>
    );
};

const OrderHistory = () => {
    return (
        <div className="order-history">
            <FontAwesomeIcon icon={faHistory} /> <span>Order History</span>
        </div>
    );
};

const ProfileSection: React.FC<ProfileSectionProps> = ({ title, children }) => {
    return (
        <div className="profile-section">
            <h4>{title}</h4>
            {children}
        </div>
    );
};

const Profile: React.FC<UserProfileProps> = ({ name = "John Doe", bio = "I got the good stuff" }) => {


    return (
        <div className="profile-page">


            <main className="main-content">
                <div className="profile-header">
                    <div className="profile-avatar">
                        <FontAwesomeIcon icon={faUser} size="3x" />
                    </div>
                    <div className="profile-info">
                        <h4>{name}</h4>
                        <p>{bio}</p>
                    </div>
                </div>
                <ProfileSection title="Profile Settings">
                    <ProfileSettings />
                </ProfileSection>
                <ProfileSection title="Order History">
                    <OrderHistory />
                </ProfileSection>
            </main>
        </div>
    );
};

export default Profile;

