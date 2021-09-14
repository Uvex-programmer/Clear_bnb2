import { useState } from "react";
import "./Login.css";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const submitHandler = (e) => {
    const obj = {
      username: username,
      password: password,
    };
    console.log(obj);
    e.preventDefault();
  };
  return (
    <>
      <div className="login-container">
        <div className="login-header">Login</div>
        <div className="login-form">
          <form onSubmit={submitHandler}>
            <label>
              username
              <input
                type="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
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
          <button type="submit" onClick={submitHandler}>
            Login
          </button>
          <button>Register</button>
        </div>
      </div>
    </>
  );
}
