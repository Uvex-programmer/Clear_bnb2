import { createSlice } from '@reduxjs/toolkit'

export const userSlicer = createSlice({
  name: 'user-info',
  initialState: {
    chosenObject: {
      days: '',
      totalPrice: '',
      startDate: '',
      endDate: '',
    },
  },

  reducers: {
    setChosenObject: (state, action) => {
      state.chosenObject = { ...action.payload }
    },
  },
})

export const { setChosenObject } = userSlicer.actions

export default userSlicer.reducer
