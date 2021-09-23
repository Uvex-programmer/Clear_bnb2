import './App.css'
import Navbar from './components/Navbar/Navbar'
import FrontPage from './components/Frontpage/Frontpage'
import Login from './components/Login/Login'
import AddProperty from './components/RentalObject/AddProperty'
import { Switch, Route } from 'react-router-dom'
import { useEffect } from 'react'
import Searchpage from './components/Searchpage/Searchpage'
import { useDispatch } from 'react-redux'
import { login } from './slicers/LoginSlicer'

function App() {
	const dispatch = useDispatch()

	useEffect(() => {
		fetch('/api/whoami')
			.then((res) => res.json())
			.then((data) => {
				if (!data) return console.log('No user currently logged in.')
				console.log('user logged in: ', data)
				dispatch(login(data))
			})
	}, [])

	return (
		<div id='App'>
			<Navbar />
			<div className='app-container'>
				<Switch>
					<Route exact path='/' component={FrontPage} />
					<Route path='/login' component={Login} />
					<Route path='/add-property' component={AddProperty} />
					<Route path='/search' component={Searchpage} />
				</Switch>
			</div>
		</div>
	)
}

export default App
