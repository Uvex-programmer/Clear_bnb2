import "./App.css";
import Navbar from "./components/Navbar/Navbar";
import Login from "./components/Login/Login";
import AddProperty from "./components/RentalObject/AddProperty";
import FrontPage from "./components/Views/Frontpage/Frontpage";
import ProfilePage from "./components/Views/ProfilePage/ProfilePage";
import { getUserProperties, getUserBookings } from "./utils/API";
import { Switch, Route } from "react-router-dom";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { login } from "./slicers/LoginSlicer";

function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    async function whoAmI() {
      let res = await fetch("/api/whoami");
      const user = JSON.parse(await res.json());
      if (user === null) return;
      const userLoggedIn = {
        id: user.id,
        firstName: user.firstName,
        lastName: user.lastName,
        email: user.email,
      };
      console.log("user logged in: ", userLoggedIn);
      dispatch(login(userLoggedIn));
      getUserProperties();
      getUserBookings();
    }
    whoAmI();
  }, [dispatch]);

  return (
    <div id="App">
      <Navbar />
      <div className="app-container">
        <Switch>
          <Route exact path="/" component={FrontPage} />
          <Route path="/login" component={Login} />
          <Route path="/addProperty" component={AddProperty} />
          <Route path="/profile-page" component={ProfilePage} />
        </Switch>
      </div>
    </div>
  );
}

export default App;
