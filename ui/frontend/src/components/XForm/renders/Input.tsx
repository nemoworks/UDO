import { aggregatedOperation } from '@perish/react-xform'
import { __depth__ } from '../utils'

function Input({ schema, index }) {
  const { [__depth__]: depth } = schema

  return (
    <input
      className="Info"
      data-index={index}
      data-depth={depth}
      value={schema.data || ''}
      onChange={e =>
        aggregatedOperation(() => {
          schema.data = e.target.value
        })
      }
    />
  )
}

export default Input
