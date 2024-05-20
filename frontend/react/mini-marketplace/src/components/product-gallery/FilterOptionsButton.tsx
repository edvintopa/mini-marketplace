import { ClothingFilterRequest } from '../../types/types.ts';

interface FilterOptionsButtonProps {
    nameOfButton: string;
    selectedFilters: ClothingFilterRequest;
    onApplyFilter: (filterTerms: ClothingFilterRequest) => void;
}

export const FilterOptionsButton = ({ nameOfButton, selectedFilters, onApplyFilter }: FilterOptionsButtonProps) => {
    const handleClick = () => {
        console.log('Selected filters:', selectedFilters);
        onApplyFilter(selectedFilters);
    };

    return (
        <div className='FilterOptionsButton'>
            <button className='ApplyFilterTagButton' onClick={handleClick}>{nameOfButton}</button>
        </div>
    );
};
