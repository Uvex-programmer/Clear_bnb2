import classes from './ChatRooms.module.css'

export const ChatRooms = ({ chatRooms, fetchMessages, active }) => {
  let rooms = chatRooms.map((chatroom_id, index) => {
    let styles =
      active === index
        ? [classes['user-symbol'], classes.active].join(' ')
        : classes['user-symbol']
    return (
      <li
        key={index}
        onClick={() => fetchMessages(chatroom_id, index)}
        className={styles}
      >
        <p>{index + 1}</p>
      </li>
    )
  })

  return <ul className={classes['customer-container']}>{rooms} </ul>
}
