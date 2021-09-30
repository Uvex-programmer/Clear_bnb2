import { useState } from 'react'
import Card from '../../UI/CardOld/DanneRörInteDettaCard'

export const AddProperty = ({ property }) => {
  const [title, setTitle] = useState(property.title)
  const [description, setDescription] = useState(property.description)
  const [street, setStreet] = useState(property.address.street)
  const [zipcode, setZipcode] = useState(property.address.zipcode)
  const [city, setCity] = useState(property.address.city)
  const [beds, setBeds] = useState(property.beds)
  const [bathrooms, setBathrooms] = useState(property.bathrooms)
  const [guests, setGuests] = useState(property.guests)
  const [startDate, setStartDate] = useState(
    new Date(property.startDate)
      .toLocaleDateString('en-GB')
      .split('/')
      .reverse()
      .join('-')
  )
  const [endDate, setEndDate] = useState(
    new Date(property.endDate)
      .toLocaleDateString('en-GB')
      .split('/')
      .reverse()
      .join('-')
  )
  const [price, setPrice] = useState(property.dailyPrice)

  const submitHandler = async (e) => {
    e.preventDefault()
    let propertyObj = {
      description: description,
      title: title,
      beds: beds,
      bathrooms: bathrooms,
      guests: guests,
      startDate: startDate,
      endDate: endDate,
      dailyPrice: price,
    }
    let addressObj = {
      street: street,
      zipcode: zipcode,
      city: city,
    }
    console.log(`propertyObj`, propertyObj)
    let res = await fetch(`/api/property/update/${property.id}`, {
      method: 'POST',
      body: JSON.stringify(propertyObj),
    })
    let res1 = await res.json()
    console.log(res1)
    // console.log(addressObj)
    await fetch(`/api/update-address/${property.address.id}`, {
      method: 'POST',
      body: JSON.stringify(addressObj),
    })
    //var res = await fetch(`/api/get-properties-log/${121}`)
  }

  return (
    <>
      <Card>
        <div className='add-property-container'>
          <form onSubmit={submitHandler}>
            <label>Title</label>
            <input
              type='text'
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />

            <label>Small description</label>
            <input
              type='text'
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />

            <label>Address</label>
            <input
              type='text'
              value={street}
              onChange={(e) => setStreet(e.target.value)}
            />

            <label>Zipcode</label>
            <input
              type='text'
              value={zipcode}
              onChange={(e) => setZipcode(e.target.value)}
            />

            <label>City</label>
            <input
              type='text'
              value={city}
              onChange={(e) => setCity(e.target.value)}
            />

            <label>Number of beds</label>
            <input
              type='text'
              value={beds}
              onChange={(e) => setBeds(e.target.value)}
            />

            <label>Number of bathrooms</label>
            <input
              type='text'
              value={bathrooms}
              onChange={(e) => setBathrooms(e.target.value)}
            />

            <label>Max guests</label>
            <input
              type='text'
              value={guests}
              onChange={(e) => setGuests(e.target.value)}
            />

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
            <input
              type='text'
              value={price}
              onChange={(e) => setPrice(e.target.value)}
            />
            <button onClick={(e) => submitHandler(e)}>Save</button>
          </form>
        </div>
      </Card>
    </>
  )
}

export default AddProperty
