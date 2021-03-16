export default function Label({ schema, children, index }) {
  return (
    <div className={'Label container ' + index}>
      <div className="title">{schema.title}</div>
      <div className="content">{children}</div>
    </div>
  )
}
