import React, { useState, useEffect } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleDown, faAngleUp } from '@fortawesome/free-solid-svg-icons';
import { useUser } from '../../context/UserContext';
import '../../CSS-files/orderHistory.css';

const SellOrderAndListingHistory: React.FC = () => {
    const [isOpen, setIsOpen] = useState(false);
    const { sellOrders, getSellOrders, confirmOrder, rejectOrder, listings, getListings } = useUser();

    const toggle = () => {
        setIsOpen(!isOpen);
    };

    useEffect(() => {
        getSellOrders();
        getListings();
    }, [getSellOrders, getListings]);

    const formatDate = (dateString: string) => {
        const options: Intl.DateTimeFormatOptions = { year: 'numeric', month: 'long', day: 'numeric' };
        return new Date(dateString).toLocaleDateString(undefined, options);
    };

    const handleConfirmOrder = (orderId: string) => {
        confirmOrder(orderId);
    };

    const handleRejectOrder = (orderId: string) => {
        rejectOrder(orderId);
    };

    return (
        <div className="order-history-parent">
            <div className="order-action-button">
                <button onClick={toggle}>
                    <span>Listings & Sell Orders</span>
                    {isOpen ? <FontAwesomeIcon className="order-history-icon" icon={faAngleUp} />
                    : <FontAwesomeIcon className="order-history-icon" icon={faAngleDown} /> }
                </button>
            </div>
            <div className={`order-history-container ${isOpen ? 'visible' : ''}`}>
                {isOpen && (
                    <>
                    <h3>Listings</h3>
                    <table className="order-table">
                        <thead>
                            <tr>
                                <th>Product ID</th>
                                <th>Title</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listings.map(listing => (
                                <tr key={listing.productId}>
                                    <td>{listing.productId}</td>
                                    <td>{listing.title}</td>
                                    <td>{listing.price}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    <h3>Sell Orders</h3>
                    <table className="order-table">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Date</th>
                                <th>Product ID</th>
                                <th>Total</th>
                                <th>Confirmed</th>
                            </tr>
                        </thead>
                        <tbody>
                            {sellOrders.map(order => (
                                <tr key={order.orderId}>
                                    <td>{order.orderId}</td>
                                    <td className="order-date">{formatDate(order.orderDate)}</td>
                                    <td>{order.productId}</td>
                                    <td>{order.total}</td>
                                    <td>{order.confirmed ? 'Yes' : 'No'}</td>
                                    <td>
                                        {!order.confirmed && (
                                            <>
                                                <button onClick={() => handleConfirmOrder(order.orderId)} className="confirm-order-button">
                                                    Confirm
                                                </button>
                                                <button onClick={() => handleRejectOrder(order.orderId)} className="reject-order-button">
                                                    Reject
                                                </button>
                                            </>
                                        )}
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                    </>
                )}
            </div>
        </div>
    );
};

export default SellOrderAndListingHistory;

