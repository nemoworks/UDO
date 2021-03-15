import { useState } from 'react'
import XForm from '@perish/react-xform'
import { JSONEditor, Card } from '@/components'
import {
  initialSchema,
  initialFormData,
} from '@/components/XForm/examples/link'
import { composer, extractor, transformer } from '@/components/XForm'
import './index.sass'

export default function Page() {
  // defaultFormData 用于处理 XForm 在 formData 变更机制上的 BUG
  const [defaultFormData] = useState(initialFormData)
  const [formData, setFormData] = useState(null)
  const [schema, setSchema] = useState(initialSchema)

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
          formData={defaultFormData}
          onChange={setFormData}
          transformer={transformer}
          extractor={extractor}
          composer={composer}
        />
      </div>
    </div>
  )
}
