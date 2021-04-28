import {__depth__} from '../utils'

export default function List({
                               children,
                               schema: {[__depth__]: depth},
                               index,
                             }) {
  return Array.isArray(children) ? (
    <div className="List container" data-index={index} data-depth={depth}>
      {children.map((line, index) => (
        <div key={index} className="item">
          {line}
        </div>
      ))}
    </div>
  ) : (
    children
  )
}
