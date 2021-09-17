import { useSelector } from "react-redux";

export default function ProfilePage() {
  const userOnline = useSelector((state) => state.loginUser.user);

  return (
    <div
      className="user-info-container"
      style={{ border: "1px solid black", width: "300px" }}
    >
      <h2 className="user-info-header">User Info</h2>
      <label htmlFor="">Firstname</label>
      <p className="firstname">{userOnline ? userOnline.firstName : ""}</p>
      <label htmlFor="">Lastname</label>
      <p className="lastname">{userOnline ? userOnline.lastName : ""}</p>
      <label htmlFor="">Email</label>
      <p className="user-email">{userOnline ? userOnline.email : ""}</p>
    </div>
  );
}
