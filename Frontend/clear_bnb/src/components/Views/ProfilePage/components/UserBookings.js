import Card from "../../../UI/Card";
import { useSelector } from "react-redux";

export default function UserBookings() {
  const state1 = useSelector((state) => state.userProperties.bookings);
  console.log(state1);
  return state1.map((prop, index) => {
    return (
      <div className="property" key={index} style={{ width: "250px" }}>
        <Card>
          <div>id: {prop.id}</div>
          <div>title: {prop.propertyId}</div>
          <div>Start Date: {Date(prop.startDate)}</div>
          <div>End Date: {Date(prop.endDate)}</div>
          <div>User: {}</div>
        </Card>
      </div>
    );
  });
}
