export interface User {
        UUID: string,
        first_name: string,
        last_name: string,
        username: string,
        password: string,
        date_of_birth: string,
        email: string,
        balance: number,
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

