import { useEffect, useState } from 'react'
import { history } from 'umi'
import { documentQuery } from '@/utils'

export default function Page({
  match: {
    params: { schemaName },
  },
}) {
  const [documents, setDocuments] = useState([])

  useEffect(() => {
    documentQuery(`{
        ${schemaName}Documents{
            udoi
        }
    }`)
      // .then(({ data }) => setDocuments(data[schemaName + 'Documents']))
      .then(({ data }) => {
        const res = data[schemaName + 'Documents']
        console.log(res)
        setDocuments(res)
      })
  }, [])

  return (
    <div className="page document-all container">
      <button
        id="create"
        onClick={() => history.push('/document/' + schemaName + '/new')}
      >
        创建 Document
      </button>
      {documents.map((document: any, index) => (
        <pre key={index}>{JSON.stringify(document, null, 2)}</pre>
      ))}
    </div>
  )
}
