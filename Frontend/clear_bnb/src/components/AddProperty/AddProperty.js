import './AddProperty.css'
import { useState } from 'react'
import { useSelector } from 'react-redux'

export default function AddProperty() {
	const [title, setTitle] = useState('')
	const [description, setDescription] = useState('')
	const [address, setAddress] = useState('')
	const [zipcode, setZipcode] = useState('')
	const [city, setCity] = useState('')
	const [beds, setBeds] = useState('')
	const [bathrooms, setBathrooms] = useState('')
	const [guests, setGuests] = useState('')
	const [startDate, setStartDate] = useState('')
	const [endDate, setEndDate] = useState('')
	const [price, setPrice] = useState('')
	const [wifi, setWifi] = useState(false)
	const [toiletries, setToiletries] = useState(false)
	const [personalCare, setPersonalCare] = useState(false)
	const [coffeeKit, setCoffeeKit] = useState(false)
	const [bathrobes, setBathrobes] = useState(false)
	const [breakfast, setBreakfast] = useState(false)
	const [parking, setParking] = useState(false)
	const [gym, setGym] = useState(false)

	const userOnline = useSelector((state) => state.loginUser.user)
	let amenitiesAdd = []

	const submitHandler = async (e) => {
		e.preventDefault()
		// let propertyObj = {
		// 	description: description,
		// 	title: title,
		// 	beds: beds,
		// 	bathrooms: bathrooms,
		// 	guests: guests,
		// 	startDate: startDate,
		// 	endDate: endDate,
		// 	dailyPrice: price,
		// 	address: {
		// 		street: address,
		// 		zipcode: zipcode,
		// 		city: city,
		// 	},
		// 	user: {
		// 		id: userOnline.id,
		// 		firstName: userOnline.firstName,
		// 		lastName: userOnline.lastName,
		// 		email: userOnline.email,
		// 	},
		// 	amenities: {
		// 		wifi: wifi,
		// 		toiletries: toiletries,
		// 		personalCare: personalCare,
		// 		coffeeKit: coffeeKit,
		// 		bathrobes: bathrobes,
		// 		breakfast: breakfast,
		// 		parking: parking,
		// 		gym: gym,
		// 	},
		// }

		let propertyObj = {
			description: 'Dannes hem',
			title: 'Test',
			beds: '1',
			bathrooms: '2',
			guests: '1',
			startDate: '2021-10-04',
			endDate: '2021-11-01',
			dailyPrice: '113',
			address: {
				street: 'Vikengatan 2',
				zipcode: '22655',
				city: 'Furulund',
			},
			user: {
				id: userOnline.id,
				firstName: userOnline.firstName,
				lastName: userOnline.lastName,
				email: userOnline.email,
			},
			amenities: amenitiesAdd,
		}

		console.log(`propertyObj`, propertyObj)
		await fetch('/api/add-property', {
			method: 'POST',
			body: JSON.stringify(propertyObj),
		})
	}

	const pushOrDelete = (type) => {
		console.log(amenitiesAdd)
		let duplicate
		amenitiesAdd.forEach((item, index) => {
			if (item.amenity === type) return (duplicate = index)
		})
		if (duplicate) {
			amenitiesAdd.splice(duplicate, 1)
			return console.log(amenitiesAdd)
		} else {
			amenitiesAdd.push({ amenity: type })
			console.log(amenitiesAdd)
		}
	}

	return (
		<>
			<div className='add-property-container'>
				<form onSubmit={submitHandler}>
					<label>Title</label>
					<input type='text' value={title} onChange={(e) => setTitle(e.target.value)} />

					<label>Small description</label>
					<input type='text' value={description} onChange={(e) => setDescription(e.target.value)} />

					<label>Address</label>
					<input type='text' value={address} onChange={(e) => setAddress(e.target.value)} />

					<label>Zipcode</label>
					<input type='text' value={zipcode} onChange={(e) => setZipcode(e.target.value)} />

					<label>City</label>
					<input type='text' value={city} onChange={(e) => setCity(e.target.value)} />

					<label>Number of beds</label>
					<input type='text' value={beds} onChange={(e) => setBeds(e.target.value)} />

					<label>Number of bathrooms</label>
					<input type='text' value={bathrooms} onChange={(e) => setBathrooms(e.target.value)} />

					<label>Max guests</label>
					<input type='text' value={guests} onChange={(e) => setGuests(e.target.value)} />

					<label>Start date</label>
					<input
						type='date'
						min={Date.now()}
						max='2023-01-01'
						value={startDate}
						onChange={(e) => setStartDate(e.target.value)}
					/>

					<label>End date</label>
					<input
						type='date'
						min={Date()}
						max='2023-01-01'
						value={endDate}
						onChange={(e) => setEndDate(e.target.value)}
					/>

					<label>Price per night</label>
					<input type='text' value={price} onChange={(e) => setPrice(e.target.value)} />
					<div className='input-controls'>
						<div className='input-control'>
							<label>Wifi</label>
							<input type='checkbox' value={wifi} onChange={() => pushOrDelete('WIFI')} />
						</div>
						<div className='input-control'>
							<label>Toiletries</label>
							<input
								type='checkbox'
								value={toiletries}
								onChange={() => pushOrDelete('TOILETRIES')}
							/>
						</div>
						<div className='input-control'>
							<label>Personal Care</label>
							<input
								type='checkbox'
								value={personalCare}
								onChange={() => pushOrDelete('PERSONAL_CARE')}
							/>
						</div>
						<div className='input-control'>
							<label>Coffee Kit</label>
							<input
								type='checkbox'
								value={coffeeKit}
								onChange={() => pushOrDelete('COFFEE_KIT')}
							/>
						</div>
						<div className='input-control'>
							<label>Bathrobes</label>
							<input type='checkbox' value={bathrobes} onChange={() => pushOrDelete('BATHROBES')} />
						</div>
						<div className='input-control'>
							<label>Breakfast</label>
							<input type='checkbox' value={breakfast} onChange={() => pushOrDelete('BREAKFAST')} />
						</div>
						<div className='input-control'>
							<label>Parking</label>
							<input type='checkbox' value={parking} onChange={() => pushOrDelete('PARKING')} />
						</div>
						<div className='input-control'>
							<label>Gym</label>
							<input type='checkbox' value={gym} onChange={() => pushOrDelete('GYM')} />
						</div>
					</div>
					<button onClick={(e) => submitHandler(e)}>Save</button>
				</form>
			</div>
		</>
	)
}
