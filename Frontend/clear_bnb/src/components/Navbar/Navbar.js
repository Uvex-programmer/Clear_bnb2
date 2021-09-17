import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { logout } from "../../slicers/LoginSlicer";
import "./Navbar.css";

export default function Navbar() {
  const userOnline = useSelector((state) => state.loginUser.user);
  const dispatch = useDispatch();

  return (
    <>
      <div className="topNav">
        <Link to="/">Home</Link>
        <Link to="/Something">Something</Link>
        <Link to="/Search">Search</Link>
        <Link to="/addProperty">addProperty</Link>
        {userOnline !== null && <Link to="/profilePage">profilePage</Link>}
        {userOnline !== null && (
          <button
            className="nav-logout-btn"
            onClick={() => {
              dispatch(logout());
            }}
          >
            Logout
          </button>
        )}
        {userOnline === null && <Link to="/Login">Login</Link>}
      </div>
    </>
  );
}
