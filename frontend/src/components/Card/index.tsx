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
  const Title = title && <div className="title">{title}</div>
  const Options = title && options && <div className="options">{options}</div>

  return (
    <div className={'card ' + className}>
      {Title}
      {Options}
      <div className="body">{children}</div>
    </div>
  )
}
