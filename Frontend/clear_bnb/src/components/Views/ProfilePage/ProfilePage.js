import UserInfo from './components/UserInfo'
import UserHouse from './components/UserHouses'
import UserBookings from './components/UserBookings'
import NewCard from '../../UI/CardOld/DanneRörInteDettaCard'
import { MessageWindow } from '../../Review/ReviewMsgWindow'
import { useSelector, useDispatch } from 'react-redux'
import { useEffect } from 'react'
import { getUserReview } from '../../../slicers/UserInfoSlicer'

export default function ProfilePage() {
  const userOnline = useSelector((state) => state.loginUser.user)
  const reviews = useSelector((state) => state.userProperties.reviews)
  const dispatch = useDispatch()

  useEffect(() => {
    if (!userOnline) return
    fetch(`/api/get-reviews-on-user/${userOnline.id}`)
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
