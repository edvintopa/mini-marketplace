import { ProductGalleryCard } from './ProductGalleryCard'
import { FilterTagWrapper } from './FilterTagWrapper' 

export const ProductGallery = () => {
  return (
    <>
    <div className='FilterTagWrapper'><FilterTagWrapper /></div>
    <div className='ProductGallery'>
        <ProductGalleryCard imagelink={'https://media-photos.depop.com/b1/11985249/1825399086_03666561d28c44f09dfdcd78b8cd5ca1/P0.jpg'} url={'https://hd.se'} title={'Hoodie'} price={200 + " kr"}/>
        <ProductGalleryCard imagelink={'https://media-photos.depop.com/b1/14563374/1813639761_27c530aaa2f84b2c89eb00f9bb0c2381/P0.jpg'} url={'https://hd.se'} title={'Comme de Garcon'} price={1000 + " kr"}/>
        <ProductGalleryCard imagelink={'https://media-photos.depop.com/b1/39105082/1792970550_701cc8458f0a499fb86b01d7ad7ccae9/P0.jpg'} url={'https://hd.se'} title={'Ballet shoes'} price={350 + " kr"}/>
        <ProductGalleryCard imagelink={'https://media-photos.depop.com/b1/36997595/1826378382_2aeeaf9316d5404c88d91aa6b0f56355/P0.jpg'} url={'https://hd.se'} title={'Hoodie'} price={275 + " kr"}/>
        <ProductGalleryCard imagelink={'https://media-photos.depop.com/b1/6260622/1820567173_661acb1583064a6e9e3f39edb8eaecbb/P0.jpg'} url={'https://hd.se'} title={'Hollister hoodie'} price={300 + " kr"}/>
        <ProductGalleryCard imagelink={'https://media-photos.depop.com/b1/47780301/1822121408_4be6229f80994c80a83ccd3fc0288126/P0.jpg'} url={'https://hd.se'} title={'Fendi bag'} price={700 + " kr"}/>
        <ProductGalleryCard imagelink={'https://media-photos.depop.com/b1/23033242/1826419642_32259e2a5765428d8e12b4c0e480a318/P0.jpg'} url={'https://hd.se'} title={'Leather bag'} price={400 + " kr"}/>
        <ProductGalleryCard imagelink={'https://media-photos.depop.com/b1/21291151/1826390941_449b3bf72c404dfa87dda0f1d1913f5d/P0.jpg'} url={'https://hd.se'} title={'Leather jacket'} price={650 + " kr"}/>
    </div>
    </>
  )
}
