import { useState } from "react";
import { useDispatch } from "react-redux";
import { login, logout } from "../../slicers/LoginSlicer";
import "./Login.css";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  let dispatch = useDispatch();

  const submitHandler = async (e) => {
    const loginCheck = {
      email: email,
      password: password,
    };

    let res = await fetch("/api/loginUser", {
      method: "POST",
      body: JSON.stringify(loginCheck),
    });

    let userLoggedIn = await res.json();
    const userObject = JSON.parse(userLoggedIn);
    if (userLoggedIn === "wrong login") {
      console.log("Wrong login");
      return;
    }
    dispatch(login(JSON.parse(userObject)));
  };

  return (
    <>
      <div className="login-container">
        <div className="login-header">Login</div>
        <div className="login-form">
          <form>
            <label>
              username
              <input
                type="username"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </label>
            <label>
              password
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </label>
          </form>
        </div>
        <div className="login-buttons">
          <button onClick={submitHandler}>Login</button>
          <button>Register</button>
          <button
            onClick={() => {
              dispatch(logout());
            }}
          >
            logout
          </button>
        </div>
      </div>
    </>
  );
}
