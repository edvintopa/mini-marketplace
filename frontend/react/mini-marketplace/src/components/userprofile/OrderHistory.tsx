import React, { useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleDown, faAngleUp } from '@fortawesome/free-solid-svg-icons';
import { Order } from '../../types/types';
import '../../CSS-files/orderHistory.css';
import { useUser } from '../../context/UserContext';


interface OrderHistoryProps {
    orders: Order[];
}

const OrderHistory: React.FC<OrderHistoryProps> = ({ orders }) => {
    const [isOpen, setIsOpen] = useState(false);
    const { cancelOrder } = useUser();
    const toggle = () => {
        setIsOpen(!isOpen);
    };

    const formatDate = (dateString: string) => {
        const options: Intl.DateTimeFormatOptions = { year: 'numeric', month: 'long', day: 'numeric' };
        return new Date(dateString).toLocaleDateString(undefined, options);
    };

    const handleCancelOrder = (id: string) => {
        cancelOrder(id);
    };

    return (
        <div className="order-history-parent">
            <div className="order-action-button">
                <button onClick={toggle}>
                    <span>Order History</span>
                    {isOpen ? <FontAwesomeIcon className="order-history-icon" icon={faAngleUp} />
                    : <FontAwesomeIcon className="order-history-icon" icon={faAngleDown} /> }
                </button>
            </div>
            <div className={`order-history-container ${isOpen ? 'visible' : ''}`}>
                {isOpen && (
                    <table className="order-table">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Total</th>
                                <th>Confirmed</th>
                                <th>Date</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {orders.map(order => (
                                <tr key={order.orderId}>
                                    <td>{order.orderId}</td>
                                    <td>{order.total}</td>
                                    <td>{order.confirmed ? 'Yes' : 'No'}</td>
                                    <td className="order-date">{formatDate(order.orderDate)}</td>
                                    <td>
                                        {!order.confirmed && (
                                            <button onClick={() => handleCancelOrder(order.orderId)} className="cancel-order-button">
                                                Cancel
                                            </button>
                                        )}
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};


export default OrderHistory;
