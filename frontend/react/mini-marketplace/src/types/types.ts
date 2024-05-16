export interface User {
        userId: string;
        firstName: string;
        lastName: string;
        username: string;
        password: string;
        dateOfBirth: string;
        email: string;
        balance: number;
}

export interface TextFieldComponentTypes {
    name: string;
    type: string;
    onChange: (event: any) => void;
    textFieldTitle: string;
}

export interface LoginRequest {
    username: string;
    password: string;
}

export interface RegisterRequest {
    first_name: string;
    last_name: string;
    username: string;
    password: string;
    date_of_birth: string;
    email: string;
}

export interface UserContextType {
    user: User | null;
    error: string;
    //updateUser: (user: User) => void;
    fetchUser: (username: string) => void;
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

