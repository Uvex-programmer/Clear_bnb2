import classes from './ChatConversation.module.css'

export const ChatConversation = ({ messages }) => {
  let conversation = messages.map((message, index) => {
    let color = message.is_support
      ? classes['server-color']
      : classes['user-color']
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
