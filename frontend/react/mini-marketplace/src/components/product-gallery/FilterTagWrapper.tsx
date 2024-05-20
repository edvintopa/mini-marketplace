import { useState } from 'react';
import { FilterTags } from './FilterTags';
import { FilterOptionsButton } from './FilterOptionsButton';
import { ClothingFilterRequest } from '../../types/types.ts';

interface FilterTagWrapperProps {
    onApplyFilter: (filterTerms: ClothingFilterRequest) => void;
}

export const FilterTagWrapper = ({ onApplyFilter }: FilterTagWrapperProps) => {
    //const seasonTags = ['SPRING', 'SUMMER', 'AUTUMN', 'WINTER', 'ALL_SEASON'];
    //const genderTags = ['MEN', 'WOMEN', 'UNISEX'];
    //const sizeTags = ['XS', 'S', 'M', 'L', 'XL'];
    //const colorTags = ['BLACK', 'WHITE', 'RED', 'BLUE', 'GREEN', 'YELLOW', 'PURPLE', 'ORANGE', 'PINK', 'BROWN', 'GRAY', 'OTHERS'];
    const typeTags = ['TSHIRT', 'SHIRT', 'HOODIE', 'TROUSERS', 'SHORTS', 'SKIRT', 'DRESS', 'FOOTWEAR', 'HEADWEAR', 'ACCESSORY', 'OTHERS'];
    const conditionTags = ['NEW', 'VERY GOOD', 'GOOD', 'NOT_WORKING_PROPERLY', 'USED'];
    const priceRange = ['0-100', '101-300', '301-500', '501-1000', '1000+'];

    const [selectedFilters, setSelectedFilters] = useState<ClothingFilterRequest>({});

    const handleSelectionChange = (tag: string, items: string[]) => {
        setSelectedFilters(prevState => ({
            ...prevState,
            [tag]: items
        }));
    };

    return (
        <div className='FilterTagGallery'>
            <FilterTags tag='Product type' listOfCheckbox={typeTags} onSelectionChange={handleSelectionChange} />
            <FilterTags tag='Price range' listOfCheckbox={priceRange} onSelectionChange={handleSelectionChange} />
            <FilterTags tag='Condition' listOfCheckbox={conditionTags} onSelectionChange={handleSelectionChange} />
            <FilterOptionsButton nameOfButton='Apply filter' selectedFilters={selectedFilters} onApplyFilter={onApplyFilter} />
        </div>
    );
};
