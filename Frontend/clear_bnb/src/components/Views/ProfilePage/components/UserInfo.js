import { useSelector } from "react-redux";
import Card from "../../../UI/Card";

export default function ProfilePage() {
  const userOnline = useSelector((state) => state.loginUser.user);

  const style = {
    color: "black",
    fontSize: "20px",
    fontWeight: "700",
  };

  return (
    <Card>
      <h2 className="user-info-header">User Info:</h2>
      <label style={style}>Firstname:</label>
      <p className="firstname">{userOnline ? userOnline.userFirstName : ""}</p>
      <label style={style}>Lastname:</label>
      <p className="lastname">{userOnline ? userOnline.userLastName : ""}</p>
      <label style={style}>Email:</label>
      <p className="user-email">{userOnline ? userOnline.userEmail : ""}</p>
    </Card>
  );
}
