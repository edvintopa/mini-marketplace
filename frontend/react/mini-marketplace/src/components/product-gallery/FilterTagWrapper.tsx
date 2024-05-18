import { FilterTags } from './FilterTags' /** change from plural to singular */

export const FilterTagWrapper = () => {
  return (
    <div className='FilterTagGallery'>
        <FilterTags tag={'Season'}/>
        <FilterTags tag={'Gender'}/>
        <FilterTags tag={'Size'}/>
        <FilterTags tag={'Clothing Type'}/>
        <FilterTags tag={'Color'}/>
        <FilterTags tag={'Condition'}/>
    </div>
  )
}
