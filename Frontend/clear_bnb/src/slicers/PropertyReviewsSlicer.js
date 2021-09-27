import { createSlice } from '@reduxjs/toolkit'

export const reviews = createSlice({
  name: 'reviews',
  initialState: {
    reviews: [],
  },

  reducers: {
    setReviews: (state, action) => {
      state.reviews = action.payload
    },
    addReview: (state, action) => {
      state.reviews = [...state.reviews, action.payload]
    },
    removeReview: (state, action) => {
      state.reviews = state.reviews.filter((rev) => rev[0] !== action.payload)
    },
  },
})

export const { setReviews, addReview, removeReview } = reviews.actions

export default reviews.reducer
