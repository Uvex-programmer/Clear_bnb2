import Card from "../../../UI/Card";
import { useSelector } from "react-redux";

export default function UserProperties() {
  const state1 = useSelector((state) => state.userProperties.properties);
  return state1.map((prop, index) => {
    return (
      <div className="property" key={index} style={{ width: "350px" }}>
        <Card>
          <div>id: {prop.id}</div>
          <div>title: {prop.title}</div>
          <div>Description: {prop.description}</div>
          <div>beds: {prop.beds}</div>
          <div>bathrooms{prop.bathrooms}</div>
          <div>city: {prop.address.city}</div>
          <div>street: {prop.address.street}</div>
          <div>Zipcode: {prop.address.zipcode}</div>
        </Card>
      </div>
    );
  });
}
