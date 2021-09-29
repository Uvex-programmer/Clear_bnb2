import './App.css'
import Navbar from './components/Navbar/Navbar'
import Login from './components/Login/Login'
import AddProperty from './components/AddProperty/AddProperty'
import FrontPage from './components/Views/Frontpage/Frontpage'
import ProfilePage from './components/Views/Profilepage/ProfilePage'
import Detailpage from './components/Views/Detailpage/Detailpage'
import SearchPage from './components/Views/Searchpage/Searchpage'
import BookingPage from './components/Views/Bookingpage/Bookingpage'
import { Switch, Route } from 'react-router-dom'
import { useEffect } from 'react'
import { useDispatch } from 'react-redux'
import { login } from './slicers/LoginSlicer'

function App() {
	const dispatch = useDispatch()

	useEffect(() => {
		fetch('/api/whoami')
			.then(async (res) => await res.json())
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
			})
	}, [dispatch])

	return (
		<div id='App'>
			<Navbar />
			<div className='app-container'>
				<Switch>
					<Route exact path='/' component={FrontPage} />
					<Route path='/login' component={Login} />
					<Route path='/add-property' component={AddProperty} />
					<Route path='/search' component={SearchPage} />
					<Route path='/profile-page/:id' component={ProfilePage} />
					<Route path='/detail-page/:id' component={Detailpage} />
					<Route path='/booking/:id' component={BookingPage} />
				</Switch>
			</div>
		</div>
	)
}

export default App
