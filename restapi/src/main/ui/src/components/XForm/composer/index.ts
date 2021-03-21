import { combine } from '../utils'

const parserMap = {
  object: ({ properties }, formData) =>
    Object.keys(formData || {}).forEach(key => {
      composer(properties[key], formData[key])
    }),
  array: (schema, formData = []) =>
    (schema.items = formData.map(data => {
      const auxiliary = {}
      composer(combine(schema.template, auxiliary), data)
      return auxiliary
    })),
  link: (schema, formData) => (schema['uid'] = formData),
  default: (schema, formData) => (schema['data'] = formData),
} as any

export default function composer(schema, formData) {
  const parser = parserMap[schema.type] || parserMap['default']
  parser(schema, formData)
  return schema
}
