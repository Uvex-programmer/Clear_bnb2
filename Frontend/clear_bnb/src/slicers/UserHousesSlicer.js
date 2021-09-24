import { createSlice } from "@reduxjs/toolkit";

export const propertySlicer = createSlice({
  name: "user-properties",
  initialState: {
    properties: [],
    bookings: [],
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
  },
});

export const { getProperties, addProperties, getBookings, addBookings } =
  propertySlicer.actions;

export default propertySlicer.reducer;
