import classes from './ChatConversation.module.css'

export const ChatConversation = ({
  messages,
  user = 'user-color',
  server = 'server-color',
}) => {
  let conversation = messages.map((message, index) => {
    let color = message.is_support ? classes[server] : classes[user]
    return (
      <li key={index} className={classes['message-bubble'] + ' ' + color}>
        <p>{message.msg}</p>
        <p>{message.time_sent}</p>
      </li>
    )
  })
  return (
    <div className={classes['message-container']}>
      <ul>{conversation}</ul>
    </div>
  )
}
