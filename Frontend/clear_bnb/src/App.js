import "./App.css";
import Navbar from "./components/Navbar/Navbar";
import FrontPage from "./components/Frontpage/Frontpage";
import Login from "./components/Login/Login";
import AddProperty from "./components/RentalObject/AddProperty";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { useEffect } from "react";

function App() {
  useEffect(() => {
    async function whoAmI() {
      let res = await fetch("/api/whoami");
      let user = await res.json();
      console.log("user logged in: " + user);
    }
    whoAmI();
  }, []);

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
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
