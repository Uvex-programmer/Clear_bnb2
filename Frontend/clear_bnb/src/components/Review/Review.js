import "./Review.css";
import { useState } from "react";

const Review = () => {
  const [reviewText, setReviewText] = useState("");
  const [reviewPoint, setPoint] = useState(1);

  const sendReview = (e) => {
    e.preventDefault();
    console.log("press post", reviewText);
  };

  return (
    <div className="review-container">
      <div className="container">
        <div className="post">
          <div className="text">Review the user!</div>
        </div>
        <div className="star-widget">
          <input type="radio" name="rate" id="rate-5" />
          <label htmlFor="rate-5" className="fas fa-star"></label>
          <input type="radio" name="rate" id="rate-4" />
          <label htmlFor="rate-4" className="fas fa-star"></label>
          <input type="radio" name="rate" id="rate-3" />
          <label htmlFor="rate-3" className="fas fa-star"></label>
          <input type="radio" name="rate" id="rate-2" />
          <label htmlFor="rate-2" className="fas fa-star"></label>
          <input type="radio" name="rate" id="rate-1" />
          <label htmlFor="rate-1" className="fas fa-star"></label>
          <form action="#">
            <header></header>
            <div className="textarea">
              <textarea
                cols="30"
                placeholder="Describe your experience.."
                value={reviewText}
                onChange={(e) => setReviewText(e.target.value)}
              ></textarea>
            </div>
            <div className="btn">
              <button type="submit" onClick={sendReview}>
                Post
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Review;
