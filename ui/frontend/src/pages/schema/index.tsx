import { useState } from 'react'
import XForm from '@perish/react-xform'
import { JSONEditor, Card } from '@/components'
import { composer, extractor, transformer } from '@/utils/XForm'
import './index.sass'

export default function Page() {
  const [formData, setFormData] = useState(null)
  const [schema, setSchema] = useState({
    type: 'array',
    template: {
      type: 'string',
      title: 'item',
    },
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
          onChange={console.log}
          formData={['123', '456']}
          transformer={transformer}
          extractor={extractor}
          composer={composer}
        />
      </div>
    </div>
  )
}
