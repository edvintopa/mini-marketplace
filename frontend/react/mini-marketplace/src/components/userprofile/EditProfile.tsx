import React, { ReactNode } from 'react';
import '../../CSS-files/profile.css';
import { TextFieldComponent } from '../common-components/TextFieldComponent';


interface EditProfileProps {
    user: {
        name: string;
        bio: string;
        avatarUrl: string;
    };
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    children?: ReactNode;
}



const EditProfile: React.FC<EditProfileProps> = ({ user, onChange, children }) => {
    return (
        <div className="profile-info profile-content">
            <input
            type="text"
            name="avatarUrl"
            value={user.avatarUrl}
            onChange={onChange}
            />
            <input
                className="profile-details"
                type="text"
                name="name"
                value={user.name}
                onChange={onChange}
            />
            <input
                className="profile-details"
                type="text"
                name="bio"
                value={user.bio}
                onChange={onChange}
            />
            {children}

        </div>
    );
};


export default EditProfile;
