import './App.css';
import Navbar from './components/Navbar/Navbar';
import FrontPage from './components/Frontpage/Frontpage';
import Login from './components/Login/Login';
import AddProperty from './components/RentalObject/AddProperty';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';

function App() {
  const userOnline = useSelector((state) => state.loginUser.user);

  useEffect(() => {
    async function whoAmI() {
      let res = await fetch('/api/whoami');
      let userLoggedIn = JSON.parse(await res.json());
      const user = JSON.parse(userLoggedIn);
      console.log('user logged in: ', user);
    }
    whoAmI();
  }, [userOnline]);

  return (
    <Router>
      <div id='App'>
        <Navbar />
        <div className='app-container'>
          <Switch>
            <Route exact path='/' component={FrontPage} />
            <Route path='/login' component={Login} />
            <Route path='/addProperty' component={AddProperty} />
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
