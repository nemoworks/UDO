import { useEffect, useState } from 'react'
import {
  aggregatedOperation as O,
  Factory,
  __render__,
} from '@perish/react-xform'
import { Icon } from '@/components'
import { transformer, composer } from '@/components/XForm'
import { __depth__ } from '../utils'
import axios from 'axios'

export default function Link({ schema, index }) {
  const [options, setOptions] = useState([])
  const [subSchema, setSubSchema] = useState({ [__render__]: [] } as any)
  const { url, uid, [__depth__]: depth } = schema

  useEffect(() => {
    fetch(url)
      .then(res => res.json())
      .then(setOptions)
  }, [url])

  useEffect(() => {
    uid &&
      axios
        .get(url + '/' + uid)
        .then(({ data: { schema: schemaUrl, content } }) =>
          axios.get(schemaUrl).then(({ data: initialSchema }) =>
            transformer(initialSchema, depth, true)
              .then(s => composer(s, content))
              .then(setSubSchema),
          ),
        )
  }, [url, uid])

  return schema.uid === undefined ? (
    <select
      className="Link selector"
      data-index={index}
      data-depth={depth}
      onChange={e =>
        O(() => {
          schema.uid = e.target.value
        })
      }
    >
      <option>--------</option>
      {options.map((o: any) => (
        <option value={o.id} key={o.id}>
          {o.content.Name || o.id}
        </option>
      ))}
    </select>
  ) : (
    <div className="Link container" data-index={index} data-depth={depth}>
      <Factory schema={subSchema} />
      <Icon
        type="iconclear"
        className="clear"
        onClick={() =>
          O(() => {
            schema.uid = undefined
          })
        }
      />
    </div>
  )
}
