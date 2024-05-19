

export const FilterOptionsButton = ({nameOfButton} : {nameOfButton : string}) => {
    return (
        <div className='FilterOptionsButton'>
            <button className={'FilterTagButton'}>{nameOfButton}</button>
        </div>
    )
}
