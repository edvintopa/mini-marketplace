import React from 'react'
import { FilterTags } from './FilterTags' /** change from plural to singular */

export const FilterTagWrapper = () => {
  return (
    <div className='FilterTagGallery'>
        <FilterTags tag={'Hoodie'}/>
        <FilterTags tag={'Bag'}/>
        <FilterTags tag={'Shoes'}/>
    </div>
  )
}
