import { Link } from 'react-router-dom'
import { useSelector, useDispatch } from 'react-redux'
import { logout } from '../../slicers/LoginSlicer'
import './Navbar.css'

export default function Navbar() {
	const userOnline = useSelector((state) => state.loginUser.user)
	const dispatch = useDispatch()

	return (
		<>
			<div className='topNav'>
				<Link to='/'>Home</Link>
				<Link to='/something'>Something</Link>
				<Link to='/search'>Search</Link>
				{userOnline ? <Link to='/add-property'>addProperty</Link> : ''}
				{userOnline ? <Link to='/profile-page'>profilePage</Link> : ''}
				{userOnline ? (
					<button
						className='nav-logout-btn'
						onClick={async () => {
							dispatch(logout())
							await fetch('/api/logout-user')
						}}>
						Logout
					</button>
				) : (
					''
				)}
				{userOnline ? '' : <Link to='/login'>Login</Link>}
			</div>
		</>
	)
}
