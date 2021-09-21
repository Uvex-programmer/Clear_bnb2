import UserInfo from "./components/UserInfo";
import UserHouse from "./components/UserHouses";
import { useSelector } from "react-redux";

export default function ProfilePage() {
  const userProperties = useSelector(
    (state) => state.userProperties.properties
  );
  return (
    <>
      <div className="profile-page-container">
        <UserInfo />
        {userProperties === [] ? "" : <UserHouse />}
      </div>
    </>
  );
}
