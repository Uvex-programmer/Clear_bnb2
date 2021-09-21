import "./App.css";
import Navbar from "./components/Navbar/Navbar";
import Login from "./components/Login/Login";
import AddProperty from "./components/RentalObject/AddProperty";
import FrontPage from "./components/Views/Frontpage/Frontpage";
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
        userId: user.id,
        userFirstName: user.firstName,
        userLastName: user.lastName,
        userEmail: user.email,
      };
      console.log("user logged in: ", userLoggedIn);
      dispatch(login(userLoggedIn));
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
        </Switch>
      </div>
    </div>
  );
}

export default App;
