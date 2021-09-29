import { useState } from 'react'
import classes from './Amenities.module.css'

const Amenities = () => {
	const [wifi, setWifi] = useState('')
	const [Toiletries, setToiletries] = useState('')
	const [personalCare, setPersonalCare] = useState('')
	const [coffeeKit, setCoffeeKit] = useState('')
	const [bathrobes, setBathrobs] = useState('')
	const [breakfast, setBreakfast] = useState('')
	const [parking, setParking] = useState('')
	const [gym, setGym] = useState('')
	const amenities = [
		,
		'Toiletries',
		'Personal care',
		'Coffee Kit',
		'Bathrobes',
		'Breakfast',
		'Parking',
		'Gym',
	]

	return (
		<div className={classes['input-controls']}>
			<div className={classes['input-control']}>
				<label>'Wifi'</label>
				<input type='checkbox' value={wifi} onChange={setWifi} />
			</div>
			<div className={classes['input-control']}>
				<label>'Wifi'</label>
				<input type='checkbox' value={wifi} onChange={setWifi} />
			</div>
			<div className={classes['input-control']}>
				<label>'Wifi'</label>
				<input type='checkbox' value={wifi} onChange={setWifi} />
			</div>
			<div className={classes['input-control']}>
				<label>'Wifi'</label>
				<input type='checkbox' value={wifi} onChange={setWifi} />
			</div>
			<div className={classes['input-control']}>
				<label>'Wifi'</label>
				<input type='checkbox' value={wifi} onChange={setWifi} />
			</div>
			<div className={classes['input-control']}>
				<label>'Wifi'</label>
				<input type='checkbox' value={wifi} onChange={setWifi} />
			</div>
		</div>
	)
}

export default Amenities
