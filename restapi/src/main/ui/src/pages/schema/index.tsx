import { useEffect, useState } from 'react'
import { history } from 'umi'
import axios from 'axios'
import XForm from '@perish/react-xform'
import { transformer } from '@/components/XForm'
import { Icon } from '@/components'
import { initializeSchema } from '@/utils'

export default function Page() {
  const [schemas, setSchemas] = useState([])

  useEffect(() => {
    axios
      .get('/api/schemas')
      .then(({ data }) =>
        data.length
          ? setSchemas(data)
          : initializeSchema().then(() => location.reload()),
      )
  }, [])

  return (
    <div className="page schema-all container">
      <button id="create" onClick={() => history.push('/schema/new')}>
        新建 Schema
      </button>
      {schemas.map(({ schemaName, schemaContent }: any) => (
        <div key={schemaName} className="block">
          <XForm schema={schemaContent} transformer={transformer} />
          <Icon
            type="iconlist"
            onClick={() => history.push('/document/' + schemaName)}
          />
        </div>
      ))}
    </div>
  )
}
