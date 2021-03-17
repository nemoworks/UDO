const initialFormData = [['aaaa']]

const initialSchema = {
  type: 'array',
  title: 'Link 类型二维数组',
  template: {
    type: 'array',
    title: 'Link 类型一维数组',
    template: {
      type: 'link',
      title: '设备',
      url: '/api/object',
    },
  },
}

export { initialFormData, initialSchema }
