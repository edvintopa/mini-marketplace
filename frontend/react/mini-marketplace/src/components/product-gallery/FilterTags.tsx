import { useState } from 'react';

export const FilterTags = ({tag, listOfCheckbox}: { tag: string, listOfCheckbox: Array<string> }) => {
    const [isOpen, setIsOpen] = useState(false);

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    }

    return (
        <div id="list1" className={`dropdown-filter ${isOpen ? 'open' : ''}`} onClick={toggleDropdown}>
            <span className="anchor">{tag}</span>
            <ul className="items">
                {listOfCheckbox.map((item: string) => {
                    return (
                        <li><input type="checkbox" className='FilterOption'/>{item}</li>
                    )
                })}
            </ul>
        </div>
    )
}