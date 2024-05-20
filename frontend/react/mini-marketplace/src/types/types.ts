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

export interface ClothingFilterRequest {
    clothingType: string;
    productCondition: string;
    minPrice: number;
    maxPrice: number;
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
    token: string | null;
    orders: Order[];
    sellOrders: Order[];
    notifications: Notification[];
    fetchedInterests: string[];
    listings: Product[];
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
    fetchNotifications: () => Promise<void>;
    fetchInterests: () => Promise<void>;
    getListings: () => Promise<void>;
    addToCart: (productId: string) => Promise<boolean>
}

export interface Product {
    productId: string;
    seller: User;
    title: string;
    description: string;
    manufacturer: string;
    datePosted: string;
    price: number;
    productCondition: string;
    productStatus: string;
    productColor: string;
    imagePath: string;
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

