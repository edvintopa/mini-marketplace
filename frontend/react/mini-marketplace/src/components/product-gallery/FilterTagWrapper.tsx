import { FilterTags } from './FilterTags' /** change from plural to singular */
import { FilterOptionsButton } from './FilterOptionsButton.tsx' /** change from plural to singular */

export const FilterTagWrapper = () => {
        const seasonTags = ['SPRING', 'SUMMER', 'AUTUMN', 'WINTER', 'ALL_SEASON'];
        const genderTags = ['MEN', 'WOMEN', 'UNISEX'];
        const sizeTags = ['XS','S', 'M', 'L', 'XL'];
        const typeTags = ['TSHIRT', 'SHIRT', 'HOODIE', 'TROUSERS', 'SHORTS', 'SKIRT', 'DRESS', 'FOOTWEAR', 'HEADWEAR', 'ACCESSORY', 'OTHERS'];
        const colorTags = ['BLACK', 'WHITE', 'RED', 'BLUE', 'GREEN', 'YELLOW', 'PURPLE', 'ORANGE', 'PINK', 'BROWN', 'GRAY', 'OTHERS'];
        const conditionTags = ['NEW', 'VERY GOOD', 'GOOD', 'NOT_ WORKING_PROPERLY', 'USED'];
        const priceRange = ['0-100', '101-300', '301-500', '501-1000', '1000+'];
        /**
         *  <FilterTags tag={'Season'} listOfCheckbox={seasonTags}/>
         *         <FilterTags tag={'Gender'} listOfCheckbox={genderTags}/>
         *         <FilterTags tag={'Size'} listOfCheckbox={sizeTags}/>
         *         <FilterTags tag={'Clothing Type'} listOfCheckbox={typeTags}/>
         *         <FilterTags tag={'Color'} listOfCheckbox={colorTags}/>
         *         <FilterTags tag={'Condition'} listOfCheckbox={conditionTags}/>
         */

  return (
    <div className='FilterTagGallery'>
        <FilterTags tag={'Product type'} listOfCheckbox={typeTags}/>
        <FilterTags tag={'Price range'} listOfCheckbox={priceRange}/>
        <FilterTags tag={'Condition'} listOfCheckbox={conditionTags}/>
        <FilterOptionsButton nameOfButton={'Apply filter'}/>
    </div>
  )
}
