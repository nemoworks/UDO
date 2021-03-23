import './index.sass'

interface Props {
  title?: any
  children: any
  className?: string
  options?: any
}

export default function Card({
  title = null,
  children,
  className = '',
  options = null,
}: Props) {
  return (
    <div className={'FixedCard ' + className}>
      {title && <div className="title">{title}</div>}
      {options && <div className="options">{options}</div>}
      <div className="body">{children}</div>
    </div>
  )
}
