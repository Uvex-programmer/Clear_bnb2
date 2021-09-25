import classes from './Card.module.css'

const NewCard = (props) => {
  return <div className={classes.card}>{props.children}</div>
}

export default NewCard
