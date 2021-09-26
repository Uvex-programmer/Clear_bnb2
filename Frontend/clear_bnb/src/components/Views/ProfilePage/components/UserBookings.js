import CardOld from '../../../UI/CardOld/CardOld'
import { useSelector } from 'react-redux'

export default function UserBookings() {
  const state1 = useSelector((state) => state.userProperties.bookings)

  return state1.map((prop, index) => {
    return (
      <div className='property' key={index} style={{ width: '250px' }}>
        <CardOld>
          <div>id: {prop.id}</div>
          <div>title: {prop.propertyId}</div>
          <div>Start Date: {Date(prop.startDate)}</div>
          <div>End Date: {Date(prop.endDate)}</div>
          <div>User: {}</div>
        </CardOld>
      </div>
    )
  })
}
