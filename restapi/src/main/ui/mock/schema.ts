const schemas = [
  {
    id: 'a',
    content: {
      type: 'object',
      title: '一个对象',
      properties: {
        a: {
          type: 'string',
          title: '一个字符串',
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
      id: String(Math.floor(Math.random() * 100000)).padStart(6, '0'),
      content: body,
    })
    res.send('success')
  },
}
