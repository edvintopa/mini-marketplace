import React, { useEffect, useState } from 'react';
import { ProductGalleryCard } from './ProductGalleryCard';
import { FilterTagWrapper } from './FilterTagWrapper';
import {ClothingFilterRequest, User} from '../../types/types.ts';
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

export async function fetchProductsByFilter(filterTerms: any): Promise<ProductInfo[]> {
  try {
    // Map keys from filterTerms to match ClothingFilterRequest interface
    const requestBody: ClothingFilterRequest = {
      clothingType: filterTerms['Product type'] ? filterTerms['Product type'][0] : '',
      productCondition: filterTerms['Condition'] ? filterTerms['Condition'][0] : '',
      minPrice: getPriceRangeMin(filterTerms['Price range']),
      maxPrice: getPriceRangeMax(filterTerms['Price range'])
    };

    console.log(requestBody.clothingType + " is the clothing type");
    console.log(requestBody.productCondition + " is the product condition");
    console.log(requestBody.minPrice + " is the min price");
    console.log(requestBody.maxPrice + " is the max price");

    // Send the filter terms in the request body
    const response = await axios.post<ProductInfo[]>(`http://localhost:8080/product/filterAll`, requestBody);
    const products = response.data;

    console.log('Received filter terms:', requestBody);
    console.log('Filtered products:', products);
    return products;
  } catch (error) {
    console.error('Error:', error);
    return [];
  }
}


// Helper functions to extract min and max price from price range array
function getPriceRangeMin(priceRange: string[]): number {
  if (!priceRange || priceRange.length === 0) return 0;
  const prices = priceRange.map(range => {
    const [min, max] = range.split('-').map(str => str.replace('+', '')).map(Number);
    return min;
  });
  prices.sort((a, b) => a - b); // Sort the prices in ascending order
  return prices[0]; // Get the first (lowest) value
}


function getPriceRangeMax(priceRange: string[]): number {
  if (!priceRange || priceRange.length === 0) return 0;
  const prices = priceRange.map(range => {
    const [min, max] = range.split('-').map(Number);
    return max === undefined || isNaN(max) ? Number.POSITIVE_INFINITY : max;
  });
  prices.sort((a, b) => b - a); // Sort the prices in descending order
  return prices[0]; // Get the first (highest) value
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
