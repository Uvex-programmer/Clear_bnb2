import "./App.css";
import Navbar from "./components/Navbar/Navbar";
import FrontPage from "./components/Frontpage/Frontpage";
import Login from "./components/Login/Login";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

function App() {
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
          </Switch>
          <Switch>
            <Route
              exact
              path="/login"
              render={() => {
                return <Login />;
              }}
            />
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
