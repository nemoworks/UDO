import { useEffect, useState } from 'react'
import { Collapse } from 'antd'
import XForm from '@perish/react-xform'
import { transformer, composer } from '@/components/XForm'
import { DocumentQuery, SchemaQuery } from '@/utils'

const { Panel } = Collapse

function Preview({ document: { schemaId, content: formData } }) {
  const [x, setX] = useState(null as any)

  useEffect(() => {
    SchemaQuery.get(schemaId).then(({ content: schema }) =>
      transformer(schema, 0, true)
        .then(result => composer(result, formData))
        .then(setX),
    )
  }, [])

  return x && <XForm schema={x} />
}

export default function Page({ location: { search } }) {
  const [documents, setDocuments] = useState([])

  useEffect(() => {
    DocumentQuery.getAll(search).then(setDocuments)
  }, [])

  return (
    <div className="page document-all container">
      <Collapse>
        {documents.map((document: any) => (
          <Panel key={document.id} header={document.id}>
            <Preview document={document} />
          </Panel>
        ))}
      </Collapse>
    </div>
  )
}
