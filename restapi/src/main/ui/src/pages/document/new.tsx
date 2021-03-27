import { useEffect, useState } from 'react'
import axios from 'axios'
import { history } from 'umi'
import XForm from '@perish/react-xform'
import { transformer, extractor, composer } from '@/components/XForm'
import { DocumentQuery, SchemaQuery } from '@/utils'

export default function Page({
  location: {
    query: { schemaId },
    search,
  },
}) {
  const [schema, setSchema] = useState(null)
  const [formData, setFormData] = useState(null)

  useEffect(() => {
    SchemaQuery.get(schemaId).then(({ content }) => setSchema(content))
  }, [])

  function createHandler() {
    DocumentQuery.create(search, formData).then(() =>
      history.push('/document' + search),
    )
  }

  return (
    <div className="page document-new container">
      <XForm
        schema={schema}
        transformer={transformer}
        extractor={extractor}
        onChange={setFormData}
      />
      <button onClick={createHandler} className="create">
        确认创建
      </button>
    </div>
  )
}
