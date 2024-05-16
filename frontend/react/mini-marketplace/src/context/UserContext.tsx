import React, { createContext, useState, useContext, ReactNode } from 'react';
import axios from 'axios';
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

const UserContext = createContext<UserContextType | undefined>(undefined);

export const useUser = () => {
    const context = useContext(UserContext);
    if (!context) {
        console.log("useUser must be used within a UserProvider");
        throw new Error("useUser must be used within a UserProvider");
    }
    return context;
}

interface UserProviderProps {
    children: ReactNode;
}

export const UserProvider: React.FC<UserProviderProps> = ({ children }) => {
    const [user, setUser] = useState<User | null>(null);
    const [error, setError] = useState<string>('');

    const fetchUser = async (username: string) => {
        try {
            const response = await axios.get<User>(`http://localhost:8080/user/get?username=${username}`);
            console.log(response.data.firstName);
            setUser(response.data);
        } catch (err: unknown) {
            if (axios.isAxiosError(err) && err.response) {
                const errorResponse = err.response.data as string;
                setError(errorResponse);
            } else {
                setError('An unexpected error occurred, could not fetch user.');
            }
        }
    };



    const updateUser = (newUserData: User) => {
        setUser(newUserData);
    };

    return (
        <UserContext.Provider value={{ user, fetchUser, error }}>
            {children}
        </UserContext.Provider>
    );
};

export default UserProvider;
