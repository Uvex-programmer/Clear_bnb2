import { useState } from 'react'
import { useDispatch } from 'react-redux'
import { login } from '../../../slicers/LoginSlicer'
import './Workerloginpage.css'
import { useHistory } from 'react-router-dom'

export default function WorkerLoginpage() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  let dispatch = useDispatch()
  let history = useHistory()

  const submitHandler = (e) => {
    e.preventDefault()
    const loginCheck = {
      email: email,
      password: password,
    }

    fetch(`/api/support/login`, {
      method: 'POST',
      body: JSON.stringify(loginCheck),
    })
      .then((res) => res.json())
      .then((data) => {
        console.log(data)
        dispatch(login(data))
        history.go('/')
      })
  }

  return (
    <>
      <div className='login-container'>
        <div className='login-header'>Login</div>
        <div className='login-form'>
          <form className='form-group' onSubmit={submitHandler}>
            <label>username</label>
            <input
              type='username'
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <label>password</label>
            <input
              type='password'
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <div className='login-buttons'>
              <button type='submit' onClick={(event) => submitHandler(event)}>
                Login
              </button>
              <button
                type='submit'
                onClick={(e) => {
                  e.preventDefault()
                  fetch(`/api/logout-user`, { credentials: 'include' })
                }}
              >
                Logout
              </button>
            </div>
          </form>
        </div>
      </div>
    </>
  )
}
