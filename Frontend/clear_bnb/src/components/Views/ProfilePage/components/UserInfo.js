import { useSelector } from 'react-redux'
import Card from '../../../UI/Card'

export default function UserInfo() {
  const userOnline = useSelector((state) => state.loginUser.user)
  return <>{userOnline && <Card userOnline={userOnline} />}</>
}
