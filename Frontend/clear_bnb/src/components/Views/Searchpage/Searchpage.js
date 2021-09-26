import { useState } from 'react'
import styles from './Searchpage.module.css'
import { setSearchResults } from '../../../slicers/SearchSlicer'
import { useDispatch, useSelector } from 'react-redux'
import SearchResults from '../../SearchResults/SearchResults'

const Searchpage = () => {
  const [city, setSearch] = useState('')
  const [beds, setBeds] = useState(1)
  const [baths, setBathrooms] = useState(1)
  const [dailyPrice, setMaxPrice] = useState(600)
  const [startDate, setStartDate] = useState(
    new Date().toISOString().split('T')[0]
  )
  const [endDate, setEndDate] = useState(new Date().toISOString().split('T')[0])
  const [guests, setMinGuests] = useState(1)
  const dispatch = useDispatch()
  const searchResults = useSelector((state) => state.searchData.results)

  const searchHandler = () => {
    const searchInfo = {
      city,
      beds,
      baths,
      dailyPrice,
      startDate,
      endDate,
      guests,
    }
    console.log(searchInfo)

    fetch('/api/search', {
      method: 'POST',
      body: JSON.stringify(searchInfo),
    })
      .then((res) => res.json())
      .then((data) => {
        if (!data) return
        console.log(JSON.parse(data))
        dispatch(setSearchResults(JSON.parse(data)))
      })
      .catch((error) => console.log(error))
  }

  return (
    <div className={styles['searchpage_container']}>
      <div className={styles['search-controller']}>
        <label>Free search</label>
        <input
          type='search'
          value={city}
          onChange={(e) => setSearch(e.target.value)}
        />
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
            value={baths}
            max='10'
            min='1'
            onChange={(e) => setBathrooms(e.target.value)}
          />
          <p>{baths}</p>
        </div>
        <div className={styles['amenity-controller']}>
          <label>Max Price: </label>
          <input
            type='range'
            min='100'
            max='5000'
            name='maxprice'
            value={dailyPrice}
            onChange={(e) => setMaxPrice(e.target.value)}
          />
          <p>{dailyPrice}</p>
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
            value={guests}
            onChange={(e) => setMinGuests(e.target.value)}
          />
          <p>{guests}</p>
        </div>
        <button onClick={searchHandler}>Search</button>
      </div>
      {searchResults && <SearchResults results={searchResults} />}
    </div>
  )
}

export default Searchpage
