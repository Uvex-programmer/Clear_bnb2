import './ReviewPostProperty.css'
import { useState, useEffect } from 'react'
import { addReview } from '../../slicers/PropertyReviewsSlicer'
import { useDispatch } from 'react-redux'
import { useParams } from 'react-router'

const Review = ({ userOnline, property }) => {
  const [reviewText, setReviewText] = useState('')
  const [rating, setRating] = useState(1)
  const [check, setcheck] = useState(false)
  const dispatch = useDispatch()
  const { id } = useParams()

  useEffect(() => {
    if (!userOnline) return
    fetch(`/api/get-property-bookings/${id}/${userOnline.id}`)
      .then((res) => res.json())
      .then((data) => {
        if (data === 'no') {
          setcheck(false)
        } else setcheck(true)
      })
  }, [id, userOnline])

  const sendReview = async (e) => {
    e.preventDefault()

    let reviewObj = {
      rating: rating,
      comment: reviewText,
      reviewId: property.id,
      userId: userOnline.id,
    }
    console.log(reviewObj)

    let res = await fetch('/api/add-property-review', {
      method: 'POST',
      body: JSON.stringify(reviewObj),
    })
    var reviewPost = await res.json()
    dispatch(addReview(reviewPost))
  }

  return (
    <>
      {check ? (
        <div className='review-container'>
          <div className='container'>
            <div className='post'>
              <div className='text'>Review the user!</div>
            </div>
            <div className='star-widget'>
              <input
                value={5}
                onChange={(e) => setRating(parseInt(e.target.value))}
                type='radio'
                name='rate'
                id='rate-5'
              />
              <label htmlFor='rate-5' className='fas fa-star'></label>
              <input
                value={4}
                onChange={(e) => setRating(parseInt(e.target.value))}
                type='radio'
                name='rate'
                id='rate-4'
              />
              <label htmlFor='rate-4' className='fas fa-star'></label>
              <input
                value={3}
                onChange={(e) => setRating(parseInt(e.target.value))}
                type='radio'
                name='rate'
                id='rate-3'
              />
              <label htmlFor='rate-3' className='fas fa-star'></label>
              <input
                value={2}
                onChange={(e) => setRating(parseInt(e.target.value))}
                type='radio'
                name='rate'
                id='rate-2'
              />
              <label htmlFor='rate-2' className='fas fa-star'></label>
              <input
                value={1}
                onChange={(e) => setRating(parseInt(e.target.value))}
                type='radio'
                name='rate'
                id='rate-1'
              />
              <label htmlFor='rate-1' className='fas fa-star'></label>
              <form action='#'>
                <header></header>
                <div className='textarea'>
                  <textarea
                    cols='30'
                    placeholder='Describe your experience..'
                    value={reviewText}
                    onChange={(e) => setReviewText(e.target.value)}
                  ></textarea>
                </div>
                <div className='btn'>
                  <button onClick={sendReview}>Post</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      ) : (
        ''
      )}
    </>
  )
}

export default Review
