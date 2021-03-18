import { __depth__ } from '../utils'
import HOC from './HOC'

function Label({ schema: { title }, children }) {
  return (
    <div className="Label container">
      <div className="title">{title}</div>
      <div className="content">{children}</div>
    </div>
  )
}

export default HOC(Label)
