import classes from './SupportChat.module.css'
import { useState, useMemo, useEffect } from 'react'
import { createAndConnect } from '../../utils/Socket'
import { useSelector } from 'react-redux'
import { ChatRooms } from './ChatRooms'
import { ChatConversation } from './ChatConversation'

const SupportChat = () => {
  const [open, setOpen] = useState(false)
  const [hover, setHover] = useState(false)
  const [cTimeout, setCTimeout] = useState(false)
  const userOnline = useSelector((state) => state.loginUser.user)
  const [conversation, setConversation] = useState([])
  const [chatRooms, setChatRooms] = useState([])
  const [supportReply, setSupportReply] = useState('')
  const [activeRoom, setActiveRoom] = useState('')
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

  useEffect(() => {
    console.log('SupportChat component booted up..')
    //fetchAllMessages()
  }, [])

  const typeHandler = (e) => {
    e.stopPropagation()
    setSupportReply(e.target.value)
  }

  const fromSocketHandlers = (data, type) => {
    if (type === 'onmessage') {
      let parsed = JSON.parse(data)
      console.log(parsed)
      if (!parsed?.msg) {
        return setChatRooms(parsed)
      }
      if (parsed?.payload) {
        setChatRooms(parsed.payload)
      }
      setConversation((prevState) => [...prevState, parsed])
    }
  }

  let webSocket = useMemo(() => {
    return createAndConnect(fromSocketHandlers)
  }, [])

  const sendToServer = () => {
    if (!supportReply.trim()) return
    const message = {
      msg: supportReply,
      time_sent: new Date().getTime(),
      userId: userOnline?.id ? userOnline.id : window.location.host,
      chatroom_id: conversation[0].chatroom_id,
    }
    webSocket.send(JSON.stringify(message), webSocket)
    setSupportReply('')
  }

  const fetchChatRoomMsg = (chatroom_id, active_index) => {
    setActiveRoom(active_index)
    fetch('/api/support/messages', {
      method: 'POST',
      body: JSON.stringify({ id: chatroom_id }),
    })
      .then(async (res) => await res.json())
      .then((data) => {
        if (data?.payload) {
        }

        setConversation(data)
      })
  }

  return (
    <div
      className={toggleSize}
      onMouseEnter={resizeChatWhenHover}
      onMouseLeave={resizeChatWhenHover}
    >
      {chatRooms?.length > 0 && (
        <ChatRooms
          active={activeRoom}
          chatRooms={chatRooms}
          fetchMessages={fetchChatRoomMsg}
        />
      )}
      <p onClick={resizeChat}>
        Customer Support
        <span className={toggleSymbol}>{open ? '-' : '+'}</span>
      </p>
      <ChatConversation messages={conversation} />
      <div className={classes['input-container']}>
        <input
          type='text'
          value={supportReply}
          onChange={typeHandler}
          placeholder='Write message...'
        />
        <button onClick={sendToServer}>Send</button>
      </div>
    </div>
  )
}

export default SupportChat
