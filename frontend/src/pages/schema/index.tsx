import { useState } from 'react'
import XForm from '@xform/react'
import { extractor, transformer } from '@/utils/XForm'
import { JSONEditor, Card } from '@/components'
import './index.sass'

export default function Page() {
  const [formData, setFormData] = useState(null)
  const [schema, setSchema] = useState({
    type: 'object',
    containers: ['Card'],
    title: '设备信息',
    properties: {
      DOI: {
        $ref: '/api/schema/test',
      },
      FirstAuthor: {
        type: 'string',
      },
    },
  })

  return (
    <div className="page schema-new container">
      <Card title="Yeah" className="editor">
        <JSONEditor
          mode="code"
          json={schema}
          onChange={setSchema}
          hideMenu={true}
        />
      </Card>
      <Card title="表单数据预览" className="preview-formdata">
        <JSONEditor mode="view" json={formData} />
      </Card>
      {/* <Card title="表单预览" className="preview-form">
        <XForm
          schema={schema}
          onChange={setFormData}
          transformer={transformer}
          extractor={extractor}
        />
      </Card> */}
      <div className="preview-form">
        <XForm
          schema={schema}
          onChange={setFormData}
          transformer={transformer}
          extractor={extractor}
        />
      </div>
    </div>
  )
}
