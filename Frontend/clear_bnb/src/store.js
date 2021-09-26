import { configureStore } from '@reduxjs/toolkit'
import loginSlicer from './slicers/LoginSlicer'
import userInfoSlicer from './slicers/UserInfoSlicer'
import searchSlicer from './slicers/SearchSlicer'
import propertyReviews from './slicers/PropertyReviewsSlicer'
import userSlicer from './slicers/UserSlicer'

export default configureStore({
  reducer: {
    loginUser: loginSlicer,
    userProperties: userInfoSlicer,
    searchData: searchSlicer,
    propertyReviews,
    userInfo: userSlicer,
  },
})
