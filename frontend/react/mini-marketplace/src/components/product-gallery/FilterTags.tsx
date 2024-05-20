import React, { useState } from 'react';

export const FilterTags = ({ tag, listOfCheckbox, onSelectionChange }: { tag: string, listOfCheckbox: Array<string>, onSelectionChange: (tag: string, items: string[]) => void }) => {
    const [isOpen, setIsOpen] = useState(false);
    const [selectedItems, setSelectedItems] = useState<Array<string>>([]);

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    }

    const handleCheckboxChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { checked, value } = event.target;
        let newSelectedItems;
        if (checked) {
            newSelectedItems = [...selectedItems, value];
        } else {
            newSelectedItems = selectedItems.filter(item => item !== value);
        }
        setSelectedItems(newSelectedItems);
        onSelectionChange(tag, newSelectedItems); // Call the parent callback
    }

    return (
        <div id="list1" className={`dropdown-filter ${isOpen ? 'open' : ''}`} onClick={toggleDropdown}>
            <span className="anchor">{tag}</span>
            <ul className="items">
                {listOfCheckbox.map((item: string, index: number) => (
                    <li key={index}>
                        <input
                            type="checkbox"
                            className='FilterOption'
                            value={item}
                            onChange={handleCheckboxChange}
                        />
                        {item}
                    </li>
                ))}
            </ul>
        </div>
    );
}
