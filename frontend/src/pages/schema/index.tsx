import { useState } from 'react'
import XForm from '@perish/react-xform'
import { extractor, transformer } from '@/utils/XForm'
import { JSONEditor, Card } from '@/components'
import './index.sass'

export default function Page() {
  const [formData, setFormData] = useState(null)
  const [schema, setSchema] = useState({
    type: 'object',
    title: '设备信息',
    properties: {
      DOI: {
        $ref: '/api/schema/test',
        title: 'DOI',
      },
      FirstAuthor: {
        type: 'string',
        title: '第一作者',
        'max-length': 5,
      },
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
          onChange={setFormData}
          transformer={transformer}
          extractor={extractor}
        />
      </div>
    </div>
  )
}
