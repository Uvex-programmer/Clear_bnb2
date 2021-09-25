import UserInfo from './components/UserInfo'
import UserHouse from './components/UserHouses'
import UserBookings from './components/UserBookings'
import NewCard from '../../UI/CardOld/DanneRörInteDettaCard'
import ReviewPost from '../../Review/ReviewPost'
import { MessageWindow } from '../../Review/ReviewMsgWindow'
export default function ProfilePage() {
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
          <ReviewPost />
          <MessageWindow />
        </div>
      </div>
    </>
  )
}
