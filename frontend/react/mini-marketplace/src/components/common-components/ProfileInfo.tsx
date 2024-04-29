import React, { ReactNode } from "react";
import "../../CSS-files/profile.css";




interface ProfileInfoProps {
    user: {
        name: string; /** TODO: split into first and last name */
        bio: string;
        avatarUrl: string;
    };
    children?: ReactNode;
}

const ProfileInfo: React.FC<ProfileInfoProps> = ({ user, children }) => {
    return (
        <div className="profile-info">
        <div className="profile-content">
            <div className="profile-avatar">
                <img src={user.avatarUrl} alt="avatar" />
            </div>
            <div className="profile-details">
                <h1>{user.name}</h1>
                <p>{user.bio}</p>
            </div>
        </div>
        {children}
        </div>
    );
};

export default ProfileInfo;

