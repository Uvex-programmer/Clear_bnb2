import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'

const Detailpage = () => {
	const { id } = useParams()
	const [property, setProperty] = useState()

	useEffect(() => {
		fetch(`/api/properties/${id}`)
			.then(async (res) => await JSON.parse(await res.json()))
			.then((data) => {
				console.log(data)
				setProperty(data)
			})
	}, [id])

	return <div>{/* <h1>{property.title}</h1> */}</div>
}

export default Detailpage
