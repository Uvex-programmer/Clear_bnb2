import Card from '../../../UI/Card'

const UserInfo = ({ user }) => {
  return <>{user && <Card userOnline={user} />}</>
}

export default UserInfo
