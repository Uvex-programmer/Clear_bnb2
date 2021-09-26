import './ReviewMsgWindow.css'

export const MessageWindow = ({ reviews }) => {
  return (
    <div className='window'>
      <div className='title'>Reviews!</div>
      <MessageList messages={reviews} />
    </div>
  )
}

const MessageList = ({ messages }) => {
  function deleteReview(id) {
    console.log(id)
    fetch(`/api/delete-review/${id}`)
  }

  return (
    <ul className='message-list'>
      {messages.map((message, index) => {
        return (
          <li key={index} className='message'>
            <div>Rating: {message.rating}/5</div>
            <div>{message.comment}</div>
            <div>{message.createdAt}</div>
            <button onClick={() => deleteReview(message.id)}>X</button>
          </li>
        )
      })}
    </ul>
  )
}
