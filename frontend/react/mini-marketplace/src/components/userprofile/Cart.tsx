import React, { useEffect } from 'react';
import { useUser } from '../../context/UserContext';
import '../../CSS-files/cart.css';

const Cart: React.FC = () => {
    const { token, cartItems, fetchCartItems, removeFromCart, clearCart, createOrder } = useUser();

    useEffect(() => {
        if (token) {
            fetchCartItems();
        }
    }, [token, fetchCartItems]);

    const handleRemoveFromCart = (productId: string) => {
        removeFromCart(productId).then(success => {
            if (success) {
                console.log(`Product ${productId} removed from cart`);
            }
        });
    };

    const handleClearCart = () => {
        clearCart().then(success => {
            if (success) {
                console.log('Cart cleared');
            }
        });
    };

    const handleCreateOrder = (productId: string) => {
        createOrder(productId).then(success => {
            if (success) {
                console.log(`Order created for product ${productId}`);
            }
        });
    };

    if (!token) {
        return <div>Please log in to view your cart.</div>;
    }

    if (cartItems.length === 0) {
        return <div>Your cart is empty.</div>;
    }

    return (
        <div className="cart-container">
            <h2>Your Cart</h2>
            <ul>
                {cartItems.map(item => (
                    <li key={item.productId}>
                        <img src={item.imagePath} alt={item.title} />
                        <div>{item.title}</div>
                        <div>{item.price} kr</div>
                        <button className="remove-button" onClick={() => handleRemoveFromCart(item.productId)}>Remove</button>
                        <button className="create-order-button" onClick={() => handleCreateOrder(item.productId)}>Create Order</button>
                    </li>
                ))}
            </ul>
            <button onClick={handleClearCart}>Clear Cart</button>
        </div>
    );
};

export default Cart;

