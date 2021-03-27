import axios from 'axios'
import room_schema from './room.json'
import air_purifier_schema from './air_purifier.json'

const schema = {
  getAll: () => axios.get('/api/schemas').then(({ data }) => data),
  create: source =>
    axios.post('/api/schemas', source, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
      },
    }),
}

const document = {
  getAll: () => {},
  create: () => {},
  update: () => {},
  delete: () => {},
}

function initializeSchema() {
  return axios.all([
    axios.post('/api/schemas', room_schema, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
      },
    }),
    axios.post('/api/schemas', air_purifier_schema, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
      },
    }),
  ])
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

export { initializeSchema, documentQuery, json2query }
