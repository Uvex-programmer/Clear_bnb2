import './ReviewMsgWindow.css'
import { useSelector, useDispatch } from 'react-redux'
import { removeReview } from '../../slicers/PropertyReviewsSlicer'
import { useState, useEffect } from 'react'

export const MessageWindow = ({ reviews }) => {
  const userOnline = useSelector((state) => state.loginUser.user)
  const [rating, setrating] = useState(0)

  useEffect(() => {
    let num = 0
    let count = 0
    if (reviews.length < 1) setrating(0)
    reviews.forEach((e) => {
      num = num + e[2]
      count++
    })
    if (num < 1) return
    setrating((num / count).toFixed(1))
  }, [reviews])

  return (
    <div className='window'>
      <div className='title'>Reviews! {rating}/5</div>
      {userOnline ? (
        <MessageList messages={reviews} userOnline={userOnline} />
      ) : (
        ''
      )}
    </div>
  )
}

const MessageList = ({ messages, userOnline }) => {
  const dispatch = useDispatch()
  function deleteReview(id) {
    fetch(`/api/delete-review/${id}`)
    dispatch(removeReview(id))
  }

  return (
    <ul className='message-list'>
      {messages.map((message, index) => {
        return (
          <li key={index} className='message'>
            <div>
              Rating: {message[2]}/5 - {message[4]}
            </div>
            <div>{message[1]}</div>
            {userOnline.id === message[3] ? (
              <button onClick={() => deleteReview(message[0])}>X</button>
            ) : (
              ''
            )}
          </li>
        )
      })}
    </ul>
  )
}
