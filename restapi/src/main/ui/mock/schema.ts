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
    schema: '/mock/schema/device',
    content: {
      Name: '净化器',
      Brand: '小米',
      Operations: ['write', 'read'],
    },
  },

  {
    id: 'bbbb',
    schema: '/mock/schema/device',
    content: {
      Name: '小太阳',
      Brand: '美的',
      Operations: ['switch on', 'switch off'],
    },
  },
  {
    id: 'cccc',
    schema: '/mock/schema/device',
    content: {
      Name: 'iPhone 12 Pro Max',
      Brand: 'Apple',
      Operations: [],
    },
  },
  {
    id: 'dddd',
    schema: '/mock/schema/device',
    content: {
      Name: '台灯',
      Brand: '小米',
      Operations: ['turn red', 'turn green'],
    },
  },
]

export default {
  'GET /mock/schema/test': (req, res) =>
    res.send({
      type: 'string',
    }),

  'GET /mock/schema/device': (req, res) => res.send(schema),

  'GET /mock/object/*': ({ originalUrl }, res) => {
    res.send(
      objects.find(o => o.id === originalUrl.replace(/\/mock\/object\//, '')),
    )
  },

  'GET /mock/object': (req, res) => {
    res.send(objects)
  },
}
