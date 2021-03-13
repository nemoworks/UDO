const objects = [
  {
    id: 'aaaa',
    schema: '/api/schema/device',
    content: {
      Name: '净化器',
    },
  },

  {
    id: 'bbbb',
    schema: '/api/schema/device',
    content: {
      Name: '净化器',
    },
  },
]

export default {
  'GET /api/schema/test': (req, res) =>
    res.send({
      type: 'string',
    }),

  'GET /api/schema/device': (req, res) =>
    res.send({
      type: 'object',
      title: '设备信息',
      properties: {
        Name: {
          type: 'string',
          title: '产品名称',
        },
      },
    }),

  'GET /api/object/*': ({ originalUrl }, res) => {
    const path = originalUrl.replace(/\/api\/object\//, '')

    res.send(objects.find(o => o.id === path))
  },

  'GET /api/object': (req, res) => {
    res.send(objects)
  },
}
