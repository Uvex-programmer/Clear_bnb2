import { useState, useEffect } from 'react'
import { useSelector } from 'react-redux'
import { useDispatch } from 'react-redux'
import classes from './Bookingpage.module.css'

const Bookingpage = () => {
  const [storageHouse, setStorageHouse] = useState()
  const chosenProperty = useSelector((state) => state.userInfo.chosenObject)
  const userOnline = useSelector((state) => state.loginUser.user)
  let dispatch = useDispatch()

  useEffect(() => {
    async function retrieveHouse() {
      const property = await JSON.parse(localStorage.getItem('house_selection'))
      setStorageHouse(property)
    }
    retrieveHouse()
  }, [chosenProperty])

  const submitHandler = () => {
    const payment = {
      propertyId: storageHouse.id,
      startDate: chosenProperty.startDate,
      endDate: chosenProperty.endDate,
      userId: userOnline.id,
      propertyPrice: chosenProperty.totalPrice,
    }
    fetch('/api/purchase-booking', {
      method: 'POST',
      body: JSON.stringify(payment),
    })
      .then(async (res) => await JSON.parse(await res.json()))
      .then((data) => {
        console.log(data)
        dispatch()
      })
  }

  return (
    <div className={classes['booking-container']}>
      <h1>Booking:</h1>
      {storageHouse && chosenProperty && (
        <>
          <div className={classes['info-container']}>
            <h2>Property Info:</h2>
            <p>Title: {storageHouse.title}</p>
            <p>Description: {storageHouse.description}</p>
            <p>Daily Price: {storageHouse.dailyPrice} kr</p>
            <p>Bathrooms: {storageHouse.bathrooms}</p>
            <p>Beds: {storageHouse.beds}</p>
            <p>Guests: {storageHouse.guests}</p>
            <p>
              Address: {storageHouse.address.street},{' '}
              {storageHouse.address.city}, {storageHouse.address.zipcode}
            </p>
            <div className={classes['selection-container']}>
              <h3>Selection info: </h3>
              <p>Days: {chosenProperty.days}</p>
              <p>Total Price: {chosenProperty.totalPrice} kr</p>
              <p>Start date: {chosenProperty.startDate}</p>
              <p>End date: {chosenProperty.endDate}</p>
            </div>
            <div className={classes['payment-container']}>
              <p>Credit Card:</p>
              <div className={classes['credit-control']}>
                <label>Card Number: </label>
                <input
                  type='tel'
                  inputMode='numeric'
                  pattern='[0-9\s]{13,19}'
                  autoComplete='cc-number'
                  maxLength='19'
                  placeholder='xxxx xxxx xxxx xxxx'
                />
              </div>
              <div className={classes['credit-control']}>
                <label>Month / Year: </label>
                <input className={classes['input-small']} type='month' />
                <label className={classes.divider}>/</label>
                <input className={classes['input-small']} type='number' />
              </div>
              <div className={classes['credit-control']}>
                <label>CVC Code: </label>
                <input
                  className={classes['input-small']}
                  type='tel'
                  maxLength='3'
                  placeholder='xxx'
                />
              </div>
            </div>
            <button className={classes['pay-button']} onClick={submitHandler}>
              Purchase
            </button>
          </div>
        </>
      )}
    </div>
  )
}

export default Bookingpage
