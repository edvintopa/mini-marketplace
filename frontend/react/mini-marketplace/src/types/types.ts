export interface User {
        userId: string;
        firstName: string;
        lastName: string;
        username: string;
        password: string;
        dateOfBirth: string;
        email: string;
        balance: number;
        interests?: string[];
}

export interface TextFieldComponentTypes {
    name: string;
    type: string;
    onChange: (event: unknown) => void;
    textFieldTitle: string;
}

export interface Notification {
    "category": string,
    "dateOfNotification": string
}

export interface SignupFormData {
    username: string,
    password: string,
    firstName: string,
    lastName: string,
    email: string,
    dateOfBirth: string,
}

export interface UserContextType {
    user: User | null;
    error: string;
    //updateUser: (user: User) => void;
    fetchUser: (username: string) => void;
    loginUser: (username: string, password: string) => Promise<boolean>;
    logoutUser: () => void;
    signupUser: (formData: SignupFormData) => Promise<boolean>;
    setUserInterests: (interests: string[]) => Promise<boolean>;
    fetchOrders: () => Promise<void>;
    cancelOrder: (id: string) => Promise<boolean>;
    getSellOrders: () => Promise<void>;
    confirmOrder: (id: string) => Promise<boolean>;
    rejectOrder: (id: string) => Promise<boolean>;
    token: string | null;
    orders: Order[];
    sellOrders: Order[];
    notifications: Notification[];
    fetchNotifications: () => Promise<void>;
    fetchedInterests: string[];
    fetchInterests: () => Promise<void>;
}

export interface Order {
    orderId: string;
    orderDate: string;
    total: number;
    confirmed: boolean;
    productId: string;
}

export interface OrderContextType {
    orders: Order[];
    addOrder: (order: Order) => void;
    removeOrder: (orderId: string) => void;
}

