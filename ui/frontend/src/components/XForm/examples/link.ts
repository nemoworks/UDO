const initialFormData = [{ a: { aa: 'aaaa' }, b: '一个简单的设备' }]

const initialSchema = {
  type: 'array',
  template: {
    type: 'object',
    title: 'Link 类型测试',
    properties: {
      a: {
        type: 'object',
        properties: {
          aa: {
            type: 'link',
            title: '设备',
            url: '/api/object',
          },
        },
      },
      b: {
        type: 'string',
        title: '设备描述',
      },
    },
  },
}

export { initialFormData, initialSchema }
