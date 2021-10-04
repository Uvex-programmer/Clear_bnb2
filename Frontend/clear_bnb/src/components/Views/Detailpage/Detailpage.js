import { useState, useEffect } from 'react'
import { useParams, useHistory, Link } from 'react-router-dom'
import { setChosenObject } from '../../../slicers/UserSlicer'
import classes from './Detailpage.module.css'
import { MessageWindow } from '../../Review/ReviewMsgWindow'
import ReviewPost from '../../Review/ReviewPostProperty'
import { setReviews } from '../../../slicers/PropertyReviewsSlicer'
import { useSelector, useDispatch } from 'react-redux'
import Card from '../../UI/CardOld/DanneRörInteDettaCard'
import AddProperty from '../UpdateProperty/UpdateProperty'

const Detailpage = () => {
	const [property, setProperty] = useState()
	const [checkUpdate, setCheckUpdate] = useState(false)
	const [startDate, setStartDate] = useState('')
	const [endDate, setEndDate] = useState('')
	const { id } = useParams()

	const userOnline = useSelector((state) => state.loginUser.user)
	const reviews = useSelector((state) => state.propertyReviews.reviews)
	let history = useHistory()
	let dispatch = useDispatch()

	let notSelected = startDate.length > 0 && endDate.length > 0 ? true : false
	let images = ''
	let amenities = 'No amenities'
	let logs = 'No older versions'

  useEffect(() => {
    fetch(`/api/get-property/${id}`)
      .then((res) => res.json())
      .then((data) => {
        if (data === null) {
          history.push('/')
          return
        }
        setProperty(data)
      })
  }, [id, checkUpdate])

  useEffect(() => {
    fetch(`/api/get-reviews-on-property/${id}`)
      .then((res) => res.json())
      .then((review) => {
        if (review === null) dispatch(setReviews([]))
        dispatch(setReviews(review))
      })
  }, [id, dispatch])

  const getPriceFromDates = () => {
    if (!notSelected) return [0, 0]

    const diffTime = Math.abs(new Date(startDate) - new Date(endDate))
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))

		const totalPrice = property.dailyPrice ? property.dailyPrice * diffDays : 450 * diffDays
		return [diffDays, totalPrice]
	}

  const bookHandler = () => {
    const info = {
      days: getPriceFromDates()[0],
      totalPrice: getPriceFromDates()[1],
      startDate: startDate,
      endDate: endDate,
    }
    localStorage.setItem('house_selection', JSON.stringify(property))
    dispatch(setChosenObject(info))
    history.push(`/booking/${id}`)
  }

  if (property?.images.length > 0) {
    images = property.images.map((image, index) => {
      return (
        <img
          style={{ width: '200px' }}
          key={index}
          src={image.url}
          alt={image.id}
        />
      )
    })
  }

  if (property?.amenities.length) {
    amenities = property.amenities.map((amenity, index) => {
      return <li key={index}>{amenity.amenity}</li>
    })
  }

  if (property?.propertyLogs.length) {
    logs = property.propertyLogs.map((log, index) => {
      return (
        <Card key={index}>
          <h2>Version: {index + 1}</h2>
          <p>Street: {log.addressLog.street}</p>
          <p>City: {log.addressLog.city}</p>
          <p>Zipcode: {log.addressLog.zipcode}</p>
          <ul>
            Amenities:
            {log.amenities.map((a, index) => {
              return <li key={index}>{a.amenity}</li>
            })}
          </ul>

          <p>Bathrooms: {log.bathrooms}</p>
          <p>Beds: {log.beds}</p>
          <p>Price per day: {log.dailyPrice}</p>
          <p>Description: {log.description}</p>
          <p>Max guests: {log.guests}</p>
          <p>Title: {log.title}</p>
          {log.images.map((url, num) => {
            return (
              <div key={num}>
                <img style={{ width: '200px' }} src={url.url} alt={url} />
              </div>
            )
          })}
        </Card>
      )
    })
  }

  return (
    <div className={classes['detailpage-container']}>
      {property && (
        <>
          {images}
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
            <p>Start at: {Date(property.startDate * 1000)}</p>
            <p>Ending at: {Date(property.endDate * 1000)}</p>
            <p>Max guests: {property.guests}</p>
            <p>Amenities: </p>
            <ul className={classes['amenities-container']}>{amenities}</ul>
          </div>
          <Link to={`/profile-page/${property.userId}`}>
            <div className='user'>User: {property.userId}</div>
          </Link>
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
            onClick={bookHandler}
          >
            Book
          </button>
          <div className='review-window' style={{ display: 'flex' }}>
            <MessageWindow reviews={reviews} />
            <ReviewPost userOnline={userOnline} property={property} />
          </div>
          <Card>
            Update property!
            <Card>
              <AddProperty
                property={property}
                setCheckUpdate={setCheckUpdate}
                value={checkUpdate}
              />
            </Card>
          </Card>
          <div style={{}}>{logs}</div>
        </>
      )}
    </div>
  )
}

export default Detailpage
