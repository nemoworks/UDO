import { useEffect, useState } from 'react'
import { history } from 'umi'
import XForm from '@perish/react-xform'
import { transformer } from '@/components/XForm'
import { Icon } from '@/components'
import { SchemaQuery } from '@/utils'

export default function Page() {
  const [schemas, setSchemas] = useState([])

  useEffect(() => {
    SchemaQuery.getAll().then(setSchemas)
  }, [])

  return (
    <div className="page schema-all container">
      <div className="FlexCard create">
        <Icon type="iconcreate" onClick={() => history.push('/schema/new')} />
      </div>
      {schemas.map((schema: any) => (
        <div className="FlexCard" key={schema.id}>
          <div className="options">
            <Icon
              type="iconcreatetemplate"
              onClick={() =>
                history.push('/document/new?schemaId=' + schema.id)
              }
            />
          </div>
          <XForm schema={schema.content} transformer={transformer} />
        </div>
      ))}
    </div>
  )
}
