import React, { createContext, useState, useContext, ReactNode, useEffect, useCallback } from 'react';
import axios from 'axios';
import {SignupFormData, User, UserContextType, Order, Notification} from '../types/types';

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
    const [orders, setOrders] = useState<Order[]>([]);
    const [sellOrders, setSellOrders] = useState<Order[]>([]);
    const [notifications, setNotifications] = useState<Notification[]>([]);
    const [fetchedInterests, setFetchedInterests] = useState<string[]>([]);

    useEffect(() => {
        const storedToken = localStorage.getItem('token');
        if (storedToken) {
            setToken(storedToken);
            axios.get<User>(`http://localhost:8080/user/me`, {
                headers: { Authorization: `Bearer ${storedToken}` },
            })
            .then(response => {
                setUser(response.data);
                localStorage.setItem('user', JSON.stringify(response.data));
            })
            .catch(err => {
                console.log('Failed to fetch user details:', err);
                localStorage.removeItem('token');
                localStorage.removeItem('user');
            });
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

    const fetchNotifications = useCallback(async () => {
        if(!token) return;
        try {
            const response = await axios.get<Notification[]>('http://localhost:8080/user/notifications', {
                headers: { Authorization: `Bearer ${token}`},
            });
            console.log(response.data);
            setNotifications(response.data);
        } catch (error) {
            console.error('Error fetching notifications:', error)
            setError('Error fetching notifications')
        }
    }, [token]);

    const fetchInterests = useCallback( async () => {
        if(!token) return;
        try {
            const response = await axios.get<string[]>('http://localhost:8080/user/getinterests', {
                headers: { Authorization: `Bearer ${token}`},
            });
            console.log(response.data);
            setFetchedInterests(response.data);
        } catch (error) {
            console.error('Error fetching interests:', error)
            setError('Error fetching interests')
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

    const fetchOrders = useCallback(async () => {
        if (!token) return;
        try {
            const response = await axios.get<Order[]>(`http://localhost:8080/order/get`, {
                headers: { Authorization: `Bearer ${token}` },
            });
            console.log(response.data);
            setOrders(response.data);
        } catch (error) {
            console.error('Error fetching orders:', error);
            setError('Error fetching orders:');
        }
    }, [token]);

    const cancelOrder = async (orderId: string) => {
        if (!token) return false;
        console.log('Order id that is being sent: ', orderId);
        const requestBody = { id: orderId };
        console.log('Request body:', requestBody);
        try {
            const response = await axios.post(`http://localhost:8080/order/cancel`, requestBody, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            if (response.status === 200) {
                setOrders(prevOrders => prevOrders.filter(order => order.orderId !== orderId));
                console.log('Order cancellation successful for order id:', orderId);
                return true;
            } else {
                setError('Failed to cancel order.');
                return false;
            }
        } catch (error) {
            console.error('Failed to cancel order:', error);
            setError('Failed to cancel order.');
            return false;
        }
    };

    const getSellOrders = useCallback(async () => {
        if (!token) return;
        try {
            const response = await axios.get<Order[]>(`http://localhost:8080/order/sellorder`, {
                headers: { Authorization: `Bearer ${token}` },
            });
            console.log(response.data);
            setSellOrders(response.data);
        } catch (error) {
            console.error('Error fetching sell orders:', error);
            setError('Error fetching sell orders:');
        }
    }, [token]);

    const confirmOrder = async (orderId: string) => {
        if (!token) return false;
        console.log('Order id that is being confirmed: ', orderId);
        const requestBody = { id: orderId };
        console.log('Request body:', requestBody);
        try {
            const response = await axios.post(`http://localhost:8080/order/confirm`, requestBody, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            if (response.status === 200) {
                setSellOrders(prevOrders => prevOrders.map(order => 
                    order.orderId === orderId ? { ...order, confirmed: true } : order
                ));
                console.log('Order confirmation successful for order id:', orderId);
                return true;
            } else {
                setError('Failed to confirm order.');
                return false;
            }
        } catch (error) {
            console.error('Failed to confirm order:', error);
            setError('Failed to confirm order.');
            return false;
        }
    };

    const rejectOrder = async (orderId: string) => {
        if (!token) return false;
        console.log('Order id that is being rejected: ', orderId);
        const requestBody = { id: orderId };
        console.log('Request body:', requestBody);
        try {
            const response = await axios.post(`http://localhost:8080/order/reject`, requestBody, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            if (response.status === 200) {
                setSellOrders(prevOrders => prevOrders.filter(order => order.orderId !== orderId));
                console.log('Order rejection successful for order id:', orderId);
                return true;
            } else {
                setError('Failed to reject order.');
                return false;
            }
        } catch (error) {
            console.error('Failed to reject order:', error);
            setError('Failed to reject order.');
            return false;
        }
    };





    const loginUser = async (username: string, password: string): Promise<boolean> => {
        try {
            const response = await axios.post<{ username: string, token: string }>(`http://localhost:8080/user/login`, {
                username,
                password,
            });
            
            console.log("Full response:", response);
            console.log("Response data:", response.data);

            if (response.data) {
                const { token } = response.data;
                setToken(token);
                localStorage.setItem('token', token);

                const userResponse = await axios.get<User>(`http://localhost:8080/user/me`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                const user = userResponse.data;
                setUser(user);
                localStorage.setItem('user', JSON.stringify(user));


                console.log('Login successful for user:', username);
                return true;
            } else {
                setError('No data returned from server');
                console.log('No data returned from server');
                return false;
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
            return false;
        }
    };

    const logoutUser = () => {
        setUser(null);
        setToken(null);
        localStorage.removeItem('user');
        localStorage.removeItem('token');
    };

    const signupUser = async (formData: SignupFormData): Promise<boolean> => {
        try {
            const response = await axios.post<{ username: string, token: string }>(`http://localhost:8080/user/register`, formData);

            if (response.data) {
                const { token } = response.data;
                setToken(token);
                localStorage.setItem('token', token);

                // Fetch full user details using the /me endpoint
                const userResponse = await axios.get<User>(`http://localhost:8080/user/me`, {
                    headers: { Authorization: `Bearer ${token}` }
                });

                const user = userResponse.data;
                setUser(user);
                localStorage.setItem('user', JSON.stringify(user));

                console.log('Signup successful for user:', user);
                return true;
            } else {
                setError('No data returned from server');
                return false;
            }
        } catch (err: unknown) {
            if (axios.isAxiosError(err) && err.response) {
                const errorResponse = err.response.data as string;
                setError(`Signup failed: ${errorResponse}`);
            } else {
                setError('An unexpected error occurred during signup.');
            }
            return false;
        }
    };

    const setUserInterests = async (interests: string[]): Promise<boolean> => {
        if (!token) return false;
        try {
            await axios.post(`http://localhost:8080/user/setinterests`, { interests }, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            setUser((prevUser) => prevUser ? {...prevUser, interests } : null);
            return true;
        } catch (error) {
            console.error('Failed to set user interests:', error);
            setError('Failed to set user interests');
            return false;
        }
    };

    return (
        <UserContext.Provider value={{
            user, fetchUser, loginUser, logoutUser, signupUser, setUserInterests,
            token, error, fetchOrders, orders, cancelOrder, getSellOrders, sellOrders,
            confirmOrder, rejectOrder, notifications, fetchNotifications, fetchedInterests, fetchInterests }}>
            {children}
        </UserContext.Provider>
    );

};

export default UserProvider;
