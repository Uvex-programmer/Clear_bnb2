import './ReviewMsgWindow.css'
import { useSelector, useDispatch } from 'react-redux'
import { removeReview } from '../../slicers/PropertyReviewsSlicer'

export const MessageWindow = ({ reviews }) => {
  const userOnline = useSelector((state) => state.loginUser.user)
  return (
    <div className='window'>
      <div className='title'>Reviews!</div>
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
    console.log(id)
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
