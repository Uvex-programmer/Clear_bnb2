import classes from './Card.module.css'

const CardOld = (props) => {
	return <div className={classes.card}>{props.children}</div>
}

export default CardOld
