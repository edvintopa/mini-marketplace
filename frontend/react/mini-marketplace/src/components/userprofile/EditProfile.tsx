import React from 'react';


interface EditProfileProps {
    user: {
        name: string;
        bio: string;
        avatarUrl: string;
    };
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}




const EditProfile: React.FC<EditProfileProps> = ({ user, onChange }) => {
    return (
        <div>
            <input
                type="text"
                name="name"
                value={user.name}
                onChange={onChange}
            />
            <input
                type="text"
                name="bio"
                value={user.bio}
                onChange={onChange}
            />
            <input
                type="text"
                name="avatarUrl"
                value={user.avatarUrl}
                onChange={onChange}
            />
        </div>
    );
};


export default EditProfile;
