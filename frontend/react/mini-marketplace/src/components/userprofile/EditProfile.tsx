import React, { ReactNode } from 'react';
import '../../CSS-files/profile.css';
import { User } from '../../types/types';


interface EditProfileProps {
    user: User | null; 
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    children?: ReactNode;
}



const EditProfile: React.FC<EditProfileProps> = ({ user, onChange, children }) => {
    return (
        <div className="profile-info profile-content">
            <input
                className="profile-details"
                type="text"
                name="first-name"
                value={!user ? 'null user' : user.first_name}
                onChange={onChange}
            />
            <input
                className="profile-details"
                type="text"
                name="last-name"
                value={!user ? 'null user' : user.last_name}
                onChange={onChange}
            />
            {children}

        </div>
    );
};


export default EditProfile;
