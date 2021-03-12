import { useState } from 'react'
import XForm from '@perish/react-xform'
import { JSONEditor, Card } from '@/components'
import { composer, extractor, transformer } from '@/utils/XForm'
import './index.sass'

export default function Page() {
  const [initialFormData] = useState({
    Name: '一个房间',
    Area: 15,
    Size: {
      length: 10,
      width: 8,
      height: 15,
    },
    DeviceList: ['净化器', '小太阳'],
  })
  const [formData, setFormData] = useState(null)
  const [schema, setSchema] = useState({
    type: 'object',
    title: '房间信息',
    properties: {
      Name: {
        type: 'string',
        title: '房间名',
        description: '房间名',
      },
      Area: {
        type: 'number',
        title: '房间面积',
        description: '房间面积(平方米)',
      },
      Size: {
        type: 'object',
        properties: {
          length: {
            type: 'number',
            title: '长',
            description: '长',
          },
          width: {
            type: 'number',
            title: '宽',
            description: '宽',
          },
          height: {
            type: 'number',
            title: '高',
            description: '高',
          },
        },
      },
      DeviceList: {
        type: 'array',
        template: {
          // type: "link",
          // url: "/schemas",
          // params: {
          //     kind: "device",
          // },
          type: 'string',
          title: 'Device',
        },
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
          formData={initialFormData}
          onChange={setFormData}
          transformer={transformer}
          extractor={extractor}
          composer={composer}
        />
      </div>
    </div>
  )
}
