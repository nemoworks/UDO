const documents = [
  {
    id: 'aaa',
    schemaId: 'a',
    content: {
      a: 'haha',
    },
  },
  {
    id: 'bbb',
    schemaId: 'a',
    content: {
      a: 'haha',
    },
  },
  {
    id: 'ccc',
    schemaId: 'a',
    content: {
      a: 'haha',
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
      id: String(Math.floor(Math.random() * 100000)).padStart(6, '0'),
      schemaId,
      content: body,
    })
    res.send('success')
  },
}
