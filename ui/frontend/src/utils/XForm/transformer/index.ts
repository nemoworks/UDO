import { __render__ } from '@perish/react-xform'
import validatorRules from '../renders/Validator/parser'
import {
  Info,
  Input,
  Card,
  XObject,
  XArray,
  Validator,
  Label,
  Options,
  Frame,
  Link,
} from '../renders'

const defaultRender = {
  object: () => [XObject],
  array: () => [XArray],
  string: () => [Input],
  number: () => [Input],
  link: () => [Link],
  info: () => [Info],
  none: () => [],
}

const parser = {
  object: async (schema, depth = 0) => {
    const { properties } = schema
    for (const key in properties)
      properties[key] = await transformer(properties[key], depth + 1)

    depth === 0 && schema[__render__].push(Card)

    return schema
  },
  array: async (schema, depth = 0) => {
    schema.template = await transformer(schema.template || {}, depth + 1)
    schema[__render__].push(Options)

    depth === 1 && schema[__render__].push(Frame)

    return schema
  },
  default: (schema, depth = 0) => {
    depth === 1 && schema[__render__].push(Label)

    return schema
  },
}

async function transformer(schema, depth = 0) {
  if (schema['$ref']) {
    const response = await fetch(schema['$ref'])
    const json = await response.json()
    const result = Object.assign(json, schema)
    delete result['$ref']
    return transformer(result, depth)
  }

  if (schema['type'] === undefined) return schema

  const type = schema['type']
  schema[__render__] = (defaultRender[type] || defaultRender['none'])()

  const rules = {} as any

  Object.keys(schema).forEach(key => {
    if (validatorRules[key] !== undefined) rules[key] = schema[key]
  })

  if (Object.keys(rules).length) {
    rules.type = Validator
    schema[__render__].push(rules)
  }

  return (parser[type] || parser['default'])(schema, depth)
}

export default transformer
