import "../CSS-files/productview.css";
import { CurrentProductView } from './product-view/ProductView';
import {useParams} from "react-router-dom";

export const ProductViewApp = () => {
    const { id } = useParams<{ id: string }>();

    if (!id) {
        // Redirect to a different page or return a fallback UI
        return <div>No product ID provided</div>;
    }

    return (
        <div className='ProductViewWrapper'>
            <CurrentProductView id={id} />
        </div>
    )
}