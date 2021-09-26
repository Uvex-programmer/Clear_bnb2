import CardOld from '../UI/CardOld/CardOld'

const SearchResults = ({ results }) => {
  console.log('inside', results)
  return results.map((property) => {
    return (
      <div style={{ width: '200px' }}>
        <CardOld key={property.id} id={property.id}>
          {property.title}
        </CardOld>
      </div>
    )
  })
}

export default SearchResults
