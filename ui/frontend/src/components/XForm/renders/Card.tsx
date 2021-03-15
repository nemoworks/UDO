function Card({ schema, children, index }) {
  return (
    <div className={'Card container ' + index}>
      <div className="title">{schema.title}</div>
      <div className="content">{children}</div>
    </div>
  )
}

export default Card
