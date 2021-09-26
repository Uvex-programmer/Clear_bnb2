import classes from './Card.module.css'
import { Link } from 'react-router-dom'

const CardOld = (props) => {
  return (
    <Link to={`/detail-page/${props.id}`}>
      <div className={classes.card}>{props.children}</div>
    </Link>
  )
}

export default CardOld
