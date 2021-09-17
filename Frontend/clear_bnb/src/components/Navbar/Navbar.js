import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import "./Navbar.css";

export default function Navbar() {
  const userOnline = useSelector((state) => state.loginUser.user);

  return (
    <>
      <div className="topNav">
        <Link to="/">Home</Link>
        <Link to="/Something">Something</Link>
        <Link to="/Search">Search</Link>
        <Link to="/addProperty">addProperty</Link>
        {userOnline !== null && <Link to="/profilePage">profilePage</Link>}
        {userOnline === null && <Link to="/Login">Login</Link>}
      </div>
    </>
  );
}
