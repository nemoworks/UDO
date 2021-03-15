function Card({ schema, children }) {
  return (
    <div className="Card container">
      <div className="title">{schema.title}</div>
      <div className="content">{children}</div>
    </div>
  )
}

export default Card
