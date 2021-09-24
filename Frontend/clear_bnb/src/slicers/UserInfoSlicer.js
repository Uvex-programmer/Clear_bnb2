import { createSlice } from "@reduxjs/toolkit";

export const userInfoSlicer = createSlice({
  name: "user-properties",
  initialState: {
    properties: [],
    bookings: [],
    rating: 0,
    reviews: [],
  },

  reducers: {
    getProperties: (state, action) => {
      state.properties = action.payload;
    },
    addProperties: (state, action) => {
      state.properties = [...state, action.payload];
    },
    getBookings: (state, action) => {
      state.bookings = action.payload;
    },
    addBookings: (state, action) => {
      state.bookings = [...state, action.payload];
    },
    getReviewRating: (state, action) => {
      state.rating = action.payload;
    },
    getUserReview: (state, action) => {
      state.reviews = [action.payload];
    },
  },
});

export const {
  getProperties,
  addProperties,
  getBookings,
  addBookings,
  getReviewRating,
  getUserReview,
} = userInfoSlicer.actions;

export default userInfoSlicer.reducer;
