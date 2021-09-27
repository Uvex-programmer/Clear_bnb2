import { getBookings, getUserReview } from '../slicers/UserInfoSlicer'
import store from '../store'

export const getUserBookings = async () => {
  const state = store.getState()
  const userOnline = state.loginUser.user

  let res = await fetch('/api/getUserBookings/' + userOnline.id)
  const bookings = JSON.parse(await res.json())
  store.dispatch(getBookings(bookings))
}

//Get reviews on another user
export const getReviewsOnUser = async (id) => {
  let res = await fetch('/api/get-reviews-on-user/' + id)
  const reviews = JSON.parse(await res.json())
  store.dispatch(getUserReview(reviews))
  console.log(reviews)
}

export const addUserReview = async () => {
  const state = store.getState()
  const userOnline = state.userProperties.user

  let res = await fetch('/api/getUserBookings/' + userOnline.id)
  const bookings = JSON.parse(await res.json())
  store.dispatch(getBookings(bookings))
}
