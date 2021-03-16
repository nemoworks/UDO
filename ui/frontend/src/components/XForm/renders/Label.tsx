import { __depth__ } from '../utils'

export default function Label({
  schema: { title, [__depth__]: depth },
  children,
  index,
}) {
  return (
    <div className="Label container" data-index={index} data-depth={depth}>
      <div className="title">{title}</div>
      <div className="content">{children}</div>
    </div>
  )
}
