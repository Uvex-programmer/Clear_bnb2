import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import classes from './Detailpage.module.css'
import { MessageWindow } from '../../Review/ReviewMsgWindow'
import ReviewPost from '../../Review/ReviewPost'
import { setReviews } from '../../../slicers/PropertyReviewsSlicer'
import { useSelector, useDispatch } from 'react-redux'

const Detailpage = () => {
  const { id } = useParams()
  const [property, setProperty] = useState()
  const userOnline = useSelector((state) => state.loginUser.user)
  const reviews = useSelector((state) => state.propertyReviews.reviews)
  const dispatch = useDispatch()
  let images = ''

  useEffect(() => {
    fetch(`/api/properties/${id}`)
      .then(async (res) => JSON.parse(await res.json()))
      .then((data) => {
        setProperty(data)
      })
  }, [id])

  useEffect(() => {
    fetch(`/api/get-reviews-on-property/${id}`)
      .then(async (res) => JSON.parse(await res.json()))
      .then((review) => {
        dispatch(setReviews(review))
      })
  }, [id, dispatch])

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
          <div className='review-window' style={{ display: 'flex' }}>
            <MessageWindow reviews={reviews} />
            <ReviewPost userOnline={userOnline} property={property} />
          </div>
        </>
      )}
    </div>
  )
}

export default Detailpage
