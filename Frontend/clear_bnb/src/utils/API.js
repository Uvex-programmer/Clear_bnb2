import { getProperties, getBookings } from "../slicers/UserHousesSlicer";
import store from "../store";

export const getUserProperties = async () => {
  const state = store.getState();
  const userOnline = state.loginUser.user;
  if (!userOnline?.id) return;

  let res = await fetch('/api/get-user-properties/' + userOnline.id);
  const properties = JSON.parse(await res.json());
  store.dispatch(getProperties(properties));
};

export const getUserBookings = async () => {
  const state = store.getState();
  const userOnline = state.loginUser.user;

  let res = await fetch("/api/getUserBookings/" + userOnline.id);
  const bookings = JSON.parse(await res.json());
  store.dispatch(getBookings(bookings));
};
