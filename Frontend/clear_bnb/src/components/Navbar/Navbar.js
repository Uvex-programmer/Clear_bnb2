import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router";
import { logout } from "../../slicers/LoginSlicer";
import "./Navbar.css";

export default function Navbar() {
  const userOnline = useSelector((state) => state.loginUser.user);
  const dispatch = useDispatch();
  const history = useHistory();

  return (
    <>
      <div className="topNav">
        <Link to="/">Home</Link>
        <Link to="/Something">Something</Link>
        <Link to="/Search">Search</Link>
        {userOnline ? <Link to="/addProperty">addProperty</Link> : ""}
        {userOnline ? <Link to="/profile-page">profilePage</Link> : ""}
        {userOnline ? (
          <button
            className="nav-logout-btn"
            onClick={async () => {
              dispatch(logout());
              await fetch("/api/logoutUser");
              history.push("/");
            }}
          >
            Logout
          </button>
        ) : (
          ""
        )}
        {userOnline ? "" : <Link to="/Login">Login</Link>}
      </div>
    </>
  );
}
