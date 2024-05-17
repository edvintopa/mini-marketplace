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
    onChange: (event: unknown) => void;
    textFieldTitle: string;
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
    token: string | null;
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

