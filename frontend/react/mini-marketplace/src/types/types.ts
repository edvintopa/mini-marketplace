export interface User {
    id: number;
    name: string;
    bio: string;
    avatarUrl: string;
}

export interface UserContextType {
    user: User;
    updateUser: (user: User) => void;
}

export interface Order {
    id: number;
    description: string;
    date: string;
}

export interface OrderContextType {
    orders: Order[];
    addOrder: (order: Order) => void;
    removeOrder: (id: number) => void;
}

