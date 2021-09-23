import CardOld from '../UI/CardOld/CardOld'

const SearchResults = ({ results }) => {
	let properties = results.map((property) => {
		return (
			<CardOld>
				<div>id: {property.id}</div>
				<div>title: {property.title}</div>
				<div>Description: {property.description}</div>
				<div>beds: {property.beds}</div>
				<div>bathrooms{property.bathrooms}</div>
				<div>city: {property.address.city}</div>
				<div>street: {property.address.street}</div>
				<div>Zipcode: {property.address.zipcode}</div>
			</CardOld>
		)
	})
	return <div>{properties}</div>
}

export default SearchResults
