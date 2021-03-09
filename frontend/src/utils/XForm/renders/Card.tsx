import { Card as Antd_Card } from 'antd'

function Card({ schema, children }) {
  return (
    <Antd_Card
      className="xform-component__Card__container"
      size="small"
      title={schema.title || schema.label}
    >
      {children}
    </Antd_Card>
  )
}

export default Card
