import "./AddProperty.css";

export default function AddProperty() {
  const submitHandler = (e) => {
    console.log("create object");
    e.preventDefault();
  };
  return (
    <>
      <div className="add-property-container">
        <form onSubmit={submitHandler}>
          <label>Title</label>
          <input type="text" />

          <label>Small description</label>
          <input type="text" />

          <label>Address</label>
          <input type="text" />

          <label>Zipcode</label>
          <input type="text" />

          <label>City</label>
          <input type="text" />

          <label>Number of beds</label>
          <input type="text" />

          <label>Number of bathrooms</label>
          <input type="text" />

          <label>Max guests</label>
          <input type="text" />

          <label>Start date</label>
          <input type="date" min={Date.now()} max="2023-01-01" />

          <label>End date</label>
          <input type="date" min={Date()} max="2023-01-01" />

          <label>Price per night</label>
          <input type="text" />
          <button onClick={(e) => submitHandler(e)}>Save</button>
        </form>
      </div>
    </>
  );
}
