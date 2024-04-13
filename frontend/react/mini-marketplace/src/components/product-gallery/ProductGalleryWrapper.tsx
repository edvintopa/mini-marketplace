import { ProductGallery } from './ProductGallery'
import { SearchTitle } from './SearchTitle'

export const ProductGalleryWrapper = () => {
  return (
    <div>
        <SearchTitle searchquery={'Placeholder'}/>
        <ProductGallery/>
    </div>
  )
}
