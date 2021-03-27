import { useEffect, useState } from 'react'
import axios from 'axios'
import {
  aggregatedOperation as O,
  Factory,
  __render__,
} from '@perish/react-xform'
import { Icon } from '@/components'
import { transformer, composer } from '@/components/XForm'
import { DocumentQuery, SchemaQuery } from '@/utils'
import HOC from './HOC'

function UDOLink({ schema }) {
  const [options, setOptions] = useState([] as any)
  const [subSchema, setSubSchema] = useState({ [__render__]: [] } as any)
  const { schemaId, id } = schema

  useEffect(() => {
    DocumentQuery.getAll('?schemaId=' + schemaId).then(setOptions)
  }, [schemaId])

  useEffect(() => {
    id &&
      SchemaQuery.get(schemaId).then(({ content: initialSchema }) =>
        transformer(initialSchema, 2, true)
          .then(result =>
            composer(result, options.find(o => o.id === id).content),
          )
          .then(setSubSchema),
      )
  }, [id])

  return schema.id === undefined ? (
    <select
      className="MockLink selector"
      onChange={e =>
        O(() => {
          schema.id = e.target.value
        })
      }
    >
      <option>--------</option>
      {options.map((o: any) => (
        <option value={o.id} key={o.id}>
          {o.id}
        </option>
      ))}
    </select>
  ) : (
    <div className="UDOLink container">
      <Factory schema={subSchema} />
      <Icon
        type="iconclear"
        className="clear"
        onClick={() =>
          O(() => {
            schema.id = undefined
          })
        }
      />
    </div>
  )
}

export default HOC(UDOLink)
