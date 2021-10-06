import UserInfo from './components/UserInfo'
import UserHouse from './components/UserHouses'
import NewCard from '../../UI/CardOld/DanneRörInteDettaCard'
import ReviewPostUser from '../../Review/ReviewPostUser'
import { useParams, useHistory } from 'react-router'
import { MessageWindow } from '../../Review/ReviewMsgWindow'
import { useSelector, useDispatch } from 'react-redux'
import { useEffect, useState } from 'react'
import { setReviews } from '../../../slicers/PropertyReviewsSlicer'

export default function ProfilePage() {
  const { id } = useParams()
  const userOnline = useSelector((state) => state.loginUser.user)
  const reviews = useSelector((state) => state.propertyReviews.reviews)
  const [userProfile, setUserProfile] = useState()
  const [userHouses, setUserHouses] = useState()
  const dispatch = useDispatch()
  const history = useHistory()

  useEffect(() => {
    fetch(`/api/get-user/${id}`)
      .then((res) => res.json())
      .then((data) => {
        if (data == null) {
          history.push('/')
          return
        }
        setUserProfile(data)
      })
    fetch(`/api/get-user-properties/${id}`)
      .then((res) => res.json())
      .then((data) => {
        setUserHouses(data)
      })
  }, [id, history])

  useEffect(() => {
    fetch(`/api/get-reviews-on-user/${id}`)
      .then((res) => res.json())
      .then((review) => {
        if (review === null) dispatch(setReviews([]))
        dispatch(setReviews(review))
      })
  }, [id, dispatch])

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
          <div className='review-container'>
            Reviews on you by others!
            <MessageWindow reviews={reviews} />
            <ReviewPostUser userOnline={userOnline} user1={userProfile} />
          </div>
        </NewCard>
      </div>
    </>
  )
}
