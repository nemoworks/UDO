import { useState } from 'react'
import XForm from '@perish/react-xform'
import { Card, JSONEditor } from '@/components'
import { transformer, extractor, composer } from '@/components/XForm'

export default function Page() {
  const [schema, setSchema] = useState({})
  const [formData, setFormData] = useState(null)

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
          formData={null}
          onChange={setFormData}
          transformer={transformer}
          extractor={extractor}
          composer={composer}
        />
      </div>
    </div>
  )
}
