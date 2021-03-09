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
  object: schema => {
    const { properties } = schema
    Object.keys(properties).forEach(
      k => (properties[k] = transformer(properties[k])),
    )
    return schema
  },
  array: schema => {
    schema.template = transformer(schema.template)
    return schema
  },
  default: schema => schema,
}

function transformer(schema) {
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

// export default async function asyncTransformer() {
//   return {}
// }

export default transformer
