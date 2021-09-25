import classes from './Card.module.css'

const Card = ({ userOnline }) => {
  return (
    <div className={classes.card}>
      <h2 className='user-info-header'>User Info:</h2>
      <label className={classes['card-label']}>Firstname:</label>
      <p className='firstname'>{userOnline ? userOnline.firstName : ''}</p>
      <label className={classes['card-label']}>Lastname:</label>
      <p className='lastname'>{userOnline ? userOnline.lastName : ''}</p>
      <label className={classes['card-label']}>Email:</label>
      <p className='user-email'>{userOnline ? userOnline.email : ''}</p>
    </div>
  )
}

export default Card
