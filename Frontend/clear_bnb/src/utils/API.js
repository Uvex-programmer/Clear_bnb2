import { getProperties } from "../slicers/UserHousesSlicer";
import store from "../store";

export const getUserProperties = async () => {
  const state = store.getState();
  const userOnline = state.loginUser.user;

  let res = await fetch("/api/userGetProperties/" + userOnline.userId);
  const properties = JSON.parse(await res.json());
  store.dispatch(getProperties(properties));
};
