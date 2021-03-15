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
  },
}

const objects = [
  {
    id: 'aaaa',
    schema: '/api/schema/device',
    content: {
      Name: '净化器',
      Brand: '小米',
    },
  },

  {
    id: 'bbbb',
    schema: '/api/schema/device',
    content: {
      Name: '小太阳',
      Brand: '美的',
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
