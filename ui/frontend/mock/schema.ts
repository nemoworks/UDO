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

  'GET /api/object/': (req, res) =>
    res.send({
      Name: '净化器',
    }),
}
