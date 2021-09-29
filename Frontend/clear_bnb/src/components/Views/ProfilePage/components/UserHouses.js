import CardOld from '../../../UI/CardOld/CardOld'

const UserProperties = ({ properties }) => {
  return properties.map((property, index) => {
    return (
      <CardOld id={property.id} key={index}>
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
}

export default UserProperties
