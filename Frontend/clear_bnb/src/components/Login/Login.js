import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useHistory } from 'react-router';
import { login, logout } from '../../slicers/LoginSlicer';
import './Login.css';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  let dispatch = useDispatch();
  let history = useHistory();

  const submitHandler = async (selection = 'login-user', e) => {
    e.preventDefault();
    const loginCheck = {
      email: email,
      password: password,
    };

    fetch(`/api/${selection}`, {
      method: 'POST',
      body: JSON.stringify(loginCheck),
    })
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        dispatch(login(data));
        history.push('/');
      });
  };

  return (
    <>
      <div className='login-container'>
        <div className='login-header'>Login</div>
        <div className='login-form'>
          <form onSubmit={submitHandler}>
            <label>
              username
              <input
                type='username'
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </label>
            <label>
              password
              <input
                type='password'
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </label>
          </form>
        </div>
        <div className='login-buttons'>
          <button type='submit' onClick={(e) => submitHandler('login-user', e)}>
            Login
          </button>
          <button
            type='submit'
            onClick={(e) => submitHandler('register-user', e)}
          >
            Register
          </button>
          <button
            onClick={async () => {
              await fetch('/api/logoutUser');
              dispatch(logout());
            }}
          >
            logout
          </button>
        </div>
      </div>
    </>
  );
}
