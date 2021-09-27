import UserInfo from './components/UserInfo'
import UserHouse from './components/UserHouses'
import UserBookings from './components/UserBookings'
import NewCard from '../../UI/CardOld/DanneRörInteDettaCard'
import { useParams } from 'react-router'
import { MessageWindow } from '../../Review/ReviewMsgWindow'
import { useSelector, useDispatch } from 'react-redux'
import { useEffect, useState } from 'react'
import { getUserReview } from '../../../slicers/UserInfoSlicer'

export default function ProfilePage() {
  const { id } = useParams()
  const userOnline = useSelector((state) => state.loginUser.user)
  const reviews = useSelector((state) => state.userProperties.reviews)
  const [userProfile, setUserProfile] = useState()
  const [userHouses, setUserHouses] = useState()
  const dispatch = useDispatch()

  useEffect(() => {
    fetch(`/api/get-user/${id}`)
      .then(async (res) => JSON.parse(await res.json()))
      .then((data) => {
        setUserProfile(data)
      })
    fetch(`/api/get-user-properties/${id}`)
      .then(async (res) => JSON.parse(await res.json()))
      .then((data) => {
        setUserHouses(data)
        console.log(data)
      })
  }, [id])

  useEffect(() => {
    if (!userOnline) return
    fetch(`/api/get-reviews-on-user/${id}`)
      .then(async (res) => JSON.parse(await res.json()))
      .then((review) => {
        if (review === null) dispatch(getUserReview([]))
        dispatch(getUserReview(review))
        console.log(review)
      })
  }, [userOnline, dispatch])

  return (
    <>
      <div className='profile-page-container'>
        <UserInfo user={userProfile} />
        <NewCard>
          <label
            htmlFor=''
            style={{ fontWeight: '700', fontSize: '25px', marginLeft: '10px' }}
          >
            User rental properties:
          </label>
          <div className='user-container' style={{ display: 'flex' }}>
            {userHouses ? <UserHouse properties={userHouses} /> : ''}
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
        <NewCard>
          <div className='review-container'>
            Reviews on you by others!
            <MessageWindow reviews={reviews} />
          </div>
        </NewCard>
      </div>
    </>
  )
}
