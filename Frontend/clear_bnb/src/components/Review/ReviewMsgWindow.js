import './ReviewMsgWindow.css'
import { useSelector } from 'react-redux'

export const MessageWindow = () => {
  const userProperties = useSelector((state) => state.userProperties.reviews)

  return (
    <div className='window'>
      <div className='title'>Reviews!</div>
      <MessageList messages={userProperties} />
    </div>
  )
}

const MessageList = ({ messages }) => {
  console.log(messages)
  return (
    <ul className='message-list'>
      {messages.map((message, index) => {
        return (
          <li key={index} className='message'>
            <div>Rating: {message.rating}/5</div>
            <div>{message.comment}</div>
            <div>{message.createdAt}</div>
          </li>
        )
      })}
    </ul>
  )
}
