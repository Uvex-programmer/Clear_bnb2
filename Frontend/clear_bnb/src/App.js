import './App.css'
import Navbar from './components/Navbar/Navbar'
import Login from './components/Login/Login'
import AddProperty from './components/RentalObject/AddProperty'
import Frontpage from './components/Views/Frontpage/Frontpage'
import Searchpage from './components/Views/Searchpage/Searchpage'
import ProfilePage from './components/Views/ProfilePage/ProfilePage'
import { Switch, Route } from 'react-router-dom'
import { useDispatch } from 'react-redux'
import { login } from './slicers/LoginSlicer'
import { getUserProperties } from './utils/API'
import { useEffect } from 'react'

function App() {
	const dispatch = useDispatch()

	useEffect(() => {
		fetch('/api/whoami')
			.then((res) => res.json())
			.then((user) => {
				if (!user) return console.log('No user currently logged in.')

				const userLoggedIn = {
					id: user.id,
					firstName: user.firstName,
					lastName: user.lastName,
					email: user.email,
				}
				dispatch(login(userLoggedIn))
				getUserProperties()
				console.log('user logged in: ', user)
			})
	}, [dispatch])

	return (
		<div id='App'>
			<Navbar />
			<div className='app-container'>
				<Switch>
					<Route exact path='/' component={Frontpage} />
					<Route path='/login' component={Login} />
					<Route path='/add-property' component={AddProperty} />
					<Route path='/search' component={Searchpage} />
					<Route path='/profile-page' component={ProfilePage} />
				</Switch>
			</div>
		</div>
	)
}

export default App
