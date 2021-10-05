import classes from './ChatRooms.module.css'

export const ChatRooms = ({ chatRooms, fetchMessages }) => {
  let rooms = chatRooms.map((chatroom_id, index) => {
    return (
      <li
        key={index}
        onClick={() => fetchMessages(chatroom_id)}
        className={classes['user-symbol']}
      >
        <p>{index}</p>
      </li>
    )
  })

  return <ul className={classes['customer-container']}>{rooms} </ul>
}
