import CardOld from '../UI/CardOld/CardOld'

const SearchResults = ({ results }) => {
  let properties = results.map((property) => {
    return <CardOld>{property.title}</CardOld>
  })
  return <div>{properties}</div>
}

export default SearchResults
