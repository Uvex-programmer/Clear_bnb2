import UserInfo from './components/UserInfo'
import UserHouse from './components/UserHouses'
import UserBookings from './components/UserBookings'
import NewCard from '../../UI/CardOld/DanneRörInteDettaCard'
import { MessageWindow } from '../../Review/ReviewMsgWindow'
import { useSelector } from 'react-redux'
import { useEffect, useState } from 'react'

export default function ProfilePage() {
  const userOnline = useSelector((state) => state.loginUser.user)
  const [reviews, setreviews] = useState([])

  useEffect(() => {
    if (!userOnline) return
    fetch(`/api/get-reviews-on-user/${userOnline.id}`)
      .then(async (res) => JSON.parse(await res.json()))
      .then((review) => {
        setreviews(review)
      })
  }, [userOnline])

  return (
    <>
      <div className='profile-page-container'>
        <UserInfo />
        <NewCard>
          <label
            htmlFor=''
            style={{ fontWeight: '700', fontSize: '25px', marginLeft: '10px' }}
          >
            User rental properties:
          </label>
          <div className='user-container' style={{ display: 'flex' }}>
            <UserHouse />
          </div>
        </NewCard>
        <NewCard>
          <label
            style={{ fontWeight: '700', fontSize: '25px', marginLeft: '10px' }}
          >
            User bookings:
          </label>
          <div className='user-bookings' style={{ display: 'flex' }}>
            <UserBookings />
          </div>
        </NewCard>
        <div className='review-container'>
          {reviews ? <MessageWindow reviews={reviews} /> : ''}
        </div>
      </div>
    </>
  )
}
