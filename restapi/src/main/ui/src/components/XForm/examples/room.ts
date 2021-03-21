const initialFormData = {
  Name: '一个房间',
  Area: 600,
  Size: {
    length: 20,
    width: 30,
    height: 5,
  },
}

const initialSchema = {
  type: 'object',
  title: 'room',
  properties: {
    Name: {
      type: 'string',
      title: '房间名',
    },
    Area: {
      type: 'number',
      title: '房间面积(平方米)',
    },
    Size: {
      type: 'object',
      title: 'Size',
      properties: {
        length: {
          type: 'number',
          title: '长',
        },
        width: {
          type: 'number',
          title: '宽',
        },
        height: {
          type: 'number',
          title: '高',
        },
      },
    },
  },
}

export { initialFormData, initialSchema }
