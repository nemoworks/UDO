const schema = {
  type: 'object',
  title: '设备信息',
  properties: {
    Name: {
      type: 'string',
      title: '产品名称',
    },
    Brand: {
      type: 'string',
      title: '品牌',
    },
    Operations: {
      type: 'array',
      title: '操作记录',
      template: {
        type: 'string',
      },
    },
  },
}

const objects = [
  {
    id: 'aaaa',
    schema: '/api/schema/device',
    content: {
      Name: '净化器',
      Brand: '小米',
      Operations: ['write', 'read'],
    },
  },

  {
    id: 'bbbb',
    schema: '/api/schema/device',
    content: {
      Name: '小太阳',
      Brand: '美的',
      Operations: ['switch on', 'switch off'],
    },
  },
]

export default {
  'GET /api/schema/test': (req, res) =>
    res.send({
      type: 'string',
    }),

  'GET /api/schema/device': (req, res) => res.send(schema),

  'GET /api/object/*': ({ originalUrl }, res) => {
    res.send(
      objects.find(o => o.id === originalUrl.replace(/\/api\/object\//, '')),
    )
  },

  'GET /api/object': (req, res) => {
    res.send(objects)
  },
}
