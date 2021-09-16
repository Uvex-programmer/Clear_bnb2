import { configureStore } from "@reduxjs/toolkit";
import loginSlicer from "./slicers/LoginSlicer";
export default configureStore({
  reducer: {
    loginUser: loginSlicer,
  },
});
