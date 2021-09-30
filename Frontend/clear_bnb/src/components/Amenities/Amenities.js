const Amenities = ({ pushOrDelete }) => {
  return (
    <div className='input-controls'>
      <div className='input-control'>
        <label>Wifi</label>
        <input type='checkbox' onClick={() => pushOrDelete('WIFI')} />
      </div>
      <div className='input-control'>
        <label>Dishwasher</label>
        <input type='checkbox' onChange={() => pushOrDelete('DISHWASHER')} />
      </div>
      <div className='input-control'>
        <label>AC</label>
        <input type='checkbox' onChange={() => pushOrDelete('AC')} />
      </div>
      <div className='input-control'>
        <label>Fridge</label>
        <input type='checkbox' onChange={() => pushOrDelete('FRIDGE')} />
      </div>
      <div className='input-control'>
        <label>Iron</label>
        <input type='checkbox' onChange={() => pushOrDelete('IRON')} />
      </div>
      <div className='input-control'>
        <label>Microwave</label>
        <input type='checkbox' onChange={() => pushOrDelete('MICROWAVE')} />
      </div>
    </div>
  )
}

export default Amenities
