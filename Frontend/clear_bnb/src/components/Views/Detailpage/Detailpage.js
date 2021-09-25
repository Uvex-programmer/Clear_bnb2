import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import classes from './Detailpage.module.css'

const Detailpage = () => {
  const { id } = useParams()
  const [property, setProperty] = useState()
  let images = ''

  useEffect(() => {
    fetch(`/api/properties/${id}`)
      .then(async (res) => JSON.parse(await res.json()))
      .then((data) => {
        console.log(data)
        setProperty(data)
      })
  }, [id])

  if (property?.images?.length > 0) {
    images = property.images.map((image) => {
      return <img src={image.img_url} alt={image.id} />
    })
  }

  return (
    <div className={classes['detailpage-container']}>
      {property && (
        <>
          {/* {images} */}
          <img src='https://i.imgur.com/k9W5G.jpeg' alt='test' />
          <h1>{property.title}</h1>
          <p>{property.description}</p>
          <div>
            <h2>Details:</h2>
            <p>Bathrooms: {property.bathrooms}</p>
            <p>Beds: {property.beds}</p>
            <p>Created at: {property.createdAt}</p>
            <p>Start at: {property.startDate}</p>
            <p>Ending at: {property.endDate}</p>
            <p>Max guests: {property.guests}</p>
          </div>
        </>
      )}
    </div>
  )
}

export default Detailpage
