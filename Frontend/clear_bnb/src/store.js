import { configureStore } from "@reduxjs/toolkit";
import loginSlicer from "./slicers/LoginSlicer";
import propertySlicer from "./slicers/UserHousesSlicer";
export default configureStore({
  reducer: {
    loginUser: loginSlicer,
    userProperties: propertySlicer,
  },
});
