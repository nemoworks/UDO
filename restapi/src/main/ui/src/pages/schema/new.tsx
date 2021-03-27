import { useState } from 'react'
import { history } from 'umi'
import XForm from '@perish/react-xform'
import { transformer, extractor, composer } from '@/components/XForm'
import { Card, JSONEditor, Icon } from '@/components'
import { SchemaQuery } from '@/utils'

export default function Page() {
  const [formData, setFormData] = useState(null)
  const [schema, setSchema] = useState({
    type: 'object',
    title: "schema' title",
    properties: {},
  })

  function createHandler() {
    SchemaQuery.create(schema).then(() => history.push('/schema'))
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
