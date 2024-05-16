import React, { ReactNode } from "react";
import "../../CSS-files/profile.css";
import { User } from "../../types/types";



interface ProfileInfoProps {
    user?: User | null;
    children?: ReactNode;
}

const ProfileInfo: React.FC<ProfileInfoProps> = ({ user, children }) => {
    if (!user) {
        console.log("user is null");
        return <div>Loading...</div>;
    }

    return (
        <div className="profile-info">
        <div className="profile-content">
            <div className="profile-avatar">
                {/*<img src={user.avatarUrl} alt="avatar" />*/}
            </div>
            <div className="profile-details">
                <h1>{user.firstName}</h1>
                {/*<p>{user.bio}</p>*/}
            </div>
        </div>
        {children}
        </div>
    );
};

export default ProfileInfo;

