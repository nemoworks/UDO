import { useEffect, useState } from 'react'
import { Button, message } from 'antd'
import axios from 'axios'
import XForm from '@perish/react-xform'
import { composer, extractor, transformer } from '@/components/XForm'

function toQuery(target) {
  if (typeof target === 'string') return `"${target}"`
  if (typeof target === 'number') return target

  let query = ''
  Object.keys(target).forEach(key => {
    query += key + ':' + toQuery(target[key]) + '\n'
  })
  return '{\n' + query + '}'
}

export default function Page() {
  const [formData, setFormData] = useState(null)
  const [schema, setSchema] = useState(null)

  // useEffect(() => {
  //   axios
  //     .get('/api/schemas/uid777')
  //     .then(({ data: { schemaContent } }) => setSchema(schemaContent))
  // }, [])

  // function createHandler() {
  //   const query = `{
  //     newroom(
  //         schemaId: "${'UID' + Math.floor(Math.random() * 100000)}",
  //         content: ${toQuery(formData)}
  //     ){
  //         Name
  //         Size{
  //             length
  //             width
  //         }
  //     }
  //   }`

  //   axios
  //     .post('/api/documents/query', query, {
  //       headers: {
  //         'Content-type': 'text/plain',
  //       },
  //     })
  //     .then(({ data }) => {
  //       console.log(data)
  //       message.success('创建成功', 1)
  //     })
  // }

  return (
    <div className="page document-new container">
      <XForm
        schema={schema}
        formData={null}
        onChange={setFormData}
        transformer={transformer}
        extractor={extractor}
        composer={composer}
      />
      {/* <Button id="create" onClick={createHandler}>
        创建表单
      </Button> */}
    </div>
  )
}
