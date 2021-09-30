import CardOld from '../../../UI/CardOld/CardOld'
import { useSelector } from 'react-redux'

export default function UserBookings() {
  const bookings = useSelector((state) => state.userProperties.bookings)

  return bookings.map((booking, index) => {
    return (
      <div className='property' key={index} style={{ width: '250px' }}>
        <CardOld>
          <div>id: {booking.id}</div>
          <div>title: {booking.propertyId}</div>
          <div>Start Date: {Date(booking.startDate)}</div>
          <div>End Date: {Date(booking.endDate)}</div>
        </CardOld>
      </div>
    )
  })
}
