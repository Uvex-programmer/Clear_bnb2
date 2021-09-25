import {
  getProperties,
  getBookings,
  getUserReview,
} from '../slicers/UserInfoSlicer'
import store from '../store'

export const getUserProperties = async () => {
  const state = store.getState()
  const userOnline = state.loginUser.user
  if (!userOnline?.id) return

  let res = await fetch('/api/get-user-properties/' + userOnline.id)
  const properties = JSON.parse(await res.json())
  store.dispatch(getProperties(properties))
}

export const getUserBookings = async () => {
  const state = store.getState()
  const userOnline = state.loginUser.user

  let res = await fetch('/api/getUserBookings/' + userOnline.id)
  const bookings = JSON.parse(await res.json())
  store.dispatch(getBookings(bookings))
}

//Get reviews made by the user who is logged in
export const getReviewsMadeByUser = async () => {
  const state = store.getState()
  const userOnline = state.loginUser.user

  let res = await fetch('/api/get-reviews-made-by-user/' + userOnline.id)
  const reviews = JSON.parse(await res.json())
  console.log('reviews', reviews)
  store.dispatch(getUserReview(reviews))
}

//Get reviews on another user
export const getReviewsOnUser = async (id) => {
  let res = await fetch('/api/get-reviews-on-user/' + id)
  const reviews = JSON.parse(await res.json())
  // store.dispatch(getReviewOnUser(reviews));
}

export const addUserReview = async () => {
  const state = store.getState()
  const userOnline = state.userProperties.user

  let res = await fetch('/api/getUserBookings/' + userOnline.id)
  const bookings = JSON.parse(await res.json())
  store.dispatch(getBookings(bookings))
}
