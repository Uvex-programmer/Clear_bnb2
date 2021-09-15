import "./AddProperty.css";
import { useState } from "react";

export default function AddProperty() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [address, setAddress] = useState("");
  const [zipcode, setZipcode] = useState("");
  const [city, setCity] = useState("");
  const [beds, setBeds] = useState("");
  const [bathrooms, setBathrooms] = useState("");
  const [guests, setGuests] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [price, setPrice] = useState("");

  const submitHandler = async (e) => {
    e.preventDefault();
    let propertyObj = {
      user_id: 1,
      description: description,
      title: title,
      address_city: city,
      address_street: address,
      address_zipcode: zipcode,
      bed_count: beds,
      bathroom_count: bathrooms,
      guest_max: guests,
      start_date: startDate,
      end_date: endDate,
      daily_price: price,
    };

    console.log(`propertyObj`, propertyObj);
    let res = await fetch("/property", {
      method: "POST",
      body: JSON.stringify(propertyObj),
    });
    console.log(res);
  };
  return (
    <>
      <div className="add-property-container">
        <form onSubmit={submitHandler}>
          <label>Title</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />

          <label>Small description</label>
          <input
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />

          <label>Address</label>
          <input
            type="text"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />

          <label>Zipcode</label>
          <input
            type="text"
            value={zipcode}
            onChange={(e) => setZipcode(e.target.value)}
          />

          <label>City</label>
          <input
            type="text"
            value={city}
            onChange={(e) => setCity(e.target.value)}
          />

          <label>Number of beds</label>
          <input
            type="text"
            value={beds}
            onChange={(e) => setBeds(e.target.value)}
          />

          <label>Number of bathrooms</label>
          <input
            type="text"
            value={bathrooms}
            onChange={(e) => setBathrooms(e.target.value)}
          />

          <label>Max guests</label>
          <input
            type="text"
            value={guests}
            onChange={(e) => setGuests(e.target.value)}
          />

          <label>Start date</label>
          <input
            type="date"
            min={Date.now()}
            max="2023-01-01"
            value={startDate}
            onChange={(e) => setStartDate(e.target.value)}
          />

          <label>End date</label>
          <input
            type="date"
            min={Date()}
            max="2023-01-01"
            value={endDate}
            onChange={(e) => setEndDate(e.target.value)}
          />

          <label>Price per night</label>
          <input
            type="text"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
          />
          <button onClick={(e) => submitHandler(e)}>Save</button>
        </form>
      </div>
    </>
  );
}
