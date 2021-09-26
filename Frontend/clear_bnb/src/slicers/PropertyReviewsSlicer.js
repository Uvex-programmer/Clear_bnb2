import { createSlice } from '@reduxjs/toolkit'

export const propertyReviews = createSlice({
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
  },
})

export const { setReviews, addReview } = propertyReviews.actions

export default propertyReviews.reducer
