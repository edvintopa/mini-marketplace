import React, { createContext, useState, useContext, ReactNode } from 'react';
import { User, UserContextType } from '../types/types';

const defaultState = {
    user: {
        UUID: "",
        first_name: "",
        last_name: "",
        username: "",
        password: "",
        date_of_birth: "",
        email: "",
        balance: 0,
    },
    updateUser: () => {}
};

const UserContext = createContext<UserContextType>(defaultState);

export const useUser = () => useContext(UserContext);

interface UserProviderProps {
    children: ReactNode;
}

export const UserProvider: React.FC<UserProviderProps> = ({ children }) => {
    const [user, setUser] = useState<User>({
        name: "Elliot Alderson",
        bio: "hello friend",
        avatarUrl: "https://i.imgur.com/9KYqMnT.png"
    });

    const updateUser = (newUserData: User) => {
        setUser(newUserData);
    };

    return (
        <UserContext.Provider value={{ user, updateUser }}>
            {children}
        </UserContext.Provider>
    );
};
