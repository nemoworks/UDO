import { useState } from 'react'
import XForm from '@perish/react-xform'
import { JSONEditor, Card } from '@/components'
import { extractor, transformer } from '@/utils/XForm'
import './index.sass'

export default function Page() {
  const [formData, setFormData] = useState(null)
  const [schema, setSchema] = useState({
    type: 'array',
    template: {
      type: 'object',
      properties: {
        a: {
          type: 'string',
          title: 'item',
        },
      },
    },
    items: [{}, {}],
  })

  return (
    <div className="page schema-new container">
      <Card title="表单设计" className="editor">
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
