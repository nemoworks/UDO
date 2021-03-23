import { useEffect, useState } from 'react'
import axios from 'axios'
import { history } from 'umi'
import XForm from '@perish/react-xform'
import { transformer, extractor, composer } from '@/components/XForm'
import { documentQuery, json2query } from '@/utils'

export default function Page({
  match: {
    params: { schemaName },
  },
}) {
  const [schema, setSchema] = useState(null)
  const [formData, setFormData] = useState(null)

  useEffect(() => {
    axios
      .get('/api/schemas/udoi' + schemaName)
      .then(({ data }) => setSchema(data.schemaContent))
  }, [])

  function createHandler() {
    documentQuery(`{
      new${schemaName}(
        schemaId: "${String(Math.floor(Math.random() * 100000)).padStart(
          8,
          '0',
        )}",
        content: ${json2query(formData)}
      ){
        udoi
      }
    }`).then(({ data }) =>
      history.push(
        '/api/documents/' + schemaName + '/' + data['new' + schemaName].udoi,
      ),
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
      <button id="create" onClick={createHandler}>
        确认创建
      </button>
    </div>
  )
}
