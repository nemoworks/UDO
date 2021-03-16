import { __depth__ } from '../utils'

export default function Info({ schema, index }) {
  const { [__depth__]: depth } = schema
  return (
    <div className="Info" data-index={index} data-depth={depth}>
      {schema.data}
    </div>
  )
}
