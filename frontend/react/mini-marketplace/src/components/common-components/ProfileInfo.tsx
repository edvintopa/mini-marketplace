import React, { useState } from "react";
import "../../CSS-files/profile.css";




interface ProfileInfoProps {
    user: {
        name: string;
        bio: string;
        avatarUrl: string;
    };
}

const ProfileInfo: React.FC<ProfileInfoProps> = ({ user }) => {
    return (
        <div className="profile-info">
            <div className="profile-avatar">
                <img src={user.avatarUrl} alt="avatar" />
            </div>
            <div className="profile-details">
                <h1>{user.name}</h1>
                <p>{user.bio}</p>
            </div>
        </div>
    );
};

export default ProfileInfo;

