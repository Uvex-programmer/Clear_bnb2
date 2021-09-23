import { createSlice } from '@reduxjs/toolkit'

export const propertySlicer = createSlice({
	name: 'user-properties',
	initialState: {
		properties: [],
	},

	reducers: {
		getProperties: (state, action) => {
			state.properties = action.payload
		},
		addProperties: (state, action) => {
			state.properties = [...state, action.payload]
		},
	},
})

export const { getProperties, addProperties } = propertySlicer.actions

export default propertySlicer.reducer
