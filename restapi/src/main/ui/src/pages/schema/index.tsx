import { useState } from 'react'
import axios from 'axios'
import { message } from 'antd'
import XForm from '@perish/react-xform'
import { JSONEditor, Card, Icon } from '@/components'
import {
  initialSchema,
  initialFormData,
} from '@/components/XForm/examples/room'
import { composer, extractor, transformer } from '@/components/XForm'
import './index.sass'

export default function Page() {
  // defaultFormData 用于处理 XForm 在 formData 变更机制上的 BUG
  const [defaultFormData] = useState(initialFormData)
  const [formData, setFormData] = useState(null)
  const [schema, setSchema] = useState(initialSchema)
  const [udoi, setUdoi] = useState('')

  function createSchemaHandler() {
    axios
      .post(
        '/api/schemas',
        {
          udoi,
          schemaName: 'room',
          schemaContent: schema,
        },
        {
          headers: {
            'Content-Type': 'application/json',
          },
        },
      )
      .then(() => message.success('创建成功', 1))
  }

  return (
    <div className="page schema-new container">
      <Card
        title={
          <input
            value={udoi}
            onChange={e => setUdoi(e.target.value)}
            placeholder="此处填写 udoi"
          />
        }
        className="editor"
        options={
          <Icon type="iconcreatetemplate" onClick={createSchemaHandler} />
        }
      >
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
