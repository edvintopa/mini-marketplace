import React, { useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleDown, faAngleUp } from '@fortawesome/free-solid-svg-icons';
import { Order } from '../../types/types';



interface OrderHistoryProps {
    orders: Order[];
}

const OrderHistory: React.FC<OrderHistoryProps> = ({ orders }) => {
    const [isOpen, setIsOpen] = useState(false);
    const toggle = () => {
        setIsOpen(!isOpen);
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
                    <ul>
                        {orders.map(order => (
                            <li className="order-item" key={order.id}>
                                {order.description}
                                <div className="order-date">
                                    {order.date}
                                </div>
                            </li>
                        ))}
                    </ul>
                )}
            </div>
        </div>
    );
};


export default OrderHistory;