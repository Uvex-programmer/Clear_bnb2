import classes from './Chat.module.css'
import { useState, useMemo } from 'react'
import { createAndConnect } from '../../utils/Socket'
import { useSelector } from 'react-redux'
import { ChatConversation } from './ChatConversation'

const Chat = () => {
  const [open, setOpen] = useState(false)
  const [hover, setHover] = useState(false)
  const [cTimeout, setCTimeout] = useState(false)
  const userOnline = useSelector((state) => state.loginUser.user)
  const [conversation, setConversation] = useState([])
  const [userMessage, setUserMessage] = useState('')

  let toggleSize = open
    ? [classes['chat-container'], classes.open].join(' ')
    : classes['chat-container']
  let toggleSymbol = hover
    ? [classes.symbol, classes['symbol-rotate']].join(' ')
    : classes.symbol

  // UI -----------------------------
  const resizeChat = () => {
    setOpen((prevState) => !prevState)
    setHover(false)
    setCTimeout(true)
  }

  const resizeChatWhenHover = () => {
    if (open) return setHover(false)

    if (cTimeout) {
      return setTimeout(() => {
        setCTimeout(false)
      }, 310)
    }
    setHover((prevState) => !prevState)
  }

  if (hover && !open) {
    toggleSize = toggleSize + ' ' + classes.hover
  }

  // -------------------------------

  const userTypeHandler = (e) => {
    e.stopPropagation()
    setUserMessage(e.target.value)
  }

  const fromSocketHandler = (data, type) => {
    if (type === 'onmessage') {
      let parsed = JSON.parse(data)
      setConversation((prevState) => [...prevState, parsed])
    }
  }

  let webSocket = useMemo(() => {
    return createAndConnect(fromSocketHandler)
  }, [])

  const sendToServer = () => {
    const message = {
      msg: userMessage,
      time_sent: new Date().getTime(),
      userId: userOnline?.id ? userOnline.id : window.location.host,
    }
    webSocket.send(JSON.stringify(message), webSocket)
    setUserMessage('')
  }

  return (
    <div
      className={toggleSize}
      onMouseEnter={resizeChatWhenHover}
      onMouseLeave={resizeChatWhenHover}
    >
      <p onClick={resizeChat}>
        Support Chat <span className={toggleSymbol}>{open ? '-' : '+'}</span>
      </p>
      <ChatConversation messages={conversation} />
      <div className={classes['input-container']}>
        <input
          type='text'
          value={userMessage}
          onChange={userTypeHandler}
          placeholder='Write message...'
        />
        <button onClick={sendToServer}>Send</button>
      </div>
    </div>
  )
}

export default Chat
