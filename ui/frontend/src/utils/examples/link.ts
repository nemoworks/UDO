const initialFormData = [
  {
    a: 'aaaa',
  },
]

const initialSchema = {
  type: 'array',
  template: {
    type: 'object',
    title: 'Link 类型测试',
    properties: {
      a: {
        type: 'link',
        title: '设备',
        url: '/api/object',
      },
    },
  },
}

export { initialFormData, initialSchema }
