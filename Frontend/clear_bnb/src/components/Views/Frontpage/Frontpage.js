import { useEffect, useState } from 'react'
import CardOld from '../../UI/CardOld/CardOld'

function FrontPage() {
  const [properties, setProperties] = useState([])
  let cards = ''

  useEffect(() => {
    fetch('/api/get-properties')
      .then((res) => res.json())
      .then((data) => {
        console.log(data)
        setProperties(data)
      })
  }, [])

  if (properties?.length) {
    cards = properties.map((property) => {
      return (
        <CardOld key={property.id} id={property.id}>
          <p>{property.title}</p>
          <p>Price: {property.dailyPrice} kr</p>
        </CardOld>
      )
    })
  }

  return (
    <>
      <div className='frontpage'>
        <p>HOMEPAGE</p>
        {cards}
      </div>
    </>
  )
}

export default FrontPage
