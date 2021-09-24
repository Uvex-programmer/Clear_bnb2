import UserInfo from "./components/UserInfo";
import UserHouse from "./components/UserHouses";
import UserBookings from "./components/UserBookings";
import { useSelector } from "react-redux";
import Card from "../../UI/Card";
import CardOld from "../../UI/CardOld/CardOld";

export default function ProfilePage() {
  const userProperties = useSelector(
    (state) => state.userProperties.properties
  );
  console.log(userProperties);
  return (
    <>
      <div className="profile-page-container">
        <UserInfo />
        <CardOld>
          <label
            htmlFor=""
            style={{ fontWeight: "700", fontSize: "25px", marginLeft: "10px" }}
          >
            User rental properties:
          </label>
          <div className="user-container" style={{ display: "flex" }}>
            <UserHouse />
          </div>
        </CardOld>
        <CardOld>
          <label
            style={{ fontWeight: "700", fontSize: "25px", marginLeft: "10px" }}
          >
            User bookings:
          </label>
          <div className="user-bookings" style={{ display: "flex" }}>
            <UserBookings />
          </div>
        </CardOld>
      </div>
    </>
  );
}
