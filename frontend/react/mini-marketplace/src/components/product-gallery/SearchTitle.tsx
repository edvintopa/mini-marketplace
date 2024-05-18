export const SearchTitle = ({searchquery}: {searchquery: string}) => {

  console.log(searchquery + " searchtitle")
  return (
      <div><h2 className="SearchQueryTitle">{searchquery}</h2></div>
  )
}

