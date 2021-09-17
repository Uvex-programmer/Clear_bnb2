import "./App.css";
import Navbar from "./components/Navbar/Navbar";
import FrontPage from "./components/Views/Frontpage/Frontpage";
import Login from "./components/Login/Login";
import AddProperty from "./components/RentalObject/AddProperty";
import ProfilePage from "./components/Views/ProfilePage/ProfilePage";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { login } from "./slicers/LoginSlicer";

function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    async function whoAmI() {
      let res = await fetch("/api/whoami");
      let userLoggedIn = JSON.parse(await res.json());
      const user = JSON.parse(userLoggedIn);
      dispatch(login(user));
      console.log("user logged in: ", user);
    }
    whoAmI();
  }, [dispatch]);

  return (
    <Router>
      <div id="App">
        <Navbar />
        <div className="app-container">
          <Switch>
            <Route
              exact
              path="/"
              render={() => {
                return <FrontPage />;
              }}
            />
            <Route
              exact
              path="/login"
              render={() => {
                return <Login />;
              }}
            />
            <Route
              exact
              path="/addProperty"
              render={() => {
                return <AddProperty />;
              }}
            />
            <Route
              exact
              path="/profilePage"
              render={() => {
                return <ProfilePage />;
              }}
            />
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
