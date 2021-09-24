import "./App.css";
import Navbar from "./components/Navbar/Navbar";
import Login from "./components/Login/Login";
import AddProperty from "./components/RentalObject/AddProperty";
import FrontPage from "./components/Views/Frontpage/Frontpage";
import ProfilePage from "./components/Views/ProfilePage/ProfilePage";
import SearchPage from "./components/Views/Searchpage/Searchpage";
import {
  getUserProperties,
  getUserBookings,
  getReviewsMadeByUser,
} from "./utils/API";
import { Switch, Route } from "react-router-dom";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { login } from "./slicers/LoginSlicer";

function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    fetch("/api/whoami")
      .then(async (res) => JSON.parse(await res.json()))
      .then((user) => {
        if (!user) return console.log("No user currently logged in.");

        const userLoggedIn = {
          id: user.id,
          firstName: user.firstName,
          lastName: user.lastName,
          email: user.email,
        };

        dispatch(login(userLoggedIn));
        getUserProperties();
        getUserBookings();
        getReviewsMadeByUser();
        console.log("user logged in: ", user);
      });
  }, [dispatch]);

  return (
    <div id="App">
      <Navbar />
      <div className="app-container">
        <Switch>
          <Route exact path="/" component={FrontPage} />
          <Route path="/login" component={Login} />
          <Route path="/add-property" component={AddProperty} />
          <Route path="/search" component={SearchPage} />
          <Route path="/profile-page" component={ProfilePage} />
        </Switch>
      </div>
    </div>
  );
}

export default App;
