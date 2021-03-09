import { __render__, wrapAsDependency as $ } from '@xform/react'
import { Info, Input, Card, XObject, XArray } from './renders'

const defaultRender = {
  object: () => [XObject],
  array: () => [XArray],
  string: () => [Input],
  number: () => [Input],
  info: () => [Info],
}

const containerMap = {
  Card: Card,
}

const parser = {
  object: async schema => {
    const { properties } = schema
    let result = {}
    await Promise.all(
      Object.keys(properties).map(
        async key => (result[key] = await transformer(properties[key])),
      ),
    )
    schema.properties = result
    return schema
  },
  array: async schema => {
    schema.template = await transformer(schema.template)
    return schema
  },
  default: schema => schema,
}

async function transformer(schema) {
  if (schema['$ref']) {
    const response = await fetch(schema['$ref'])
    const json = await response.json()
    return transformer(json)
  }

  if (schema['type'] === undefined) return schema

  const type = schema['type']
  schema[__render__] = defaultRender[type]()
  if (schema['containers'])
    schema['containers'].forEach((container: any) => {
      if (typeof container === 'string')
        schema[__render__].push(containerMap[container])
      else schema[__render__].push(container)
    })
  return (parser[type] || parser['default'])(schema)
}

export default transformer
