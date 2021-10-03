const Amenities = ({ pushOrDelete }) => {
  return (
    <div className='input-controls'>
      <div className='input-control'>
        <label>Wifi</label>
        <input type='checkbox' onClick={() => pushOrDelete('wifi')} />
      </div>
      <div className='input-control'>
        <label>Dishwasher</label>
        <input type='checkbox' onChange={() => pushOrDelete('dishwasher')} />
      </div>
      <div className='input-control'>
        <label>AC</label>
        <input type='checkbox' onChange={() => pushOrDelete('ac')} />
      </div>
      <div className='input-control'>
        <label>Fridge</label>
        <input type='checkbox' onChange={() => pushOrDelete('fridge')} />
      </div>
      <div className='input-control'>
        <label>Iron</label>
        <input type='checkbox' onChange={() => pushOrDelete('iron')} />
      </div>
      <div className='input-control'>
        <label>Microwave</label>
        <input type='checkbox' onChange={() => pushOrDelete('microwave')} />
      </div>
    </div>
  )
}

export default Amenities
