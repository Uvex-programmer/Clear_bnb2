import { configureStore } from '@reduxjs/toolkit'
import loginSlicer from './slicers/LoginSlicer'
import propertySlicer from './slicers/UserHousesSlicer'
import searchSlicer from './slicers/SearchSlicer'
export default configureStore({
	reducer: {
		loginUser: loginSlicer,
		userProperties: propertySlicer,
		searchData: searchSlicer,
	},
})
