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
  const [imgUrl, setImgUrl] = useState('')
  const [imgUrls, setImgUrls] = useState([])

  const userOnline = useSelector((state) => state.loginUser.user)
  let amenitiesAdd = []
  let urls = []
  let history = useHistory()

  const submitHandler = async (e) => {
    e.preventDefault()

    let propertyObj = {
      description: description,
      title: title,
      beds: beds >= 0 ? beds : 0,
      bathrooms: bathrooms >= 0 ? bathrooms : 0,
      guests: guests >= 0 ? guests : 0,
      startDate: startDate,
      endDate: endDate,
      dailyPrice: price >= 0 ? price : 0,
      address: {
        street: address,
        zipcode: zipcode,
        city: city,
      },
      user: {
        id: userOnline.id,
        firstName: userOnline.firstName,
        lastName: userOnline.lastName,
        email: userOnline.email,
      },
      amenities: amenitiesAdd,
      images: imgUrls,
    }

    console.log(`propertyObj`, propertyObj)

    await fetch('/api/add-property', {
      method: 'POST',
      body: JSON.stringify(propertyObj),
    }).then(
      setTimeout(() => {
        history.push('/')
      }, 1000)
    )
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

  if (imgUrls.length > 0) {
    console.log(imgUrls)
    urls = imgUrls.map((url, index) => {
      return (
        <div key={index} style={{ display: 'flex' }}>
          <img className='image-url' src={url.url} alt={url} />
          <button
            style={{ width: '20px', height: '20px', alignSelf: 'center' }}
            type='button'
            onClick={() => {
              let filter = imgUrls.filter((img, indexx) => indexx !== index)
              setImgUrls(filter)
            }}
          >
            X
          </button>
        </div>
      )
    })
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
            type='number'
            min='0'
            required
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
            type='number'
            required
            value={beds}
            onChange={(e) => setBeds(e.target.value)}
          />
          <label>Number of bathrooms</label>
          <input
            type='number'
            value={bathrooms}
            onChange={(e) => setBathrooms(e.target.value)}
          />
          <label>Max guests</label>
          <input
            type='number'
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
            type='number'
            value={price}
            onChange={(e) => setPrice(e.target.value)}
          />
          <div className='img-urls'>{urls}</div>
          <label>Add image</label>
          <input
            type='text'
            value={imgUrl}
            onChange={(e) => setImgUrl(e.target.value)}
          />
          <button
            type='button'
            onClick={() => {
              setImgUrls([
                ...imgUrls,
                {
                  url: imgUrl,
                  primaryImage: 0,
                },
              ])
              setImgUrl('')
              console.log(urls)
            }}
          >
            add img
          </button>
          <Amenities pushOrDelete={pushOrDelete} />
          <button onClick={(e) => submitHandler(e)}>Save</button>
        </form>
      </div>
    </>
  )
}
