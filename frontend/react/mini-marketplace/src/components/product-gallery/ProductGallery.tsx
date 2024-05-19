import { ProductGalleryCard } from './ProductGalleryCard'
import { FilterTagWrapper } from './FilterTagWrapper'
import {useEffect, useState} from "react";

export interface ProductInfo {
  product_id: string;
  title: string;
  name: string;
  price: number;
  product_image: string;
  url: string;
}



export const arrayOfProducts = [
  { id: '1', name: 'Hoodie', price: 200, imagelink: 'https://media-photos.depop.com/b1/11985249/1825399086_03666561d28c44f09dfdcd78b8cd5ca1/P0.jpg', url: '/productview/1' },
  { id: '2', name: 'Comme de Garcon', price: 1000, imagelink: 'https://media-photos.depop.com/b1/14563374/1813639761_27c530aaa2f84b2c89eb00f9bb0c2381/P0.jpg', url: '/productview/2' },
  { id: '3', name: 'Ballet shoes', price: 350, imagelink: 'https://media-photos.depop.com/b1/39105082/1792970550_701cc8458f0a499fb86b01d7ad7ccae9/P0.jpg', url: '/productview/3' },
  { id: '4', name: 'Hoodie', price: 275, imagelink: 'https://media-photos.depop.com/b1/36997595/1826378382_2aeeaf9316d5404c88d91aa6b0f56355/P0.jpg', url: '/productview/4' },
  { id: '5', name: 'Hollister hoodie', price: 300, imagelink: 'https://media-photos.depop.com/b1/6260622/1820567173_661acb1583064a6e9e3f39edb8eaecbb/P0.jpg', url: '/productview/5' },
  { id: '6', name: 'Fendi bag', price: 700, imagelink: 'https://media-photos.depop.com/b1/47780301/1822121408_4be6229f80994c80a83ccd3fc0288126/P0.jpg', url: '/productview/6' },
  { id: '7', name: 'Leather bag', price: 400, imagelink: 'https://media-photos.depop.com/b1/23033242/1826419642_32259e2a5765428d8e12b4c0e480a318/P0.jpg', url: '/productview/7' },
  { id: '8', name: 'Leather jacket', price: 650, imagelink: 'https://media-photos.depop.com/b1/21291151/1826390941_449b3bf72c404dfa87dda0f1d1913f5d/P0.jpg', url: '/productview/8' },
];

async function fetchProducts(): Promise<ProductInfo[]> {
  try {
    const response = await fetch('http://localhost:8080/product/get');
    const data = await response.json();
    console.log('Received data:', data); // Log the received data

    // Map the received data to the ProductInfo interface
    const products: ProductInfo[] = data.map((product: any) => ({
      product_id: product.productId,
      title: product.title,
      username: product.username,
      price: product.price,
      product_image: product.imagePath,
      url: product.url,
    }));

    console.log('Mapped products:', products); // Log the mapped products
    return products;
  } catch (error) {
    console.error('Error:', error);
    return [];
  }
}

export const ProductGallery = () => {
  const [products, setProducts] = useState<ProductInfo[]>([]);

  useEffect(() => {
    fetchProducts().then(products => {
      console.log('Fetched products:', products); // Log the fetched products
      setProducts(products);
    });
  }, []);


  return (
      <>
        <div className='FilterTagWrapper'><FilterTagWrapper /></div>
        <div className='ProductGallery'>
          {products.map((product) => (
              <ProductGalleryCard
                  imagelink={product.product_image}
                  url={`/productview/${product.product_id}`}
                  title={product.title}
                  price={`${product.price} kr`}
                  id={product.product_id}
              />
          ))}
        </div>
      </>
  )
}
