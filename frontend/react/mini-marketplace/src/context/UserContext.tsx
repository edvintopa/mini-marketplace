import React, { createContext, useState, useContext, ReactNode, useEffect } from 'react';
import axios from 'axios';
import { User, UserContextType } from '../types/types';

/* TODO: leftover from editing profile
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
*/

const defaultState = {
    user: null,
    error: '',
    updateUser: () => {},
    loginUser: async (username: string, password: string) => {},
    logoutUser: () => {},
    fetchUser: async (username: string) => {},
    token: null,
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
    const [token, setToken] = useState<string | null>(null);

    useEffect(() => {
        const storedUser = localStorage.getItem('user');
        const storedToken = localStorage.getItem('token');
        if (storedUser && storedToken) {
            setUser(JSON.parse(storedUser));
            setToken(storedToken);
        }
    }, []);

    useEffect(() => {
        if (user) {
            localStorage.setItem('user', JSON.stringify(user));
        } else {
            localStorage.removeItem('user');
        }
    }, [user]);

    useEffect(() => {
        if (token) {
            localStorage.setItem('token', token);
        } else {
            localStorage.removeItem('token');
        }
    }, [token]);

    const fetchUser = async (username: string) => {
        try {
            const response = await axios.get<User>(`http://localhost:8080/user/get?username=${username}`);
            setUser(response.data);
        } catch (err: unknown) {
            if (axios.isAxiosError(err) && err.response) {
                const errorResponse = err.response.data as string;
                setError(`Fetching user failed: ${errorResponse}`);
            } else {
                setError('An unexpected error occurred, could not fetch user.');
            }
        }
    };

    const loginUser = async (username: string, password: string) => {
        try {
            const response = await axios.post<{ username: string, token: string }>(`http://localhost:8080/user/login`, {
                username,
                password,
            });
            
            console.log("Full response:", response);
            console.log("Response data:", response.data);

            if (response.data) {
                const { token, username } = response.data;
                setToken(token);

                const user: User = {
                    userId: "",
                    firstName: "",
                    lastName: "",
                    username: username,
                    password: "",
                    dateOfBirth: "",
                    email: "",
                    balance: 0,
                };
                setUser(user);
                localStorage.setItem('token', token);

                console.log('Login successful for user:', username);
            } else {
                setError('No data returned from server');
                console.log('No data returned from server');
            }
        } catch (err: unknown) {
            if (axios.isAxiosError(err) && err.response) {
                const errorResponse = err.response.data as string;
                setError(`Login failed: ${errorResponse}`);
                console.log('Login failed:', errorResponse);
            } else {
                setError('An unexpected error occurred during login.');
                console.log('An unexpected error occurred during login:', err)
            }
        }
    };

    const logoutUser = () => {
        setUser(null);
        setToken(null);
        localStorage.removeItem('user');
        localStorage.removeItem('token');
    };

    /* TODO: leftover from editing profile
    const updateUser = (newUserData: User) => {
        setUser(newUserData);
    };
    */

    return (
        <UserContext.Provider value={{ user, fetchUser, loginUser, logoutUser, token, error }}>
            {children}
        </UserContext.Provider>
    );

};

export default UserProvider;
