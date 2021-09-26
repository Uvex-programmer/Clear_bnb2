import './ReviewMsgWindow.css'
import { useSelector } from 'react-redux'

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
  function deleteReview(id) {
    console.log(id)
    fetch(`/api/delete-review/${id}`)
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
            <div>{new Date(message[5] * 1000).toISOString().substr(14, 5)}</div>
            {userOnline.id === message[3] ? (
              <button onClick={() => deleteReview(message.id)}>X</button>
            ) : (
              ''
            )}
          </li>
        )
      })}
    </ul>
  )
}
