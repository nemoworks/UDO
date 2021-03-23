import { __depth__ } from '../utils'

export default function Card({
  schema: {
    title: namely_title,
    [__depth__]: depth,
    'display-title': display_title,
  },
  children,
  index,
}) {
  return (
    <div className="Card container" data-index={index} data-depth={depth}>
      <div className="title">{display_title || namely_title}</div>
      <div className="content">{children}</div>
    </div>
  )
}
