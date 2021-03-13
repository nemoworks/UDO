import { wrapAsDependency as $ } from '@perish/react-xform'

const initialFormData = {
  Name: '一个房间',
  Size: {
    length: 10,
    width: 8,
    height: 15,
  },
  DeviceList: ['净化器', 'bilibili', 'alibaba'],
}

const initialSchema = {
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
      title: '房间容积',
      description: '房间面积(平方米)',
      data: $(node => {
        const { length, width } = node.$.Size.properties
        return length.data * width.data
      }),
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
      },
    },
    DeviceList: {
      type: 'array',
      title: '设备列表',
      template: {
        type: 'string',
        title: 'Device',
      },
    },
  },
}

export { initialFormData, initialSchema }
