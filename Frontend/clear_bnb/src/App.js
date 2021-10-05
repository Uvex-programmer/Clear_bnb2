import './App.css'
import Navbar from './components/Navbar/Navbar'
import Login from './components/Login/Login'
import AddProperty from './components/AddProperty/AddProperty'
import FrontPage from './components/Views/Frontpage/Frontpage'
import ProfilePage from './components/Views/Profilepage/ProfilePage'
import Detailpage from './components/Views/Detailpage/Detailpage'
import SearchPage from './components/Views/Searchpage/Searchpage'
import BookingPage from './components/Views/Bookingpage/Bookingpage'
import Chat from './components/Chat/Chat'
import { Switch, Route } from 'react-router-dom'
import { useEffect, useState } from 'react'
import { useDispatch } from 'react-redux'
import { login } from './slicers/LoginSlicer'
import WorkerLoginpage from './components/Views/Workerloginpage/Workerloginpage'
import CustomerChat from './components/Chat/SupportChat'

function App() {
  const dispatch = useDispatch()
  const [done, setDone] = useState(false)

  useEffect(() => {
    fetch('/api/whoami')
      .then(async (res) => res.json())
      .then((user) => {
        if (!user) return console.log('No user currently logged in.')

        const userLoggedIn = {
          id: user.id,
          firstName: user.firstName,
          lastName: user.lastName,
          email: user.email,
        }
        dispatch(login(userLoggedIn))
        console.log('user logged in: ', user)
        setDone(true)
      })
      .catch((error) => {
        console.log('Didnt work: ' + error)
        setDone(true)
      })
  }, [])

  //KOLLA IFALL COOKIESEN ÄR SATT TILL IS_SUPPORT, VILKEN GES IFALL EN SUPPORT/ADMIN LOGGAR IN!
  const checkCookie = () => {
    let name = 'is_support='
    let ca = document.cookie.split(';')
    for (let i = 0; i < ca.length; i++) {
      let c = ca[i]
      while (c.charAt(0) === ' ') {
        c = c.substring(1)
      }
      if (c.indexOf(name) === 0) {
        return c.substring(name.length, c.length)
      }
    }
    return ''
  }

  const isSupport = () => {
    let user = checkCookie()
    if (user !== '') {
      return <CustomerChat />
    } else {
      return <Chat />
    }
  }

  return (
    <div id='App'>
      <Navbar />
      <div className='app-container'>
        {done && isSupport()}
        <Switch>
          <Route exact path='/' component={FrontPage} />
          <Route path='/login' component={Login} />
          <Route path='/add-property' component={AddProperty} />
          <Route path='/search' component={SearchPage} />
          <Route path='/profile-page/:id' component={ProfilePage} />
          <Route path='/detail-page/:id' component={Detailpage} />
          <Route path='/booking/:id' component={BookingPage} />
          <Route path='/support/login' component={WorkerLoginpage} />
        </Switch>
      </div>
    </div>
  )
}

export default App
