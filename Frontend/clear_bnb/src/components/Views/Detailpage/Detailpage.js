import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import classes from './Detailpage.module.css'

const Detailpage = () => {
  const [property, setProperty] = useState()
  const [startDate, setStartDate] = useState('')
  const [endDate, setEndDate] = useState('')
  const { id } = useParams()
  let notSelected = startDate.length > 0 && endDate.length > 0 ? true : false
  let images = ''
  const visibility = notSelected
    ? { visibility: 'show' }
    : { visibility: 'hidden' }
  const showTotal = notSelected
    ? { visibility: 'hidden' }
    : { visibility: 'show' }

  const getPriceFromDates = () => {
    if (!notSelected) return [0, 0]

    const diffTime = Math.abs(new Date(startDate) - new Date(endDate))
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))
    const price = property.dailyPrice
      ? property.dailyPrice * diffDays
      : 150 * diffDays
    const totalPrice = diffDays * price
    return [diffDays, totalPrice]
  }

  useEffect(() => {
    fetch(`/api/properties/${id}`)
      .then(async (res) => await JSON.parse(await res.json()))
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

  console.log(notSelected)
  console.log([classes['date-requirement'], classes['hide-info']].join(' '))

  return (
    <div className={classes['detailpage-container']}>
      {property && (
        <>
          {/* {images} */}
          <img src='https://i.imgur.com/k9W5G.jpeg' alt='test' />
          <h1>{property.title}</h1>
          <p>{property.description}</p>
          <div className={classes['date-controls']}>
            <h3>Choose Date:</h3>
            <div className={classes['date-control']}>
              <label name='start-date'>Start date:</label>
              <input
                type='date'
                value={startDate}
                onChange={(e) => setStartDate(e.target.value)}
              />
            </div>
            <div className={classes['date-control']}>
              <label name='end-date'>End date:</label>
              <input
                type='date'
                value={endDate}
                onChange={(e) => setEndDate(e.target.value)}
              />
            </div>
          </div>
          <div className={classes['details-container']}>
            <h2>Details:</h2>
            <p>Bathrooms: {property.bathrooms}</p>
            <p>Beds: {property.beds}</p>
            <p>Created at: {property.createdAt}</p>
            <p>Start at: {property.startDate}</p>
            <p>Ending at: {property.endDate}</p>
            <p>Max guests: {property.guests}</p>
          </div>
          <p
            className={
              notSelected
                ? [classes['date-requirement'], classes['hide-info']].join(' ')
                : classes['date-requirement']
            }
          >
            You have to choose dates.
          </p>
          <p
            className={
              !notSelected
                ? [classes['date-requirement'], classes['hide-info']].join(' ')
                : classes['date-requirement']
            }
          >
            {getPriceFromDates()[0]} days. For a total of:{' '}
            {getPriceFromDates()[1]} kr
          </p>
          <button
            disabled={notSelected ? false : true}
            className={classes['booking-button']}
          >
            Book
          </button>
        </>
      )}
    </div>
  )
}

export default Detailpage
