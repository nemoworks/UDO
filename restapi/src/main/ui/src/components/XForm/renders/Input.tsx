import { aggregatedOperation } from '@perish/react-xform'
import HOC from './HOC'

function Input({ schema }) {
  return (
    <input
      className="Input"
      value={schema.data || ''}
      onChange={e =>
        aggregatedOperation(() => {
          schema.data = e.target.value
        })
      }
    />
  )
}

export default HOC(Input)
