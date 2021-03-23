import axios from 'axios'
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
  Link,
  List,
  UDOLink,
} from '../renders'
import { __depth__ } from '../utils'

const defaultRender = {
  object: () => [XObject],
  array: () => [XArray],
  string: readOnly => (readOnly ? [Info] : [Input]),
  number: readOnly => (readOnly ? [Info] : [Input]),
  link: () => [Link],
  info: () => [Info],
  none: () => [],
  Link: () => [UDOLink],
}

const parser = {
  object: async (schema, depth = 0, readOnly = false) => {
    const { properties } = schema
    for (const key in properties)
      properties[key] = await transformer(properties[key], depth + 1, readOnly)
    schema[__render__].push(Card)
    return schema
  },
  array: async (schema, depth = 0, readOnly = false) => {
    schema.template = await transformer(
      schema.template || {},
      depth + 1,
      readOnly,
    )
    !readOnly && schema[__render__].push(Options)
    schema[__render__].push(List)
    schema[__render__].push(Card)
    return schema
  },
  default: schema => {
    schema.title && schema[__render__].push(Label)
    return schema
  },
}

async function transformer(schema, depth = 0, readOnly = false) {
  // deal with $ref type
  if (schema['$ref']) {
    const { data } = await axios.get(schema['$ref'])
    const result = Object.assign(data, schema)
    delete result['$ref']
    return transformer(result, depth, readOnly)
  }

  // non-type object
  if (schema['type'] === undefined) return schema

  // default render of target type
  const type = schema['type']
  schema[__render__] = (defaultRender[type] || defaultRender['none'])(readOnly)

  // schema's depth
  schema[__depth__] = depth

  // schema's validator
  const rules = {} as any

  Object.keys(schema).forEach(key => {
    if (validatorRules[key] !== undefined) rules[key] = schema[key]
  })

  if (Object.keys(rules).length) {
    rules.type = Validator
    schema[__render__].push(rules)
  }

  // return
  return (parser[type] || parser['default'])(schema, depth, readOnly)
}

export default transformer
