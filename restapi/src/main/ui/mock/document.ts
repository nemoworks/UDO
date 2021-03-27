const documents = [
  {
    id: 'D000001',
    schemaId: 'S000001',
    content: {
      Name: 'Yeah',
      Brand: 'Hei',
    },
  },
]

export default {
  'GET /mock/document': ({ query: { schemaId } }, res) => {
    schemaId === undefined
      ? res.send(documents)
      : res.send(documents.filter(d => d.schemaId === schemaId))
  },
  'POST /mock/document': ({ body, query: { schemaId } }, res) => {
    documents.push({
      id: 'D' + String(Math.floor(Math.random() * 100000)).padStart(6, '0'),
      schemaId,
      content: body,
    })
    res.send('success')
  },
}
