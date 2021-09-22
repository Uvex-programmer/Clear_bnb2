import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useHistory } from 'react-router';
import { login } from '../../slicers/LoginSlicer';
import './Login.css';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [registerMode, setRegisterMode] = useState(false);
  let dispatch = useDispatch();
  let history = useHistory();

  const submitHandler = async (type = 'login-user', e) => {
    const loginCheck = {
      email: email,
      password: password,
      firstName: firstName,
      lastName: lastName,
    };

    fetch(`/api/${type}`, {
      method: 'POST',
      body: JSON.stringify(loginCheck),
    })
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        dispatch(login(data));
        // history.push('/');
      });
  };

  const toggleAction = (event, type = '') => {
    event.preventDefault();
    console.log(type);
    !type
      ? setRegisterMode((prevState) => !prevState)
      : submitHandler(type, event);
  };

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
            {registerMode && (
              <>
                <label>first name: </label>
                <input
                  type='text'
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                />
                <label>last name: </label>
                <input
                  type='text'
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                />
              </>
            )}

            <div className='login-buttons'>
              <button
                type='submit'
                onClick={(event) =>
                  toggleAction(event, registerMode ? '' : 'login-user')
                }
              >
                Login
              </button>
              <button
                type='submit'
                onClick={(e) => {
                  e.preventDefault();
                  fetch(`/api/logout-user`);
                }}
              >
                Logout
              </button>
              <button
                type='submit'
                onClick={(event) =>
                  toggleAction(event, registerMode ? 'register-user' : '')
                }
              >
                Register
              </button>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}
