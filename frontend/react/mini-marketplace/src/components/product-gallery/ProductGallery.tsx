import React, { useEffect, useState } from 'react';
import { ProductGalleryCard } from './ProductGalleryCard';
import { FilterTagWrapper } from './FilterTagWrapper';
import { ClothingFilterRequest } from '../../types/types.ts';
import axios from 'axios';

export interface ProductInfo {
  product_id: string;
  title: string;
  name: string;
  price: number;
  product_image: string;
  url: string;
}

async function fetchProducts(): Promise<ProductInfo[]> {
  try {
    const response = await fetch('http://localhost:8080/product/get');
    const data = await response.json();
    console.log('Received data:', data);

    const products: ProductInfo[] = data.map((product: any) => ({
      product_id: product.productId,
      title: product.title,
      username: product.username,
      price: product.price,
      product_image: product.imagePath,
      url: product.url,
    }));

    console.log('Mapped products:', products);
    return products;
  } catch (error) {
    console.error('Error:', error);
    return [];
  }
}

export async function fetchProductsByFilter(filterTerms: ClothingFilterRequest): Promise<ProductInfo[]> {
  try {
    const query = new URLSearchParams(filterTerms as any).toString();
    const response = await axios.get<ProductInfo[]>(`http://localhost:8080/product/filterAll?${query}`);

    const products = response.data;

    console.log('Received filter terms:', filterTerms);
    console.log('Filtered products:', products);
    return products;
  } catch (error) {
    console.error('Error:', error);
    return [];
  }
}

export const ProductGallery = () => {
  const [products, setProducts] = useState<ProductInfo[]>([]);

  useEffect(() => {
    fetchProducts().then(setProducts);
  }, []);

  const handleApplyFilter = (filterTerms: ClothingFilterRequest) => {
    console.log('Applying filter with terms:', filterTerms);
    fetchProductsByFilter(filterTerms).then(setProducts);
  };

  return (
      <>
        <div className='FilterTagWrapper'>
          <FilterTagWrapper onApplyFilter={handleApplyFilter} />
        </div>
        <div className='ProductGallery'>
          {products.map((product) => (
              <ProductGalleryCard
                  key={product.product_id}
                  imagelink={product.product_image}
                  url={`/productview/${product.product_id}`}
                  title={product.title}
                  price={`${product.price} kr`}
                  id={product.product_id}
              />
          ))}
        </div>
      </>
  );
};
