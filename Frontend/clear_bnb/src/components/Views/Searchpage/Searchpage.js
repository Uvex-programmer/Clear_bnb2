import { useState } from 'react'
import styles from './Searchpage.module.css'

const Searchpage = () => {
	const [search, setSearch] = useState('')
	const [beds, setBeds] = useState(1)
	const [bathrooms, setBathrooms] = useState(1)
	const [maxPrice, setMaxPrice] = useState(1)
	const [startDate, setStartDate] = useState('YYYY-MM-DD')
	const [endDate, setEndDate] = useState('YYYY-MM-DD')
	const [minGuests, setMinGuests] = useState(1)

	const searchHandler = () => {
		const searchInfo = {
			search,
			beds,
			bathrooms,
			maxPrice,
			startDate,
			endDate,
			minGuests,
		}

		fetch('/api/search', {
			method: 'POST',
			body: JSON.stringify(searchInfo),
		})
			.then((res) => res.json())
			.then((data) => {
				console.log(data)
			})
			.catch((error) => console.log(error))
	}

	return (
		<div className={styles['searchpage_container']}>
			<div className={styles['search-controller']}>
				<input type='search' value={search} onChange={(e) => setSearch(e.target.value)} />
				<button onClick={searchHandler}>Search</button>
			</div>
			<div className={styles['amenity-controls']}>
				<div className={styles['amenity-controller']}>
					<label>Beds: </label>
					<input
						type='range'
						max='10'
						min='1'
						name='beds'
						value={beds}
						onChange={(e) => setBeds(e.target.value)}
					/>
					<p>{beds}</p>
				</div>
				<div className={styles['amenity-controller']}>
					<label>Bathrooms: </label>
					<input
						type='range'
						name='bathrooms'
						value={bathrooms}
						max='10'
						min='1'
						onChange={(e) => setBathrooms(e.target.value)}
					/>
					<p>{bathrooms}</p>
				</div>
				<div className={styles['amenity-controller']}>
					<label>Max Price: </label>
					<input
						type='range'
						min='100'
						max='50000'
						name='maxprice'
						value={maxPrice}
						onChange={(e) => setMaxPrice(e.target.value)}
					/>
					<p>{maxPrice}</p>
				</div>
				<div className={styles['amenity-controller']}>
					<label>Start Date: </label>
					<input
						type='date'
						name='startdate'
						value={startDate}
						onChange={(e) => setStartDate(e.target.value)}
					/>
					<p>{startDate}</p>
				</div>
				<div className={styles['amenity-controller']}>
					<label>End Date: </label>
					<input
						type='date'
						name='enddate'
						value={endDate}
						onChange={(e) => setEndDate(e.target.value)}
					/>
					<p>{endDate}</p>
				</div>
				<div className={styles['amenity-controller']}>
					<label>Minimum Guests: </label>
					<input
						type='range'
						min='1'
						max='10'
						name='minguests'
						value={minGuests}
						onChange={(e) => setMinGuests(e.target.value)}
					/>
					<p>{minGuests}</p>
				</div>
			</div>
		</div>
	)
}

export default Searchpage
