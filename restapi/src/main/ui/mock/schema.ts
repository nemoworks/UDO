const schemas = [
  {
    id: 'S000001',
    content: {
      type: 'object',
      title: '空气净化器',
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
    },
  },
  {
    id: 'S000002',
    content: {
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
        air_purifier: {
          type: 'array',
          template: {
            type: 'link',
            title: '空气净化器',
            schemaId: 'S000001',
          },
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
    },
  },
]

export default {
  'GET /mock/schema': (req, res) => res.send(schemas),
  'GET /mock/schema/:id': ({ params: { id } }, res) =>
    res.send(schemas.find(s => s.id === id)),
  'POST /mock/schema': ({ body }, res) => {
    schemas.push({
      id: 'S' + String(Math.floor(Math.random() * 100000)).padStart(6, '0'),
      content: body,
    })
    res.send('success')
  },
}
