export const getSocket = (function () {
  let instance

  function createInstance() {
    const webSocket = new WebSocket('ws://localhost:4000/websockets/chat')
    return webSocket
  }

  return {
    getInstance: function () {
      if (!instance) {
        instance = createInstance()
      }
      return instance
    },
  }
})()

export function addSocketEventListeners(socket, callback) {
  socket.onclose = (event) => {
    console.warn('Disconnected:', event)
  }

  socket.onerror = (event) => {
    console.error('Error:', event)
  }

  socket.onmessage = (event) => {
    callback(event.data, 'onmessage')
  }

  socket.onopen = (event) => {
    console.warn('Connected:', event)
    callback(event.data, 'onopen')
  }
}

export const SendMessageToServer = (msg, socket) => {
  console.log('Sending:', msg)
  socket.send(JSON.stringify(msg))
}

export const createAndConnect = (callback) => {
  const socket = getSocket.getInstance()
  addSocketEventListeners(socket, callback)
  return socket
}

export const disconnectFromServer = (socket) => {
  console.log('Disconnecting...')
  socket.close()
}
