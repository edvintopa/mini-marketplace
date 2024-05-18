import { ProductGallery } from './ProductGallery'
import { SearchTitle } from './SearchTitle'

export const ProductGalleryWrapper = ({ searchTerm }: { searchTerm: string }) => {

    return (
        <div>
            <SearchTitle searchquery={searchTerm.toString()}/>
            <ProductGallery/>
        </div>
    )
}