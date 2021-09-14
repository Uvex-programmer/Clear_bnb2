import { Link } from "react-router-dom";
import "./Navbar.css";

export default function Navbar() {
  return (
    <>
      <div className="topNav">
        <Link to="/">Home</Link>
        <Link to="/Something">Something</Link>
        <Link to="/Search">Search</Link>
        <Link to="/Login">Login</Link>
      </div>
    </>
  );
}
