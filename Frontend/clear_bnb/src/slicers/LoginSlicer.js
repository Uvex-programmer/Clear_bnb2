import { createSlice } from "@reduxjs/toolkit";

export const loginSlice = createSlice({
  name: "user",
  initialState: {
    user: null,
  },

  reducers: {
    login: (state, user) => {
      state.user = user;
    },
    logout: (state) => {
      state.user = null;
    },
  },
});

export const { login, logout } = loginSlice.actions;

export default loginSlice.reducer;
