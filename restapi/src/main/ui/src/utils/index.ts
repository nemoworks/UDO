import axios from 'axios'

const SchemaQuery = {
  getAll: () => axios.get('/mock/schema').then(({ data }) => data),
  create: schema =>
    axios.post('/mock/schema', schema, {
      headers: {
        'Content-type': 'application/json; charset=utf-8',
      },
    }),
  get: id => axios.get('/mock/schema/' + id).then(({ data }) => data),
}

const DocumentQuery = {
  getAll: (search = '') =>
    axios.get('/mock/document' + search).then(({ data }) => data),
  create: (search = '', document) =>
    axios.post('/mock/document' + search, document, {
      headers: {
        'Content-type': 'application/json; charset=utf-8',
      },
    }),
  update: () => {},
  delete: () => {},
}

function documentQuery(query) {
  return axios.post('/api/documents/query', query, {
    headers: {
      'Content-Type': 'plain/text',
    },
  })
}

function json2query(target) {
  if (typeof target === 'string') return `"${target}"`
  if (typeof target === 'number') return target

  let query = ''
  Object.keys(target).forEach(key => {
    query += key + ':' + json2query(target[key]) + '\n'
  })

  return '{\n' + query + '}'
}

export { DocumentQuery, SchemaQuery }
