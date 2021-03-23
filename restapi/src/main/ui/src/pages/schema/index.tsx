import { useEffect } from 'react'
import { history } from 'umi'
import axios from 'axios'

export default function Page() {
  useEffect(() => {
    axios.get('/api/schemas').then(({ data }) => {
      console.log(data)
    })
  }, [])

  return (
    <div className="page schema-all container">
      <button onClick={() => history.push('/schema/new')}>新建 schema</button>
    </div>
  )
}
