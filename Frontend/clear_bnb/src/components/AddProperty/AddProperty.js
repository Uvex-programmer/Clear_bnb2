import './AddProperty.css'
import { useState } from 'react'
import { useSelector } from 'react-redux'
import { useHistory } from 'react-router-dom'
import Amenities from '../Amenities/Amenities'

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

  const userOnline = useSelector((state) => state.loginUser.user)
  let amenitiesAdd = []
  let history = useHistory()

  const submitHandler = async (e) => {
    e.preventDefault()

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
    }) //.then(history.push('/'))
  }

  const pushOrDelete = (type) => {
    if (amenitiesAdd.some((e) => e.amenity === type)) {
      return (amenitiesAdd = amenitiesAdd.filter(
        (item) => item.amenity !== type
      ))
    } else {
      amenitiesAdd.push({ amenity: type })
    }
  }

  return (
    <>
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
            value={address}
            onChange={(e) => setAddress(e.target.value)}
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
          <Amenities pushOrDelete={pushOrDelete} />
          <button onClick={(e) => submitHandler(e)}>Save</button>
        </form>
      </div>
    </>
  )
}
