import { useState } from "react";
import { useDispatch } from "react-redux";
import { useHistory } from "react-router";
import "./Login.css";
import { login } from "../../slicers/LoginSlicer";
import { getUserProperties } from "../../utils/API";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [registerMode, setRegisterMode] = useState(false);
  let dispatch = useDispatch();
  let history = useHistory();

  const submitHandler = async (type = "login-user") => {
    const loginCheck = {
      email: email,
      password: password,
      firstName: firstName,
      lastName: lastName,
    };

    let res = await fetch(`/api/${type}`, {
      method: "POST",
      body: JSON.stringify(loginCheck),
    });
    let user = JSON.parse(await res.json());
    dispatch(login(user));
    getUserProperties();
    console.log("user", user);
    history.push("/");
  };

  const toggleAction = (event, type = "") => {
    event.preventDefault();
    !type
      ? setRegisterMode((prevState) => !prevState)
      : submitHandler(type, event);
  };

  return (
    <>
      <div className="login-container">
        <div className="login-header">Login</div>
        <div className="login-form">
          <form className="form-group" onSubmit={submitHandler}>
            <label>username</label>
            <input
              type="username"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <label>password</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {registerMode && (
              <>
                <label>first name: </label>
                <input
                  type="text"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                />
                <label>last name: </label>
                <input
                  type="text"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                />
              </>
            )}

            <div className="login-buttons">
              <button
                type="submit"
                onClick={(event) =>
                  toggleAction(event, registerMode ? "" : "login-user")
                }
              >
                Login
              </button>
              <button
                type="submit"
                onClick={(event) =>
                  toggleAction(event, registerMode ? "register-user" : "")
                }
              >
                Register
              </button>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}
