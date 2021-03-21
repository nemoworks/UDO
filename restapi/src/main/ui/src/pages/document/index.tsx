import { useEffect, useState } from 'react'
import axios from 'axios'
import { Button } from 'antd'
import XForm from '@perish/react-xform'
import { composer, extractor, transformer } from '@/components/XForm'
import './index.sass'

export default function Page() {
  const [formData, setFormData] = useState(null)
  const [schema, setSchema] = useState(null)

  useEffect(() => {
    axios
      .get('/api/schemas/udoi888')
      .then(({ data: { schemaContent } }) => setSchema(schemaContent))
  }, [])

  function createHandler() {
    axios
      .post(
        '/api/documents/query',
        `{
            newroom(
                schemaId : "1222",
                content:{
                    Name : "409"
                    Area : 100
                    Size : {
                        length : 10
                        width : 5
                        height : 3
                    }
                }
            ){
                Name
                Size{
                    length
                    width
                }
            }
        }`,
        {
          headers: {
            'Content-type': 'text/plain',
          },
        },
      )
      .then(console.log)
  }

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
      <Button id="create" onClick={createHandler}>
        创建表单
      </Button>
    </div>
  )
}
