import { useState } from 'react'
import axios from 'axios'
import { history } from 'umi'
import { message } from 'antd'
import XForm from '@perish/react-xform'
import { transformer, extractor, composer } from '@/components/XForm'
import { Card, JSONEditor, Icon } from '@/components'

export default function Page() {
  const [schema, setSchema] = useState({
    type: 'object',
    title: 'schema identifier',
    properties: {},
  })

  const [formData, setFormData] = useState(null)

  function createHandler() {
    const request = {
      schemaName: schema.title,
      schemaContent: schema,
    }

    axios
      .post('/api/schemas', request, {
        headers: {
          'Content-Type': 'application/json;charset=UTF-8',
        },
      })
      .then(() => {
        message.success('创建成功', 1)
        history.push('/schema')
      })
      .catch(() => {
        message.error('创建失败', 1)
      })
  }

  return (
    <div className="page schema-new container">
      <Card
        className="editor"
        title="表单编辑"
        options={<Icon type="iconcreatetemplate" onClick={createHandler} />}
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
