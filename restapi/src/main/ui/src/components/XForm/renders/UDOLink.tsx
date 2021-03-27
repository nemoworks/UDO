import { useEffect, useState } from 'react'
import axios from 'axios'
import {
  aggregatedOperation as O,
  Factory,
  __render__,
} from '@perish/react-xform'
import { Icon } from '@/components'
// import { transformer, composer } from '@/components/XForm'
// import { documentQuery } from '@/utils'
import HOC from './HOC'

function UDOLink({ schema }) {
  const [options, setOptions] = useState([])
  const [subSchema, setSubSchema] = useState({ [__render__]: [] } as any)
  const { linkTo, udoi } = schema

  // useEffect(() => {
  //   documentQuery(`{
  //     air_purifierDocuments{
  //         udoi
  //     }
  //   }`).then(({ data }) => setOptions(data['air_purifierDocuments']))
  // }, [linkTo])

  // useEffect(() => {
  //   udoi &&
  //     axios
  //       .get('/api/schemas/udoiair_purifier')
  //       .then(({ data: { schemaContent: schema } }) => {
  //         documentQuery(`{
  //           air_purifier(
  //               udoi : "${udoi}"
  //           ){
  //               Name
  //               Brand
  //           }
  //         }`).then(({ data: { ['air_purifier']: formData } }) => {
  //           transformer(schema, 1, true)
  //             .then(s => composer(s, formData))
  //             .then(setSubSchema)
  //         })
  //       })
  // }, [linkTo, udoi])

  return schema.udoi === undefined ? (
    <select
      className="UDOLink selector"
      onChange={e =>
        O(() => {
          schema.udoi = e.target.value
        })
      }
    >
      <option>--------</option>
      {options.map((o: any) => (
        <option value={o.udoi} key={o.udoi}>
          {o.udoi}
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
            schema.udoi = undefined
          })
        }
      />
    </div>
  )
}

export default HOC(UDOLink)
