import { useEffect, useState } from 'react'
import CardOld from '../../UI/CardOld/CardOld'

function FrontPage() {
	const [properties, setProperties] = useState()
	let cards

	useEffect(() => {
		fetch('/api/properties')
			.then(async (res) => await JSON.parse(await res.json()))
			.then((data) => {
				console.log(data)
				setProperties(data)
			})
	}, [])

	if (properties) {
		cards = properties.map((property) => {
			const style = {
				display: 'flex',
				width: '50%',
			}

			return (
				<CardOld>
					<p>{property.title}</p>
					<p>Price: {property.dailyPrice} kr</p>
				</CardOld>
			)
		})
	}

	return (
		<>
			<div className='frontpage'>
				<p>HOMEPAGE</p>
				{cards}
			</div>
		</>
	)
}

export default FrontPage
