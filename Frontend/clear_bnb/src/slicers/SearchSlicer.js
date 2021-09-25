import { createSlice } from '@reduxjs/toolkit'

export const SearchSlicer = createSlice({
  name: 'properties',
  initialState: {
    results: [],
  },

  reducers: {
    setSearchResults: (state, action) => {
      state.results = action.payload
    },
    emptySearchResults: (state) => {
      state.results = []
    },
  },
})

export const { setSearchResults, emptySearchResults } = SearchSlicer.actions

export default SearchSlicer.reducer
